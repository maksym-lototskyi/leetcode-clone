package org.example.infrastructure.adapters.test_runner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedStreamReader {
    public static String read(InputStream inputStream) throws IOException {
        StringBuilder builder = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while((line = reader.readLine()) != null){
                builder.append(line).append("\n");
            }
        }
        return builder.toString();
    }
}
