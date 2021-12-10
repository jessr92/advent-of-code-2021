package com.gordonreid.adventofcode2021.december04;

import com.google.common.collect.Iterables;

import java.util.List;
import java.util.Optional;

public class Part2 {
    public static int run(List<String> input) {
        BingoGame bingoGame = BingoGame.create(input);
        int winningNumber = 0;
        Optional<BingoBoard> lastWinningBoard = Optional.empty();
        for (int number : bingoGame.pickedNumbers()) {
            bingoGame.bingoBoards().forEach(bingoBoard -> bingoBoard.mark(number));
            List<BingoBoard> boardsStillToWin = bingoGame.bingoBoards().stream().filter(BingoBoard::notWon).toList();
            if (boardsStillToWin.size() == 1) {
                lastWinningBoard = Optional.of(Iterables.getOnlyElement(boardsStillToWin));
            } else if (boardsStillToWin.isEmpty()) {
                winningNumber = number;
                break;
            }
        }
        assert lastWinningBoard.isPresent();
        int sumOfUnpickedNumbers = lastWinningBoard.get().unpickedNumbers().stream().mapToInt(a -> a).sum();
        return sumOfUnpickedNumbers * winningNumber;
    }
}
