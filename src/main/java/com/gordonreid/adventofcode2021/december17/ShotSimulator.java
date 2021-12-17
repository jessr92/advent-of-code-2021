package com.gordonreid.adventofcode2021.december17;

import com.gordonreid.adventofcode2021.helpers.GridHelpers.Coordinates;

import java.util.Arrays;

public class ShotSimulator {

    public static ShotResult shotHitsTarget(int xVelocity, int yVelocity, TargetArea targetArea) {
        Coordinates shotPosition = new Coordinates(0, 0);
        int maxHeight = 0;
        while (shotPosition.x() < targetArea.end().x() && shotPosition.y() > targetArea.end.y()) {
            shotPosition = shotPosition.moveForward(xVelocity).moveUp(yVelocity);
            maxHeight = Math.max(maxHeight, shotPosition.y());
            // At each step, the x velocity trends to 0 and the y velocity drops by one
            xVelocity = Math.max(xVelocity - 1, 0);
            yVelocity--;
            if (shotInTargetArea(targetArea, shotPosition)) {
                return new ShotResult(true, maxHeight);
            }
        }
        return new ShotResult(false, maxHeight);
    }

    private static boolean shotInTargetArea(TargetArea targetArea, Coordinates shotPosition) {
        return shotPosition.x() >= targetArea.start().x() && shotPosition.x() <= targetArea.end().x() &&
                shotPosition.y() <= targetArea.start().y() && shotPosition.y() >= targetArea.end().y();
    }

    public record ShotResult(boolean hit, int maxHeight) {
    }

    public record TargetArea(Coordinates start, Coordinates end) {
        public static TargetArea from(String line) {
            String[] portionsOfInterest = line.substring("target area: x=".length()).split(", y=");
            int[] xCoordinates = Arrays.stream(portionsOfInterest[0].split("\\.\\.")).mapToInt(Integer::parseInt).toArray();
            int[] yCoordinates = Arrays.stream(portionsOfInterest[1].split("\\.\\.")).mapToInt(Integer::parseInt).toArray();

            Coordinates targetAreaBegin = new Coordinates(
                    Arrays.stream(xCoordinates).min().orElseThrow(),
                    Arrays.stream(yCoordinates).max().orElseThrow()
            );
            Coordinates targetAreaEnd = new Coordinates(
                    Arrays.stream(xCoordinates).max().orElseThrow(),
                    Arrays.stream(yCoordinates).min().orElseThrow()
            );
            return new TargetArea(targetAreaBegin, targetAreaEnd);
        }

        int getMinXVelocity() {
            // Based off of sum=1/2*n(n+1) where n = minimum x velocity and sum = x value where target area begins
            // This works because an initial x velocity of n will travel n, n-1, n-2, ..., 2, 1 spaces forwards
            // so the total distance travelled for a given starting velocity is the
            // 1/2(n)(n+1) = sum
            // n^2 + n = 2sum
            // 4n^2 + 4n = 8sum
            // 4n^2 + 4n + 1 = 1 + 8sum
            // (2n + 1)(2n + 1) = 1 + 8sum
            // 2n + 1 = sqrt(1 + 8sum)
            // 2n = sqrt(1 + 8sum) - 1
            // n = 1/2(sqrt(1 + 8sum) - 1)
            // We need to Math.ceil the value since if the min velocity is, say, 4.8 then the value should be 5 since
            // we can only have integer velocities.
            return (int) Math.ceil((Math.sqrt(1 + start().x() * 8) - 1) / 2);
        }
    }
}
