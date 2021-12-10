package com.gordonreid.adventofcode2021.december10;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Chunk {
    PARENTHESIS('(', 3, 1),
    SQUARE_BRACKET('[', 57, 2),
    CURLY_BRACES('{', 1197, 3),
    ANGLE_BRACKET('<', 25137, 4);

    private final char openingCharacter;
    private final long mismatchPoints;
    private final long autocompletePoints;

    public static Chunk fromCharacter(Character character) {
        return switch (character) {
            case '(', ')' -> PARENTHESIS;
            case '[', ']' -> SQUARE_BRACKET;
            case '{', '}' -> CURLY_BRACES;
            case '<', '>' -> ANGLE_BRACKET;
            default -> throw new IllegalArgumentException(character + " is not a valid character.");
        };
    }
}
