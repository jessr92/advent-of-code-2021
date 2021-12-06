package com.gordonreid.adventofcode2021.december06;

import java.util.Arrays;
import java.util.List;

public class LanternFishCounts {

    private static final int INITIAL_TIMER = 8;
    private static final int TIMER_AFTER_GIVING_BIRTH = 6;

    private final long[] lanternFishCounts = new long[INITIAL_TIMER + 1];

    public LanternFishCounts(List<String> input) {
        List<Integer> inputTimers = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
        inputTimers.forEach(timer -> lanternFishCounts[timer]++);
    }

    public long totalCount() {
        return Arrays.stream(lanternFishCounts).sum();
    }

    public void nextDay() {
        long newFish = lanternFishCounts[0];
        // Decrement counter for all existing fish with a non-zero timer by copying position [1..end] to [0..end-1]
        System.arraycopy(lanternFishCounts, 1, lanternFishCounts, 0, lanternFishCounts.length - 1);
        // Fish that have given birth is the same number as new fish created. The existing fish have a timer of 6 for
        // when they'll next give birth so we _increase_ the count of that timer by the number of fish that have given birth
        lanternFishCounts[TIMER_AFTER_GIVING_BIRTH] += newFish;
        lanternFishCounts[INITIAL_TIMER] = newFish;
    }
}
