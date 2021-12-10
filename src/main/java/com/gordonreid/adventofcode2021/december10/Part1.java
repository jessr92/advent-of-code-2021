package com.gordonreid.adventofcode2021.december10;

import java.util.List;
import java.util.Stack;

public class Part1 {

    public static long run(List<String> input) {
        long score = 0;
        for (String line : input) {
            Stack<Chunk> openChunks = new Stack<>();
            for (Character character : line.toCharArray()) {
                Chunk chunk = Chunk.fromCharacter(character);
                if (character == chunk.getOpeningCharacter()) {
                    openChunks.push(chunk);
                } else if (openChunks.empty()) {
                    throw new IllegalStateException("Stack is empty but encountered closing character " + character);
                } else {
                    Chunk chunkToClose = openChunks.pop();
                    if (character != chunkToClose.getClosingCharacter()) {
                        score += chunk.getMismatchPoints();
                        break; // Corrupted line
                    }
                }
            }
        }
        return score;
    }

}
