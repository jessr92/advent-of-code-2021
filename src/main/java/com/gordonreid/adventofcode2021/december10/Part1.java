package com.gordonreid.adventofcode2021.december10;

import java.util.List;
import java.util.Stack;

public class Part1 {

    public static long run(List<String> input) {
        return input.stream().mapToLong(Part1::getScore).sum();
    }

    private static long getScore(String line) {
        Stack<Chunk> openChunks = new Stack<>();
        for (char character : line.toCharArray()) {
            Chunk chunk = Chunk.fromCharacter(character);
            if (character == chunk.getOpeningCharacter()) {
                openChunks.push(chunk);
            } else if (openChunks.empty()) {
                throw new IllegalStateException("Stack is empty but encountered closing character " + character);
            } else {
                Chunk chunkToClose = openChunks.pop();
                if (character != chunkToClose.getClosingCharacter()) {
                    return chunk.getMismatchPoints();
                }
            }
        }
        return 0;
    }

}
