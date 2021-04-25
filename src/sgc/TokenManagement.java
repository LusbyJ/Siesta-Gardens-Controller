package sgc;
import javafx.scene.layout.FlowPane;

/**
 * Class to manage tokens
 */
public class TokenManagement {
    public FlowPane visitorLog;
    public final String defaultCSS = "-fx-background-color: #404040;" + "-fx-border-color: white;";

    /**
     * Create the log for active tokens/visitors
     */
    public TokenManagement(){
        visitorLog = new FlowPane();
        visitorLog.setPrefSize(1000,200);
        visitorLog.setStyle(defaultCSS);
    }
}
