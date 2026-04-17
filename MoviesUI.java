import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MoviesUI extends Application {

    private final Map<String, ComboBox<String>> genreMoviesMap = new HashMap<>();

    @Override
    public void start(Stage stage) {
        Label genreLabel = new Label("Genres:");
        Label nameLabel = new Label("Name:");
        Label registeredLabel = new Label("Registered:");

        ComboBox<String> genreCombo = new ComboBox<>();
        genreCombo.setPrefWidth(250);
        genreCombo.getItems().addAll("Action", "Comedy", "Drama", "Horror", "Sci-Fi");

        TextField nameField = new TextField();
        nameField.setPrefWidth(250);

        ComboBox<String> registeredCombo = new ComboBox<>();
        registeredCombo.setPrefWidth(250);

        Button saveButton = new Button("Save Movie");
        Button removeButton = new Button("Remove Movie");

        styleButton(saveButton);
        styleButton(removeButton);
        saveButton.setPrefWidth(250);
        removeButton.setPrefWidth(250);

        Font labelFont = Font.font("Serif", FontWeight.BOLD, 16);
        genreLabel.setFont(labelFont);
        nameLabel.setFont(labelFont);
        registeredLabel.setFont(labelFont);

        genreCombo.setOnAction(e -> {
            String selectedGenre = genreCombo.getValue();
            registeredCombo.getItems().clear();
            if (selectedGenre != null && genreMoviesMap.containsKey(selectedGenre)) {
                registeredCombo.getItems().addAll(genreMoviesMap.get(selectedGenre).getItems());
            }
        });

        saveButton.setOnAction(e -> {
            String genre = genreCombo.getValue();
            String movieName = nameField.getText().trim();
            if (genre != null && !movieName.isEmpty()) {
                genreMoviesMap.computeIfAbsent(genre, k -> new ComboBox<>()).getItems().add(movieName);
                if (genre.equals(genreCombo.getValue())) {
                    registeredCombo.getItems().add(movieName);
                }
                nameField.clear();
            }
        });

        removeButton.setOnAction(e -> {
            String selected = registeredCombo.getValue();
            String genre = genreCombo.getValue();
            if (selected != null && genre != null) {
                registeredCombo.getItems().remove(selected);
                if (genreMoviesMap.containsKey(genre)) {
                    genreMoviesMap.get(genre).getItems().remove(selected);
                }
                registeredCombo.setValue(null);
            }
        });

        GridPane grid = new GridPane();
        grid.setMinSize(500, 380);
        grid.setPadding(new Insets(30));
        grid.setVgap(12);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #F5F5F0; -fx-border-color: #5B8DB8; -fx-border-width: 2;");

        grid.add(genreLabel, 0, 0);
        grid.add(genreCombo, 1, 0);
        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(saveButton, 1, 2);
        grid.add(registeredLabel, 0, 3);
        grid.add(registeredCombo, 1, 3);
        grid.add(removeButton, 1, 4);

        Scene scene = new Scene(grid);
        stage.setTitle("Video Library System - Movies");
        stage.setScene(scene);
        stage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #1E5799; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 5;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #2980B9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 5;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #1E5799; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 5;"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
