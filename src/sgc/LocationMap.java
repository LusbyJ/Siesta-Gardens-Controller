package sgc;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Class to manage the map
 */
public class LocationMap {
    //Park map with vehicle locations
    public static Pane parkMap;
    public final String defaultCSS = "-fx-background-color: #003300;" + "-fx-border-color:  white;";
    public static Circle routeStart;
    public static Circle exhibit;
    public static StackPane car1;
    public static StackPane car2;
    public static Rectangle car_1;
    public static Rectangle car_2;
    public static Text label1;
    public static Text label2;


    /**
     * Creates the LocationMap pane to display map of island and vehicles throughout the park
     */
    public LocationMap() {
        parkMap = new Pane();
        parkMap.setPrefSize(600, 500);
        parkMap.setStyle(defaultCSS);

        //Create circle and label to indicate start
        routeStart = new Circle((4 * 80), (2 * 80), 15, Color.BLACK);
        Text startPosition = new Text("Start");
        startPosition.setStroke(Color.WHITE);
        startPosition.relocate((4 * 80) + 10, (2 * 80) - 10);

        //Create circle and label to indicate exhibit
        exhibit = new Circle((1 * 80), (3 * 80), 15, Color.RED);
        Text exPosition = new Text("Exhibit");
        exPosition.setStroke(Color.WHITE);
        exPosition.relocate((1 * 80) + 10, (3 * 80) - 10);
        createCars();

        //Add components to the parkMap
        parkMap.getChildren().addAll(routeStart, startPosition, exhibit, exPosition, car1, car2);
    }

    /**
     * Create the cars
     */
    public void createCars(){
        car_1 = new Rectangle(20, 12, Color.YELLOW);
        car_2 = new Rectangle(20,12,Color.YELLOW);
        label1 = new Text("1");
        label1.setStroke(Color.BLACK);
        label2 = new Text("2");
        label2.setStroke(Color.BLACK);
        car1 = new StackPane(car_1, label1);
        car2 = new StackPane(car_2,label2);

        car1.setLayoutX(1*80);
        car1.setLayoutY(3*80);
        car2.setLayoutX(4*80);
        car2.setLayoutY(2*80);
    }
}























