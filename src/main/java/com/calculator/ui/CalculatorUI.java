package com.calculator.ui;

import com.calculator.core.CalculatorEngine;
import com.calculator.model.CalculationHistory;
import com.calculator.model.CalculatorMode;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Main UI class for the calculator application.
 * Handles the creation and layout of all UI components.
 */
public class CalculatorUI {
    
    // Core calculator components
    private final CalculatorEngine calculatorEngine;
    private final CalculationHistory history;
    
    // UI components
    private final BorderPane root;
    private TextField displayField;
    private Label memoryIndicator;
    private VBox historyPanel;
    private TabPane modeTabPane;
    
    // Current calculator state
    private CalculatorMode currentMode = CalculatorMode.STANDARD;
    private boolean darkModeEnabled = false;
    
    /**
     * Constructor initializes the calculator UI and its components.
     */
    public CalculatorUI() {
        // Initialize calculator engine and history
        calculatorEngine = new CalculatorEngine();
        history = new CalculationHistory();
        
        // Create the root layout
        root = new BorderPane();
        root.setPadding(new Insets(10));
        
        // Set up the UI components
        setupDisplayArea();
        setupModeSelector();
        setupKeypadArea();
        setupHistoryPanel();
        setupMenuBar();
    }
    
    /**
     * Returns the root node of the calculator UI.
     */
    public Parent getRoot() {
        return root;
    }
    
    /**
     * Sets up the display area at the top of the calculator.
     */
    private void setupDisplayArea() {
        VBox displayArea = new VBox(5);
        displayArea.setPadding(new Insets(10));
        
        // Memory indicator
        memoryIndicator = new Label("M: 0");
        memoryIndicator.setVisible(false);
        
        // Main display field
        displayField = new TextField("0");
        displayField.setAlignment(Pos.CENTER_RIGHT);
        displayField.setEditable(true);
        displayField.setFont(Font.font("Monospaced", FontWeight.BOLD, 24));
        displayField.setPrefHeight(60);
        
        // Add components to display area
        displayArea.getChildren().addAll(memoryIndicator, displayField);
        
        // Add display area to the top of the root layout
        root.setTop(displayArea);
    }
    
    /**
     * Sets up the mode selector tabs.
     */
    private void setupModeSelector() {
        modeTabPane = new TabPane();
        
        // Create tabs for different calculator modes
        Tab standardTab = new Tab("Standard");
        standardTab.setClosable(false);
        
        Tab scientificTab = new Tab("Scientific");
        scientificTab.setClosable(false);
        
        Tab programmerTab = new Tab("Programmer");
        programmerTab.setClosable(false);
        
        Tab conversionTab = new Tab("Conversions");
        conversionTab.setClosable(false);
        
        // Add tabs to the tab pane
        modeTabPane.getTabs().addAll(standardTab, scientificTab, programmerTab, conversionTab);
        
        // Set up tab change listener
        modeTabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab == standardTab) {
                setCalculatorMode(CalculatorMode.STANDARD);
            } else if (newTab == scientificTab) {
                setCalculatorMode(CalculatorMode.SCIENTIFIC);
            } else if (newTab == programmerTab) {
                setCalculatorMode(CalculatorMode.PROGRAMMER);
            } else if (newTab == conversionTab) {
                setCalculatorMode(CalculatorMode.CONVERSION);
            }
        });
    }
    
    /**
     * Sets up the keypad area with buttons.
     */
    private void setupKeypadArea() {
        // Main container for keypad and mode tabs
        VBox centerArea = new VBox(10);
        centerArea.setPadding(new Insets(10));
        
        // Add mode tabs to the center area
        centerArea.getChildren().add(modeTabPane);
        
        // Create keypad grid
        GridPane keypad = createStandardKeypad();
        
        // Add keypad to the center area
        centerArea.getChildren().add(keypad);
        
        // Set the center area as the center of the root layout
        root.setCenter(centerArea);
    }
    
    /**
     * Creates the standard calculator keypad.
     */
    private GridPane createStandardKeypad() {
        GridPane keypad = new GridPane();
        keypad.setHgap(5);
        keypad.setVgap(5);
        
        // Define button styles
        String numberButtonStyle = "-fx-font-size: 18px; -fx-background-radius: 5;";
        String operatorButtonStyle = "-fx-font-size: 18px; -fx-background-color: #f0f0f0; -fx-background-radius: 5;";
        String equalsButtonStyle = "-fx-font-size: 18px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5;";
        String clearButtonStyle = "-fx-font-size: 18px; -fx-background-color: #f44336; -fx-text-fill: white; -fx-background-radius: 5;";
        
        // Create number buttons
        for (int i = 0; i < 10; i++) {
            Button numButton = createButton(String.valueOf(i), numberButtonStyle);
            
            // Position the buttons in a calculator layout (0 at the bottom)
            int row, col;
            if (i == 0) {
                row = 4;
                col = 1;
            } else {
                row = 3 - (i - 1) / 3;
                col = (i - 1) % 3;
            }
            
            keypad.add(numButton, col, row);
        }
        
        // Create operator buttons
        Button addButton = createButton("+", operatorButtonStyle);
        Button subtractButton = createButton("-", operatorButtonStyle);
        Button multiplyButton = createButton("×", operatorButtonStyle);
        Button divideButton = createButton("÷", operatorButtonStyle);
        Button equalsButton = createButton("=", equalsButtonStyle);
        Button clearButton = createButton("C", clearButtonStyle);
        Button clearEntryButton = createButton("CE", clearButtonStyle);
        Button backspaceButton = createButton("⌫", operatorButtonStyle);
        Button decimalButton = createButton(".", numberButtonStyle);
        Button negateButton = createButton("±", operatorButtonStyle);
        
        // Add operator buttons to the grid
        keypad.add(addButton, 3, 3);
        keypad.add(subtractButton, 3, 2);
        keypad.add(multiplyButton, 3, 1);
        keypad.add(divideButton, 3, 0);
        keypad.add(equalsButton, 3, 4);
        keypad.add(clearButton, 0, 0);
        keypad.add(clearEntryButton, 1, 0);
        keypad.add(backspaceButton, 2, 0);
        keypad.add(decimalButton, 2, 4);
        keypad.add(negateButton, 0, 4);
        
        // Set column and row constraints for equal sizing
        for (int i = 0; i < 4; i++) {
            ColumnConstraints colConstraint = new ColumnConstraints();
            colConstraint.setHgrow(Priority.ALWAYS);
            colConstraint.setFillWidth(true);
            keypad.getColumnConstraints().add(colConstraint);
            
            RowConstraints rowConstraint = new RowConstraints();
            rowConstraint.setVgrow(Priority.ALWAYS);
            rowConstraint.setFillHeight(true);
            keypad.getRowConstraints().add(rowConstraint);
        }
        
        // Set minimum size for the keypad
        keypad.setMinSize(300, 300);
        
        return keypad;
    }
    
    /**
     * Helper method to create a button with the specified text and style.
     */
    private Button createButton(String text, String style) {
        Button button = new Button(text);
        button.setStyle(style);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setPrefSize(60, 60);
        
        // Add action handler
        button.setOnAction(event -> handleButtonPress(text));
        
        return button;
    }
    
    /**
     * Sets up the history panel on the right side.
     */
    private void setupHistoryPanel() {
        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setPrefWidth(200);
        
        // History header
        Label historyLabel = new Label("History");
        historyLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        
        // History list
        historyPanel = new VBox(5);
        ScrollPane scrollPane = new ScrollPane(historyPanel);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);
        
        // Clear history button
        Button clearHistoryButton = new Button("Clear History");
        clearHistoryButton.setMaxWidth(Double.MAX_VALUE);
        clearHistoryButton.setOnAction(event -> clearHistory());
        
        // Add components to the right panel
        rightPanel.getChildren().addAll(historyLabel, scrollPane, clearHistoryButton);
        
        // Add the right panel to the root layout
        root.setRight(rightPanel);
    }
    
    /**
     * Sets up the menu bar at the top of the application.
     */
    private void setupMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        // File menu
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> System.exit(0));
        fileMenu.getItems().add(exitItem);
        
        // Edit menu
        Menu editMenu = new Menu("Edit");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        editMenu.getItems().addAll(copyItem, pasteItem);
        
        // View menu
        Menu viewMenu = new Menu("View");
        CheckMenuItem darkModeItem = new CheckMenuItem("Dark Mode");
        darkModeItem.setOnAction(event -> toggleDarkMode(darkModeItem.isSelected()));
        viewMenu.getItems().add(darkModeItem);
        
        // Help menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        helpMenu.getItems().add(aboutItem);
        
        // Add menus to the menu bar
        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, helpMenu);
        
        // Create a VBox to hold the menu bar and the existing top component
        VBox topContainer = new VBox();
        topContainer.getChildren().addAll(menuBar, root.getTop());
        
        // Set the new container as the top component
        root.setTop(topContainer);
    }
    
    /**
     * Handles button presses from the calculator keypad.
     */
    private void handleButtonPress(String buttonText) {
        // Implementation will depend on the calculator engine
        // This is a placeholder for the actual implementation
        switch (buttonText) {
            case "C":
                displayField.setText("0");
                break;
            case "CE":
                displayField.setText("0");
                break;
            case "⌫":
                String currentText = displayField.getText();
                if (currentText.length() > 1) {
                    displayField.setText(currentText.substring(0, currentText.length() - 1));
                } else {
                    displayField.setText("0");
                }
                break;
            case "=":
                try {
                    String expression = displayField.getText();
                    double result = calculatorEngine.evaluate(expression);
                    displayField.setText(formatResult(result));
                    addToHistory(expression + " = " + formatResult(result));
                } catch (Exception e) {
                    displayField.setText("Error");
                }
                break;
            default:
                // For numbers and operators
                if (displayField.getText().equals("0") && !buttonText.equals(".")) {
                    displayField.setText(buttonText);
                } else {
                    displayField.setText(displayField.getText() + buttonText);
                }
                break;
        }
    }
    
    /**
     * Formats the calculation result for display.
     */
    private String formatResult(double result) {
        // Remove trailing zeros for integer results
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%.8f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }
    
    /**
     * Adds an entry to the calculation history.
     */
    private void addToHistory(String entry) {
        history.addEntry(entry);
        updateHistoryPanel();
    }
    
    /**
     * Updates the history panel with the current history entries.
     */
    private void updateHistoryPanel() {
        historyPanel.getChildren().clear();
        for (String entry : history.getEntries()) {
            Label historyEntry = new Label(entry);
            historyEntry.setMaxWidth(Double.MAX_VALUE);
            historyEntry.setPadding(new Insets(5));
            historyEntry.setStyle("-fx-border-color: #e0e0e0; -fx-border-radius: 3;");
            
            // Make history entries clickable to recall them
            historyEntry.setOnMouseClicked(event -> {
                String expression = entry.split(" = ")[0];
                displayField.setText(expression);
            });
            
            historyPanel.getChildren().add(historyEntry);
        }
    }
    
    /**
     * Clears the calculation history.
     */
    private void clearHistory() {
        history.clear();
        historyPanel.getChildren().clear();
    }
    
    /**
     * Toggles dark mode for the calculator UI.
     */
    private void toggleDarkMode(boolean enabled) {
        darkModeEnabled = enabled;
        
        if (enabled) {
            root.setStyle("-fx-background-color: #2d2d2d;");
            displayField.setStyle("-fx-background-color: #3d3d3d; -fx-text-fill: white; -fx-font-family: 'Monospaced'; -fx-font-weight: bold; -fx-font-size: 24px;");
        } else {
            root.setStyle("");
            displayField.setStyle("-fx-font-family: 'Monospaced'; -fx-font-weight: bold; -fx-font-size: 24px;");
        }
    }
    
    /**
     * Changes the calculator mode and updates the UI accordingly.
     */
    private void setCalculatorMode(CalculatorMode mode) {
        currentMode = mode;
        
        // Update UI based on the selected mode
        // This is a placeholder for the actual implementation
    }
}