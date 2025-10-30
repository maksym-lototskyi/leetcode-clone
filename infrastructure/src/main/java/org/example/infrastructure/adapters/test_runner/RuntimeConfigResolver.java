package org.example.infrastructure.adapters.test_runner;

import lombok.extern.slf4j.Slf4j;
import org.example.infrastructure.exception.UnknownRuntimeException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RuntimeConfigResolver {
    private final RuntimeProperties runtimeProperties;

    public RuntimeConfigResolver(RuntimeProperties runtimeProperties) {
        this.runtimeProperties = runtimeProperties;
        runtimeProperties.getConfigs()
                .forEach((lang, config) -> log.info("Loaded runtime config for language {}: image={}, command={}",
                        lang, config.getImage(), config.getCommand()));
    }

    public List<String> getCommandFor(String language) {
        RuntimeProperties.RuntimeConfig config = runtimeProperties.getConfigs().get(language);
        if (config == null) {
            throw new UnknownRuntimeException("Unknown runtime: " + language);
        }
        return config.getCommand();
    }

    public String getImageFor(String language) {
        return runtimeProperties.getConfigs().get(language).getImage();
    }
}
