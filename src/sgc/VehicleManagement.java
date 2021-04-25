package sgc;
import javafx.scene.layout.FlowPane;

/**
 * Class to manage vehicles
 */
public class VehicleManagement {
    public FlowPane vehicles;
    public final String defaultCSS = "-fx-background-color: #404040;"+"-fx-border-color: white;";

    /**
     * Create vehicle management object
     */
    public VehicleManagement() {
        vehicles = new FlowPane();
        vehicles.setPrefSize(400, 250);
        vehicles.setStyle(defaultCSS);
    }
}
