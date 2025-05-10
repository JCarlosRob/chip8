package com.chip8.model.core.instruction;

import com.chip8.api.core.register.IndexRegister;
import com.chip8.api.core.register.VRegister;
import com.chip8.model.core.sprite.SpritesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HexFormat;

@Component
public class SetILocationOfSpriteInstruction extends InstructionAbstract {

    private static final String COMMAND_REGEX = "^F\\w{1}29$";

    private final VRegister vRegister;

    private final IndexRegister indexRegister;

    @Autowired
    public SetILocationOfSpriteInstruction(final VRegister vRegister, final IndexRegister indexRegister) {
        super(COMMAND_REGEX);
        this.vRegister = vRegister;
        this.indexRegister = indexRegister;
    }

    @Override
    public void execute(final String opcode) {
        final Integer vData = this.vRegister.get(HexFormat.fromHexDigits(opcode.substring(1, 2)));
        SpritesEnum.findByRepresentation(vData).ifPresent(spritesEnum -> this.indexRegister.set(spritesEnum.getPositionInMemory()));
    }
}