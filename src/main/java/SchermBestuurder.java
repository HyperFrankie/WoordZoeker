import com.asprise.ocr.Ocr;
import com.sun.istack.internal.Nullable;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;

public class SchermBestuurder {

    @FXML
    AnchorPane scherm;
    @FXML
    TabPane tabPane;
    @FXML
    AnchorPane tab1Pane;
    @FXML
	ImageView tab1ImgView;

    @FXML
    public void initialize() {
//        tab1Pane.getChildren().add(new WoordzoekerTabel(30, 30, 20, 20));
    }

    public static void postInit() {
        SchermStart.stage.setMaximized(true);
    }

	public void openBestandKiezer() throws MalformedURLException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Kies afbeelding");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Afbeeldingen", "*.img", "*.png", "*.jpg"));
		File geselecteerdBestand = fileChooser.showOpenDialog(SchermStart.stage);

		System.out.println(geselecteerdBestand.toString());
        Image afbeelding = new Image("file:///" + geselecteerdBestand.getPath());
        tab1ImgView.setImage(afbeelding);
        tab1ImgView.prefWidth(afbeelding.getWidth());
        tab1ImgView.prefHeight(afbeelding.getHeight());
        tab1ImgView.setFitWidth(afbeelding.getWidth());
        tab1ImgView.setFitHeight(afbeelding.getHeight());
	}

	@FXML
    public void applyFilters() {
        applyFilters(tab1ImgView.getImage(), tab1ImgView);
    }

    public void applyFilters(Image afbeelding, ImageView laag) {
        applyGrayScale(afbeelding, laag);
        applyBlur(afbeelding, laag);
    }

    @FXML
    public void applyGrayScale() {
        applyGrayScale(tab1ImgView.getImage(), tab1ImgView);
    }

    public void applyGrayScale(Image afbeelding, ImageView laag) {
        PixelReader pxReader = afbeelding.getPixelReader();
        double h = 0, width = afbeelding.getWidth(), height = afbeelding.getHeight();
        WritableImage afbeelding1 = new WritableImage((int) width, (int) height);
        PixelWriter pxWriter = afbeelding1.getPixelWriter();

        for(int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double brightness = pxReader.getColor(x, y).getBrightness();
                pxWriter.setColor(x, y, new Color(brightness, brightness, brightness, 1.0));
            }
        }
        laag.setImage(afbeelding1);
    }

    @FXML
    public void applyBlur() {
        applyBlur(tab1ImgView.getImage(), tab1ImgView);
    }

	public void applyBlur(Image afbeelding, ImageView laag) {
        PixelReader pxReader = afbeelding.getPixelReader();
        double r = 0, g = 0, b = 0, a = 0, totalWeight = 0, width = afbeelding.getWidth(), height = afbeelding.getHeight();
        WritableImage afbeelding1 = new WritableImage((int) width, (int) height);
        PixelWriter pxWriter = afbeelding1.getPixelWriter();

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                for(int xf = -1; xf < 2; xf++) {
                    for (int yf = -1; yf < 2; yf++) {
                        if(x + xf >= 0 && x + xf < width && y + yf >= 0 && y + yf < height) {
                            r += pxReader.getColor(x + xf, y + yf).getRed();
                            g += pxReader.getColor(x + xf, y + yf).getGreen();
                            b += pxReader.getColor(x + xf, y + yf).getBlue();
                            a += pxReader.getColor(x + xf, y + yf).getOpacity();
                            totalWeight += 1;
                        }
                    }
                }
                pxWriter.setColor(
                        x, y,
                        new Color(
                                r / totalWeight,
                                g / totalWeight,
                                b / totalWeight,
                                a / totalWeight));
                r = 0;
                g = 0;
                b = 0;
                a = 0;
                totalWeight = 0;
            }
        }

        laag.setImage(afbeelding1);


        /*Ocr.setUp();
		Ocr ocr = new Ocr(); // create a new OCR engine
		ocr.startEngine("eng", Ocr.SPEED_SLOW); // English
		String s = ocr.recognize(new File[] {geselecteerdBestand},
				Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT); // PLAINTEXT | XML | PDF | RTF
		System.out.println("Result: " + s);
		ocr.stopEngine();*/
    }

}
