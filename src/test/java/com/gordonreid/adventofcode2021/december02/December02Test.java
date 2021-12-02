package com.gordonreid.adventofcode2021.december02;

import com.google.common.collect.ImmutableList;
import com.gordonreid.adventofcode2021.helpers.FileHelpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December02Test {

    private static final List<String> EXAMPLE_INPUT = ImmutableList.of(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2"
    );

    private static List<String> ACTUAL_INPUT;

    @BeforeAll
    public static void setup() throws Exception {
        ACTUAL_INPUT = FileHelpers.getResourceLines("day-2-input");
    }

    @Test
    public void part1_given_example() {
        int expectedResult = 150;

        int actualResult = Part1.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_actual() {
        int expectedResult = 1882980;

        int actualResult = Part1.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void part2_given_example() {
        int expectedResult = 900;

        int actualResult = Part2.run(EXAMPLE_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_actual() {
        int expectedResult = 1971232560;

        int actualResult = Part2.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }
}