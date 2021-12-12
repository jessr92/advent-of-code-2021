package com.gordonreid.adventofcode2021.december12;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@SuppressWarnings("UnstableApiUsage")
@UtilityClass
public class CaveTraversal {

    public static final String START = "start";
    public static final String END = "end";

    public static long paths(Graph<String> caveGraph, String cave, Set<String> visited) {
        if (cave.equals(END)) {
            return 1;
        }
        if (visited.contains(cave)) {
            return 0;
        }
        Set<String> visitedCopy = new HashSet<>(visited);
        if (isLowercase(cave)) {
            visitedCopy.add(cave);
        }
        long paths = 0;
        for (String adjacentCave : caveGraph.adjacentNodes(cave)) {
            paths += paths(caveGraph, adjacentCave, visitedCopy);
        }
        return paths;
    }

    public static long paths2(Graph<String> caveGraph, String cave, Set<String> visited, boolean canRevisitSmallCaves) {
        if (cave.equals(END)) {
            return 1;
        }
        if (visited.contains(cave)) {
            if (canRevisitSmallCaves) {
                return 0;
            } else {
                if (cave.equals(START)) {
                    return 0;
                }
                canRevisitSmallCaves = true;
            }
        }
        Set<String> visitedCopy = new HashSet<>(visited);
        if (isLowercase(cave)) {
            visitedCopy.add(cave);
        }
        long paths = 0;
        for (String adjacentCave : caveGraph.adjacentNodes(cave)) {
            paths += paths2(caveGraph, adjacentCave, visitedCopy, canRevisitSmallCaves);
        }
        return paths;
    }

    private static boolean isLowercase(String cave) {
        return cave.equals(cave.toLowerCase(Locale.ROOT));
    }

    static Graph<String> createGraph(List<String> input) {
        MutableGraph<String> caveGraph = GraphBuilder.undirected().build();
        for (String line : input) {
            String[] caves = line.split("-");
            caveGraph.putEdge(caves[0], caves[1]);
        }
        return caveGraph;
    }
}
