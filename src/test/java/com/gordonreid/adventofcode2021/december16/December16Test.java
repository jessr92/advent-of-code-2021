package com.gordonreid.adventofcode2021.december16;

import com.google.common.collect.ImmutableList;
import com.gordonreid.adventofcode2021.helpers.FileHelpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class December16Test {

    private static final String PART_1_FIRST_EXAMPLE = "8A004A801A8002F478";
    private static final String PART_1_SECOND_EXAMPLE = "620080001611562C8802118E34";
    private static final String PART_1_THIRD_EXAMPLE = "C0015000016115A2E0802F182340";
    private static final String PART_1_FOURTH_EXAMPLE = "A0016C880162017C3686B18A3D4780";

    private static final String PART_2_FIRST_EXAMPLE = "C200B40A82";
    private static final String PART_2_SECOND_EXAMPLE = "04005AC33890";
    private static final String PART_2_THIRD_EXAMPLE = "880086C3E88112";
    private static final String PART_2_FOURTH_EXAMPLE = "CE00C43D881120";
    private static final String PART_2_FIFTH_EXAMPLE = "D8005AC2A8F0";
    private static final String PART_2_SIXTH_EXAMPLE = "F600BC2D8F";
    private static final String PART_2_SEVENTH_EXAMPLE = "9C005AC2F8F0";
    private static final String PART_2_EIGHTH_EXAMPLE = "9C0141080250320F1802104A08";

    private static List<String> ACTUAL_INPUT;

    @BeforeAll
    public static void setup() throws Exception {
        ACTUAL_INPUT = FileHelpers.getResourceLines("day-16-input");
    }

    @Test
    public void part1_first_example() {
        long expectedResult = 16;

        long actualResult = Part1.run(ImmutableList.of(PART_1_FIRST_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_second_example() {
        long expectedResult = 12;

        long actualResult = Part1.run(ImmutableList.of(PART_1_SECOND_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_third_example() {
        long expectedResult = 23;

        long actualResult = Part1.run(ImmutableList.of(PART_1_THIRD_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_fourth_example() {
        long expectedResult = 31;

        long actualResult = Part1.run(ImmutableList.of(PART_1_FOURTH_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part1_actual() {
        long expectedResult = 955;

        long actualResult = Part1.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_first_example() {
        long expectedResult = 3;

        long actualResult = Part2.run(ImmutableList.of(PART_2_FIRST_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_second_example() {
        long expectedResult = 54;

        long actualResult = Part2.run(ImmutableList.of(PART_2_SECOND_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_third_example() {
        long expectedResult = 7;

        long actualResult = Part2.run(ImmutableList.of(PART_2_THIRD_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_fourth_example() {
        long expectedResult = 9;

        long actualResult = Part2.run(ImmutableList.of(PART_2_FOURTH_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_fifth_example() {
        long expectedResult = 1;

        long actualResult = Part2.run(ImmutableList.of(PART_2_FIFTH_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_sixth_example() {
        long expectedResult = 0;

        long actualResult = Part2.run(ImmutableList.of(PART_2_SIXTH_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_seventh_example() {
        long expectedResult = 0;

        long actualResult = Part2.run(ImmutableList.of(PART_2_SEVENTH_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_eighth_example() {
        long expectedResult = 1;

        long actualResult = Part2.run(ImmutableList.of(PART_2_EIGHTH_EXAMPLE));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void part2_actual() {
        long expectedResult = 158135423448L;

        long actualResult = Part2.run(ACTUAL_INPUT);

        assertEquals(expectedResult, actualResult);
    }
}
