import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SchermStart extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static Stage stage;
    public static SchermBestuurder mainScreenController;

    @Override
    public void start(Stage s) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Scherm.fxml"));
        Scene scene = new Scene(root);
        stage = s;
        stage.setScene(scene);
        stage.setTitle("Woordzoeker");
        stage.show();
    }
}