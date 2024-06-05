package com.chip8.configure;

import com.chip8.model.core.register.VectorRegisterHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfigure {

    @Bean("vectorXRegister")
    public VectorRegisterHandler vectorXRegister() {
        return new VectorRegisterHandler();
    }

    @Bean("vectorYRegister")
    public VectorRegisterHandler vectorYRegister() {
        return new VectorRegisterHandler();
    }

    @Bean
    public Random random() {
        return new Random();
    }
}
