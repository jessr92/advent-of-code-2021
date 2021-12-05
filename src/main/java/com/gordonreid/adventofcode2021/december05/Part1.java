package com.gordonreid.adventofcode2021.december05;

import java.util.List;

public class Part1 {

    public static int run(List<String> input) {
        List<HydrothermalVent> hydrothermalVents = input.stream().map(HydrothermalVent::create).toList();
        assert !hydrothermalVents.isEmpty();
        int gridXSize = 1 + hydrothermalVents.stream().map(HydrothermalVent::highestXValue).max(Integer::compareTo).get();
        int gridYSize = 1 + hydrothermalVents.stream().map(HydrothermalVent::highestYValue).max(Integer::compareTo).get();
        Grid grid = Grid.create(gridXSize, gridYSize);
        List<HydrothermalVent> ventsOfInterest = hydrothermalVents.stream()
                .filter(vent -> vent.isHorizontal() || vent.isVertical())
                .toList();
        ventsOfInterest.forEach(grid::mark);
        return grid.cellsWithMultipleVents().size();
    }

}
