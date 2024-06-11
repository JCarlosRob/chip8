package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HexFormat;

@ExtendWith(MockitoExtension.class)
class ShiftRightInstructionTest {

    @Mock
    private VRegister vRegister;

    @InjectMocks
    private ShiftRightInstruction shiftRightInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.shiftRightInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.shiftRightInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.shiftRightInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.shiftRightInstruction.isExecutable("8FF6"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.shiftRightInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.shiftRightInstruction.run(""));
    }

    @Test
    void run_vfSet1_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(1);
        this.shiftRightInstruction.run("8AF2");
        Mockito.verify(this.vRegister, Mockito.times(0))
                .set(HexFormat.fromHexDigits("A"), 1);
        Mockito.verify(this.vRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("F"), 1);
    }

    @Test
    void run_withoutCarry_test() {
        Mockito.when(this.vRegister.get(HexFormat.fromHexDigits("A"))).thenReturn(2);
        this.shiftRightInstruction.run("8AF2");
        Mockito.verify(this.vRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("A"), 1);
        Mockito.verify(this.vRegister, Mockito.times(1))
                .set(HexFormat.fromHexDigits("F"), 0);
    }


}
