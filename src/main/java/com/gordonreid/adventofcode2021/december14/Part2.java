package com.gordonreid.adventofcode2021.december14;

import java.util.List;
import java.util.stream.IntStream;

public class Part2 {
    public static long run(List<String> input) {
        Polymer polymer = new Polymer(input);
        IntStream.range(0, 40).forEach(stepNumber -> polymer.step());
        return polymer.polymerValue();
    }
}
