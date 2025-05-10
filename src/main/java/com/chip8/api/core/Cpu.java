package com.chip8.api.core;

import java.io.IOException;

public interface Cpu {

    void run() throws IOException, InterruptedException;

    void stop();

}
