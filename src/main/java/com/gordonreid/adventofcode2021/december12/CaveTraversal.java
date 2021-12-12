package com.gordonreid.adventofcode2021.december12;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@UtilityClass
public class CaveTraversal {

    public static final String START = "start";
    public static final String END = "end";

    public static long pathCount(Multimap<String, String> caveGraph, String cave, Set<String> visited, boolean canRevisitSmallCaves) {
        if (cave.equals(END)) {
            return 1;
        }
        if (visited.contains(cave)) {
            if (cave.equals(START) || !canRevisitSmallCaves) {
                return 0;
            } else {
                canRevisitSmallCaves = false;
            }
        }
        Set<String> visitedCopy = new HashSet<>(visited);
        if (isSmallCave(cave)) {
            visitedCopy.add(cave);
        }
        long paths = 0;
        for (String adjacentCave : caveGraph.get(cave)) {
            paths += pathCount(caveGraph, adjacentCave, visitedCopy, canRevisitSmallCaves);
        }
        return paths;
    }

    private static boolean isSmallCave(String cave) {
        return cave.equals(cave.toLowerCase(Locale.ROOT));
    }

    static Multimap<String, String> createGraph(List<String> input) {
        Multimap<String, String> caveGraph = HashMultimap.create();
        input.stream().map(line -> line.split("-")).forEach(caves -> {
            caveGraph.put(caves[0], caves[1]);
            caveGraph.put(caves[1], caves[0]);
        });
        return caveGraph;
    }
}
