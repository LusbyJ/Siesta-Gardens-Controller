package sgc;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Driver class to create all objects and initialize the GUI
 */
public class CentralManagement extends Application {
    private Stage pStage;
    public static BorderPane adminView;        //View the admin would have
    public static FlowPane combine;
    public static BorderPane kiosk;
    public static FlowPane vehicles;
    public static Pane parkMap;
    public static FlowPane visitorLog;
    public static HBox topPane;
    public static FlowPane alarmMonitor;
    public static ArrayList<Point2D> points = new ArrayList(); //list to store map coordinates
    public final String defaultCSS = "-fx-background-color: #404040;"+"-fx-border-color: white;";


    /**
     * Create a border pane for all components to fit into
     */
    public BorderPane createAdminView() throws IOException {
        kiosk = new Kiosk().kiosk;
        vehicles = new VehicleManagement().vehicles;
        parkMap = new LocationMap().parkMap;
        readMap();
        visitorLog = new TokenManagement().visitorLog;
        alarmMonitor = new AlarmManagement().alarmMonitor;
        combine = new FlowPane();

        combine.setPrefSize(400,500);
        combine.getChildren().addAll(kiosk,vehicles);

        Button tabletButton = new Button("Tablet");
        tabletButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            EmployeeTablet tablet = new EmployeeTablet();
            tablet.initializeTablet(pStage);
        });
        topPane = new HBox(alarmMonitor, tabletButton);

        adminView = new BorderPane();
        adminView.setPrefSize(1000,800);
        adminView.setBottom(visitorLog);
        adminView.setTop(topPane);
        adminView.setLeft(parkMap);
        adminView.setRight(combine);

        kiosk.getChildren().add(createMessage("\n\t\tWelcome to Siesta Gardens",3));
        vehicles.getChildren().add(createMessage("Vehicle Manager:", 0));
        visitorLog.getChildren().add(createMessage("Current Visitors:\t", 0));
        alarmMonitor.getChildren().add(createMessage("Alarm Status\t", 0));
        return adminView;
    }

    /**
     * Creates a Text object with a specified color to be used on GUI
     * @param text : info String
     * @param color : int to indicate color of text
     */
    public Text createMessage(String text, int color){
        Text newLog = new Text(text);
        if(color == 1) {
            newLog.setStroke(Color.WHITE);
        }
        else if(color == 2){
            newLog.setStroke(Color.RED);
        }
        else if(color == 3){
            newLog.setStroke(Color.GREEN);
        }
        else newLog.setStroke(Color.BLACK);
        return newLog;
    }

    /**
     * Reads the map to create edges for GUI
     * @throws IOException
     */
    public void readMap() throws IOException {
        InputStream in = getClass().getResourceAsStream("map.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String st;

        //Loop through each line of the input file
        while((st = br.readLine()) != null){
            Edge edge = new Edge((int)st.charAt(0)-48,(int)st.charAt(2)-48,
                    (int)st.charAt(4)-48, (int)st.charAt(6)-48);
            createLine(edge);
            //add point to array list
            points.add(createPoint((int)st.charAt(0)-48, (int)st.charAt(2)-48));
        }
    }


    /**
     * Creates a line object and adds to the display
     * @param edge : line between graph nodes
     */
    public void createLine(Edge edge){
        //multiply values by a 80 to position map better
        double startX = edge.getStartX()*80;
        double startY = edge.getStartY()*80;
        double endX = edge.getEndX()*80;
        double endY = edge.getEndY()*80;
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.WHITE);
        parkMap.getChildren().add(line);
    }

    /**
     * Creates a point for the node
     * @param xValue : x coordinate
     * @param yValue : y coordinate
     * @return new 2D point
     */
    public Point2D createPoint(int xValue, int yValue) {
        int x = xValue*80;
        int y = yValue*80;
        return new Point2D(x,y);
    }

    /**
     * Set up the scene
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Siesta Gardens Controller");
        pStage = primaryStage;
        CentralManagement view = new CentralManagement();
        Group root = new Group(view.createAdminView());
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        /*
        TODO: call to run simulation
         */
    }
}
