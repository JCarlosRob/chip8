package com.chip8.model.core.instruction;

import com.chip8.api.core.register.TimerRegister;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class SetSoundTimerInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}18$";

    private final VRegister vRegister;

    private final TimerRegister soundTimerRegister;

    @Autowired
    public SetSoundTimerInstruction(final VRegister vRegister, final TimerRegister soundTimerRegister) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.soundTimerRegister = soundTimerRegister;
    }

    @Override
    public void execute(final String opcode) {
        this.soundTimerRegister.set(this.vRegister.get(HexFormat.fromHexDigits(opcode.substring(1, 2))));
    }
}