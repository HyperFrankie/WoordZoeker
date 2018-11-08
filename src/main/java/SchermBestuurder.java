import com.asprise.ocr.Ocr;
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
    }

    public static void postInit() {
        SchermStart.stage.setMaximized(true);
    }

	public void openBestandKiezer() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Kies afbeelding");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Afbeeldingen", "*.img", "*.png", "*.jpg"));
		File geselecteerdBestand = fileChooser.showOpenDialog(SchermStart.stage);

		System.out.println(geselecteerdBestand.toString());

		Ocr.setUp();
		Ocr ocr = new Ocr(); // create a new OCR engine
		ocr.startEngine("eng", Ocr.SPEED_SLOW); // English
		String s = ocr.recognize(new File[] {geselecteerdBestand},
				Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT); // PLAINTEXT | XML | PDF | RTF
		System.out.println("Result: " + s);
		ocr.stopEngine();
	}

}
