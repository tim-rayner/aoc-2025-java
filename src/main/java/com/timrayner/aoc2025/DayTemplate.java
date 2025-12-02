package com.timrayner.aoc2025;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class DayTemplate {

    protected List<String> readInput(String path) throws IOException {
        String resourcePath = path.startsWith("/") ? path.substring(1) : path;
        
        if (!resourcePath.contains("/")) {
            resourcePath = "inputs/" + resourcePath;
        }
        
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            
            try (Stream<String> lines = new java.io.BufferedReader(
                    new java.io.InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()) {
                return lines.collect(Collectors.toList());
            }
        }
    }

    public abstract String challenge1() throws Exception;
    public abstract String challenge2() throws Exception;
}