package com.gordonreid.adventofcode2021.december15;

import com.google.common.collect.ImmutableList;
import com.gordonreid.adventofcode2021.helpers.Coordinates;
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

    public CavernRiskGraph(List<String> input, int copies) {
        this.riskMap = readCaveMap(input, copies);
        this.riskMap[0][0].setRiskLevelToSource(0L);
    }

    private static Square[][] readCaveMap(List<String> input, int copies) {
        Square[][] givenCaveMap = readGivenCaveMap(input);
        int originalXLength = givenCaveMap[0].length;
        int originalYLength = givenCaveMap.length;
        Square[][] fullCaveMap = new Square[originalYLength * copies][originalXLength * copies];
        for (int y = 0; y < originalYLength; y++) {
            for (int x = 0; x < originalXLength; x++) {
                for (int yMultiple = 0; yMultiple < copies; yMultiple++) {
                    for (int xMultiple = 0; xMultiple < copies; xMultiple++) {
                        int riskLevel = (givenCaveMap[y][x].getRiskLevel() + xMultiple + yMultiple - 1) % 9 + 1;
                        int fullMapX = x + (originalYLength * xMultiple);
                        int fullMapY = y + (originalXLength * yMultiple);
                        fullCaveMap[fullMapY][fullMapX] = new Square(fullMapX, fullMapY, riskLevel);
                    }
                }
            }
        }
        return fullCaveMap;
    }

    public static Square[][] readGivenCaveMap(List<String> input) {
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
