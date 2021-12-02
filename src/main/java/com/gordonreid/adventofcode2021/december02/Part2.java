package com.gordonreid.adventofcode2021.december02;

import com.gordonreid.adventofcode2021.december02.MoveUtils.Move;

import java.util.List;

public class Part2 {

    private static final class Location {
        int depth;
        int horizontal;
        int aim;

        public void move(String moveString) {
            Move move = Move.parse(moveString);
            switch (move.moveType) {
                case forward -> travelForwards(move.amount);
                case down -> aimDownwards(move.amount);
                case up -> aimUpwards(move.amount);
            }
        }

        public void aimUpwards(int up) {
            aim -= up;
        }

        public void aimDownwards(int down) {
            aim += down;
        }

        public void travelForwards(int forward) {
            horizontal += forward;
            depth += (aim * forward);
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
