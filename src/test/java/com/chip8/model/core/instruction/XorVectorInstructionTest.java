package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VectorRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class XorVectorInstructionTest {

    @Mock
    private VectorRegister vectorXRegister;

    @Mock
    private VectorRegister vectorYRegister;

    private XorVectorInstruction orVectorInstruction;

    @BeforeEach
    void setUp() {
        this.orVectorInstruction = new XorVectorInstruction(this.vectorXRegister, this.vectorYRegister);
    }

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.orVectorInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.orVectorInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.orVectorInstruction.isExecutable("8000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.orVectorInstruction.isExecutable("8FF3"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.orVectorInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.orVectorInstruction.run(""));
    }

    @Test
    void run_test() {
        Mockito.when(this.vectorXRegister.getVRegister(HexFormat.fromHexDigits("A"))).thenReturn(1);
        Mockito.when(this.vectorYRegister.getVRegister(HexFormat.fromHexDigits("F"))).thenReturn(2);
        this.orVectorInstruction.run("8AF2");
        Mockito.verify(this.vectorXRegister, Mockito.times(1))
                .setVRegister(HexFormat.fromHexDigits("A"), 3);
    }

}
