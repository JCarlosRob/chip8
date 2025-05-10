package com.chip8.model;

import com.chip8.api.Chip8;
import com.chip8.api.core.Cpu;
import com.chip8.api.core.Loader;
import com.chip8.model.screen.ScreenHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Chip8Handler implements Chip8 {

    private final Loader loader;

    private final Cpu cpu;

    private final ScreenHandler screenHandler;


    public Chip8Handler(final Loader loader, final Cpu cpu, final ScreenHandler screenHandler) {
        this.loader = loader;
        this.cpu = cpu;
        this.screenHandler = screenHandler;
    }


    @Override
    public void start() throws IOException, InterruptedException {
        this.loader.pathRom("src/main/resources/roms/PONG");
        this.loader.loadSprites();
        this.screenHandler.init();
        this.cpu.run();
    }

    @Override
    public void stop() {
        this.cpu.stop();
    }
}
