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
                case forward -> travelForwards(move.amount());
                case down -> descend(move.amount());
                case up -> ascend(move.amount());
            }
        }

        public void ascend(int up) {
            depth -= up;
        }

        public void descend(int down) {
            depth += down;
        }

        public void travelForwards(int forward) {
            horizontal += forward;
        }

        public int locationValue() {
            return depth * horizontal;
        }
    }

    public static int run(List<String> exampleInput) {
        Location location = new Location();
        exampleInput.forEach(location::move);
        return location.locationValue();
    }
}
