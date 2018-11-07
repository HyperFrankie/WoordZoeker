import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class SchermBestuurder {

    @FXML
    AnchorPane scherm;
    @FXML
    TabPane tabPane;
    @FXML
    AnchorPane tab1Pane;

    @FXML
    public void initialize() {
        tab1Pane.getChildren().add(new WoordzoekerTabel(10, 10, 40, 40));
    }

    public static void postInit() {
        SchermStart.stage.setMaximized(true);
    }


}
