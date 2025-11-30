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

        String answer = solver.solution();
        System.out.println("Day " + day + " Solution: " + answer);
    }
}