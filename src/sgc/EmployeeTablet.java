package sgc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

// This class handles running the employee tablet simulation
public class EmployeeTablet{
    private Scene menuScene;
    private BorderPane menuPane;
    private BorderPane locatorPane;
    private BorderPane routeOverridePane;
    private BorderPane emergencyPane;
    private GraphicsContext overrideGC;
    private boolean scanActive;
    private boolean overrideActive;
    private boolean emergencyAppOpen;
    private boolean emergencyActive;

    public void initializeTablet(Stage primaryStage){
        Stage tabletStage = new Stage();
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
        locatorCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            menuScene.setRoot(locatorPane);
        });

        Canvas routeOverrideCanvas = new Canvas(210, 210);
        GraphicsContext routeOverrideGC = routeOverrideCanvas.getGraphicsContext2D();
        Image routeOverrideIcon = new Image("file:Resources\\RouteOverrideIcon.png");
        routeOverrideGC.setFill(Color.TURQUOISE);
        routeOverrideGC.setStroke(Color.BLACK);
        routeOverrideGC.setLineWidth(10);
        routeOverrideGC.fillRect(0, 0, 210, 210);
        routeOverrideGC.drawImage(routeOverrideIcon, 5, 5);
        routeOverrideGC.strokeRoundRect(0, 0, 210, 210, 20, 20);
        routeOverrideCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            menuScene.setRoot(routeOverridePane);
        });

        Canvas emergencyCanvas = new Canvas(210, 210);
        GraphicsContext emergencyGC = emergencyCanvas.getGraphicsContext2D();
        Image emergencyIcon = new Image("file:Resources\\EmergencyIcon.jpg");
        emergencyGC.setFill(Color.HONEYDEW);
        emergencyGC.setStroke(Color.BLACK);
        emergencyGC.setLineWidth(10);
        emergencyGC.fillRect(0, 0, 210, 210);
        emergencyGC.drawImage(emergencyIcon, 5, 5);
        emergencyGC.strokeRoundRect(0, 0, 210, 210, 20, 20);
        emergencyCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            menuScene.setRoot(emergencyPane);
            emergencyAppOpen = true;
        });

        HBox hBox = new HBox(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(locatorCanvas, routeOverrideCanvas, emergencyCanvas);

        BorderPane border = new BorderPane();
        border.setBackground(new Background(new BackgroundFill(
                Color.LIGHTGREEN, new CornerRadii(10), new Insets(0))));
        border.setCenter(hBox);
        menuPane = border;

        initializeLocator();
        initializeRouteOverride();
        initializeEmergency();

        menuScene = new Scene(border, 800, 500);
        tabletStage.setScene(menuScene);
        tabletStage.show();
    }

    private void initializeLocator(){
        BorderPane border = new BorderPane();
        BorderPane buttonPane = new BorderPane();
        Button scan = new Button("Scan Tokens");
        Button back = new Button("Back");
        Canvas scanCanvas = new Canvas(600, 500);
        GraphicsContext gc = scanCanvas.getGraphicsContext2D();

        scanActive = false;

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, 600, 500);
        gc.strokeText("Scan Token Now", 250, 150);

        scan.setFont(new Font(20));
        scan.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(scanActive){
                scan.setText("Scan Token Now");
                border.setCenter(LocationMap.parkMap);
                scanActive = false;
            }
            else{
                scan.setText("Stop Scan");
                border.setCenter(scanCanvas);
                scanActive = true;
            }
        });

        back.setFont(new Font(20));
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            menuScene.setRoot(menuPane);
            if(scanActive){
                scan.setText("Scan Token Now");
                border.setCenter(LocationMap.parkMap);
                scanActive = false;
            }
        });

        buttonPane.setTop(back);
        buttonPane.setBottom(scan);
        BorderPane.setAlignment(buttonPane, Pos.CENTER_LEFT);

        BorderPane.setAlignment(border, Pos.CENTER_LEFT);
        border.setCenter(LocationMap.parkMap);
        border.setRight(buttonPane);

        locatorPane = border;
    }

    private void initializeRouteOverride(){
        BorderPane border = new BorderPane();
        BorderPane buttonPane = new BorderPane();
        StackPane stack = new StackPane();
        Button override = new Button("Override Route");
        Button back = new Button("Back");
        Canvas overrideCanvas = new Canvas(600, 500);
        overrideGC = overrideCanvas.getGraphicsContext2D();
        overrideActive = false;

        overrideGC.clearRect(0, 0, 600, 500);
        overrideGC.setFill(Color.PERU);
        stack.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            overrideGC.fillOval(event.getSceneX()-20, event.getSceneY(), 4, 4);
        });

        override.setFont(new Font(20));
        override.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(overrideActive){
                override.setText("Override Route");
                overrideActive = false;
            }
            else{
                override.setText("Confirm Route");
                overrideActive = true;
            }
        });

        back.setFont(new Font(20));
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(overrideActive){
                override.setText("Override Route");
                overrideActive = false;
            }
            else{
                menuScene.setRoot(menuPane);
            }
            overrideGC.clearRect(0, 0, 600, 500);
        });

        buttonPane.setTop(back);
        buttonPane.setBottom(override);
        BorderPane.setAlignment(buttonPane, Pos.CENTER_LEFT);

        stack.getChildren().add(LocationMap.parkMap);
        stack.getChildren().add(overrideCanvas);

        BorderPane.setAlignment(border, Pos.CENTER_LEFT);
        border.setCenter(stack);
        border.setRight(buttonPane);

        routeOverridePane = border;
    }

    private void initializeEmergency(){
        BorderPane border = new BorderPane();
        Button back = new Button("Back");
        Canvas emergencyCanvas = new Canvas(700, 500);
        GraphicsContext gc = emergencyCanvas.getGraphicsContext2D();
        emergencyAppOpen = false;
        emergencyActive = false;

        gc.setFont(new Font(50));
        gc.setFill(Color.MISTYROSE);
        gc.fillRect(0, 0, 700, 500);
        gc.setFill(Color.RED);
        gc.fillOval(200, 100, 300, 300);
        gc.setLineWidth(10);
        gc.strokeOval(200, 100, 300, 300);
        gc.setFill(Color.BLACK);
        gc.fillText("EMERGENCY", 210, 260);

        emergencyCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(emergencyActive){
                gc.setFill(Color.MISTYROSE);
                emergencyActive = false;
            }
            else{
                gc.setFill(Color.TOMATO);
                emergencyActive = true;
            }
            gc.fillRect(0, 0, 700, 500);
            gc.setFill(Color.RED);
            gc.fillOval(200, 100, 300, 300);
            gc.setLineWidth(10);
            gc.strokeOval(200, 100, 300, 300);
            gc.setFill(Color.BLACK);
            gc.fillText("EMERGENCY", 210, 260);
        });

        back.setFont(new Font(20));
        back.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            menuScene.setRoot(menuPane);
            emergencyAppOpen = false;
        });

        BorderPane.setAlignment(border, Pos.CENTER_LEFT);
        border.setCenter(emergencyCanvas);
        border.setRight(back);

        emergencyPane = border;
    }
}
