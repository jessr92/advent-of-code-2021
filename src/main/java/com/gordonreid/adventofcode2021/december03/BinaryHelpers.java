package com.gordonreid.adventofcode2021.december03;

import java.util.stream.IntStream;

public final class BinaryHelpers {

    private BinaryHelpers() {
    }

    public static int binaryToInt(String binaryString) {
        String reversedString = new StringBuffer(binaryString).reverse().toString();
        return IntStream.range(0, reversedString.length())
                .filter(i -> reversedString.charAt(i) == '1')
                .map(i -> (int) Math.pow(2, i))
                .sum();
    }
}
