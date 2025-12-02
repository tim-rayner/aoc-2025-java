package com.timrayner.aoc2025.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.timrayner.aoc2025.DayTemplate;

public class Day01 extends DayTemplate {
    static int sumOf0 = 0;
    static int zeroCount = 0;
    
    public record Instruction(char direction, int amount) {}

    @Override
    public String challenge1() throws Exception {
        List<String> directions = readInput("/inputs/day01.txt");

        int counter = 0; 
        int currentPosition = 50;
    

        for(int i = 0; i < directions.size(); i++){

            String instruction = directions.get(i);

            Instruction decodedInstruction = getInstruction(instruction);

            currentPosition = rotate(decodedInstruction, currentPosition);

            if(currentPosition == 0){
                counter++;
            } 

        }
   
        return Integer.toString(counter);
    }

    /**
     * @ENHANCE: challenge 2 actually solves both challenges, using a slightly different approach... however I've kept my first answer above to document my learning process/progress
     */
    @Override
    public String challenge2() throws Exception {

        try {
            File input = new File("src/main/resources/inputs/day01.txt");        
            Scanner scanner = new Scanner(input);
            int dial = 50;
    
            System.out.println("The dial starts by pointing at " + dial);
    
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                char direction = line.charAt(0);
                int num = reduce(Integer.parseInt(line.substring(1)));

                if (direction == 'R') {
                    dial = rotateRight(dial, num);
                } else {
                    dial = rotateLeft(dial, num);
                }
               
                if (dial == 0) {
                    zeroCount++;
                }
            }    
            System.out.println(zeroCount);
            System.out.println(sumOf0);

            scanner.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error! File not found!");
        }
       

        return Integer.toString(sumOf0);
     }


    // Private Methods

    private int rotate(Instruction instruction, int currentPosition){
        int newVal;
        
        if (instruction.direction == 'R') {
            newVal = currentPosition + instruction.amount;
        } else {
            newVal = currentPosition - instruction.amount;
        }
        
        // Use modulo to wrap around the 0-99 dial
        newVal = ((newVal % 100) + 100) % 100;
        
        return newVal;
    }

    private Instruction getInstruction(String direction){
        Matcher m = Pattern.compile("([LR])(\\d+)").matcher(direction);
        if (!m.matches()){
            throw new IllegalArgumentException("Invalid instruction format: " + direction);
        }
  
        char dir = m.group(1).charAt(0);  
        int amount = Integer.parseInt(m.group(2));  

        return new Instruction(dir, amount);
    }

    private static int rotateRight(int currentPosition, int rotation) {
        int next = currentPosition + rotation;
        if (next > 99) {
            sumOf0++;
            return next - 100;
        }
        return next;
    }

    private static int rotateLeft(int currentPosition, int rotation) {

        int next = currentPosition - rotation;
        if (next < 0) {
            if (currentPosition != 0) {
                sumOf0++;
            }
            return 100 + next;
        }
        if (next == 0) {
            sumOf0++;
        }
        return next;

    }

    private int reduce(int amount) {
        int hundreds = amount / 100;
        sumOf0 += hundreds;
        return amount - 100 * hundreds;
    }
}


