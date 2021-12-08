package com.gordonreid.adventofcode2021.december08;

import com.google.common.collect.ImmutableMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Part1 {

    private static final Map<Integer, Integer> UNIQUE_SEGMENTS = ImmutableMap.of(
            1, 2, // 1 is the only number to light two segments
            4, 4, // 4 is the only number to light four segments
            7, 3, // 7 is the only number to light three segments
            8, 7 // 8 is the only number to light seven segments
    );


    public static long run(List<String> input) {
        return input.stream()
                .flatMap(line -> {
                    String[] outputSegments = line.split("\\|")[1].trim().split(" ");
                    return Arrays.stream(outputSegments)
                            .filter(segments -> UNIQUE_SEGMENTS.containsValue(segments.length()));
                })
                .count();
    }
}
