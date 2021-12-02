package com.gordonreid.adventofcode2021.december02;

import java.util.Locale;

public class MoveUtils {

    enum MoveType {
        forward, down, up
    }

    static class Move {
        MoveType moveType;
        int amount;

        public Move(MoveType moveType, int amount) {
            this.moveType = moveType;
            this.amount = amount;
        }

        public static Move parse(String move) {
            String[] split = move.toLowerCase(Locale.ROOT).split(" ");
            MoveType moveType = MoveType.valueOf(split[0]);
            int amount = Integer.parseInt(split[1]);
            return new Move(moveType, amount);
        }
    }

}
