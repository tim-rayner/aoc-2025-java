package com.timrayner.aoc2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class DayTemplate {

    protected List<String> readInput(String fileName) throws IOException {
        String basePath;
        if (fileName.startsWith("/sample/")) {
            basePath = "src/main/resources/sample/";
            fileName = fileName.substring("/sample/".length());
        } else if (fileName.startsWith("/inputs/")) {
            basePath = "src/main/resources/inputs/";
            fileName = fileName.substring("/inputs/".length());
        } else {
            basePath = "src/main/resources/inputs/";
        }
        return Files.readAllLines(Path.of(basePath + fileName));
    }

    public abstract String challenge1() throws Exception;
    public abstract String challenge2() throws Exception;
}