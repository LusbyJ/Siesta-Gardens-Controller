package sgc;
import javafx.scene.layout.FlowPane;

/**
 * Class to create and manage the kiosk
 */
public class Kiosk {
    public FlowPane kiosk;
    public final String defaultCSS = "-fx-background-color: #404040;"+"-fx-border-color: white;";

    /**
     * Creates the kiosk object
     * @return
     */
    public Kiosk(){
        kiosk = new FlowPane();
        kiosk.setPrefSize(400,250);
        kiosk.setStyle(defaultCSS);
    }
}
