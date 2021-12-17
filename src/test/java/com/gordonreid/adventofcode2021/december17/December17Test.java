package com.gordonreid.adventofcode2021.december17;

import com.gordonreid.adventofcode2021.helpers.FileHelpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December17Test {

    private static List<String> EXAMPLE_INPUT;
    private static List<String> ACTUAL_INPUT;

    @BeforeAll
    public static void setup() throws Exception {
        EXAMPLE_INPUT = FileHelpers.getResourceLines("day-17-test-input");
        ACTUAL_INPUT = FileHelpers.getResourceLines("day-17-input");
    }

    @Test
    public void part1_given_example() {
        long expectedResult = 45;

        long actualResult = Part1.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_actual() {
        long expectedResult = 35511;

        long actualResult = Part1.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_given_example() {
        long expectedResult = 112;

        long actualResult = Part2.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_actual() {
        long expectedResult = 3282;

        long actualResult = Part2.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }
}
