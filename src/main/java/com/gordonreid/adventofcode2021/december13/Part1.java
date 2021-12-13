package com.gordonreid.adventofcode2021.december13;

import java.util.List;

public class Part1 {

    public static long run(List<String> input) {
        DottedPaper dottedPaper = new DottedPaper(input);
        // Part 1 just involves the first instruction
        dottedPaper.fold(1);
        return dottedPaper.dotCount();
    }
}
