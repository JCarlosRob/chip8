package com.chip8.model.core.instruction;

import com.chip8.api.core.register.TimerRegister;
import com.chip8.api.core.register.VRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class LoadDelayTimerToVInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}07$";

    private final VRegister vRegister;

    private final TimerRegister delayTimerRegister;

    @Autowired
    public LoadDelayTimerToVInstruction(final VRegister vRegister, final TimerRegister delayTimerRegister) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.delayTimerRegister = delayTimerRegister;
    }

    @Override
    public void execute(final String opcode) {
        this.vRegister.set(HexFormat.fromHexDigits(opcode.substring(1, 2)), this.delayTimerRegister.get());
    }
}