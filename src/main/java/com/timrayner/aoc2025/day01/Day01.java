package com.timrayner.aoc2025.day01;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.timrayner.aoc2025.DayTemplate;

public class Day01 extends DayTemplate {

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

    @Override
    public String challenge2() throws Exception {
        List<String> directions = readInput("/sample/day01Sample.txt");

        int counter = 0;  
        int cycles = 0;
        int currentPosition = 50;


        for(int i = 0; i < directions.size(); i++){

            String instruction = directions.get(i);

            Instruction decodedInstruction = getInstruction(instruction);
            cycles += getCyclesPastZero(decodedInstruction, currentPosition);
            
            currentPosition = rotate(decodedInstruction, currentPosition);

            if(currentPosition == 0){
                counter++;
            } 

        }
   
        return Integer.toString(cycles + counter);    }


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

    private int getCyclesPastZero(Instruction instruction, int currentPosition){

        int instructionAmount = instruction.amount; 
        int max = 100; 
        int min = 0; 
        int zcount = 0;


        if(instruction.direction == 'R'){
            if (currentPosition != 0 && (currentPosition + instructionAmount) > max) {
           
                zcount = zcount + 1;
                int diff = (currentPosition + instructionAmount) - max;
                boolean multipleLoops = diff >= max;
                if(multipleLoops){
                    zcount = zcount + ((diff / max) / 100);
                }
                return zcount;
            }
        }
        else {
            if((currentPosition != 0 && (currentPosition - instructionAmount) < min)){
                zcount = zcount + 1;
                int diff = min - (currentPosition - instructionAmount);
                boolean multipleLoops = diff >= max;
                if(multipleLoops){
                    zcount = zcount + ((diff / max) / 100);
                }
                return zcount;
            }
        }

        return zcount;

    }


}


