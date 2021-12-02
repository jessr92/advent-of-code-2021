package com.gordonreid.adventofcode2021.december01;

import com.google.common.collect.ImmutableList;
import com.gordonreid.adventofcode2021.helpers.FileHelpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December01Test {

    private static final List<Integer> EXAMPLE_INPUT = ImmutableList.of(
            199, 200, 208, 210, 200, 207, 240, 269, 260, 263
    );

    private static List<Integer> ACTUAL_INPUT;

    @BeforeAll
    public static void setup() throws Exception {
        List<String> rawInput = FileHelpers.getResourceLines("day-1-input");
        ACTUAL_INPUT = rawInput.stream().map(Integer::valueOf).toList();
    }

    @Test
    public void part1_given_example() {
        int expectedResult = 7;

        int actualResult = Part1.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_actual() {
        int expectedResult = 1655;

        int actualResult = Part1.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_given_example() {
        int expectedResult = 5;

        int actualResult = Part2.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_actual() throws Exception {
        List<String> inputLines = FileHelpers.getResourceLines("day-1-input");
        List<Integer> parsedInput = inputLines.stream().map(Integer::valueOf).toList();

        int expectedResult = 1683;

        int actualResult = Part2.run(parsedInput);

        assertEquals(expectedResult, actualResult);
    }
}