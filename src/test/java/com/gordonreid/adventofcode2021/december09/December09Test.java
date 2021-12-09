package com.gordonreid.adventofcode2021.december09;

import com.gordonreid.adventofcode2021.helpers.FileHelpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December09Test {

    private static List<String> EXAMPLE_INPUT;
    private static List<String> ACTUAL_INPUT;

    @BeforeAll
    public static void setup() throws Exception {
        EXAMPLE_INPUT = FileHelpers.getResourceLines("day-9-test-input");
        ACTUAL_INPUT = FileHelpers.getResourceLines("day-9-input");
    }

    @Test
    public void part1_given_example() {
        int expectedResult = 15;

        int actualResult = Part1.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_actual() {
        int expectedResult = 603;

        int actualResult = Part1.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_given_example() {
        int expectedResult = 1134;

        int actualResult = Part2.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_actual() {
        int expectedResult = 786780;

        int actualResult = Part2.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }
}