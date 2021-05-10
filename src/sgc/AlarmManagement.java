package sgc;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * Class to manage the alarm system
 */
public class AlarmManagement {
    public BorderPane alarmMonitor;
    //public FlowPane alarmMonitor;       //Place to monitor alarms
    public FlowPane alarmDisplay;
    public Pane alarmButtons;
    public int num;
    public static int alarmSet = 0;
    public AnimationTimer mapAnimation;
    public int resumeStasus = 0;


    /**
     * Create a pane to monitor the alarm system
     */
    public AlarmManagement() throws FileNotFoundException {
        alarmMonitor = new BorderPane();
        alarmMonitor.setPrefSize(800, 80);
        alarmMonitor.setStyle("-fx-background-color: #6495ed;");

        alarmDisplay = new FlowPane();
        alarmDisplay.setPrefSize(1000,100);
        alarmDisplay.setStyle("-fx-background-color: #6495ed;");

        alarmDisplay.getChildren().add(new CentralManagement().createMessage("\t\nAlarm Status: Idle",3));

        alarmButtons = new HBox(50);
        alarmButtons.getChildren().addAll(resumeButton(), megaAlarm(), resetAlarm(), viewExhibit(), breakoutButton());
        alarmMonitor.setCenter(alarmDisplay);
        alarmMonitor.setRight(alarmButtons);
    }

    /**
     * Creates the manual alarm for the park.
     * @return megaAlarm
     */
    public Button megaAlarm(){

        Button megaAlarm = new Button("E M E R G E N C Y");
        megaAlarm.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");

        megaAlarm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Setting Emergency");
                alarmSet = 1;
                LocationMap.exhibit.setFill(Color.RED);
                System.out.println("alarmSet: "+ alarmSet);
                alarmDisplay.getChildren().clear();
                alarmDisplay.getChildren().add(new CentralManagement().createMessage("\t\t\nAlarm Status: EMERGENCY", 2));
                //   CentralManagement.a.stop();


            }
        });
        return megaAlarm;
    }

    /**
     * Creates the view Exhibit button
     * @return viewExhibit
     * @throws FileNotFoundException
     */
    public Button viewExhibit() throws FileNotFoundException {
        Button viewExhibit = new Button("View Exhibit");
        viewExhibit.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");

        viewExhibit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InputStream stream = null;
                try {
                    //depending on the value of alarmSet, a different gif will play
                    if(alarmSet == 0) {
                        stream = new FileInputStream("src/sgc/dionExi1.gif");
                    }
                    if(alarmSet == 1 || alarmSet == 2){
                        stream = new FileInputStream("src/sgc/dinoEscape.gif");
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Image image = new Image(stream);

                ImageView imageView = new ImageView();
                imageView.setImage(image);
                imageView.setX(10);
                imageView.setY(10);
                imageView.setFitWidth(1000);
                imageView.setPreserveRatio(true);
                Group group = new Group(imageView);

                System.out.println("view button pressed");

                //StackPane secondaryLayout = new StackPane();
                Scene secondScene = new Scene(group,1020 , 600);
                Stage newWindow = new Stage();

                newWindow.setTitle("Tyrannosaurus Rex Exhibit");
                newWindow.setScene(secondScene);
                newWindow.setX(alarmDisplay.getScaleX() + 100);
                newWindow.setY(alarmDisplay.getScaleY() + 100);

                newWindow.show();
            }
        });
        return viewExhibit;
    }

    /**
     * Creates reset alarm button
     * @return resetAlarm
     */
    public Button resetAlarm(){
        Button resetAlarm = new Button("Alarm Reset");
        resetAlarm.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");

        resetAlarm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("reset button pressed");
                alarmSet = 0;
                CentralManagement.resetState = 1;
                LocationMap.exhibit.setFill(Color.BLUE);
                CentralManagement.a.start();
                System.out.println("alarmSet: "+ alarmSet);
                alarmDisplay.getChildren().clear();
                alarmDisplay.getChildren().add(new CentralManagement().createMessage("\t\t\nAlarm Status: Idle", 3));
                //  CentralManagement.a.start();
            }
        });
        return resetAlarm;

    }

    public Button resumeButton() {
        Button resumeButton = new Button("Resume Route");
        resumeButton.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");

        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("resume button pressed");
                //once status is 0 by default.
                CentralManagement.a.start();
                System.out.println("resume status: "+ resumeStasus);


            }
        });
        return resumeButton;
    }

    public Button breakoutButton() {
        Button breakoutButton = new Button("[RELEASE DINO]");
        breakoutButton.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");

       // breakoutButton.setVisible(false);


        breakoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Dino Breakout Started");
                alarmSet = 2;
                LocationMap.exhibit.setFill(Color.ORANGE);
                alarmDisplay.getChildren().clear();
                alarmDisplay.getChildren().add(
                        new CentralManagement().createMessage("\t\t\nAlarm Status: ERROR AT DINO EXHIBIT, CHECK FEED", 2));
            }
        });
        return breakoutButton;
    }
}
