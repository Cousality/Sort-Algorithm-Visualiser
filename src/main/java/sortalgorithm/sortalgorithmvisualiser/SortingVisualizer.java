package sortalgorithm.sortalgorithmvisualiser;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

public class SortingVisualizer extends Application {
    private Stage primaryStage;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int BAR_WIDTH = 20;
    private final int NUM_BARS = 25;
    private Rectangle[] bars;
    private int[] values;
    private Timeline timeline;
    private double sortingSpeed = 50; // milliseconds

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Sorting Algorithm Visualizer");
        showMainMenu();
        primaryStage.show();
    }

    private void showMainMenu() {
        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setPadding(new Insets(20));

        Label titleLabel = new Label("Sorting Algorithm Visualizer");
        titleLabel.setFont(new Font("Arial", 24));

        Button bubbleSortBtn = createMenuButton("Bubble Sort");
        Button quickSortBtn = createMenuButton("Quick Sort");
        Button mergeSortBtn = createMenuButton("Merge Sort");
        Button insertionSortBtn = createMenuButton("Insertion Sort");
        Button selectionSortBtn = createMenuButton("Selection Sort");

        menuLayout.getChildren().addAll(
                titleLabel,
                bubbleSortBtn,
                quickSortBtn,
                mergeSortBtn,
                insertionSortBtn,
                selectionSortBtn
        );

        Scene menuScene = new Scene(menuLayout, WIDTH, HEIGHT);
        primaryStage.setScene(menuScene);
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(40);
        button.setStyle("-fx-font-size: 14px;");
        button.setOnAction(_ -> showSortingScene(text));
        return button;
    }

    private void showSortingScene(String algorithm) {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        // Top controls
        HBox controls = new HBox(20);
        controls.setAlignment(Pos.CENTER);
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(_ -> {
            if (timeline != null) timeline.stop();
            showMainMenu();
        });

        Button startButton = new Button("Start");
        Button resetButton = new Button("Reset");

        // Speed slider


        controls.getChildren().addAll(backButton, startButton, resetButton);

        // Algorithm name
        Label algorithmLabel = new Label(algorithm + " Visualization");
        algorithmLabel.setFont(new Font("Arial", 20));

        // Visualization area
        Pane visualizationPane = new Pane();
        visualizationPane.setPrefSize(WIDTH, HEIGHT - 150);
        visualizationPane.setStyle("-fx-background-color: #f4f4f4;");

        // Initialize bars
        initializeBars(visualizationPane);

        // Button actions
        startButton.setOnAction(_ -> {
            if (timeline != null) timeline.stop();
            switch (algorithm) {
                case "Bubble Sort":
                    bubbleSort();
                    break;
                case "Quick Sort":
                    System.out.println("Quick Sort");
                    break;
                case "Merge Sort":
                    System.out.println("Merge Sort");
                    break;
                case "Insertion Sort":
                    System.out.println("Insertion Sort");
                    break;
                case "Selection Sort":
                    System.out.println("Selection Sort");
                    break;
            }
        });

        resetButton.setOnAction(_ -> {
            if (timeline != null) timeline.stop();
            initializeBars(visualizationPane);
        });

        layout.getChildren().addAll(controls, algorithmLabel, visualizationPane);
        Scene sortScene = new Scene(layout, WIDTH, HEIGHT);
        primaryStage.setScene(sortScene);
    }

    private void initializeBars(Pane pane) {
        pane.getChildren().clear();
        bars = new Rectangle[NUM_BARS];
        values = new int[NUM_BARS];
        Random random = new Random();

        for (int i = 0; i < NUM_BARS; i++) {
            values[i] = random.nextInt(HEIGHT - 200) + 50;
            bars[i] = new Rectangle();
            bars[i].setX(i * (BAR_WIDTH) + (WIDTH - NUM_BARS * (BAR_WIDTH + 2)) / 2);
            bars[i].setY(HEIGHT - 150 - values[i]);
            bars[i].setWidth(BAR_WIDTH);
            bars[i].setHeight(values[i]);
            bars[i].setFill(Color.BLUE);
            pane.getChildren().add(bars[i]);
        }
    }

    private void bubbleSort() {
        ArrayList<int[]> states = new ArrayList<>();
        int[] arr = values.clone();
        boolean swapped;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    states.add(arr.clone());
                    swapped = true;
                }
            }
            if (!swapped) {
                break;

            }
        }

        animateStates(states);
    }

    private void updateBars() {
        for (int i = 0; i < NUM_BARS; i++) {
            bars[i].setHeight(values[i]);
            bars[i].setY(HEIGHT - 150 - values[i]);
        }
    }

    private void animateStates(ArrayList<int[]> states) {
        timeline = new Timeline();
        for (int i = 0; i < states.size(); i++) {
            final int index = i;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(sortingSpeed * i),
                    _ -> {
                        values = states.get(index);
                        updateBars();
                    }
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.play();
    }
}