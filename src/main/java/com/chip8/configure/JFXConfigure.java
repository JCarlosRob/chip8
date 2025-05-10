package com.chip8.configure;

import com.chip8.api.Chip8;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class JFXConfigure extends Application {

    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        this.springContext = SpringApplication.run(SpringConfigure.class);
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.springContext.getBean(PrimaryStage.class).setStage(primaryStage);
        this.springContext.getBean(Chip8.class).start();
    }

    @Override
    public void stop() {
        this.springContext.getBean(Chip8.class).stop();
        this.springContext.close();
        Platform.exit();
    }

}