package com.gordonreid.adventofcode2021.helpers;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public final class ListHelpers {

    public static <T> List<List<T>> transpose(List<List<T>> table) {
        List<List<T>> transposed = new ArrayList<>();
        final int n = table.get(0).size();
        for (int i = 0; i < n; i++) {
            List<T> col = new ArrayList<>();
            for (List<T> row : table) {
                col.add(row.get(i));
            }
            transposed.add(col);
        }
        return transposed;
    }
}
