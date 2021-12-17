package com.gordonreid.adventofcode2021.december17;

import com.gordonreid.adventofcode2021.helpers.GridHelpers.Coordinates;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShotSimulator {

    public static ShotResult simulateShot(int xVelocity, int yVelocity, TargetArea targetArea) {
        Coordinates shotPosition = new Coordinates(0, 0);
        int maxHeight = 0;
        while (shotPosition.x() <= targetArea.end().x() && shotPosition.y() >= targetArea.end().y()) {
            shotPosition = shotPosition.moveForward(xVelocity).moveUp(yVelocity);
            maxHeight = Math.max(maxHeight, shotPosition.y());
            // At each step, the x velocity trends to 0 and the y velocity drops by one
            xVelocity = Math.max(xVelocity - 1, 0);
            yVelocity--;
            if (targetArea.hitBy(shotPosition)) {
                return new ShotResult(true, maxHeight);
            }
        }
        return new ShotResult(false, maxHeight);
    }

    public record ShotResult(boolean hit, int maxHeight) {
    }
}
