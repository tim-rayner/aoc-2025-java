package com.timrayner.aoc2025.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.timrayner.aoc2025.DayTemplate;

public class Day03 extends DayTemplate {

    /**
     * ⛔ Learning the hard way ⛔
     * Today I got so carried away trying to solve problem 1 in a extendible way that i ended up confusing myself and not even fully grasping task 1. this led to hours lost and lots of 
     * frustration... annoying, but its my first AoC and i'm aware some mistakes will be made first time around. Instead of solving part 2 with ai; i've left it as i'm currently flagging at 23:59. 
     */

    @Override
    public String challenge1() throws Exception {

        ArrayList<Integer> largestNums = new ArrayList<>();

        try {

            File input = new File("src/main/resources/inputs/day03.txt");
            try (Scanner johnnyFive = new Scanner(input)) {  // nobody scans chunks of text like J5 🤖

                while(johnnyFive.hasNextLine()){

                    String line = johnnyFive.nextLine().trim();
                    List<Integer> bank = getBank(line);

                    int largestNum = getLargest(bank);

                    int largestIndex = bank.indexOf(largestNum);

                    List<Integer> newArr;


                if(largestIndex >= bank.size() - 1){
                    //split list before index
                    newArr = bank.subList(0, bank.size() - 1);
                    
                }
                else {
                    //split list after index
                    newArr = bank.subList(largestIndex + 1, bank.size());
                }
       
                //get largest number in array

                int secondLargest = getLargest(newArr);
                String resultNumber; 

                if(largestNum == bank.get(bank.size() - 1)) {
                    resultNumber = Integer.toString(secondLargest) + Integer.toString(largestNum);
                }
                else {
                    resultNumber = Integer.toString(largestNum) + Integer.toString(secondLargest);
                }
                //add number to total
                largestNums.add(Integer.parseInt(resultNumber));

                }
            }
            
            int finalNumber = 0;

            for(int i = 0; i < largestNums.size(); i++){
                System.out.println(largestNums.get(i));
                finalNumber += largestNums.get(i);
            }

            return Integer.toString(finalNumber);
        }
        catch (FileNotFoundException e) {
            System.out.println("Error! File not found!");
        }

        
        return "A Reindeer seems to be poorly :(";
    }

    @Override
    public String challenge2() throws Exception {
        return "NOT IMPLEMENTED - Straight on the naughty list ☹️🎅🏼";
    } 

    private static List<Integer> getBank(String input){
        // convert string to integer array
        List<Integer> bank = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            bank.add((input.charAt(i) - '0'));
        }
        
        return bank;
    }


    private static int getLargest(List<Integer> bank){

        int max = bank.get(0); 

        for(int i = 0; i < bank.size(); i++){

            if(bank.get(i) > max) {
                max = bank.get(i);
            }
            
        }

        return max; 

    } 
        




    
}
