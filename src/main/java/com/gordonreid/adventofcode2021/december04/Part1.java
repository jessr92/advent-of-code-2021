package com.gordonreid.adventofcode2021.december04;

import java.util.List;
import java.util.Optional;

public class Part1 {

    public static int run(List<String> input) {
        BingoGame bingoGame = BingoGame.create(input);
        int winningNumber = 0;
        Optional<BingoBoard> winningBoard = Optional.empty();
        for (int number : bingoGame.pickedNumbers()) {
            for (BingoBoard bingoBoard : bingoGame.bingoBoards()) {
                bingoBoard.mark(number);
            }
            winningBoard = bingoGame.bingoBoards().stream().filter(BingoBoard::hasWon).findFirst();
            if (winningBoard.isPresent()) {
                winningNumber = number;
                break;
            }
        }
        assert winningBoard.isPresent();
        int sumOfUnpickedNumbers = winningBoard.get().unpickedNumbers().stream().reduce(0, Integer::sum);
        return sumOfUnpickedNumbers * winningNumber;
    }

}
