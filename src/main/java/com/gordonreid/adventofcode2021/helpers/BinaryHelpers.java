package com.gordonreid.adventofcode2021.helpers;

import lombok.experimental.UtilityClass;

import java.util.stream.IntStream;

@UtilityClass
public final class BinaryHelpers {

    public static int binaryToInt(String binaryString) {
        String reversedString = new StringBuffer(binaryString).reverse().toString();
        return IntStream.range(0, reversedString.length())
                .filter(i -> reversedString.charAt(i) == '1')
                .map(i -> (int) Math.pow(2, i))
                .sum();
    }
}
