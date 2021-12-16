package com.gordonreid.adventofcode2021.december16;

import com.google.common.collect.ImmutableList;
import lombok.*;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@UtilityClass
public class Packets {

    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    @ToString
    public static abstract class Packet {
        private final int version;
        private final int typeId;

        public List<Packet> getSubPackets() {
            return ImmutableList.of();
        }

        abstract long[] getValues();

        public long getValue() {
            return operationFor(typeId).apply(getValues());
        }

        private static Function<long[], Long> operationFor(int typeId) {
            return switch (typeId) {
                case 0 -> args -> Arrays.stream(args).sum(); // SUM
                case 1 -> args -> Arrays.stream(args).reduce(1, (a, b) -> a * b); // PRODUCT
                case 2 -> args -> Arrays.stream(args).min().orElse(0); // MIN
                case 3 -> args -> Arrays.stream(args).max().orElse(0); // MAX
                case 4 -> args -> args[0]; // LIT
                case 5 -> args -> args[0] > args[1] ? 1L : 0L; // GT
                case 6 -> args -> args[0] < args[1] ? 1L : 0L; // LT
                case 7 -> args -> args[0] == args[1] ? 1L : 0L; // EQ
                default -> throw new IllegalStateException("Unexpected value: " + typeId);
            };
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    @Value
    public static class LiteralPacket extends Packet {
        long value;

        public LiteralPacket(int version, int typeId, long value) {
            super(version, typeId);
            this.value = value;
        }

        @Override
        long[] getValues() {
            return new long[]{value};
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    @Value
    public static class OperatorPacket extends Packet {
        int lengthTypeId;
        int payloadLength;
        List<Packet> subPackets;

        public OperatorPacket(int version, int typeId, int lengthTypeId, int payloadLength, List<Packet> subPackets) {
            super(version, typeId);
            this.lengthTypeId = lengthTypeId;
            this.payloadLength = payloadLength;
            this.subPackets = subPackets;
        }

        @Override
        long[] getValues() {
            return subPackets.stream().mapToLong(Packet::getValue).toArray();
        }
    }
}
