import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomersUI extends Application {

    public void start(Stage stage) {
        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();
        ComboBox<String> registeredCombo = new ComboBox<>();
        Button saveButton = new Button("Save Customer");
        Button removeButton = new Button("Remove Customer");

        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                registeredCombo.getItems().add(name + " | " + phoneField.getText() + " | " + emailField.getText());
                nameField.clear();
                phoneField.clear();
                emailField.clear();
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
        grid.add(new Label("Phone:"), 0, 1);
        grid.add(phoneField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(saveButton, 1, 3);
        grid.add(new Label("Registered:"), 0, 4);
        grid.add(registeredCombo, 1, 4);
        grid.add(removeButton, 1, 5);

        stage.setTitle("Customers");
        stage.setScene(new Scene(grid, 400, 300));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
