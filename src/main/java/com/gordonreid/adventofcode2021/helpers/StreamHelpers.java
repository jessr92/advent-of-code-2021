package com.gordonreid.adventofcode2021.helpers;

import com.google.common.collect.Iterables;
import lombok.experimental.UtilityClass;

import java.util.stream.Collector;
import java.util.stream.Collectors;

@UtilityClass
public class StreamHelpers {
    public static <T> Collector<T, ?, T> singleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                Iterables::getOnlyElement
        );
    }
}
