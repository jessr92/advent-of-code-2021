package com.gordonreid.adventofcode2021.december16;

import com.google.common.collect.Iterables;

import java.util.List;

public class Part2 {

    public static long run(List<String> input) {
        String transmission = Iterables.getOnlyElement(input);
        return PacketReader.read(transmission).getValue();
    }
}

