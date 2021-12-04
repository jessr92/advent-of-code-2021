package com.gordonreid.adventofcode2021.december04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused") // IntelliJ marks this as unused sometimes then realises it is used. Suppressing.
public record BingoGame(List<Integer> pickedNumbers, List<BingoBoard> bingoBoards) {

    public static BingoGame create(List<String> input) {
        return new BingoGame(getPickedNumbers(input), getBingoBoards(input));
    }

    static List<Integer> getPickedNumbers(List<String> input) {
        return Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
    }

    static List<BingoBoard> getBingoBoards(List<String> input) {
        List<BingoBoard> bingoBoards = new ArrayList<>();
        BingoBoard currentBoard = null;
        boolean first = true;
        for (String line : input) {
            if (first) {
                first = false;
            } else if (line.isBlank()) {
                currentBoard = new BingoBoard();
                bingoBoards.add(currentBoard);
            } else {
                List<Cell> rowNumbers = Arrays.stream(line.split("\\s+"))
                        .filter(s -> !s.isBlank())
                        .map(Integer::parseInt)
                        .map(Cell::new)
                        .toList();
                assert currentBoard != null;
                currentBoard.addRow(rowNumbers);
            }
        }
        return bingoBoards;
    }
}
