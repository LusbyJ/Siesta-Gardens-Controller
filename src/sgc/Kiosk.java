package sgc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Class to create and manage the kiosk
 */
public class Kiosk {
    public BorderPane kiosk;
    public FlowPane kioskDisplay;
    public Pane kioskButtons;
    public final String defaultCSS = "-fx-background-color: #404040;" + "-fx-border-color: white;";
    public final String buttonCSS = "-fx-text-fill: rgb(49, 89, 23);\n" +
                                    "-fx-border-color: rgb(49, 89, 23);\n" +
                                    "-fx-border-radius: 5;\n" +
                                    "-fx-padding: 3 6 6 6;";

    /**
     * Creates the kiosk object
     * @return
     */
    public Kiosk() {
        kiosk = new BorderPane();
        kiosk.setPrefSize(400, 250);

        //Create the kiosk display and add initial welcome message
        kioskDisplay = new FlowPane();
        kioskDisplay.setPrefSize(400, 200);
        kioskDisplay.setStyle("-fx-background-color: #000000;");
        kioskDisplay.getChildren().add(new CentralManagement().createMessage("\n\t\tWelcome to Siesta Gardens",3));

        //Create the kiosk buttons and add them to GUI
        kioskButtons = new HBox(20);
        kioskButtons.setPrefSize(400, 50);
        kioskButtons.setStyle(defaultCSS);
        kioskButtons.getChildren().addAll(bookingButton(), selectButton(), payButton(), cancelButton());

        //Position and style kiosk components
        kiosk.setStyle(defaultCSS);
        kiosk.setCenter(kioskDisplay);
        kiosk.setBottom(kioskButtons);
    }

    /**
     * Creates the booking button and event handler
     * @return bookingButton
     */
    public Button bookingButton() {
        Button bookingButton = new Button("Available Times");
        bookingButton.setStyle(buttonCSS);
        bookingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kioskDisplay.getChildren().clear();
                kioskDisplay.getChildren().add(createMessage("\t\t\t\tAvailable Times\t\t\t\t", false));
                for(char i=49; i<58;i++) {
                    kioskDisplay.getChildren().add(createMessage(i+":00  ", true));
                }
            }
        });
        return bookingButton;
    }

    /**
     * Creates the elect button and event handler
     * @return bookingButton
     */
    public Button selectButton() {
        Button selectButton = new Button("Select Time");
        selectButton.setStyle(buttonCSS);

        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //book time slot
                TextField b = new TextField();
                b.setPrefSize(260,20);
                kioskDisplay.getChildren().clear();
                kioskDisplay.getChildren().add(createMessage("Full Name", false));
                kioskDisplay.getChildren().add(b);
                kioskDisplay.getChildren().add(createMessage("Credit Card Number", false));
            }
        });
        return selectButton;
    }

    /**
     * Creates the pay button and event handler
     * @return payButton
     */
    public Button payButton() {
        Button payButton = new Button("Pay");
        payButton.setStyle(buttonCSS);
        payButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                //make payment
            }
        });
        return payButton;
    }

    /**
     * Creates the cancel button and event handler
     * @return bookingButton
     */
    public Button cancelButton() {
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(buttonCSS);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kioskDisplay.getChildren().clear();
                kioskDisplay.getChildren().add(createMessage("\n\t\t\tWelcome to Siesta Gardens", false));
            }
        });
        return cancelButton;
    }

    /**
     * Creates a Text object with a specified color to be used on GUI
     * @param text : info String
     */
    public Text createMessage(String text, Boolean flag){
        Text newLog = new Text(text);
        newLog.setStroke(Color.GREEN);
        if(flag) {
            newLog.setOnMouseClicked((EventHandler<MouseEvent>) event -> {
                newLog.setStroke(Color.WHITE);
            });
        }
        return newLog;
    }
}
