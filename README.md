# 🎄 Advent of Code 2025 — Java Solutions

Each December, Advent of Code releases a series of programming challenges designed to sharpen problem-solving skills and stretch your thinking. This year introduces a 12-day format, and I’m using it as a focused opportunity to strengthen my Java fundamentals.

December is always a busy month, but taking a quiet moment to work through a puzzle — often with a hot chocolate nearby and a welcome break from everything else going on — is a good way to slow down, reset, and learn something new. This repository documents that process: a structured, disciplined attempt to work through Advent of Code while becoming genuinely comfortable writing Java.

---

## 🎯 Purpose of This Repository

This project serves two goals:

1. **Solve Advent of Code 2025 challenges in Java**
2. **Use the event as a practical learning environment to improve my Java skills**

By the end of the challenge, the aim is to demonstrate:

- A deeper understanding of Java’s syntax and core APIs
- Familiarity with Maven and conventional Java project structure
- Confidence writing reusable classes and organised code
- Clear, readable solutions supported by unit tests
- Good engineering habits that employers expect in production-grade code

This is intended to be both a record of progress and a reusable learning reference for future Java work.

---

## 🏗️ Project Structure

The repository follows a consistent, scalable layout:

```
src/
  main/
    java/
      com.timrayner.aoc2025/
        DayTemplate.java     ← shared abstract base class
        day01/Day01.java     ← daily solutions
        day02/Day02.java
        ...
    resources/
      inputs/                ← actual puzzle inputs (one per day)
      sample/                ← sample inputs from AoC descriptions
  test/
    java/
      com.timrayner.aoc2025/
        day01/Day01Test.java
        day02/Day02Test.java
        ...
```

### Daily Solver Classes

Each day has a class named `DayXX` (e.g., `Day01`, `Day02`), and each implements:

```java
public abstract String solution() throws Exception;
```

Most AoC puzzles are split into Part 1 and Part 2, but if 2025 keeps the single-answer format from last year, this method becomes even clearer to work with.

---

## 📦 Inputs vs Sample Inputs

### `inputs/`

Stores the **real personalised puzzle input** for each day.  
These are the files AoC gives you once you’re logged in.

### `sample/`

Stores the **example inputs provided in the problem descriptions**.  
These are normally shorter, simpler datasets intended to:

- Help you understand the puzzle
- Verify your logic before running against the full input
- Support test cases

The samples are valuable because they let you write assertions based on the expected output shown directly in the AoC explanation.

---

## 🧪 Tests

Each day includes a minimal JUnit test file to ensure:

- The solution method runs
- The class loads correctly
- Logic can be validated using sample inputs

As the challenge progresses, these tests can be expanded with concrete expected outputs for more confidence.

---

## 🚀 Running a Solution

Via the CLI runner:

```bash
mvn -q exec:java \\
  -Dexec.mainClass=com.timrayner.aoc2025.App \\
  -Dexec.args="01"
```

This executes `Day01` and prints the final answer.

---

## 🎁 A December Routine of Improvement

Advent of Code offers a rare combination of structured challenge, daily progression, and clear problem boundaries. That makes it an excellent format for levelling up in a language — in this case, Java. By keeping each solution self-contained, readable, and tested, this repository acts as both a December tradition and a long-term learning resource.

Whether the puzzles are simple or unexpectedly deep, the goal remains the same:  
**write better Java tomorrow than I did yesterday.**
