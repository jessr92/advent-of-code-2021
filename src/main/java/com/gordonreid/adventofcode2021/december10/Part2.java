package com.gordonreid.adventofcode2021.december10;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Part2 {

    public static long run(List<String> input) {
        List<Long> scores = input.stream().map(Part2::getScore).filter(score -> score > 0).sorted().toList();
        assert scores.size() % 2 == 1; // We should only expect an odd number of incomplete lines
        return scores.get((scores.size() - 1) / 2); // We take the middle score
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
                if (!chunk.equals(openChunks.pop())) {
                    return 0;
                }
            }
        }
        List<Long> autocompletePoints = openChunks.stream().map(Chunk::getAutocompletePoints).toList();
        return autocompletePoints.stream().reduce(0L, (score, points) -> (score * 5) + points);
    }
}