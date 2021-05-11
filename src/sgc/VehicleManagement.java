package sgc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Class to manage vehicles
 */
public class VehicleManagement {
    public BorderPane vehicles;
    public static VBox carPane1;
    public static VBox carPane2;
    public final String defaultCSS = "-fx-background-color: #404040;"+"-fx-border-color: white;";

    /**
     * Create vehicle management object pane
     */
    public VehicleManagement() {
        vehicles = new BorderPane();
        vehicles.setPrefSize(400, 250);
        vehicles.setStyle(defaultCSS);
        String car1 = "Car 1 Passengers";
        String car2 = "Car 2 Passengers";

        //create a text label for the panes
        Text labels = new CentralManagement().createMessage("\n\t\t\t\tVehicle Manager\n\n"+car1+"\t\t\t"+car2, 1);
        vehicles.setTop(labels);

        //create a VBox to hold passenger ids for car 1
        carPane1 = new VBox();
        carPane1.setPrefSize(200,150);
        vehicles.setLeft(carPane1);

        //create a VBox to hold passenger ids for car 2
        carPane2 = new VBox();
        carPane2.setPrefSize(200,150);
        vehicles.setRight(carPane2);



    }

    public void manageVehicles(){
        LocationMap.car2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                VehicleManagement.carPane2.getChildren().clear();
                for (int x = 0; x < CentralManagement.visitors.size(); ++x) {
                    VehicleManagement.carPane2.getChildren().add(
                            new CentralManagement().createMessage("" + CentralManagement.visitors.get(x).getNumID(), 1));
                }
            }
        });
        LocationMap.car1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                VehicleManagement.carPane1.getChildren().clear();
                for (int x = 0; x < CentralManagement.visitors.size(); ++x) {
                    VehicleManagement.carPane1.getChildren().add(
                            new CentralManagement().createMessage("" + CentralManagement.visitors.get(x).getNumID(), 1));
                }
            }
        });
    }
}
