import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public class WoordzoekerTabel extends GridPane {

    public WoordzoekerTabel(int rijAantal, int kolomAantal, double breedte, double hoogte) {
        LetterVeld.standardBreedte = breedte;
        LetterVeld.standardHoogte = hoogte;
        for(int x = 0; x < kolomAantal; x++) {
            for(int y = 0; y < rijAantal; y++) {
                this.add(new LetterVeld("A"), x, y);

            }
        }
    }

}
