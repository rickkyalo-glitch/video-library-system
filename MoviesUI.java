import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

public class MoviesUI extends Application {

    private final Map<String, List<String>> genreMovies = new HashMap<>();

    public void start(Stage stage) {
        ComboBox<String> genreCombo = new ComboBox<>();
        genreCombo.getItems().addAll("Action", "Comedy", "Drama", "Horror", "Sci-Fi");

        TextField nameField = new TextField();
        ComboBox<String> registeredCombo = new ComboBox<>();
        Button saveButton = new Button("Save Movie");
        Button removeButton = new Button("Remove Movie");

        genreCombo.setOnAction(e -> {
            registeredCombo.getItems().setAll(
                genreMovies.getOrDefault(genreCombo.getValue(), new ArrayList<>())
            );
        });

        saveButton.setOnAction(e -> {
            String genre = genreCombo.getValue();
            String name = nameField.getText().trim();
            if (genre != null && !name.isEmpty()) {
                genreMovies.computeIfAbsent(genre, k -> new ArrayList<>()).add(name);
                registeredCombo.getItems().add(name);
                nameField.clear();
            }
        });

        removeButton.setOnAction(e -> {
            String selected = registeredCombo.getValue();
            String genre = genreCombo.getValue();
            if (selected != null && genre != null) {
                genreMovies.get(genre).remove(selected);
                registeredCombo.getItems().remove(selected);
                registeredCombo.setValue(null);
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Genres:"), 0, 0);
        grid.add(genreCombo, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(saveButton, 1, 2);
        grid.add(new Label("Registered:"), 0, 3);
        grid.add(registeredCombo, 1, 3);
        grid.add(removeButton, 1, 4);

        stage.setTitle("Movies");
        stage.setScene(new Scene(grid, 400, 270));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
