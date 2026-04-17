import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GenresUI extends Application {

    public void start(Stage stage) {
        TextField nameField = new TextField();
        ComboBox<String> registeredCombo = new ComboBox<>();
        Button saveButton = new Button("Save");
        Button removeButton = new Button("Remove");

        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                registeredCombo.getItems().add(name);
                nameField.clear();
            }
        });

        removeButton.setOnAction(e -> {
            registeredCombo.getItems().remove(registeredCombo.getValue());
            registeredCombo.setValue(null);
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(saveButton, 1, 1);
        grid.add(new Label("Registered:"), 0, 2);
        grid.add(registeredCombo, 1, 2);
        grid.add(removeButton, 1, 3);

        stage.setTitle("Genres");
        stage.setScene(new Scene(grid, 400, 220));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
