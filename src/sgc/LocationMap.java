package sgc;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class to manage the map
 */
public class LocationMap {
    //Park map with vehicle locations
    public static Pane parkMap;
    public final String defaultCSS = "-fx-background-color: #003300;"+"-fx-border-color:  white;";
    public static Circle routeStart;
    public static Circle exhibit;

    /**
     * Creates the LocationMap pane to display map of island and vehicles throughout the park
     */
    public LocationMap(){
        parkMap = new Pane();
        parkMap.setPrefSize(600,500);
        parkMap.setStyle(defaultCSS);

        //Create circle and label to indicate start
        routeStart = new Circle((4*80), (2*80),15, Color.BLACK);
        Text startPosition = new Text("Start");
        startPosition.setStroke(Color.WHITE);
        startPosition.relocate((4*80)+10,(2*80)-10);

        //Create circle and label to indicate exhibit
        exhibit = new Circle((1*80), (3*80),15, Color.RED);
        Text exPosition = new Text("Exhibit");
        exPosition.setStroke(Color.WHITE);
        exPosition.relocate((1*80)+10,(3*80)-10);

        //Add components to the parkMap
        parkMap.getChildren().addAll(routeStart,startPosition,exhibit, exPosition);
    }
}
