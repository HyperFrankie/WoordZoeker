import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class WoordzoekerTabel extends GridPane {

    public WoordzoekerTabel(int rijAantal, int kolomAantal, double breedte, double hoogte) {
        setAlignment(Pos.CENTER);
        setGridLinesVisible(true);
        LetterVeld.standardBreedte = breedte;
        LetterVeld.standardHoogte = hoogte;
        for(int x = 0; x < kolomAantal; x++) {
            for(int y = 0; y < rijAantal; y++) {
                add(new LetterVeld("A"), x, y);
            }
        }
    }

}
