package com.chip8.model.screen;

import com.chip8.api.core.buffer.Buffer;
import com.chip8.api.screen.Screen;
import com.chip8.configure.PrimaryStage;
import com.chip8.model.keyboard.KeyboardHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class ScreenHandler implements Screen {

    private final PrimaryStage primaryStage;

    private final Buffer displayBuffer;

    private final KeyboardHandler keyboardHandler;

    public ScreenHandler(final PrimaryStage primaryStage, final Buffer displayBuffer, final KeyboardHandler keyboardHandler) {
        this.primaryStage = primaryStage;
        this.displayBuffer = displayBuffer;
        this.keyboardHandler = keyboardHandler;
    }

    @Override
    public void init() {
        final Scene scene = this.createScene();
        this.startUpdateScene(scene);

        this.primaryStage.getStage().setTitle("Chip 8 Emulator");
        this.primaryStage.getStage().setScene(scene);
        this.primaryStage.getStage().show();
    }

    private Scene createScene() {
        final Pane pane = new Pane();
        pane.setStyle("-fx-background-color: black;");

        final Rectangle square = new Rectangle(0, 0, 10, 10);
        square.setFill(Color.WHITE);
        pane.getChildren().add(square);

        final Scene scene = new Scene(pane, 64 * 10, 32 * 10);
        scene.setOnKeyPressed(event -> this.keyboardHandler.setKeyPressed(event.getCode().getChar()));

        return scene;
    }

    private void startUpdateScene(final Scene scene) {
        new AnimationTimer() {
            @Override
            public void handle(final long l) {
                final Canvas canvas = new Canvas(640, 320);
                final GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.fillRect(0, 0, 640, 320);
                gc.setFill(Color.WHITE);

                final Pane pane = new Pane();
                pane.setStyle("-fx-background-color: black;");

                IntStream.range(0, ScreenHandler.this.displayBuffer.get()[0].length).forEach(y -> {
                    IntStream.range(0, ScreenHandler.this.displayBuffer.get().length).forEach(x -> {
                        if (ScreenHandler.this.displayBuffer.read(x, y).equals(1)) {
                            gc.fillRect(x * 10, y * 10, 10, 10);
                        }
                    });
                });

                final StackPane root = new StackPane(canvas);
                scene.setRoot(root);
            }
        }.start();
    }
}
