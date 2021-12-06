package com.gordonreid.adventofcode2021.december06;

import java.util.Arrays;
import java.util.List;

public class LanternFishCounts {

    private static final int TIMER_VALUE_COUNT = 9;
    private static final int DAYS_BETWEEN_BIRTHS = 7;

    private final long[] lanternFishCounts = new long[TIMER_VALUE_COUNT];

    public LanternFishCounts(List<String> input) {
        List<Integer> inputTimers = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
        inputTimers.forEach(timer -> lanternFishCounts[timer]++);
    }

    public long totalCount() {
        return Arrays.stream(lanternFishCounts).sum();
    }

    public void simulateDay(int day) {
        // Fish giving birth today will next give birth in DAYS_BETWEEN_BIRTHS time, so we add the count of fish giving
        // birth today to the location we'll read from on that day.
        // After a full cycle around the count array, the fish born today will give birth, so we keep the count at the
        // current location as is.
        // Note: The first day is day 0.
        long fishGivingBirthToday = lanternFishCounts[day % lanternFishCounts.length];
        int whenThoseFishWillNextGiveBirth = (day + DAYS_BETWEEN_BIRTHS) % lanternFishCounts.length;
        lanternFishCounts[whenThoseFishWillNextGiveBirth] += fishGivingBirthToday;
    }
}
