package com.gordonreid.adventofcode2021.december15;

import com.gordonreid.adventofcode2021.helpers.FileHelpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December15Test {

    private static List<String> EXAMPLE_INPUT;
    private static List<String> ACTUAL_INPUT;

    @BeforeAll
    public static void setup() throws Exception {
        EXAMPLE_INPUT = FileHelpers.getResourceLines("day-15-test-input");
        ACTUAL_INPUT = FileHelpers.getResourceLines("day-15-input");
    }

    @Test
    public void part1_given_example() {
        long expectedResult = 40;

        long actualResult = Part1.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_actual() {
        long expectedResult = 609;

        long actualResult = Part1.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_given_example() {
        long expectedResult = 315;

        long actualResult = Part2.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_actual() {
        long expectedResult = 2925;

        long actualResult = Part2.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }
}
