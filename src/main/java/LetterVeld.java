import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public class LetterVeld extends Pane {

    public static double standardBreedte = 10.0, standardHoogte = 10.0;
    public Label label;

    public LetterVeld(String letter, double breedte, double hoogte) {
        label = new Label(letter);
        label.setTextAlignment(TextAlignment.CENTER);
        this.setGrootte(breedte, hoogte);
        this.getChildren().add(label);
    }

    public LetterVeld(String letter) {
        this(letter, standardBreedte, standardHoogte);
    }

    public void setLetter(String letter) {
        label.setText(letter);
    }

    public void setBreedte(double breedte) {
        this.setPrefWidth(breedte);
        label.setPrefWidth(breedte);
    }

    public void setHoogte(double hoogte) {
        this.setPrefHeight(hoogte);
        label.setPrefHeight(hoogte);
    }

    public void setGrootte(double breedte, double hoogte) {
        this.setPrefSize(breedte, hoogte);
        label.setPrefSize(hoogte, breedte);
    }

}