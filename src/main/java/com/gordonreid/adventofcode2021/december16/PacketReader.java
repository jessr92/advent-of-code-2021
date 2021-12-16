package com.gordonreid.adventofcode2021.december16;

import com.gordonreid.adventofcode2021.helpers.BinaryHelpers;
import com.gordonreid.adventofcode2021.helpers.HexHelpers;
import lombok.*;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@UtilityClass
public class PacketReader {

    private static final int SUB_PACKET_LENGTH_TYPE = 0;
    private static final int LITERAL_PACKET_TYPE_ID = 4;
    private static final int VERSION_BIT_LENGTH = 3;
    private static final int PACKAGE_TYPE_BIT_LENGTH = 3;

    @AllArgsConstructor
    @Getter
    @EqualsAndHashCode
    @ToString
    public static abstract class Packet {
        private final int version;
        private final int typeId;

        abstract long[] getValues();

        public long getValue() {
            return operationFor(typeId).apply(getValues());
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

        long[] getValues() {
            return subPackets.stream().mapToLong(Packet::getValue).toArray();
        }
    }

    public static Function<long[], Long> operationFor(int typeId) {
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

    private record PacketParseResult(Packet packet, int positionReadTo) {
    }

    private record LiteralParseResult(long value, int positionReadTo) {
    }

    private record SubPacketParseResult(List<Packet> subPackets, int positionReadTo) {
    }

    public static Packet read(String packetHex) {
        return parse(HexHelpers.toBinaryString(packetHex)).packet();
    }

    public static PacketParseResult parse(String packetBinary) {
        int position = 0;
        int version = BinaryHelpers.binaryToInt(packetBinary.substring(position, position + VERSION_BIT_LENGTH));
        position += VERSION_BIT_LENGTH;
        int typeId = BinaryHelpers.binaryToInt(packetBinary.substring(position, position + PACKAGE_TYPE_BIT_LENGTH));
        position += PACKAGE_TYPE_BIT_LENGTH;
        if (typeId == LITERAL_PACKET_TYPE_ID) {
            return parseLiteralPacket(packetBinary, position, version, typeId);
        } else {
            return parseOperatorPacket(packetBinary, position, version, typeId);
        }
    }

    private static PacketParseResult parseLiteralPacket(String packetBinary, int position, int version, int typeId) {
        LiteralParseResult literalParseResult = parseLiteralValue(packetBinary.substring(position));
        LiteralPacket literalPacket = new LiteralPacket(version, typeId, literalParseResult.value());
        position += literalParseResult.positionReadTo();
        return new PacketParseResult(literalPacket, position);
    }

    private static PacketParseResult parseOperatorPacket(String packetBinary, int position, int version, int typeId) {
        int lengthTypeId = BinaryHelpers.binaryToInt(packetBinary.substring(position, position + 1));
        position++;
        int lengthBinaryDigitCount = lengthTypeId == SUB_PACKET_LENGTH_TYPE ? 15 : 11;
        int length = BinaryHelpers.binaryToInt(packetBinary.substring(position, position + lengthBinaryDigitCount));
        position += lengthBinaryDigitCount;
        String subPacketBinary = packetBinary.substring(position);
        SubPacketParseResult subPacketParseResult = parseSubPackets(subPacketBinary, lengthTypeId, length);
        OperatorPacket operatorPacket = new OperatorPacket(version, typeId, lengthTypeId, length, subPacketParseResult.subPackets());
        position += subPacketParseResult.positionReadTo();
        return new PacketParseResult(operatorPacket, position);
    }

    private static SubPacketParseResult parseSubPackets(String subPacketBinary, int lengthTypeId, int length) {
        List<Packet> subPackets = new ArrayList<>();
        int position = 0;
        if (lengthTypeId == SUB_PACKET_LENGTH_TYPE) {
            // Length represents the number of numbers of bits for all the sub packets
            while (position < length) {
                String subPacket = subPacketBinary.substring(position);
                PacketParseResult packetParseResult = parse(subPacket);
                position += packetParseResult.positionReadTo();
                subPackets.add(packetParseResult.packet());
            }
        } else {
            // length represents number of sub packets
            for (int subPacket = 0; subPacket < length; subPacket++) {
                PacketParseResult packetParseResult = parse(subPacketBinary.substring(position));
                position += packetParseResult.positionReadTo();
                subPackets.add(packetParseResult.packet());
            }
        }
        return new SubPacketParseResult(subPackets, position);
    }

    // Literal binary is groups of 5 bits. The first says if there are subsequent groups (1) or not (0).
    // The 2nd to 5th bit represent four more binary digits that make up the literal.
    private static LiteralParseResult parseLiteralValue(String literalBinary) {
        int position = 0;
        StringBuilder literalBuilder = new StringBuilder();
        boolean lastGroup = false;
        while (!lastGroup) {
            lastGroup = literalBinary.charAt(position) == '0';
            literalBuilder.append(literalBinary, position + 1, position + 5);
            position += 5;
        }
        return new LiteralParseResult(BinaryHelpers.binaryToLong(literalBuilder.toString()), position);
    }
}
