package com.gordonreid.adventofcode2021.december16;

import com.google.common.collect.Iterables;
import com.gordonreid.adventofcode2021.december16.Packets.Packet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Part1 {

    // Read in transmission and visit each packet to find the sum of the versions for all of packets
    public static long run(List<String> input) {
        String transmission = Iterables.getOnlyElement(input);
        Packet packet = PacketReader.read(transmission);
        List<Packet> packets = new ArrayList<>();
        Deque<Packet> toVisit = new ArrayDeque<>();
        toVisit.add(packet);
        while (!toVisit.isEmpty()) {
            Packet p = toVisit.pop();
            packets.add(p);
            toVisit.addAll(p.getSubPackets());
        }
        return packets.stream().mapToLong(Packet::getVersion).sum();
    }
}
