package com.gordonreid.adventofcode2021.helpers;

import lombok.experimental.UtilityClass;

import java.util.stream.IntStream;

@UtilityClass
public final class BinaryHelpers {

    public static int binaryToInt(String binaryString) {
        String reversedString = new StringBuffer(binaryString).reverse().toString();
        return IntStream.range(0, reversedString.length())
                .filter(i -> reversedString.charAt(i) == '1')
                .map(i -> 1 << i)
                .sum();
    }

    public static long binaryToLong(String binaryString) {
        String reversedString = new StringBuffer(binaryString).reverse().toString();
        return IntStream.range(0, reversedString.length())
                .filter(i -> reversedString.charAt(i) == '1')
                .mapToLong(i -> 1L << i)
                .sum();
    }
}
