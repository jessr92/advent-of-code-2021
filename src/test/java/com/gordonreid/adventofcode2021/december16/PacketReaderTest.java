package com.gordonreid.adventofcode2021.december16;

import com.google.common.collect.ImmutableList;
import com.gordonreid.adventofcode2021.december16.PacketReader.LiteralPacket;
import com.gordonreid.adventofcode2021.december16.PacketReader.OperatorPacket;
import com.gordonreid.adventofcode2021.december16.PacketReader.Packet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PacketReaderTest {

    @Test
    public void read_givenLiteralPacket() {
        String givenLiteralPacket = "D2FE28";

        Packet expected = new LiteralPacket(6, 4, 2021);
        Packet result = PacketReader.read(givenLiteralPacket);

        assertEquals(expected, result);
    }

    @Test
    public void read_givenFirstOperatorPacket() {
        String givenOperatorPacket = "38006F45291200";

        Packet expected = new OperatorPacket(1, 6, 0, 27, ImmutableList.of(
                new LiteralPacket(6, 4, 10),
                new LiteralPacket(2, 4, 20)
        ));
        Packet result = PacketReader.read(givenOperatorPacket);

        assertEquals(expected, result);
    }

    @Test
    public void read_givenSecondOperatorPacket() {
        String givenOperatorPacket = "EE00D40C823060";

        Packet expected = new OperatorPacket(7, 3, 1, 3, ImmutableList.of(
                new LiteralPacket(2, 4, 1),
                new LiteralPacket(4, 4, 2),
                new LiteralPacket(1, 4, 3)
        ));
        Packet result = PacketReader.read(givenOperatorPacket);

        assertEquals(expected, result);
    }

    @Test
    public void read_givenThirdOperatorPacket() {
        String givenOperatorPacket = "8A004A801A8002F478";

        Packet expected = new OperatorPacket(4, 2, 1, 1, ImmutableList.of(
                new OperatorPacket(1, 2, 1, 1, ImmutableList.of(
                        new OperatorPacket(5, 2, 0, 11, ImmutableList.of(
                                new LiteralPacket(6, 4, 15)
                        ))
                ))
        ));
        Packet result = PacketReader.read(givenOperatorPacket);

        assertEquals(expected, result);
    }

    @Test
    public void read_givenFourthOperatorPacket() {
        String givenOperatorPacket = "620080001611562C8802118E34";

        Packet expected = new OperatorPacket(3, 0, 1, 2, ImmutableList.of(
                new OperatorPacket(0, 0, 0, 22, ImmutableList.of(
                        new LiteralPacket(0, 4, 10),
                        new LiteralPacket(5, 4, 11)
                )),
                new OperatorPacket(1, 0, 1, 2, ImmutableList.of(
                        new LiteralPacket(0, 4, 12),
                        new LiteralPacket(3, 4, 13)
                ))
        ));
        Packet result = PacketReader.read(givenOperatorPacket);

        assertEquals(expected, result);
    }

    @Test
    public void read_givenFifthOperatorPacket() {
        String givenOperatorPacket = "C0015000016115A2E0802F182340";

        Packet expected = new OperatorPacket(6, 0, 0, 84, ImmutableList.of(
                new OperatorPacket(0, 0, 0, 22, ImmutableList.of(
                        new LiteralPacket(0, 4, 10),
                        new LiteralPacket(6, 4, 11)
                )),
                new OperatorPacket(4, 0, 1, 2, ImmutableList.of(
                        new LiteralPacket(7, 4, 12),
                        new LiteralPacket(0, 4, 13)
                ))
        ));
        Packet result = PacketReader.read(givenOperatorPacket);

        assertEquals(expected, result);
    }

    @Test
    public void read_givenSixthOperatorPacket() {
        String givenOperatorPacket = "A0016C880162017C3686B18A3D4780";

        Packet expected = new OperatorPacket(5, 0, 0, 91, ImmutableList.of(
                new OperatorPacket(1, 0, 1, 1, ImmutableList.of(
                        new OperatorPacket(3, 0, 1, 5, ImmutableList.of(
                                new LiteralPacket(7, 4, 6),
                                new LiteralPacket(6, 4, 6),
                                new LiteralPacket(5, 4, 12),
                                new LiteralPacket(2, 4, 15),
                                new LiteralPacket(2, 4, 15)
                        ))
                ))
        ));
        Packet result = PacketReader.read(givenOperatorPacket);

        assertEquals(expected, result);
    }
}