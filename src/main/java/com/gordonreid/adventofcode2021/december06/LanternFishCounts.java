package com.gordonreid.adventofcode2021.december06;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class LanternFishCounts {

    private static final int INITIAL_TIMER = 8;
    private static final int TIMER_AFTER_GIVING_BIRTH = 6;

    private final List<LanternFishCount> lanternFishCounts = IntStream.rangeClosed(0, INITIAL_TIMER)
            .mapToObj(LanternFishCount::new)
            .toList();

    public LanternFishCounts(List<String> input) {
        List<Integer> inputTimers = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
        inputTimers.forEach(timer -> lanternFishCounts.get(timer).incrementCount());
    }

    public long totalCount() {
        return lanternFishCounts.stream().map(LanternFishCount::getCount).reduce(0L, Long::sum);
    }

    public void nextDay() {
        long newFish = lanternFishCounts.get(0).getCount();
        // Decrement counter for all existing fish with a non-zero timer
        for (int i = 0; i < lanternFishCounts.size() - 1; i++) {
            lanternFishCounts.get(i).setCount(lanternFishCounts.get(i + 1).getCount());
        }
        // Fish that have given birth is the same number as new fish created. Those have a timer of 6 so we _increase_
        // the count of that timer by the number of fish that have given birth
        lanternFishCounts.get(TIMER_AFTER_GIVING_BIRTH).increaseCount(newFish);
        lanternFishCounts.get(INITIAL_TIMER).setCount(newFish);
    }
}
