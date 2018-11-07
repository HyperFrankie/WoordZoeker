import javafx.scene.layout.GridPane;

public class WoordzoekerTabel extends GridPane {

    public WoordzoekerTabel(int rijAantal, int kolomAantal, double breedte, double hoogte) {
        LetterVeld.standardBreedte = breedte;
        LetterVeld.standardHoogte = hoogte;
        for(int x = 0; x < kolomAantal; x++) {
            for(int y = 0; y < rijAantal; y++) {
                add(new LetterVeld("A", y + 1 == rijAantal, x + 1 == kolomAantal), x, y);
            }
        }
    }

}
