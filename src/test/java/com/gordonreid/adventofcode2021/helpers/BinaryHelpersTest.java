package com.gordonreid.adventofcode2021.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryHelpersTest {

    @Test
    public void givenExample() {
        String input = "011111100101";

        int expected = 2021;
        int result = BinaryHelpers.binaryToInt(input);

        assertEquals(expected, result);
    }

}