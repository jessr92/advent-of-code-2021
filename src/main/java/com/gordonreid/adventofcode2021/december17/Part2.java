package com.gordonreid.adventofcode2021.december17;

import com.gordonreid.adventofcode2021.december17.ShotSimulator.ShotResult;

import java.util.List;

public class Part2 {

    public static long run(List<String> input) {
        TargetArea targetArea = TargetArea.from(input.get(0));
        int minXVelocity = targetArea.getMinXVelocity();
        // Min x velocity as calculated above.
        // Max x velocity is how far away the end of the target area is (i.e. hitting it in one step).
        // Min y velocity is how to reach the bottom of the target area in one turn
        // Max y velocity is the negation of min y velocity
        int validVelocityCount = 0;
        for (int xVelocity = minXVelocity; xVelocity <= targetArea.end().x(); xVelocity++) {
            for (int yVelocity = targetArea.end().y(); yVelocity <= -targetArea.end().y(); yVelocity++) {
                ShotResult shotResult = ShotSimulator.simulateShot(xVelocity, yVelocity, targetArea);
                if (shotResult.hit()) {
                    validVelocityCount++;
                }
            }
        }
        return validVelocityCount;
    }

}
