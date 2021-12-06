package com.gordonreid.adventofcode2021.december06;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class LanternFishCount {
    private final int timer;
    @Setter
    private long count;

    public void incrementCount() {
        count++;
    }

    public void increaseCount(long additionalCount) {
        count += additionalCount;
    }
}
