package com.chip8.model.keyboard;

import com.chip8.configure.KeyboardConfigure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class KeyboardHandlerTest {

    @Mock
    private KeyboardConfigure keyboardConfigure;

    @InjectMocks
    private KeyboardHandler keyboardHandler;

    @Test
    void read_firstTry_returnThePressedKeyThatIsConfigured_test() {
        final ByteArrayInputStream testIn = new ByteArrayInputStream("A".getBytes());
        System.setIn(testIn);
        Mockito.when(this.keyboardConfigure.getKeys()).thenReturn(Collections.singletonList("A"));
        Assertions.assertEquals("A", this.keyboardHandler.readKeyPressed());
    }

    @Test
    void read_nonFirstInputIsEquals_returnThePressedKeyThatIsConfigured_test() {
        final ByteArrayInputStream testIn = new ByteArrayInputStream("C\nA".getBytes());
        System.setIn(testIn);
        Mockito.when(this.keyboardConfigure.getKeys()).thenReturn(Collections.singletonList("A"));
        Assertions.assertEquals("A", this.keyboardHandler.readKeyPressed());
    }

}
