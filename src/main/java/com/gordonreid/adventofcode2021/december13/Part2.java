package com.gordonreid.adventofcode2021.december13;

import java.util.List;

public class Part2 {

    public static long run(List<String> input) {
        DottedPaper dottedPaper = new DottedPaper(input);
        dottedPaper.allFolds();
        System.out.println(dottedPaper);
        return dottedPaper.dotCount();
    }

}
