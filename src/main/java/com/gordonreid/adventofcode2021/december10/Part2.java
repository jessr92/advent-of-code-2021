package com.gordonreid.adventofcode2021.december10;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Stack;

public class Part2 {

    public static long run(List<String> input) {
        List<Long> scores = input.stream().map(Part2::getScore).filter(score -> score > 0).sorted().toList();
        assert scores.size() % 2 == 1; // We should only expect an odd number of incomplete lines
        return scores.get((scores.size() - 1) / 2); // We take the middle score
    }

    private static long getScore(String line) {
        Stack<Chunk> openChunks = new Stack<>();
        boolean corrupt = false;
        for (char character : line.toCharArray()) {
            Chunk chunk = Chunk.fromCharacter(character);
            if (character == chunk.getOpeningCharacter()) {
                openChunks.push(chunk);
            } else if (openChunks.empty()) {
                throw new IllegalStateException("Stack is empty but encountered closing character " + character);
            } else {
                Chunk chunkToClose = openChunks.pop();
                if (character != chunkToClose.getClosingCharacter()) {
                    corrupt = true;
                    break;
                }
            }
        }
        if (!corrupt && !openChunks.empty()) {
            List<Long> autocompletePoints = Lists.reverse(openChunks.stream().map(Chunk::getAutocompletePoints).toList());
            return autocompletePoints.stream().reduce(0L, (score, points) -> (score * 5) + points);
        }
        return 0;
    }
}