package org.example.infrastructure.adapters.test_runner.docker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RuntimeConfigResolver {
    private final RuntimeProperties runtimeProperties;

    public RuntimeConfigResolver(RuntimeProperties runtimeProperties) {
        this.runtimeProperties = runtimeProperties;
        runtimeProperties.getConfigs()
                .forEach((lang, config) -> log.info("Loaded runtime config for language {}: image={}, compile-command={}, run-command={}",
                        lang, config.getImage(), config.getCompileCommand(), config.getRunCommand()));
    }

    public List<String> getCompileCommandFor(String language){
        return getCommandFor(language, true);
    }
    public List<String> getRunCommandFor(String language){
        return getCommandFor(language, false);
    }

    private List<String> getCommandFor(String language, boolean isCompile) {
        RuntimeProperties.RuntimeConfig config = getConfigFor(language);
        if(isCompile) return config.getCompileCommand();
        return config.getRunCommand();
    }

    public String getImageFor(String language) {
        return runtimeProperties.getConfigs().get(language).getImage();
    }

    public boolean hasCompileCommandFor(String language) {
        RuntimeProperties.RuntimeConfig config = getConfigFor(language);
        return config.getCompileCommand() != null && !config.getCompileCommand().isEmpty();
    }
    private RuntimeProperties.RuntimeConfig getConfigFor(String language) {
        RuntimeProperties.RuntimeConfig config = runtimeProperties.getConfigs().get(language);
        if (config == null) {
            throw new UnknownRuntimeException("Unknown runtime: " + language);
        }
        return config;
    }
}
