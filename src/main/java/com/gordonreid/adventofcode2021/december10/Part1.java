package com.gordonreid.adventofcode2021.december10;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Part1 {

    public static long run(List<String> input) {
        return input.stream().mapToLong(Part1::getScore).sum();
    }

    private static long getScore(String line) {
        Deque<Chunk> openChunks = new ArrayDeque<>();
        for (char character : line.toCharArray()) {
            Chunk chunk = Chunk.fromCharacter(character);
            if (character == chunk.getOpeningCharacter()) {
                openChunks.push(chunk);
            } else if (openChunks.isEmpty()) {
                throw new IllegalStateException("Stack is empty but encountered closing character " + character);
            } else {
                Chunk chunkToClose = openChunks.pop();
                if (!chunk.equals(chunkToClose)) {
                    return chunk.getMismatchPoints();
                }
            }
        }
        return 0;
    }

}
