package com.timrayner.aoc2025.day01;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Day 01")
class Day01Test {

    private final Day01 day = new Day01();

    @Test
    @DisplayName("Challenge 1 ")
    void testChallenge1() throws Exception {
        var result = day.challenge1();
        assertNotNull(result);
        assertEquals("1029", result);
    }

    @Test
    @DisplayName("Challenge 2 ")
    void testChallenge2() throws Exception {
        var result = day.challenge1();
        assertNotNull(result);
        assertEquals("6", result);
    }

}
