package sgc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

// This class handles running the employee tablet simulation
public class EmployeeTablet{
    private Stage tabletStage;
    private Scene menuScene;
    private Scene locatorScene;
    private Scene routeOverrideScene;
    private Scene emergencyScene;

    public void initializeTablet(Stage primaryStage){
        tabletStage = new Stage();
        tabletStage.initModality(Modality.NONE);
        tabletStage.initOwner(primaryStage);
        tabletStage.setAlwaysOnTop(false);
        tabletStage.setTitle("Employee Chaperone Tablet");

        Canvas locatorCanvas = new Canvas(210, 210);
        GraphicsContext locatorGC = locatorCanvas.getGraphicsContext2D();
        Image locatorIcon = new Image("file:Resources\\LocatorIcon.png");
        locatorGC.setFill(Color.LIGHTYELLOW);
        locatorGC.setStroke(Color.BLACK);
        locatorGC.setLineWidth(10);
        locatorGC.fillRect(0, 0, 210, 210);
        locatorGC.drawImage(locatorIcon, 5, 5);
        locatorGC.strokeRoundRect(0, 0, 210, 210, 20, 20);

        Canvas routeOverrideCanvas = new Canvas(210, 210);
        GraphicsContext routeOverrideGC = routeOverrideCanvas.getGraphicsContext2D();
        Image routeOverrideIcon = new Image("file:Resources\\RouteOverrideIcon.png");
        routeOverrideGC.setFill(Color.TURQUOISE);
        routeOverrideGC.setStroke(Color.BLACK);
        routeOverrideGC.setLineWidth(10);
        routeOverrideGC.fillRect(0, 0, 210, 210);
        routeOverrideGC.drawImage(routeOverrideIcon, 5, 5);
        routeOverrideGC.strokeRoundRect(0, 0, 210, 210, 20, 20);

        Canvas emergencyCanvas = new Canvas(210, 210);
        GraphicsContext emergencyGC = emergencyCanvas.getGraphicsContext2D();
        Image emergencyIcon = new Image("file:Resources\\EmergencyIcon.jpg");
        emergencyGC.setFill(Color.HONEYDEW);
        emergencyGC.setStroke(Color.BLACK);
        emergencyGC.setLineWidth(10);
        emergencyGC.fillRect(0, 0, 210, 210);
        emergencyGC.drawImage(emergencyIcon, 5, 5);
        emergencyGC.strokeRoundRect(0, 0, 210, 210, 20, 20);

        HBox hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(locatorCanvas, routeOverrideCanvas, emergencyCanvas);

        BorderPane border = new BorderPane();
        border.setBackground(new Background(new BackgroundFill(
                Color.LIGHTGREEN, new CornerRadii(10), new Insets(0))));
        border.setCenter(hBox);

        menuScene = new Scene(border, 800, 500);
        tabletStage.setScene(menuScene);
        tabletStage.show();
    }
}
