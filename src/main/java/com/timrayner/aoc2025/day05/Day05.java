package com.timrayner.aoc2025.day05;

import java.util.ArrayList;
import java.util.List;

import com.timrayner.aoc2025.DayTemplate;

public class Day05 extends DayTemplate {

    private static final String INPUT = "day05.txt";

    @Override
    public String challenge1() throws Exception {

        int freshCount = 0; 
        List<String> freshIdRanges = new ArrayList<String>();
        List<Long> ingredientIds = new ArrayList<Long>();


        var lines = readInput(INPUT);
        int gapIndex = lines.indexOf("");    // find the line break 

        // 1) get lines before gap 
            //1.1) store in 'freshIdRanges' variable
        // 2) get lines after gap
            //2.1) store in 'ingredientIds' variable
        // 3) loop through each ingredientId 
            //3.1) inner loop through ranges until ingredient is proven to be fresh. 
            //3.1) add 1 to count 

        /**
         * So we know we need 
         *  - a global count variable (int) 
         *  - a global freshIdRanges list variable (List<String>) 
         *  - ingredientIds list variable (long)
         */

        freshIdRanges = lines.subList(0, gapIndex);
        List<String> rawIngredientIds = lines.subList(gapIndex + 1, lines.size());

        for(int i = 0; i < rawIngredientIds.size(); i++){
            ingredientIds.add(Long.parseLong(rawIngredientIds.get(i)));
        }
        
        for(int i = 0; i < ingredientIds.size(); i++){
            boolean fresh = false;
            Long ingredientId = ingredientIds.get(i);

            for(int f = 0; f < freshIdRanges.size(); f++){
                fresh = isInRange(ingredientId, freshIdRanges.get(f));
                if(fresh) {freshCount += 1; break;}
            }
       

        }
        return Integer.toString(freshCount);

    }

    @Override
    public String challenge2() throws Exception {
        var lines = readInput(INPUT);

        List<String> freshIdRanges = new ArrayList<String>();

        int gapIndex = lines.indexOf("");    // find the line break 
        freshIdRanges = lines.subList(0, gapIndex);

        List<Range> ranges = new ArrayList<>();
        for(int i = 0; i < freshIdRanges.size(); i++){
            Range range = parseRange(freshIdRanges.get(i));
            ranges.add(range);
        }
    
        // Sort by start value
        ranges.sort((a, b) -> Long.compare(a.start, b.start));
    
        // Merge overlapping ranges
        List<Range> merged = mergeRanges(ranges);
    
        // Sum the sizes of all merged ranges
        long totalCount = 0;
        for(Range range : merged) {
            totalCount += (range.end - range.start + 1);
        }
    
        return Long.toString(totalCount);
    }


    private boolean isInRange(Long inputNumber, String rangeAsStr){

        int splitAt = rangeAsStr.indexOf("-");

        Long start = Long.parseLong(rangeAsStr.substring(0, splitAt)); 
        Long end = Long.parseLong(rangeAsStr.substring(splitAt + 1, rangeAsStr.length()));

        boolean isInRange = (inputNumber >= start) && (inputNumber <= end);

        return isInRange;
    }
 

    private Range parseRange(String rangeAsStr) {
        int splitAt = rangeAsStr.indexOf("-");
        long start = Long.parseLong(rangeAsStr.substring(0, splitAt)); 
        long end = Long.parseLong(rangeAsStr.substring(splitAt + 1, rangeAsStr.length()));
        return new Range(start, end);
    }

    private List<Range> mergeRanges(List<Range> ranges) {
        if(ranges.isEmpty()) {
            return new ArrayList<>();
        }
    
        List<Range> merged = new ArrayList<>();
        Range current = ranges.get(0);
    
        for(int i = 1; i < ranges.size(); i++) {
            Range next = ranges.get(i);
            
            if(next.start <= current.end + 1) {
                current = new Range(current.start, Math.max(current.end, next.end));
            } else {
                // No overlap, add current and move to next
                merged.add(current);
                current = next;
            }
        }
        merged.add(current); // Add the last range
    
        return merged;
    }
    

    private static class Range {
        final long start;
        final long end;
        
        Range(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

}
