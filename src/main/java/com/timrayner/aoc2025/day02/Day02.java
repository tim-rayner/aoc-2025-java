package com.timrayner.aoc2025.day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

import com.timrayner.aoc2025.DayTemplate;

public class Day02 extends DayTemplate {

    private static final String INPUT = "day02.txt";
    private static long idAggr = 0;

    @Override
    public String challenge1() throws Exception {
        idAggr = 0; //RESET
        String raw = readInput(INPUT).get(0);
        String[] ranges = raw.split(",");

        for(int i = 0; i < ranges.length; i++){
            String currentRange = ranges[i];
            String[] parts = currentRange.split("-");


            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            long[] range = LongStream.rangeClosed(start, end).toArray();
            
            for(long item: range){
                reduce(item, false);
            }
        }

        return Long.toString(idAggr);
    }

    @Override
    public String challenge2() throws Exception {
        idAggr = 0;  //RESET
        String raw = readInput(INPUT).get(0);
        String[] ranges = raw.split(",");

        for(int i = 0; i < ranges.length; i++){
            String currentRange = ranges[i];
            String[] parts = currentRange.split("-");


            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            long[] range = LongStream.rangeClosed(start, end).toArray();
            
            for(long item: range){
                reduce(item, true);
            }
        }

        return Long.toString(idAggr);
    }



    private static void reduce(long id, boolean findSequences){
        if (findSequences) findSequences(id);
        // Split each number into array of chars
        char[] chars = String.valueOf(id).toCharArray();

        if (!isOdd(chars.length)) {
            // is even
            int mid = chars.length / 2;

            String first = new String(chars, 0, mid);
            String second = new String(chars, mid, mid);

            if (first.equals(second) && !findSequences) idAggr += id;
        }
        
    }

    private static boolean isOdd(long num){
        return num % 2 != 0;
    }

    private static void findSequences(long id){
        String digitString = String.valueOf(id);
        
        // Single regex to check if ID is invalid (made only of sequences repeated at least twice)
        // ^((\d+)\2+)+$ matches if the entire string consists of sequences where each sequence repeats at least twice
        // Examples that match (invalid): "112233" (11+22+33), "123123" (123+123), "11112222" (1111+2222)
        // Examples that don't match (valid): "1122334" (has trailing 4), "1234" (no sequences)
        String invalidIdRegex = "^((\\d+)\\2+)+$";
        
        Pattern pattern = Pattern.compile(invalidIdRegex);
        boolean isInvalid = pattern.matcher(digitString).matches();
        
        if (isInvalid) {
            String patt = extractRepeatingSequence(Long.toString(id));

            if (patt != null){
                // System.out.println("found invalid id: " + id);
                idAggr += id;
            }
        }
    }   

    private static String extractRepeatingSequence(String id) {
        Pattern p = Pattern.compile("^(\\d+?)\\1+$");
        Matcher m = p.matcher(id);
    
        if (m.matches()) {
            return m.group(1); // the base repeated sequence
        }
        return null; // means no pure repeating pattern
    }


}
