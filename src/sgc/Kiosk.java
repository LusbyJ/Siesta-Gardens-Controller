package sgc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Class to create and manage the kiosk
 */
public class Kiosk {
    public BorderPane kiosk;
    public Pane kioskDisplay;
    public Pane kioskButtons;
    public final String defaultCSS = "-fx-background-color: #404040;"+"-fx-border-color: white;";

    /**
     * Creates the kiosk object
     * @return
     */
    public Kiosk(){
        kiosk = new BorderPane();
        kiosk.setPrefSize(400,250);

        kioskDisplay = new Pane();
        kioskDisplay.setPrefSize(400, 200);
        kioskDisplay.setStyle("-fx-background-color: #000000;");

        kioskButtons = new HBox(10);
        kioskButtons.setPrefSize(400,50);
        kioskButtons.setStyle(defaultCSS);
        kioskButtons.getChildren().addAll(bookingButton(), selectButton(), payButton());

        kiosk.setStyle(defaultCSS);
        kiosk.setCenter(kioskDisplay);
        kiosk.setBottom(kioskButtons);
    }

    /**
     * Creates the kiosk buttons and event handler
     * @return bookingButton
     */
    public Button bookingButton(){
        Button bookingButton = new Button("Available Times");
        bookingButton.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");
        bookingButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //change state to start the game
            }
        });
        return bookingButton;
    }

    /**
     * Creates the kiosk buttons and event handler
     * @return bookingButton
     */
    public Button selectButton(){
        Button selectButton = new Button("Select Time");
        selectButton.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");
        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //change state to start the game
            }
        });
        return selectButton;
    }

    /**
     * Creates the kiosk buttons and event handler
     * @return bookingButton
     */
    public Button payButton(){
        Button payButton = new Button("Pay");
        payButton.setStyle("-fx-text-fill: rgb(49, 89, 23);\n" +
                "-fx-border-color: rgb(49, 89, 23);\n" +
                "-fx-border-radius: 5;\n" +
                "-fx-padding: 3 6 6 6;");
        payButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //change state to start the game
            }
        });
        return payButton;
    }
}
