package com.gordonreid.adventofcode2021.december12;

import java.util.HashSet;
import java.util.List;

public class Part1 {

    public static long run(List<String> input) {
        return CaveTraversal.pathCount(CaveTraversal.createGraph(input), CaveTraversal.START, new HashSet<>(), false);
    }
}
