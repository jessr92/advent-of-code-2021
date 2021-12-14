package com.gordonreid.adventofcode2021.december14;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Polymer {

    private Map<String, Long> polymer;
    private final Map<String, Character> insertionRules;

    public Polymer(List<String> input) {
        this.polymer = new HashMap<>();
        String template = input.get(0);
        int templateLength = template.length();
        // Add each adjacent pair of characters in the template
        for (int i = 0; i < templateLength - 1; i++) {
            polymer.merge(template.substring(i, i + 2), 1L, Long::sum);
        }
        // Add the single character at the end
        polymer.put(template.substring(templateLength - 1), 1L);
        // Insertion rules are AB -> C which means put C between A and B (i.e. AB -> ACB in the polymer)
        this.insertionRules = new HashMap<>();
        for (int i = 2; i < input.size(); i++) {
            String[] insertionRuleArray = input.get(i).split(" -> ");
            this.insertionRules.put(insertionRuleArray[0], insertionRuleArray[1].charAt(0));
        }
    }

    public long polymerValue() {
        // For each entry, we only need the first character when counting occurrences. The second character will appear
        // in a subsequent entry as the first character of another pair (or on its own, if it's the end of the polymer).
        Collection<Long> characterOccurrences = polymer.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey().charAt(0), entry.getValue()))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.summingLong(Map.Entry::getValue)))
                .values();
        long mostCommonOccurrence = characterOccurrences.stream().max(Long::compareTo).orElse(0L);
        long leastCommonOccurrence = characterOccurrences.stream().min(Long::compareTo).orElse(0L);
        return mostCommonOccurrence - leastCommonOccurrence;
    }

    public void step() {
        Map<String, Long> updatedPolymer = new HashMap<>();
        for (Map.Entry<String, Long> entry : polymer.entrySet()) {
            String portion = entry.getKey();
            long occurrences = entry.getValue();
            if (insertionRules.containsKey(portion)) {
                // For each entry in the polymer that has an insertion rule, we need to create the new portions created
                // from that insertion rule. Each new portion has the same number of additional occurrences as the original
                // portion.
                // e.g. portion = AB with 10 occurrences. insertion rule is AB -> C
                // result - AC and CB each have 10 occurrences added to new polymer
                char characterToInsert = insertionRules.get(portion);
                String leftPortion = portion.substring(0, 1) + characterToInsert;
                updatedPolymer.merge(leftPortion, occurrences, Long::sum);
                String rightPortion = characterToInsert + portion.substring(1, 2);
                updatedPolymer.merge(rightPortion, occurrences, Long::sum);
            } else {
                // If the entry doesn't have an insertion rule, we add the entry to the updated polymer as-is.
                // An entry may not have an insertion rule in two case:
                // 1. Entry is for a polymer pair that has no insertion rules
                // 2. Entry is for the single character portion at the end. Single characters have no insertion rules
                updatedPolymer.put(portion, occurrences);
            }
        }
        this.polymer = updatedPolymer;
    }
}
