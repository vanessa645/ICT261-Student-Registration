package Student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentRegistration extends Application {

    // Keeps track of successful registrations
    private int registrationCount = 0;

    @Override
    public void start(Stage stage) {

        GridPane root = new GridPane();

        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20));

        // Title
        Label title = new Label("Student Registration");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Status message
        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-font-size: 14px;");

        // Registration counter
        Label counterLabel = new Label("Students Registered: 0");
        counterLabel.setStyle(
                "-fx-font-size: 14px; -fx-font-weight: bold;"
        );

        // Student ID
        Label idLabel = new Label("Student ID:");
        TextField idField = new TextField();
        idField.setPromptText("Enter student ID");
        idField.setPrefWidth(280);

        // Student Name
        Label nameLabel = new Label("Student Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter student name");
        nameField.setPrefWidth(280);

        // Date of Birth
        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobPicker = new DatePicker();
        dobPicker.setPrefWidth(280);

        // Gender
        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll(
                "Male",
                "Female",
                "Other"
        );
        genderBox.setPromptText("Select gender");
        genderBox.setPrefWidth(280);

        // Email
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email address");
        emailField.setPrefWidth(280);

        // Phone Number
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter phone number");
        phoneField.setPrefWidth(280);

        // Program
        Label programLabel = new Label("Program:");
        ComboBox<String> programBox = new ComboBox<>();
        programBox.getItems().addAll(
                "ICT",
                "Computer Science",
                "Information Systems"
        );
        programBox.setPromptText("Select program");
        programBox.setPrefWidth(280);

        // Buttons
        Button saveButton = new Button("Save");
        Button clearButton = new Button("Clear");
        Button exitButton = new Button("Exit");

        saveButton.setPrefWidth(100);
        clearButton.setPrefWidth(100);
        exitButton.setPrefWidth(100);

        saveButton.setStyle("-fx-font-weight: bold;");
        clearButton.setStyle("-fx-font-weight: bold;");
        exitButton.setStyle("-fx-font-weight: bold;");

        // Save button
        saveButton.setOnAction(event -> {

            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String gender = genderBox.getValue();
            String program = programBox.getValue();

            // Student ID validation
            if (id.isBlank()) {
                showError(statusLabel, "Student ID is required.");
                return;
            }

            if (!id.matches("\\d+")) {
                showError(
                        statusLabel,
                        "Student ID must contain digits only."
                );
                return;
            }

            // Name validation
            if (name.isBlank()) {
                showError(statusLabel, "Name is required.");
                return;
            }

            if (name.length() < 2) {
                showError(
                        statusLabel,
                        "Name must contain at least 2 characters."
                );
                return;
            }

            // Date of birth validation
            if (dobPicker.getValue() == null) {
                showError(
                        statusLabel,
                        "Date of birth is required."
                );
                return;
            }

            if (dobPicker.getValue().isAfter(
                    java.time.LocalDate.now())) {

                showError(
                        statusLabel,
                        "Date of birth cannot be in the future."
                );
                return;
            }

            // Gender validation
            if (gender == null) {
                showError(
                        statusLabel,
                        "Please select a gender."
                );
                return;
            }

            // Email validation
            if (email.isBlank()) {
                showError(
                        statusLabel,
                        "Email is required."
                );
                return;
            }

            if (!email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                showError(
                        statusLabel,
                        "Enter a valid email address."
                );
                return;
            }

            // Phone validation
            if (phone.isBlank()) {
                showError(
                        statusLabel,
                        "Phone number is required."
                );
                return;
            }

            if (!phone.matches("\\d{10}")) {
                showError(
                        statusLabel,
                        "Phone number must contain exactly 10 digits."
                );
                return;
            }

            // Program validation
            if (program == null) {
                showError(
                        statusLabel,
                        "Please select a program."
                );
                return;
            }

            // Increase registration count
            registrationCount++;

            counterLabel.setText(
                    "Students Registered: " + registrationCount
            );

            // Successful registration
            statusLabel.setStyle(
                    "-fx-font-size: 14px; " +
                            "-fx-text-fill: green; " +
                            "-fx-font-weight: bold;"
            );

            statusLabel.setText(
                    "Registered Successfully: " + name +
                            " | ID: " + id +
                            " | DOB: " + dobPicker.getValue() +
                            " | Gender: " + gender +
                            " | Program: " + program +
                            " | Email: " + email +
                            " | Phone: " + phone
            );
        });

        // Clear button with confirmation
        clearButton.setOnAction(event -> {

            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to clear all the information?",
                    ButtonType.YES,
                    ButtonType.NO
            );

            alert.setTitle("Clear Form");
            alert.setHeaderText(
                    "Clear Student Registration Form"
            );

            alert.showAndWait().ifPresent(response -> {

                if (response == ButtonType.YES) {

                    idField.clear();
                    nameField.clear();
                    dobPicker.setValue(null);
                    genderBox.setValue(null);
                    emailField.clear();
                    phoneField.clear();
                    programBox.setValue(null);

                    statusLabel.setStyle(
                            "-fx-font-size: 14px;"
                    );

                    statusLabel.setText("");
                }
            });
        });

        // Exit button with confirmation
        exitButton.setOnAction(event -> confirmExit(stage));

        // X button with confirmation
        stage.setOnCloseRequest(event -> {

            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to exit the application?",
                    ButtonType.YES,
                    ButtonType.NO
            );

            alert.setTitle("Exit Application");
            alert.setHeaderText(
                    "Exit Student Registration"
            );

            alert.showAndWait().ifPresent(response -> {

                if (response != ButtonType.YES) {
                    event.consume();
                }
            });
        });

        // Layout
        root.add(title, 0, 0, 3, 1);
        root.add(statusLabel, 0, 1, 3, 1);
        root.add(counterLabel, 0, 2, 3, 1);

        root.add(idLabel, 0, 3);
        root.add(idField, 1, 3);

        root.add(nameLabel, 0, 4);
        root.add(nameField, 1, 4);

        root.add(dobLabel, 0, 5);
        root.add(dobPicker, 1, 5);

        root.add(genderLabel, 0, 6);
        root.add(genderBox, 1, 6);

        root.add(emailLabel, 0, 7);
        root.add(emailField, 1, 7);

        root.add(phoneLabel, 0, 8);
        root.add(phoneField, 1, 8);

        root.add(programLabel, 0, 9);
        root.add(programBox, 1, 9);

        root.add(saveButton, 0, 10);
        root.add(clearButton, 1, 10);
        root.add(exitButton, 2, 10);

        Scene scene = new Scene(root, 650, 480);

        stage.setTitle("Student Registration");
        stage.setScene(scene);
        stage.show();
    }

    // Displays validation errors
    private void showError(Label statusLabel, String message) {

        statusLabel.setStyle(
                "-fx-font-size: 14px; " +
                        "-fx-text-fill: red; " +
                        "-fx-font-weight: bold;"
        );

        statusLabel.setText(message);
    }

    // Exit confirmation
    private void confirmExit(Stage stage) {

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to exit the application?",
                ButtonType.YES,
                ButtonType.NO
        );

        alert.setTitle("Exit Application");
        alert.setHeaderText(
                "Exit Student Registration"
        );

        alert.showAndWait().ifPresent(response -> {

            if (response == ButtonType.YES) {
                stage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}