package com.gordonreid.adventofcode2021.helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HexHelpersTest {

    @Test
    public void hexString_firstGivenExample() {
        String input = "D2FE28";

        String expected = "110100101111111000101000";
        String result = HexHelpers.toBinaryString(input);

        assertEquals(expected, result);
    }

    @Test
    public void hexString_secondGivenExample() {
        String input = "38006F45291200";

        String expected = "00111000000000000110111101000101001010010001001000000000";
        String result = HexHelpers.toBinaryString(input);

        assertEquals(expected, result);
    }

    @Test
    public void hexString_thirdGivenExample() {
        String input = "EE00D40C823060";

        String expected = "11101110000000001101010000001100100000100011000001100000";
        String result = HexHelpers.toBinaryString(input);

        assertEquals(expected, result);
    }
}