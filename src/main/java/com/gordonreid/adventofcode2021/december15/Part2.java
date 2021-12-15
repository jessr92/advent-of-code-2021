package com.gordonreid.adventofcode2021.december15;

import java.util.List;

public class Part2 {

    public static long run(List<String> input) {
        CavernRiskGraph graph = new CavernRiskGraph(input, 5);
        return graph.getRiskOfShortestPath();
    }
}
