import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class SchermBestuurder {

    @FXML
    AnchorPane scherm;
    @FXML
    TabPane tabPane;
    @FXML
    AnchorPane tab1Pane;

    @FXML
    public void initialize() {
        tab1Pane.getChildren().add(new WoordzoekerTabel(30, 30, 20, 20));
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.img", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(SchermStart.stage);
    }

    public static void postInit() {
        SchermStart.stage.setMaximized(true);
    }


}
