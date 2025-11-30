package com.timrayner.aoc2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class DayTemplate {

    protected List<String> readInput(String fileName) throws IOException {
        return Files.readAllLines(Path.of("src/main/resources/inputs/" + fileName));
    }

    public abstract String solution() throws Exception;
}