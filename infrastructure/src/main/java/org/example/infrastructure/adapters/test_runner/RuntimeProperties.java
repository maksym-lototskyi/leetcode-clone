package org.example.infrastructure.adapters.test_runner;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
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
        private List<String> command;

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }

        public List<String> getCommand() { return command; }
        public void setCommand(List<String> command) { this.command = command; }
    }
}

