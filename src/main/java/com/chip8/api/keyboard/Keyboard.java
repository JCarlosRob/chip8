package com.chip8.api.keyboard;

import java.util.Optional;

public interface Keyboard {

    void setKeyPressed(final String keyPressed);

    Optional<String> readKeyPressed();

    void reset();
}
