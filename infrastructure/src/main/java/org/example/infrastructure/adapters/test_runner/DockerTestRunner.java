package org.example.infrastructure.adapters.test_runner;
import lombok.extern.slf4j.Slf4j;
import org.example.application.task.use_cases.run.*;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

@Component
@Slf4j
class DockerTestRunner implements TestRunner {

    private static final String INPUT_FILE = "input.json";
    private static final String SIGNATURE_FILE = "signature.json";
    private final RuntimeConfigResolver runtimeConfigResolver;
    private final ExecutionOutputParser outputParser;

    DockerTestRunner(RuntimeConfigResolver runtimeConfigResolver, ExecutionOutputParser outputParser) {
        this.runtimeConfigResolver = runtimeConfigResolver;
        this.outputParser = outputParser;
    }

    @Override
    public TestRunResult run(LanguageDto language, ExecutionContext executionContext) throws IOException {
        Path tmpDir = createTempDir();
        String image = runtimeConfigResolver.getImageFor(language.name());

        try {
            prepareExecutionFiles(tmpDir, language, executionContext);
            addCustomClassDefinitions(executionContext.additionalClasses(), tmpDir, language.fileExtension());

            if (hasErrors(compileUserCode(language, tmpDir, image))) {
                return TestRunResult.compilationError(compileUserCode(language, tmpDir, image).stderr());
            }

            ContainerExecutionResult runResult = runUserCode(language, tmpDir, image);
            ParsedOutput parsedOutput = outputParser.parse(runResult.stdout());

            if (hasErrors(runResult)) {
                return TestRunResult.runtimeError(runResult.stderr(), parsedOutput.stdout(), 0, 0);
            }
            return TestRunResult.success(parsedOutput.result(), parsedOutput.stdout(), parsedOutput.executionTimeMs(), 0);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Code execution was interrupted.", e);
            throw new ProcessInterruptedException("Code execution was interrupted.", e);
        } finally {
            cleanUp(tmpDir);
        }
    }

    private Path createTempDir() throws IOException {
        Path tmpDir = Paths.get(System.getProperty("user.dir"), "tmp", UUID.randomUUID().toString());
        Files.createDirectories(tmpDir);
        return tmpDir;
    }

    private void prepareExecutionFiles(Path tmpDir, LanguageDto language, ExecutionContext ctx)
            throws IOException, InterruptedException {
        Files.writeString(tmpDir.resolve(INPUT_FILE), ctx.input());
        Files.writeString(tmpDir.resolve(SIGNATURE_FILE), ctx.signature());

        Path template = copyTemplateToTmp(tmpDir, language);
        String code = Files.readString(template).formatted(ctx.userCode());
        Files.writeString(tmpDir.resolve("Program" + language.fileExtension()), code);
    }

    private ContainerExecutionResult compileUserCode(LanguageDto language, Path dir, String image)
            throws IOException, InterruptedException {
        return ContainerExecutioner.compileUserCode(
                dir, image, runtimeConfigResolver.getCompileCommandFor(language.name()));
    }

    private ContainerExecutionResult runUserCode(LanguageDto language, Path dir, String image)
            throws IOException, InterruptedException {
        return ContainerExecutioner.runUserCode(
                dir, image, runtimeConfigResolver.getRunCommandFor(language.name()));
    }

    private boolean hasErrors(ContainerExecutionResult result) {
        return result.stderr() != null && !result.stderr().isBlank();
    }

    private void addCustomClassDefinitions(List<AdditionalClassDto> additionalClasses, Path path, String ext)
            throws IOException {
        for (AdditionalClassDto c : additionalClasses) {
            Files.writeString(path.resolve(c.className() + ext), c.sourceCode());
        }
    }

    private Path copyTemplateToTmp(Path tmpDir, LanguageDto language) throws IOException, InterruptedException {
        String templateFileName = language.name() + "_template";
        Process process = new ProcessBuilder(
                "docker", "run", "--rm",
                "-v", tmpDir + ":/app/tmp",
                runtimeConfigResolver.getImageFor(language.name()),
                "bash", "-c", "cp /app/templates/" + templateFileName + " /app/tmp"
        ).redirectErrorStream(true).start();

        try (var input = process.getInputStream()) {
            BufferedStreamReader.read(input);
        }

        if (process.waitFor() != 0) {
            throw new IOException("Failed to copy template for language: " + language.name());
        }

        return tmpDir.resolve(templateFileName);
    }

    private void cleanUp(Path dir) {
        try (Stream<Path> paths = Files.walk(dir)) {
            paths.sorted(Comparator.reverseOrder()).forEach(f -> {
                try {
                    Files.deleteIfExists(f);
                } catch (IOException e) {
                    log.error("Failed to delete temporary file: " + f, e);
                }
            });
        } catch (IOException ignored) {
        }
    }
}
