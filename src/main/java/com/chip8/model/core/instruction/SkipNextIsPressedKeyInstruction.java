package com.chip8.model.core.instruction;

import com.chip8.api.core.register.ProgramCounter;
import com.chip8.api.core.register.VRegister;
import com.chip8.api.keyboard.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class SkipNextIsPressedKeyInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^E\\w{1}9E$";

    private final ProgramCounter pc;

    private final VRegister vRegister;

    private final Keyboard keyboard;

    @Autowired
    public SkipNextIsPressedKeyInstruction(final ProgramCounter pc, final VRegister vRegister, final Keyboard keyboard) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.vRegister = vRegister;
        this.keyboard = keyboard;
    }

    @Override
    public void execute(final String opcode) {
        final Integer data = this.vRegister.get(HexFormat.fromHexDigits(opcode.substring(1, 2)));
        final String dataHex = HexFormat.of().toHexDigits(data).substring(7);
        final String keyPressed = this.keyboard.readKeyPressed().orElse("");
        if (dataHex.equalsIgnoreCase(keyPressed)) {
            this.pc.next(2);
            this.keyboard.reset();
        }
    }
}