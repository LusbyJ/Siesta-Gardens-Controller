package sgc;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class to manage the map
 */
public class LocationMap {
    public static Pane parkMap;                //Park map with vehicle locations
    public final String defaultCSS = "-fx-background-color: #003300;"+"-fx-border-color:  white;";
    public static Circle routeStart;
    public static Circle exhibit;

    public LocationMap(){
        parkMap = new Pane();
        parkMap.setPrefSize(600,500);
        parkMap.setStyle(defaultCSS);
        routeStart = new Circle((4*80), (2*80),15, Color.BLACK);
        exhibit = new Circle((1*80), (3*80),15, Color.RED);
        parkMap.getChildren().addAll(routeStart,exhibit);
    }


}
