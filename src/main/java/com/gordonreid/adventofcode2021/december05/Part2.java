package com.gordonreid.adventofcode2021.december05;

import java.util.List;

public class Part2 {

    public static int run(List<String> input) {
        List<HydrothermalVent> hydrothermalVents = input.stream().map(HydrothermalVent::create).toList();
        Grid grid = Grid.create(hydrothermalVents);
        hydrothermalVents.forEach(grid::mark);
        return grid.cellsWithMultipleVents().size();
    }
}
