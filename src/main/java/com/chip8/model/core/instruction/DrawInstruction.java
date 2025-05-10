package com.chip8.model.core.instruction;

import com.chip8.api.core.graphics.GraphicController;
import com.chip8.api.core.memory.Memory;
import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class DrawInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^D\\w{3}";

    private final VRegister vRegister;

    private final Memory memoryRam;

    private final IndexRegister indexRegister;

    private final GraphicController graphicController;

    public DrawInstruction(final VRegister vRegister, final Memory memoryRam, final IndexRegister indexRegister,
                           final GraphicController graphicController) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.memoryRam = memoryRam;
        this.indexRegister = indexRegister;
        this.graphicController = graphicController;
    }

    @Override
    public void execute(final String opcode) {
        final Integer vx = this.vRegister.get(HexFormat.fromHexDigits(opcode.substring(1, 2)));
        final Integer vy = this.vRegister.get(HexFormat.fromHexDigits(opcode.substring(2, 3)));
        final Integer[] sprite = this.memoryRam.read(this.indexRegister.get(), this.indexRegister.get() + HexFormat.fromHexDigits(opcode.substring(3)));

        this.graphicController.display(vx, vy, sprite);
    }

}
