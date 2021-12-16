package com.gordonreid.adventofcode2021.december16;

import com.google.common.collect.Iterables;
import com.gordonreid.adventofcode2021.december16.PacketReader.LiteralPacket;
import com.gordonreid.adventofcode2021.december16.PacketReader.OperatorPacket;
import com.gordonreid.adventofcode2021.december16.PacketReader.Packet;

import java.util.List;

public class Part2 {

    public static long run(List<String> input) {
        String transmission = Iterables.getOnlyElement(input);
        Packet packet = PacketReader.read(transmission);
        return calculateValue(packet);
    }

    // Recursively visit the packet tree returning either the literal value of the packet (base case) or the result of
    // the operation of an operator packet (recursive case).
    private static long calculateValue(Packet packet) {
        long value;
        if (packet instanceof LiteralPacket) {
            value = ((LiteralPacket) packet).getValue();
        } else {
            OperatorPacket operatorPacket = (OperatorPacket) packet;
            long[] args = operatorPacket.getSubPackets().stream().mapToLong(Part2::calculateValue).toArray();
            value = operatorPacket.getOperation().getOperation().apply(args);
        }
        return value;
    }
}

