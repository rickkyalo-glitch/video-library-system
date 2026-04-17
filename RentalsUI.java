import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

public class RentalsUI extends Application {

    private final Map<String, List<String>> borrowed = new HashMap<>();
    private final Map<String, List<String>> returned = new HashMap<>();

    public void start(Stage stage) {
        ComboBox<String> customerCombo = new ComboBox<>();
        customerCombo.getItems().addAll("Alice", "Bob", "Carol");

        ComboBox<String> genreCombo = new ComboBox<>();
        genreCombo.getItems().addAll("Action", "Comedy", "Drama");

        ComboBox<String> moviesCombo = new ComboBox<>();
        ComboBox<String> borrowedCombo = new ComboBox<>();
        ComboBox<String> returnedCombo = new ComboBox<>();

        Map<String, List<String>> genreMovies = new HashMap<>();
        genreMovies.put("Action", List.of("Die Hard", "Mad Max"));
        genreMovies.put("Comedy", List.of("Home Alone", "The Hangover"));
        genreMovies.put("Drama", List.of("The Godfather", "Shawshank"));

        genreCombo.setOnAction(e ->
            moviesCombo.getItems().setAll(genreMovies.getOrDefault(genreCombo.getValue(), List.of()))
        );

        customerCombo.setOnAction(e -> {
            String c = customerCombo.getValue();
            borrowedCombo.getItems().setAll(borrowed.getOrDefault(c, new ArrayList<>()));
            returnedCombo.getItems().setAll(returned.getOrDefault(c, new ArrayList<>()));
        });

        Button saveRental = new Button("Save Rental");
        saveRental.setOnAction(e -> {
            String c = customerCombo.getValue();
            String m = moviesCombo.getValue();
            if (c != null && m != null) {
                borrowed.computeIfAbsent(c, k -> new ArrayList<>()).add(m);
                borrowedCombo.getItems().add(m);
            }
        });

        Button returnMovie = new Button("Return Movie");
        returnMovie.setOnAction(e -> {
            String c = customerCombo.getValue();
            String m = borrowedCombo.getValue();
            if (c != null && m != null) {
                borrowed.get(c).remove(m);
                borrowedCombo.getItems().remove(m);
                returned.computeIfAbsent(c, k -> new ArrayList<>()).add(m);
                returnedCombo.getItems().add(m);
                borrowedCombo.setValue(null);
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Customer:"), 0, 0);  grid.add(customerCombo, 1, 0);
        grid.add(new Label("Genre:"), 0, 1);      grid.add(genreCombo, 1, 1);
        grid.add(new Label("Movies:"), 0, 2);     grid.add(moviesCombo, 1, 2);
        grid.add(saveRental, 1, 3);
        grid.add(new Label("Borrowed:"), 0, 4);   grid.add(borrowedCombo, 1, 4);
        grid.add(returnMovie, 1, 5);
        grid.add(new Label("Returned:"), 0, 6);   grid.add(returnedCombo, 1, 6);

        stage.setTitle("Rentals");
        stage.setScene(new Scene(grid, 400, 370));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
