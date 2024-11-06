package sortalgorithm.sortalgorithmvisualiser;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;
import java.util.Random;
import javafx.scene.paint.Color;

public class SortingVisualizer extends Application {
    private Stage primaryStage;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int NUM_BARS = 50;
    private final int BAR_WIDTH = 10;
    private int[] values;
    private Rectangle[] bars;

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

        // Title
        Label titleLabel = new Label("Sorting Algorithm Visualizer");
        titleLabel.setFont(new Font("Arial", 24));

        // Create buttons for each sorting algorithm
        Button bubbleSortBtn = createMenuButton("Bubble Sort");
        Button quickSortBtn = createMenuButton("Quick Sort");
        Button mergeSortBtn = createMenuButton("Merge Sort");
        Button insertionSortBtn = createMenuButton("Insertion Sort");
        Button selectionSortBtn = createMenuButton("Selection Sort");

        menuLayout.getChildren().addAll(titleLabel, bubbleSortBtn, quickSortBtn, mergeSortBtn, insertionSortBtn, selectionSortBtn
        );

        Scene menuScene = new Scene(menuLayout, WIDTH, HEIGHT);
        primaryStage.setScene(menuScene);
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(40);
        button.setStyle("-fx-font-size: 14px;");

        // Add click event handler
        button.setOnAction(_ -> showSortingScene(text));

        return button;
    }

    private void showSortingScene(String algorithm) {
        // Create a basic layout for the sorting visualization
        StackPane sortLayout = new StackPane();

        // Add a back button



        // Add algorithm name label
        Label algorithmLabel = new Label(algorithm + " Visualization");
        algorithmLabel.setFont(new Font("Arial", 20));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(20));

        HBox controls = new HBox(20);
        controls.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(_ -> showMainMenu());
        Button startButton = new Button("Start");


        Pane visualizationPane = new Pane();
        visualizationPane.setPrefSize(WIDTH, HEIGHT-1000);
        visualizationPane.setStyle("-fx-background-color: #f4f4f4;");
        sortLayout.getChildren().add(layout);

        // Create and set the scene
        Scene sortScene = new Scene(sortLayout, WIDTH, HEIGHT);
        primaryStage.setScene(sortScene);

        createBars(visualizationPane);

        startButton.setOnAction(_ -> {
            switch (algorithm){
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
            }});
        controls.getChildren().addAll(backButton,startButton);
        layout.getChildren().addAll(algorithmLabel, visualizationPane, controls);


    }

    private void createBars(Pane pane){
        pane.getChildren().clear();
        bars = new Rectangle[NUM_BARS];
        Random random = new Random();
        values = new int[NUM_BARS];

        for (int i = 0; i < NUM_BARS; i++) {
            values[i] = random.nextInt(HEIGHT - 200) + 50;
            bars[i] = new Rectangle();
            bars[i].setX(i * (BAR_WIDTH) + (WIDTH - NUM_BARS * (BAR_WIDTH + 2)) / 2);
            bars[i].setY(HEIGHT-100 - values[i]);
            bars[i].setWidth(BAR_WIDTH);
            bars[i].setHeight(values[i]);
            bars[i].setFill(Color.DARKGRAY);
            pane.getChildren().add(bars[i]);
        }
    }

    private void bubbleSort(){
        System.out.println("Bubble Sort");

    }

    public static void main(String[] args) {
        launch(args);
    }
}