package Student;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public class StudentRegistration extends Application {

        @Override
        public void start(Stage stage) {

            VBox root = new VBox();

            Scene scene = new Scene(root, 500, 400);

            stage.setTitle("Student Registration");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
