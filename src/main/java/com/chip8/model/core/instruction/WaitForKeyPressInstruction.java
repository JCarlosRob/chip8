package com.chip8.model.core.instruction;

import com.chip8.api.core.register.VRegister;
import com.chip8.api.keyboard.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class WaitForKeyPressInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}0A$";

    private final VRegister vRegister;

    private final Keyboard keyboard;

    @Autowired
    public WaitForKeyPressInstruction(final VRegister vRegister, final Keyboard keyboard) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.keyboard = keyboard;
    }

    @Override
    public void execute(final String opcode) {
        final String keyPressed = this.keyboard.readKeyPressed().orElse("");
        this.vRegister.set(HexFormat.fromHexDigits(opcode.substring(1, 2)), HexFormat.fromHexDigits(keyPressed));
        this.keyboard.reset();
    }
}