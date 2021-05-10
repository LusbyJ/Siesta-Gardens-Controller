package sgc;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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
import java.util.Random;

/**
 * Driver class to create all objects and initialize the GUI
 */
public class CentralManagement extends Application {
    public static BorderPane adminView;        //View the admin would have
    public static FlowPane combine;
    public static BorderPane kiosk;
    public static BorderPane vehicles;
    public static Pane parkMap;
    public static FlowPane visitorLog;
    public static BorderPane alarmMonitor;
    public static AnimationTimer a;
    public static ArrayList<Point2D> points = new ArrayList(); //list to store map coordinates
    public static ArrayList<Visitor> visitors = new ArrayList();
    public static int i;
    public static int j;
    public static int idle = 1;
    private static long time = 999_999_999*100;  // determines speed of animation
    private static long lastUpdate = 0;

    /**
     * Create a border pane for all components to fit into
     */
    public BorderPane createAdminView() throws IOException {
        kiosk = new Kiosk().kiosk;
        parkMap = new LocationMap().parkMap;
        vehicles = new VehicleManagement().vehicles;
        readMap();

        visitorLog = new TokenManagement().visitorLog;
        alarmMonitor = new AlarmManagement().alarmMonitor;
        combine = new FlowPane();

        combine.setPrefSize(400,500);
        combine.getChildren().addAll(kiosk,vehicles);

        adminView = new BorderPane();
        adminView.setPrefSize(1000,800);
        adminView.setBottom(visitorLog);
        adminView.setTop(alarmMonitor);
        adminView.setLeft(parkMap);
        adminView.setRight(combine);

        parkMap.getChildren().add(createMessage("\nPark Map: ", 0));
        visitorLog.getChildren().add(createMessage("Current Visitors:\t", 0));
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
        //Store each coordinate into the points array list
        while((st = br.readLine()) != null){
            Edge edge = new Edge((int)st.charAt(0)-48,(int)st.charAt(2)-48,
                    (int)st.charAt(4)-48, (int)st.charAt(6)-48);
            createLine(edge);
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
        //uses 80 as a scaler to position map better in pane
        int x = xValue*80;
        int y = yValue*80;
        return new Point2D(x,y);
    }

    public void createVisitors(){
        for(int x = 0; x < 7;x++){
            Visitor visitor = new Visitor("Joe","1234",new Random().nextInt(10000));
            CentralManagement.visitors.add(visitor);
            VehicleManagement.carPane1.getChildren().add(
                    new CentralManagement().createMessage("" + CentralManagement.visitors.get(x).getNumID(), 1));
        }
    }

    /**
     * Animation timer to move cars around the map
     */
    public static void moveCars() {
        i = 0;
        j = 6;
        a = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= time) {
                    lastUpdate = now;

                    //idle initially 1, kiosk changes state to 0 when token purchased
                    //add new guest if kiosk indicates
                    //move cars
                    if(idle == 0 && AlarmManagement.alarmSet != 1){
                        if (Kiosk.newGuest == 1) {
                            System.out.println("New guest added");
                            Kiosk.newGuest = 0;
                        }
                        //Check where vehicle is by incrementing through points arraylist and move accordingly
                        if (i < points.size()) {
                            LocationMap.car1.relocate(points.get(i).getX(), points.get(i).getY());
                            i++;
                        } else {
                            i = 0;
                        }
                        if (j < points.size()) {
                            LocationMap.car2.relocate(points.get(j).getX(), points.get(j).getY());
                            j++;
                        } else {
                            j = 0;
                        }
                    }

                    //Clear the log and add again with any updates
                    visitorLog.getChildren().clear();
                    //shows Current Visitors each time one is added
                    visitorLog.getChildren().add(
                            new CentralManagement().createMessage("Current Visitors:\t", 0));
                    for (int x = 0; x < visitors.size(); x++) {
                        visitorLog.getChildren().add(
                                new CentralManagement().createMessage(" " + visitors.get(x).getNumID(), 1));
                    }
                    //------------------------
                    //if(AlarmManagement.alarmSet == 2) {
                    //    parkMap.getChildren().add(
                    //            new CentralManagement().createMessage("\nPark Map: ERROR AT DINO EXHIBIT, CHECK FEED", 2));
                    //}
                    //--------------------------

                    //If alarm is indicated by alarm management, change color of cars to red and begin
                    //moving the cars back to start
                    if(AlarmManagement.alarmSet == 1){
                        LocationMap.car_1.setFill(Color.RED);
                        LocationMap.car_2.setFill(Color.RED);
                        if(i > 0 && i < 6){
                            LocationMap.car1.relocate(points.get(i).getX(), points.get(i).getY());
                            i++;
                        }
                        else{
                            LocationMap.car1.relocate(points.get(i).getX(),points.get(i).getY());
                            i--;
                        }
                        if(j >= 6 && j < points.size()){
                            LocationMap.car2.relocate(points.get(j).getX(),points.get(j).getY());
                            j--;
                        }
                        else{
                            LocationMap.car2.relocate(points.get(j).getX(),points.get(j).getY());
                            j++;
                        }
                        if(i == 6){
                            LocationMap.car1.relocate(points.get(6).getX(),points.get(6).getY());
                        }
                        if(j == 6){
                            LocationMap.car2.relocate(points.get(6).getX(),points.get(6).getY());
                        }
                        //Once both vehicles are back at start, stop the animation, can be resumed
                        //by hitting the cancel alarm button.
                        if(j == 6 && i == 6){
                            a.stop();
                        }
                    }
                }
            }
        };
        a.start();
    }

    /**
     * Set up the scene
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Siesta Gardens Controller");
        CentralManagement view = new CentralManagement();
        Group root = new Group(view.createAdminView());
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        createVisitors();
        moveCars();
    }
}

