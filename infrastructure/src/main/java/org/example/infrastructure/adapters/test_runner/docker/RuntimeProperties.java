package org.example.infrastructure.adapters.test_runner.docker;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "runtimes")
public class RuntimeProperties {

    private Map<String, RuntimeConfig> configs;

    public Map<String, RuntimeConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, RuntimeConfig> configs) {
        this.configs = configs;
    }

    public static class RuntimeConfig {
        private String image;
        private List<String> compileCommand;
        private List<String> runCommand;

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }

        public List<String> getCompileCommand() { return compileCommand; }
        public void setCompileCommand(List<String> compileCommand) { this.compileCommand = compileCommand; }

        public List<String> getRunCommand() {
            return runCommand;
        }

        public void setRunCommand(List<String> runCommand) {
            this.runCommand = runCommand;
        }
    }
}

