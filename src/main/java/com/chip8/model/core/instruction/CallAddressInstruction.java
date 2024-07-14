package com.chip8.model.core.instruction;

import com.chip8.api.core.memory.MemoryStack;
import com.chip8.api.core.register.ProgramCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class CallAddressInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^2\\w{3}";

    private final ProgramCounter pc;

    private final MemoryStack memoryStack;

    @Autowired
    public CallAddressInstruction(final ProgramCounter pc, final MemoryStack memoryStack) {
        super(COMMAND_REGEX);
        this.pc = pc;
        this.memoryStack = memoryStack;
    }

    @Override
    public void execute(final String data) {
        this.memoryStack.push(this.pc.get());
        this.pc.set(HexFormat.fromHexDigits(data.substring(1)));
    }

}