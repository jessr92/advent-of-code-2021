package com.gordonreid.adventofcode2021.december05;

import java.util.List;

public class Part1 {

    public static int run(List<String> input) {
        List<HydrothermalVent> hydrothermalVents = input.stream().map(HydrothermalVent::create).toList();
        Grid grid = Grid.create(hydrothermalVents);
        List<HydrothermalVent> ventsOfInterest = hydrothermalVents.stream()
                .filter(vent -> vent.isHorizontal() || vent.isVertical())
                .toList();
        ventsOfInterest.forEach(grid::mark);
        return grid.cellsWithMultipleVents().size();
    }

}
