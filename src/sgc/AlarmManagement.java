package sgc;

import javafx.scene.layout.FlowPane;

/**
 * Class to manage the alarm system
 */
public class AlarmManagement {
    public FlowPane alarmMonitor;       //Place to monitor alarms
    public final String defaultCSS = "-fx-background-color: #404040;"+"-fx-border-color: white;";

    /**
     * Create a pane to monitor the alarm system
     */
    public AlarmManagement(){
        alarmMonitor = new FlowPane();
        alarmMonitor.setPrefSize(1000,100);
        alarmMonitor.setStyle(defaultCSS);
    }
}
