package com.timrayner.aoc2025;

import java.lang.reflect.Constructor;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: mvn -q exec:java -Dexec.mainClass=com.timrayner.aoc2025.App -Dexec.args=\"01\"");
            return;
        }

        String day = args[0];
        String className = "com.timrayner.aoc2025.day" + day + ".Day" + day;

        Class<?> clazz = Class.forName(className);
        Constructor<?> ctor = clazz.getDeclaredConstructor();

        DayTemplate solver = (DayTemplate) ctor.newInstance();

        String answer1 = solver.challenge1();
        String answer2 = solver.challenge2();
        System.out.println("Day " + day + " Challenge 1: " + answer1);
        System.out.println("Day " + day + " Challenge 2: " + answer2);
    }
}