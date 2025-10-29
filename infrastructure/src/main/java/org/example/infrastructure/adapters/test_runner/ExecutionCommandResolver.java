package org.example.infrastructure.adapters.test_runner;

class ExecutionCommandResolver {
    public static String[] resolve(String language) {
        return switch (language.toLowerCase()) {
            case "python" -> new String[]{"python3", "/app/Program.py"};
            case "java" -> new String[] {"bash", "-c",
                    "javac -cp /usr/local/dep/myapp.jar /app/*.java && " +
                            "java -cp /app:/usr/local/dep/myapp.jar Program"};

            case "javascript" -> new String[]{"node", "/app/Program.js"};
            case "c++" -> new String[]{"g++", "/app/Program.cpp", "-o", "Program", "&&", "./Program"};
            default -> throw new IllegalArgumentException("Unsupported language: " + language);
        };
    }
}
