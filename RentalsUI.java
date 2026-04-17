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

public class RentalsUI extends Application {

    private final Map<String, Map<String, ComboBox<String>>> genreMovieMap = new HashMap<>();
    private final Map<String, ComboBox<String>> borrowedMap = new HashMap<>();
    private final Map<String, ComboBox<String>> returnedMap = new HashMap<>();

    @Override
    public void start(Stage stage) {
        Label customerLabel = new Label("Customer:");
        Label genreLabel = new Label("Genre:");
        Label moviesLabel = new Label("Movies:");
        Label borrowedLabel = new Label("Borrowed:");
        Label returnedLabel = new Label("Returned:");

        ComboBox<String> customerCombo = new ComboBox<>();
        customerCombo.setPrefWidth(250);
        customerCombo.getItems().addAll("Alice", "Bob", "Carol");

        ComboBox<String> genreCombo = new ComboBox<>();
        genreCombo.setPrefWidth(250);
        genreCombo.getItems().addAll("Action", "Comedy", "Drama", "Horror", "Sci-Fi");

        ComboBox<String> moviesCombo = new ComboBox<>();
        moviesCombo.setPrefWidth(250);

        ComboBox<String> borrowedCombo = new ComboBox<>();
        borrowedCombo.setPrefWidth(250);

        ComboBox<String> returnedCombo = new ComboBox<>();
        returnedCombo.setPrefWidth(250);

        Button saveRentalButton = new Button("Save Rental");
        Button returnMovieButton = new Button("Return Movie");

        styleButton(saveRentalButton);
        styleButton(returnMovieButton);
        saveRentalButton.setPrefWidth(250);
        returnMovieButton.setPrefWidth(250);

        Font labelFont = Font.font("Serif", FontWeight.BOLD, 16);
        customerLabel.setFont(labelFont);
        genreLabel.setFont(labelFont);
        moviesLabel.setFont(labelFont);
        borrowedLabel.setFont(labelFont);
        returnedLabel.setFont(labelFont);

        Map<String, String[]> sampleMovies = new HashMap<>();
        sampleMovies.put("Action", new String[]{"Die Hard", "Mad Max", "John Wick"});
        sampleMovies.put("Comedy", new String[]{"Home Alone", "The Hangover"});
        sampleMovies.put("Drama", new String[]{"The Godfather", "Shawshank Redemption"});
        sampleMovies.put("Horror", new String[]{"The Shining", "Get Out"});
        sampleMovies.put("Sci-Fi", new String[]{"Interstellar", "The Matrix"});

        genreCombo.setOnAction(e -> {
            String genre = genreCombo.getValue();
            moviesCombo.getItems().clear();
            if (genre != null && sampleMovies.containsKey(genre)) {
                moviesCombo.getItems().addAll(sampleMovies.get(genre));
            }
        });

        customerCombo.setOnAction(e -> {
            String customer = customerCombo.getValue();
            borrowedCombo.getItems().clear();
            returnedCombo.getItems().clear();
            if (customer != null) {
                if (borrowedMap.containsKey(customer)) {
                    borrowedCombo.getItems().addAll(borrowedMap.get(customer).getItems());
                }
                if (returnedMap.containsKey(customer)) {
                    returnedCombo.getItems().addAll(returnedMap.get(customer).getItems());
                }
            }
        });

        saveRentalButton.setOnAction(e -> {
            String customer = customerCombo.getValue();
            String movie = moviesCombo.getValue();
            if (customer != null && movie != null) {
                borrowedMap.computeIfAbsent(customer, k -> new ComboBox<>()).getItems().add(movie);
                borrowedCombo.getItems().add(movie);
            }
        });

        returnMovieButton.setOnAction(e -> {
            String customer = customerCombo.getValue();
            String movie = borrowedCombo.getValue();
            if (customer != null && movie != null) {
                borrowedCombo.getItems().remove(movie);
                if (borrowedMap.containsKey(customer)) {
                    borrowedMap.get(customer).getItems().remove(movie);
                }
                returnedCombo.getItems().add(movie);
                returnedMap.computeIfAbsent(customer, k -> new ComboBox<>()).getItems().add(movie);
                borrowedCombo.setValue(null);
            }
        });

        GridPane grid = new GridPane();
        grid.setMinSize(500, 520);
        grid.setPadding(new Insets(30));
        grid.setVgap(12);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #F5F5F0; -fx-border-color: #5B8DB8; -fx-border-width: 2;");

        grid.add(customerLabel, 0, 0);
        grid.add(customerCombo, 1, 0);
        grid.add(genreLabel, 0, 1);
        grid.add(genreCombo, 1, 1);
        grid.add(moviesLabel, 0, 2);
        grid.add(moviesCombo, 1, 2);
        grid.add(saveRentalButton, 1, 3);
        grid.add(borrowedLabel, 0, 4);
        grid.add(borrowedCombo, 1, 4);
        grid.add(returnMovieButton, 1, 5);
        grid.add(returnedLabel, 0, 6);
        grid.add(returnedCombo, 1, 6);

        Scene scene = new Scene(grid);
        stage.setTitle("Video Library System - Rentals");
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
