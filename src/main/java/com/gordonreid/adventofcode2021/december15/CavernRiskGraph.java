package com.gordonreid.adventofcode2021.december15;

import com.google.common.collect.ImmutableList;
import com.gordonreid.adventofcode2021.helpers.GridHelpers.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@AllArgsConstructor
public class CavernRiskGraph {

    private final Square[][] riskMap;

    @RequiredArgsConstructor
    @Data
    public static class Square implements Comparable<Square> {
        private final int x;
        private final int y;
        private final int riskLevel;
        private long riskLevelToSource = Long.MAX_VALUE;
        private Square parentInShortestPath = null;

        @Override
        public int compareTo(Square o) {
            return Long.compare(this.riskLevelToSource, o.getRiskLevelToSource());
        }
    }

    public CavernRiskGraph(List<String> input) {
        this(input, true);
    }

    public CavernRiskGraph(List<String> input, boolean fullMap) {
        this.riskMap = fullMap ? readCaveMap(input) : copyFiveTimesInEachDirection(readCaveMap(input));
        this.riskMap[0][0].setRiskLevelToSource(0L);
    }

    private static Square[][] copyFiveTimesInEachDirection(Square[][] givenCaveMap) {
        Square[][] fullCaveMap = new Square[givenCaveMap.length * 5][givenCaveMap[0].length * 5];
        for (int y = 0; y < fullCaveMap.length; y++) {
            for (int x = 0; x < fullCaveMap[0].length; x++) {
                int additionalRiskDueToX = x / givenCaveMap[0].length;
                int additionalRiskDueToY = y / givenCaveMap.length;
                int xForGivenSquare = x % givenCaveMap[0].length;
                int yForGivenSquare = y % givenCaveMap.length;
                Square givenSquare = givenCaveMap[yForGivenSquare][xForGivenSquare];
                int riskLevel = (givenSquare.getRiskLevel() + additionalRiskDueToX + additionalRiskDueToY - 1) % 9 + 1;
                fullCaveMap[y][x] = new Square(x, y, riskLevel);
            }
        }
        return fullCaveMap;
    }

    public static Square[][] readCaveMap(List<String> input) {
        Square[][] riskMap = new Square[input.size()][input.get(0).length()];
        for (int y = 0; y < riskMap.length; y++) {
            for (int x = 0; x < riskMap[0].length; x++) {
                riskMap[y][x] = new Square(x, y, input.get(y).charAt(x) - '0');
            }
        }
        return riskMap;
    }

    public long getRiskOfShortestPath() {
        Queue<Square> squaresToVisit = new PriorityQueue<>();
        squaresToVisit.add(riskMap[0][0]);
        while (!squaresToVisit.isEmpty()) {
            Square currentSquare = squaresToVisit.remove();
            for (Square neighbour : neighbours(currentSquare)) {
                long newPathRisk = currentSquare.getRiskLevelToSource() + neighbour.getRiskLevel();
                if (newPathRisk < neighbour.getRiskLevelToSource()) {
                    neighbour.setRiskLevelToSource(newPathRisk);
                    neighbour.setParentInShortestPath(currentSquare);
                    squaresToVisit.add(neighbour);
                }
            }
        }
        long totalRiskLevel = 0;
        Square position = riskMap[riskMap.length - 1][riskMap[0].length - 1];
        while (!position.equals(riskMap[0][0])) {
            totalRiskLevel += position.getRiskLevel();
            position = position.getParentInShortestPath();
        }
        return totalRiskLevel;
    }

    private List<Square> neighbours(Square square) {
        // Neighbours are the squares above, left, right, or bottom. Not diagonals.
        int x = square.getX();
        int y = square.getY();
        return ImmutableList.of(new Coordinates(x - 1, y), new Coordinates(x + 1, y),
                        new Coordinates(x, y - 1), new Coordinates(x, y + 1))
                .stream()
                .filter(this::inBounds)
                .map(coordinates -> riskMap[coordinates.y()][coordinates.x()])
                .toList();
    }

    private boolean inBounds(Coordinates coordinates) {
        int x = coordinates.x();
        int y = coordinates.y();
        return y >= 0 && y < riskMap.length && x >= 0 && x < riskMap[y].length;
    }
}
