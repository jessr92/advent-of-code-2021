package com.gordonreid.adventofcode2021.december02;

import com.gordonreid.adventofcode2021.december02.MoveUtils.Move;

import java.util.List;

public class Part1 {

    private static final class Location {
        int depth;
        int horizontal;

        public void move(String moveString) {
            Move move = Move.parse(moveString);
            switch (move.moveType()) {
                case forward -> horizontal += move.amount();
                case down -> depth += move.amount();
                case up -> depth -= move.amount();
            }
        }

        public int locationValue() {
            return depth * horizontal;
        }
    }

    public static int run(List<String> input) {
        Location location = new Location();
        input.forEach(location::move);
        return location.locationValue();
    }
}
