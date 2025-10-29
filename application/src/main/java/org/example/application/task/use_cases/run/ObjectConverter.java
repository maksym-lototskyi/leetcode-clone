package org.example.application.task.use_cases.run;

public interface ObjectConverter {
    <S> String convert(S source);
}
