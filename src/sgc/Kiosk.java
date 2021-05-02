package sgc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

/**
 * Class to create and manage the kiosk
 */
public class Kiosk {
    public BorderPane kiosk;
    public FlowPane kioskDisplay;
    public Pane kioskButtons;
    public final String defaultCSS = "-fx-background-color: #404040;" + "-fx-border-color: white;";

    /**
     * Creates the kiosk object
     *
     * @return
     */
    public Kiosk() {
        kiosk = new BorderPane();
        kiosk.setPrefSize(400, 250);

        kioskDisplay = new FlowPane();
        kioskDisplay.setPrefSize(400, 200);
        kioskDisplay.setStyle("-fx-background-color: #000000;");
        kioskDisplay.getChildren().add(new CentralManagement().createMessage("\n\t\tWelcome to Siesta Gardens",3));

        kioskButtons = new HBox(20);
        kioskButtons.setPrefSize(400, 50);
        kioskButtons.setStyle(defaultCSS);
        kioskButtons.getChildren().addAll(bookingButton(), selectButton(), payButton(), cancelButton());

        kiosk.setStyle(defaultCSS);
        kiosk.setCenter(kioskDisplay);
        kiosk.setBottom(kioskButtons);
    }

    /**
     * Creates the kiosk buttons and event handler
     *
     * @return bookingButton
     */
    public Button bookingButton() {
        Button bookingButton = new Button("Available Times");
        bookingButton.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");
        bookingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                kioskDisplay.getChildren().remove(0);
                for(char i=48; i<57;i++) {
                    kioskDisplay.getChildren().add(new CentralManagement().createMessage(i+":00  ", 3));
                }
            }
        });
        return bookingButton;
    }

    /**
     * Creates the kiosk buttons and event handler
     *
     * @return bookingButton
     */
    public Button selectButton() {
        Button selectButton = new Button("Select Time");
        selectButton.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");
        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //book time slot
            }
        });
        return selectButton;
    }

    /**
     * Creates the kiosk buttons and event handler
     *
     * @return bookingButton
     */
    public Button payButton() {
        Button payButton = new Button("Pay");
        payButton.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");
        payButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                //make payment
            }
        });
        return payButton;
    }


    /**
     * Creates the kiosk buttons and event handler
     *
     * @return bookingButton
     */
    public Button cancelButton() {
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        return cancelButton;
    }
}
