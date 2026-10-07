package Student;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentRegistration extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);

        Label title = new Label("Student Registration");

        Label idLabel = new Label("Student ID:");

        TextField idField = new TextField();
        idField.setPromptText("Enter student ID");

        Label nameLabel = new Label("Student Name:");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter student name");

        Label programLabel = new Label("Program:");

        ComboBox<String> programBox = new ComboBox<>();
        programBox.getItems().addAll(
                "ICT",
                "Computer Science",
                "Information Systems"
        );
        programBox.setPromptText("Select program");

        Button saveButton = new Button("Save");

        saveButton.setOnAction(event -> {
            String id = idField.getText();
            String name = nameField.getText();

            if (id.isBlank()) {
                title.setText("Student ID is required.");
                return;
            }

            if (name.isBlank()) {
                title.setText("Name is required.");
                return;
            }

            title.setText("Saved: " + name);
        });

        root.getChildren().addAll(
                title,
                idLabel,
                idField,
                nameLabel,
                nameField,
                programLabel,
                programBox,
                saveButton
        );

        Scene scene = new Scene(root, 400, 350);

        stage.setTitle("Student Registration");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}