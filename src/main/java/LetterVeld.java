import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class LetterVeld extends Pane {

    public static double standardBreedte = 10.0, standardHoogte = 10.0;
    public Label label;

    public LetterVeld(String letter, double breedte, double hoogte, boolean onderRand, boolean rechterRand) {
        label = new Label(letter);
        label.setAlignment(Pos.CENTER);
        getChildren().add(label);
        setGrootte(breedte, hoogte);
        setStyle("-fx-border-style: solid " + (rechterRand ? "solid" : "none") + " " + (onderRand ? "solid" : "none") + " solid; -fx-border-width: 1;");
    }

    public LetterVeld(String letter, boolean onderRand, boolean rechterRand) {
        this(letter, standardBreedte, standardHoogte, onderRand, rechterRand);
    }

    public LetterVeld(String letter) {
        this(letter,true, true);
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