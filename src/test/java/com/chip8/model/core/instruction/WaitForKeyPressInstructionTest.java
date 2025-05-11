package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import com.chip8.api.keyboard.Keyboard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class WaitForKeyPressInstructionTest {

    @Mock
    private VRegister vRegister;

    @Mock
    private Keyboard keyboard;

    @InjectMocks
    private WaitForKeyPressInstruction waitForKeyPressInstruction;

    @Test
    void isExecutable_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.waitForKeyPressInstruction.isExecutable(null));
    }

    @Test
    void isExecutable_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.waitForKeyPressInstruction.isExecutable(""));
    }

    @Test
    void isExecutable_inputCommand_returnFalse_test() {
        Assertions.assertFalse(this.waitForKeyPressInstruction.isExecutable("0000"));
    }

    @Test
    void isExecutable_inputCommand_returnTrue_test() {
        Assertions.assertTrue(this.waitForKeyPressInstruction.isExecutable("FA0A"));
    }

    @Test
    void run_inputNull_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.waitForKeyPressInstruction.run(null));
    }

    @Test
    void run_inputEmpty_returnException_test() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> this.waitForKeyPressInstruction.run(""));
    }

    @Test
    void run_test() {
        Mockito.when(this.keyboard.readKeyPressed()).thenReturn(Optional.of("B"));
        this.waitForKeyPressInstruction.run("FA0A");
        Mockito.verify(this.vRegister, Mockito.times(1)).set(10, 11);
    }

}
