package com.chip8.model.core.buffer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@Import(DisplayBuffer.class)
class DisplayBufferTest {

    @Autowired
    private DisplayBuffer displayBuffer;

    @Test
    void read_inputCoordinatesAndByte_returnSameByte_test() {
        this.displayBuffer.write(1, 1, 1);
        final Integer result = this.displayBuffer.read(1, 1);
        Assertions.assertEquals(1, result);
    }

    @Test
    void read_inputXCoordinateNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.read(null, 1));
    }

    @Test
    void read_test_inputYCoordinateNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.read(1, null));
    }

    @Test
    void read_inputAllCoordinatesNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.read(null, null));
    }

    @Test
    void read_inputXCoordinateNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.read(-1, 0));
    }

    @Test
    void read_inputYCoordinateNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.read(0, -1));
    }

    @Test
    void read_inputAllCoordinatesNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.read(-1, -1));
    }

    @Test
    void read_inputXCoordinateGraterThan63_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.read(64, 0));
    }

    @Test
    void read_inputYCoordinateGraterThan31_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.read(0, 32));
    }

    @Test
    void read_inputXCoordinateGraterThan63AndYCoordinateGraterThan31_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.read(64, 32));
    }

    @Test
    void write_inputXCoordinateNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(null, 1, 1));
    }

    @Test
    void write_inputYCoordinateNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(1, null, 1));
    }

    @Test
    void write_inputDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(1, 1, null));
    }

    @Test
    void write_inputAllCoordinatesNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(null, null, 1));
    }

    @Test
    void write_inputAllCoordinatesAndDataNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(null, null, null));
    }

    @Test
    void write_inputXCoordinateNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(-1, 0, 1));
    }

    @Test
    void write_inputYCoordinateNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(0, -1, 1));
    }

    @Test
    void write_inputAllCoordinatesNegative_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(-1, -1, 1));
    }

    @Test
    void write_inputXCoordinateGraterThan63_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(64, 0, 1));
    }

    @Test
    void write_inputYCoordinateGraterThan31_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(0, 32, 1));
    }

    @Test
    void write_inputXCoordinateGraterThan63AndYCoordinateGraterThan31_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.displayBuffer.write(64, 32, 1));
    }

    @Test
    void reset_test() {
        final Integer[] rowExpected = {0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0};
        final Integer[][] expected = {rowExpected, rowExpected, rowExpected, rowExpected,
                rowExpected, rowExpected, rowExpected, rowExpected,
                rowExpected, rowExpected, rowExpected, rowExpected,
                rowExpected, rowExpected, rowExpected, rowExpected,
                rowExpected, rowExpected, rowExpected, rowExpected,
                rowExpected, rowExpected, rowExpected, rowExpected,
                rowExpected, rowExpected, rowExpected, rowExpected,
                rowExpected, rowExpected, rowExpected, rowExpected};
        this.displayBuffer.write(1, 1, 1);
        this.displayBuffer.reset();
        final Integer[][] result = this.displayBuffer.get();
        Assertions.assertTrue(Arrays.deepEquals(expected, result));
    }

}
