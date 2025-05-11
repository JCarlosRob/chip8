package com.chip8.model.keyboard;

import com.chip8.api.keyboard.Keyboard;
import com.chip8.model.screen.KeysEnum;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class KeyboardHandler implements Keyboard {

//    @Autowired
//    private KeyboardConfigure keyboardConfigure;

    private String keyPressed = "";

    private ScheduledExecutorService executor;

    @Override
    public void setKeyPressed(final String keyPressed) {
        KeysEnum.findByRepresentation(keyPressed)
                .ifPresentOrElse(keysEnum ->
                                this.keyPressed = keysEnum.getRepresentation(),
                        () -> System.out.println("Tecla " + keyPressed + " no encontrada"));
    }

    @Override
    public Optional<String> readKeyPressed() {
        if (StringUtils.hasLength(this.keyPressed.trim())) {
            return Optional.of(this.keyPressed);
        }
        return Optional.empty();
    }

    @Override
    public void reset() {
        this.keyPressed = "";
    }
}
