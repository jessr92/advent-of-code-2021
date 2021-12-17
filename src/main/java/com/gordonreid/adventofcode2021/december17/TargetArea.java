package com.gordonreid.adventofcode2021.december17;

import com.gordonreid.adventofcode2021.helpers.GridHelpers.Coordinates;

import java.util.Arrays;

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

    public int getMinXVelocity() {
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

    public boolean hitBy(Coordinates shotPosition) {
        return shotPosition.x() >= start().x() && shotPosition.x() <= end().x() &&
                shotPosition.y() <= start().y() && shotPosition.y() >= end().y();
    }
}
