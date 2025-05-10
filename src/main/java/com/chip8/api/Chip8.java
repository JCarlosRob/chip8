package com.chip8.api;

import java.io.IOException;

public interface Chip8 {

    void start() throws IOException, InterruptedException;

    void stop();

}
