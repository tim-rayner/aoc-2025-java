package com.timrayner.aoc2025.day02;

import java.util.stream.LongStream;

import com.timrayner.aoc2025.DayTemplate;

public class Day02 extends DayTemplate {

    private static final String INPUT = "day02.txt";
    private static long idAggr = 0;

    @Override
    public String challenge1() throws Exception {
        String raw = readInput(INPUT).get(0);
        String[] ranges = raw.split(",");

        for(int i = 0; i < ranges.length; i++){
            String currentRange = ranges[i];
            String[] parts = currentRange.split("-");


            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            long[] range = LongStream.rangeClosed(start, end).toArray();
            
            for(long item: range){
                reduce(item);
            }
        }

        return Long.toString(idAggr);
    }

    @Override
    public String challenge2() throws Exception {
        var lines = readInput(INPUT);
        return "NOT IMPLEMENTED 🎅🏼";
    }



    private static void reduce(long id){
        // Split each number into array of chars
        char[] chars = String.valueOf(id).toCharArray();

        if (!isOdd(chars.length)) {
            // is even
            int mid = chars.length / 2;

            String first = new String(chars, 0, mid);
            String second = new String(chars, mid, mid);

            if (first.equals(second)) idAggr += id;
        }
        
    }

    private static boolean isOdd(long num){
        return num % 2 != 0;
    }


}
