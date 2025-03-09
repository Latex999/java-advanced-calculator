package com.calculator.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.util.Objects;

/**
 * Main JavaFX Application class for the calculator.
 * Sets up the primary stage and initializes the UI.
 */
public class CalculatorApp extends Application {

    private static final String APP_TITLE = "Advanced Java Calculator";
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        // Create the main calculator UI
        CalculatorUI calculatorUI = new CalculatorUI();
        
        // Set up the scene
        Scene scene = new Scene(calculatorUI.getRoot(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        // Add CSS styling
        String cssPath = "/styles/calculator.css";
        scene.getStylesheets().add(
            Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm()
        );
        
        // Configure the primary stage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(500);
        
        // Set application icon
        // primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/calculator-icon.png")));
        
        // Show the stage
        primaryStage.show();
    }
}