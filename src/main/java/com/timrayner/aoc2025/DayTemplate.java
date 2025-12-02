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
            // Try inputs/ first, then sample/
            String inputsPath = "inputs/" + resourcePath;
            String samplePath = "sample/" + resourcePath;
            
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(inputsPath);
            if (inputStream == null) {
                inputStream = getClass().getClassLoader().getResourceAsStream(samplePath);
                if (inputStream == null) {
                    throw new IOException("Resource not found in inputs/ or sample/: " + resourcePath);
                }
            }
            
            try (InputStream stream = inputStream) {
                try (Stream<String> lines = new java.io.BufferedReader(
                        new java.io.InputStreamReader(stream, StandardCharsets.UTF_8))
                        .lines()) {
                    return lines.collect(Collectors.toList());
                }
            }
        } else {
            // Path already contains directory, use as-is
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
    }

    public abstract String challenge1() throws Exception;
    public abstract String challenge2() throws Exception;
}