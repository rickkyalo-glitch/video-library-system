import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CustomersUI extends Application {

    @Override
    public void start(Stage stage) {
        Label nameLabel = new Label("Name:");
        Label phoneLabel = new Label("Phone:");
        Label emailLabel = new Label("Email:");
        Label registeredLabel = new Label("Registered:");

        TextField nameField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();
        nameField.setPrefWidth(250);
        phoneField.setPrefWidth(250);
        emailField.setPrefWidth(250);

        ComboBox<String> registeredCombo = new ComboBox<>();
        registeredCombo.setPrefWidth(250);

        Button saveButton = new Button("Save Customer");
        Button removeButton = new Button("Remove Customer");

        styleButton(saveButton);
        styleButton(removeButton);
        saveButton.setPrefWidth(250);
        removeButton.setPrefWidth(250);

        Font labelFont = Font.font("Serif", FontWeight.BOLD, 16);
        nameLabel.setFont(labelFont);
        phoneLabel.setFont(labelFont);
        emailLabel.setFont(labelFont);
        registeredLabel.setFont(labelFont);

        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            if (!name.isEmpty()) {
                String entry = name + " | " + phone + " | " + email;
                registeredCombo.getItems().add(entry);
                nameField.clear();
                phoneField.clear();
                emailField.clear();
            }
        });

        removeButton.setOnAction(e -> {
            String selected = registeredCombo.getValue();
            if (selected != null) {
                registeredCombo.getItems().remove(selected);
                registeredCombo.setValue(null);
            }
        });

        GridPane grid = new GridPane();
        grid.setMinSize(500, 420);
        grid.setPadding(new Insets(30));
        grid.setVgap(12);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-background-color: #F5F5F0; -fx-border-color: #5B8DB8; -fx-border-width: 2;");

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(phoneLabel, 0, 1);
        grid.add(phoneField, 1, 1);
        grid.add(emailLabel, 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(saveButton, 1, 3);
        grid.add(registeredLabel, 0, 4);
        grid.add(registeredCombo, 1, 4);
        grid.add(removeButton, 1, 5);

        Scene scene = new Scene(grid);
        stage.setTitle("Video Library System - Customers");
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
