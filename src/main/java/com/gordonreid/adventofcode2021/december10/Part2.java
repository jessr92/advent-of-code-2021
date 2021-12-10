package com.gordonreid.adventofcode2021.december10;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Part2 {

    public static long run(List<String> input) {
        List<Long> scores = new ArrayList<>();
        for (String line : input) {
            Stack<Chunk> openChunks = new Stack<>();
            boolean corrupt = false;
            for (Character character : line.toCharArray()) {
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
                long score = autocompletePoints.stream().reduce(0L, (a, b) -> (a * 5) + b);
                scores.add(score);
            }
        }
        assert scores.size() % 2 == 1; // We should only expect an odd number of incomplete lines
        scores.sort(Long::compareTo);
        return scores.get((scores.size() - 1) / 2); // We take the middle score
    }
}