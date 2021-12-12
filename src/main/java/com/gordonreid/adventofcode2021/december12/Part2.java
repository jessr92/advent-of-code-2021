package com.gordonreid.adventofcode2021.december12;

import com.google.common.graph.Graph;

import java.util.HashSet;
import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public class Part2 {

    public static long run(List<String> input) {
        Graph<String> caveGraph = CaveTraversal.createGraph(input);
        return CaveTraversal.pathCount(caveGraph, CaveTraversal.START, new HashSet<>(), true);
    }

}
