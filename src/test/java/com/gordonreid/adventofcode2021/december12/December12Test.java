package com.gordonreid.adventofcode2021.december12;

import com.gordonreid.adventofcode2021.helpers.FileHelpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December12Test {

    private static List<String> SMALL_EXAMPLE_INPUT;
    private static List<String> MEDIUM_EXAMPLE_INPUT;
    private static List<String> LARGE_EXAMPLE_INPUT;
    private static List<String> ACTUAL_INPUT;

    @BeforeAll
    public static void setup() throws Exception {
        SMALL_EXAMPLE_INPUT = FileHelpers.getResourceLines("day-12-small-test-input");
        MEDIUM_EXAMPLE_INPUT = FileHelpers.getResourceLines("day-12-medium-test-input");
        LARGE_EXAMPLE_INPUT = FileHelpers.getResourceLines("day-12-large-test-input");
        ACTUAL_INPUT = FileHelpers.getResourceLines("day-12-input");
    }

    @Test
    public void part1_small_example() {
        long expectedResult = 10;

        long actualResult = Part1.run(SMALL_EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_medium_example() {
        long expectedResult = 19;

        long actualResult = Part1.run(MEDIUM_EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_large_example() {
        long expectedResult = 226;

        long actualResult = Part1.run(LARGE_EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_actual() {
        long expectedResult = 3463;

        long actualResult = Part1.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_small_example() {
        long expectedResult = 36;

        long actualResult = Part2.run(SMALL_EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_medium_example() {
        long expectedResult = 103;

        long actualResult = Part2.run(MEDIUM_EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_large_example() {
        long expectedResult = 3509;

        long actualResult = Part2.run(LARGE_EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_actual() {
        long expectedResult = 91533;

        long actualResult = Part2.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }
}