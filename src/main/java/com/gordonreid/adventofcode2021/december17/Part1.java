package com.gordonreid.adventofcode2021.december17;

import com.gordonreid.adventofcode2021.december17.ShotSimulator.ShotResult;
import com.gordonreid.adventofcode2021.december17.ShotSimulator.TargetArea;

import java.util.List;

public class Part1 {

    public static long run(List<String> input) {
        TargetArea targetArea = TargetArea.from(input.get(0));
        int minXVelocity = targetArea.getMinXVelocity();
        int maxHeight = 0;
        for (int yVelocity = 0; yVelocity <= -targetArea.end().y(); yVelocity++) {
            ShotResult shotResult = ShotSimulator.shotHitsTarget(minXVelocity, yVelocity, targetArea);
            if (shotResult.hit()) {
                maxHeight = Math.max(maxHeight, shotResult.maxHeight());
            }
        }
        return maxHeight;
    }

}
