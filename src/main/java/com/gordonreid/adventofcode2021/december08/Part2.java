package com.gordonreid.adventofcode2021.december08;

import com.gordonreid.adventofcode2021.helpers.StreamHelpers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Part2 {
    public static long run(List<String> input) {
        return input.stream()
                .mapToLong(Part2::inferOutputValue)
                .sum();
    }

    private static long inferOutputValue(String line) {
        String[] inputSegments = line.split("\\|")[0].trim().split(" ");
        String[] outputSegments = line.split("\\|")[1].trim().split(" ");

        // Represent letters as a bitmask: bce -> 0110100 (i.e. 32+16+4=52)
        Function<String, Integer> lettersToBitMask = letters -> letters.chars()
                .reduce(0, (mask, character) -> mask | 1 << ('g' - character));

        // Segments lit up (i.e. bitcount) to bit masks
        Map<Integer, List<Integer>> segmentsLitUpToBitMask = Arrays.stream(inputSegments)
                .map(lettersToBitMask)
                .collect(Collectors.groupingBy(Integer::bitCount));

        Integer[] digitBitMasks = new Integer[10];
        // Number 1 is the only number to light up two segments
        digitBitMasks[1] = segmentsLitUpToBitMask.get(2).stream().collect(StreamHelpers.singleton());
        // Number 4 is the only number to light up four segments
        digitBitMasks[4] = segmentsLitUpToBitMask.get(4).stream().collect(StreamHelpers.singleton());
        // Number 7 is the only number to light up three segments
        digitBitMasks[7] = segmentsLitUpToBitMask.get(3).stream().collect(StreamHelpers.singleton());
        // Number 8 is the only number to light up all seven segments
        digitBitMasks[8] = segmentsLitUpToBitMask.get(7).stream().collect(StreamHelpers.singleton());

        // This means that the remaining numbers are a bit trickier. We need to do some bit mask
        // comparisons to look at the number of common bits (i.e. segments lit up) between two different
        // numbers.

        // 2, 3, and 5 each have 5 segments lit up
        List<Integer> bitMasksFor5LitSegments = segmentsLitUpToBitMask.get(5);
        // 2 and 4 share two lit segments
        digitBitMasks[2] = bitMasksFor5LitSegments.stream().filter(i -> Integer.bitCount(i & digitBitMasks[4]) == 2).collect(StreamHelpers.singleton());
        // 3 and 1 share two  lit segments
        digitBitMasks[3] = bitMasksFor5LitSegments.stream().filter(i -> Integer.bitCount(i & digitBitMasks[1]) == 2).collect(StreamHelpers.singleton());
        // 5 and 2 share three lit segments
        digitBitMasks[5] = bitMasksFor5LitSegments.stream().filter(i -> Integer.bitCount(i & digitBitMasks[2]) == 3).collect(StreamHelpers.singleton());

        // 0, 6, and 9 each have 6 segments lit up
        List<Integer> bitMasksFor6LitSegments = segmentsLitUpToBitMask.get(6);
        // 6 and 1 share one lit segment
        digitBitMasks[6] = bitMasksFor6LitSegments.stream().filter(i -> Integer.bitCount(i & digitBitMasks[1]) == 1).collect(StreamHelpers.singleton());
        // 0 and 5 share four lit segments
        digitBitMasks[0] = bitMasksFor6LitSegments.stream().filter(i -> Integer.bitCount(i & digitBitMasks[5]) == 4).collect(StreamHelpers.singleton());
        // 9 and 4 share for lit segments
        digitBitMasks[9] = bitMasksFor6LitSegments.stream().filter(i -> Integer.bitCount(i & digitBitMasks[4]) == 4).collect(StreamHelpers.singleton());

        List<Integer> digits = Arrays.asList(digitBitMasks);
        // For each output segment, get its bit mask, then find out which digit the bit mask represents
        // Since the output is a four-digit number, we concatenate each digit into a string then convert
        // back to a long (so we can later find the sum of all numbers).
        return Long.parseLong(
                Arrays.stream(outputSegments)
                        .reduce("", (a, b) -> a + digits.indexOf(lettersToBitMask.apply(b)))
        );
    }

}
