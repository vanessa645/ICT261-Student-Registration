package Student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentRegistration extends Application {

    @Override
    public void start(Stage stage) {

        GridPane root = new GridPane();

        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20));

        Label title = new Label("Student Registration");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label idLabel = new Label("Student ID:");
        TextField idField = new TextField();
        idField.setPromptText("Enter student ID");
        idField.setPrefWidth(250);

        Label nameLabel = new Label("Student Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter student name");
        nameField.setPrefWidth(250);

        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobPicker = new DatePicker();
        dobPicker.setPrefWidth(250);

        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll(
                "Male",
                "Female",
                "Other"
        );
        genderBox.setPromptText("Select gender");
        genderBox.setPrefWidth(250);

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email address");
        emailField.setPrefWidth(250);

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter phone number");
        phoneField.setPrefWidth(250);

        Label programLabel = new Label("Program:");
        ComboBox<String> programBox = new ComboBox<>();
        programBox.getItems().addAll(
                "ICT",
                "Computer Science",
                "Information Systems"
        );
        programBox.setPromptText("Select program");
        programBox.setPrefWidth(250);

        Button saveButton = new Button("Save");
        Button clearButton = new Button("Clear");

        saveButton.setStyle("-fx-font-weight: bold;");
        clearButton.setStyle("-fx-font-weight: bold;");

        saveButton.setOnAction(event -> {

            String id = idField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String gender = genderBox.getValue();
            String program = programBox.getValue();

            if (id.isBlank()) {
                title.setText("Student ID is required.");
                return;
            }

            if (name.isBlank()) {
                title.setText("Name is required.");
                return;
            }

            if (dobPicker.getValue() == null) {
                title.setText("Date of birth is required.");
                return;
            }

            if (gender == null) {
                title.setText("Please select a gender.");
                return;
            }

            if (email.isBlank()) {
                title.setText("Email is required.");
                return;
            }

            if (phone.isBlank()) {
                title.setText("Phone number is required.");
                return;
            }

            if (program == null) {
                title.setText("Please select a program.");
                return;
            }

            title.setText(
                    "Registered: " + name +
                            " | ID: " + id +
                            " | Program: " + program
            );
        });

        clearButton.setOnAction(event -> {
            idField.clear();
            nameField.clear();
            dobPicker.setValue(null);
            genderBox.setValue(null);
            emailField.clear();
            phoneField.clear();
            programBox.setValue(null);
            title.setText("Student Registration");
        });

        root.add(title, 0, 0, 2, 1);

        root.add(idLabel, 0, 1);
        root.add(idField, 1, 1);

        root.add(nameLabel, 0, 2);
        root.add(nameField, 1, 2);

        root.add(dobLabel, 0, 3);
        root.add(dobPicker, 1, 3);

        root.add(genderLabel, 0, 4);
        root.add(genderBox, 1, 4);

        root.add(emailLabel, 0, 5);
        root.add(emailField, 1, 5);

        root.add(phoneLabel, 0, 6);
        root.add(phoneField, 1, 6);

        root.add(programLabel, 0, 7);
        root.add(programBox, 1, 7);

        root.add(saveButton, 0, 8);
        root.add(clearButton, 1, 8);

        Scene scene = new Scene(root, 550, 450);

        stage.setTitle("Student Registration");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}