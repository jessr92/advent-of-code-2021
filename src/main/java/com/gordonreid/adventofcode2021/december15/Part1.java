package com.gordonreid.adventofcode2021.december15;

import java.util.List;

public class Part1 {

    public static long run(List<String> input) {
        CavernRiskGraph graph = new CavernRiskGraph(input, 1);
        return graph.getRiskOfShortestPath();
    }
}
