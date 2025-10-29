package org.example.infrastructure.adapters.test_runner;

import org.example.application.task.use_cases.run.*;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Component
class DockerTestRunner implements TestRunner {
    @Override
    public TestRunResult run(LanguageDto language, ExecutionContext executionContext) throws IOException {
        String currentDir = System.getProperty("user.dir");
        Path tmpDir = Paths.get(currentDir, "tmp", UUID.randomUUID().toString());
        Path codeFilePath = Paths.get(tmpDir.toString(), "Program" + language.fileExtension());
        Path inputFile = tmpDir.resolve("input.json");
        Path signatureFile = tmpDir.resolve("signature.json");

        try {
            Files.createDirectories(tmpDir);
            Files.createFile(codeFilePath);

            Files.writeString(inputFile, executionContext.input());
            Files.writeString(signatureFile, executionContext.signature());

            Path templatePath = copyTemplateToTmp(tmpDir, language);

            String code = Files.readString(templatePath).formatted(executionContext.userCode());
            Files.writeString(codeFilePath, code);

            addCustomClassDefinitions(executionContext.customClassDefinitions(), tmpDir, language.fileExtension());

            Process process = runUserCodeInContainer(tmpDir, language.runtimeImage(), ExecutionCommandResolver.resolve(language.name()));

            String errorOutput = read(process.getErrorStream());
            String output = read(process.getInputStream());

            if(!errorOutput.isEmpty()){
                ErrorType errorType = ErrorType.fromErrorOutput(errorOutput);
                if(errorType == ErrorType.RUNTIME_ERROR){
                    return TestRunResult.runtimeError(errorOutput, 0, 0);
                }
                return TestRunResult.compilationError(errorOutput);
            }

            String[] outputLines = output.split("\n");

            long executionTime = Long.parseLong(outputLines[0]);
            String outputResult = outputLines[1];
            return TestRunResult.success(outputResult, executionTime, 0);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            cleanUp(tmpDir);
        }
    }

    private static void addCustomClassDefinitions(Map<String, String> classDefinitions, Path path, String fileExtension) throws IOException {
        for(Map.Entry<String, String> entry : classDefinitions.entrySet()) {
            Path classDefPath = Paths.get(path.toString(), entry.getKey() + fileExtension);
            Files.writeString(classDefPath, entry.getValue());
        }
    }

    private static String read(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
        }
        return builder.toString();
    }

    private static Path copyTemplateToTmp(Path tmpDir, LanguageDto language) throws IOException, InterruptedException {
        String templateFileName = language.name() + "_template";
        Process processInit = new ProcessBuilder(
                "docker", "run", "--rm",
                "-v", tmpDir + ":/app/tmp",
                language.runtimeImage(),
                "bash", "-c", "cp /app/templates/" + templateFileName + " /app/tmp"
        )
                .redirectErrorStream(true)
                .start();

        processInit.waitFor();

        read(processInit.getInputStream());
        return Path.of(tmpDir.toString(), templateFileName);
    }

    private static Process runUserCodeInContainer(Path tmpDir, String runtimeImage, String[] execCommands) throws IOException {
        String[] dockerStartCommands = {
                "docker", "run", "--rm",
                "-v", tmpDir + ":/app",
                runtimeImage};

        List<String> commands = new ArrayList<>(List.of(dockerStartCommands));
        commands.addAll(Arrays.asList(execCommands));

        return new ProcessBuilder(commands).start();
    }

    private static void cleanUp(Path dir) throws IOException {
        try(Stream<Path> paths = Files.walk(dir)){
            paths
                    .sorted(Comparator.reverseOrder())
                    .forEach(f -> {
                        try {
                            Files.delete(f);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}
