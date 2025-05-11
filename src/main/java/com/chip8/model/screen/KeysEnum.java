package com.chip8.model.screen;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum KeysEnum {
    KEY_0("0"),
    KEY_1("1"),
    KEY_2("2"),
    KEY_3("3"),
    KEY_4("4"),
    KEY_5("5"),
    KEY_6("6"),
    KEY_7("7"),
    KEY_8("8"),
    KEY_9("9"),
    KEY_A("A"),
    KEY_B("B"),
    KEY_C("C"),
    KEY_D("D"),
    KEY_E("E"),
    KEY_F("F");

    private final String representation;

    public static Optional<KeysEnum> findByRepresentation(final String representation) {
        return Arrays.stream(KeysEnum.values()).filter(keysEnum -> keysEnum.getRepresentation().equals(representation)).findFirst();
    }
}
