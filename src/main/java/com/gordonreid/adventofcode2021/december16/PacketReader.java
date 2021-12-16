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
    }

    @AllArgsConstructor
    @Getter
    public enum Operation {
        SUM(args -> Arrays.stream(args).sum()),
        PRODUCT(args -> Arrays.stream(args).reduce(1, (a, b) -> a * b)),
        MIN(args -> Arrays.stream(args).min().orElse(0)),
        MAX(args -> Arrays.stream(args).max().orElse(0)),
        GT(args -> args[0] > args[1] ? 1L : 0L),
        LT(args -> args[0] < args[1] ? 1L : 0L),
        EQ(args -> args[0] == args[1] ? 1L : 0L);

        private final Function<long[], Long> operation;

        public static Operation forTypeId(int typeId) {
            return switch (typeId) {
                case 0 -> SUM;
                case 1 -> PRODUCT;
                case 2 -> MIN;
                case 3 -> MAX;
                case 5 -> GT;
                case 6 -> LT;
                case 7 -> EQ;
                default -> throw new IllegalStateException("Unexpected value: " + typeId);
            };
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    @Value
    public static class OperatorPacket extends Packet {
        int lengthTypeId;
        int payloadLength;
        Operation operation;
        List<Packet> subPackets;

        public OperatorPacket(int version, int typeId, int lengthTypeId, int payloadLength, List<Packet> subPackets) {
            super(version, typeId);
            this.lengthTypeId = lengthTypeId;
            this.payloadLength = payloadLength;
            this.operation = Operation.forTypeId(typeId);
            this.subPackets = subPackets;
        }
    }

    public record ParseResult(Packet packet, int positionReadTo) {
    }

    public record LiteralValue(long value, int positionReadTo) {
    }

    public Packet read(String packetInHex) {
        return parse(HexHelpers.toBinaryString(packetInHex)).packet();
    }

    public ParseResult parse(String packetInBinary) {
        int position = 0;
        int version = BinaryHelpers.binaryToInt(packetInBinary.substring(position, position + VERSION_BIT_LENGTH));
        position += VERSION_BIT_LENGTH;
        int typeId = BinaryHelpers.binaryToInt(packetInBinary.substring(position, position + PACKAGE_TYPE_BIT_LENGTH));
        position += PACKAGE_TYPE_BIT_LENGTH;
        if (typeId == LITERAL_PACKET_TYPE_ID) {
            LiteralValue literalValue = readLiteralValue(packetInBinary.substring(position));
            long value = literalValue.value();
            position += literalValue.positionReadTo();
            return new ParseResult(new LiteralPacket(version, typeId, value), position);
        } else {
            int lengthTypeId = BinaryHelpers.binaryToInt(packetInBinary.substring(position, position + 1));
            position++;
            int lengthBinaryDigitCount = lengthTypeId == 0 ? 15 : 11;
            int length = BinaryHelpers.binaryToInt(packetInBinary.substring(position, position + lengthBinaryDigitCount));
            position += lengthBinaryDigitCount;
            List<Packet> subPackets = new ArrayList<>();
            if (lengthTypeId == 0) {
                int packetEnd = position + length;
                while (position < packetEnd) {
                    String subpacket = packetInBinary.substring(position);
                    ParseResult parseResult = parse(subpacket);
                    position += parseResult.positionReadTo();
                    subPackets.add(parseResult.packet());
                }
            } else {
                for (int subPacket = 0; subPacket < length; subPacket++) {
                    ParseResult parseResult = parse(packetInBinary.substring(position));
                    position += parseResult.positionReadTo();
                    subPackets.add(parseResult.packet());
                }
            }
            return new ParseResult(new OperatorPacket(version, typeId, lengthTypeId, length, subPackets), position);
        }
    }

    private static LiteralValue readLiteralValue(String payloadBinary) {
        int position = 0;
        StringBuilder literalBuilder = new StringBuilder();
        boolean lastGroup = false;
        while (!lastGroup) {
            lastGroup = payloadBinary.charAt(position) == '0';
            literalBuilder.append(payloadBinary, position + 1, position + 5);
            position += 5;
        }
        return new LiteralValue(BinaryHelpers.binaryToLong(literalBuilder.toString()), position);
    }
}
