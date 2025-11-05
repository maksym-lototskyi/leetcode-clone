package org.example.infrastructure.adapters.test_runner.docker;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class ContainerExecutioner {

    static ContainerExecutionResult compileSourceCode(Path tmpDir, String runtimeImage, List<String> compileCommands) throws IOException, InterruptedException {
        return runContainer(tmpDir, runtimeImage, compileCommands);
    }

    static ContainerExecutionResult runSourceCode(Path tmpDir, String runtimeImage, List<String> runCommands) throws IOException, InterruptedException {
        return runContainer(tmpDir, runtimeImage, runCommands);
    }

    private static ContainerExecutionResult runContainer(Path tmpDir, String runtimeImage, List<String> containerCommands) throws IOException, InterruptedException {
        String[] dockerStartCommands = {
                "docker", "run", "--rm",
                "-v", tmpDir + ":/app",
                runtimeImage};

        List<String> commands = new ArrayList<>(List.of(dockerStartCommands));
        commands.addAll(containerCommands);

        Process process = new ProcessBuilder(commands).start();

        String errorOutput = BufferedStreamReader.read(process.getErrorStream());
        String output = BufferedStreamReader.read(process.getInputStream());
        int exitCode = process.waitFor();

        return new ContainerExecutionResult(exitCode, output, errorOutput);
    }
}
