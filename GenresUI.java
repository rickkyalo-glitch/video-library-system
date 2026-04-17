import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GenresUI extends Application {

    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("Name:");
        Label registeredLabel = new Label("Registered:");

        TextField nameField = new TextField();
        nameField.setPrefWidth(250);

        ComboBox<String> registeredCombo = new ComboBox<>();
        registeredCombo.setPrefWidth(250);

        Button saveButton = new Button("Save");
        Button removeButton = new Button("Remove");

        styleButton(saveButton);
        styleButton(removeButton);
        saveButton.setPrefWidth(250);
        removeButton.setPrefWidth(250);

        nameLabel.setFont(Font.font("Serif", FontWeight.BOLD, 16));
        registeredLabel.setFont(Font.font("Serif", FontWeight.BOLD, 16));

        GridPane grid = new GridPane();
        grid.setMinSize(500, 320);
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setVgap(12);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #F5F5F0; -fx-border-color: #5B8DB8; -fx-border-width: 2;");

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(saveButton, 1, 1);
        grid.add(registeredLabel, 0, 2);
        grid.add(registeredCombo, 1, 2);
        grid.add(removeButton, 1, 3);

        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                registeredCombo.getItems().add(name);
                nameField.clear();
            }
        });

        removeButton.setOnAction(e -> {
            String selected = registeredCombo.getValue();
            if (selected != null) {
                registeredCombo.getItems().remove(selected);
                registeredCombo.setValue(null);
            }
        });

        Scene scene = new Scene(grid);
        stage.setTitle("Video Library System - Genres");
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
