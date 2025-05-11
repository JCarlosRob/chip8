package com.chip8.api.core;

public interface Cpu {

    void run() throws InterruptedException;

    void stop();

}
