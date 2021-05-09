package sgc;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * Class to create and manage the kiosk
 */
public class Kiosk {
    public BorderPane kiosk;
    public FlowPane kioskDisplay;
    public Pane kioskButtons;
    public int state;
    public TextField a;
    public TextField b;
    public Text fullName;
    public Text cardNum;
    public static int newGuest;
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
        state = 0;
        newGuest = 0;

        //Create the kiosk display and add initial welcome message
        kioskDisplay = new FlowPane();
        kioskDisplay.setPrefSize(400, 200);
        kioskDisplay.setStyle("-fx-background-color: #000000;");
        kioskDisplay.getChildren().add(new CentralManagement().createMessage(
                        "\n\t\t\tWelcome to Siesta Gardens!\n\n\n"+
                                "\tTo view available timeslots. Press the button below\n"+
                                "\tYou can cancel anytime by Pressing the cancel button.",3));

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
        bookingButton.setOnAction(event -> {
            if(state == 0) {
                state = 1;
                kioskDisplay.getChildren().clear();
                kioskDisplay.getChildren().add(createMessage("\t\t\t\tAvailable Times\t\t\t\t\n\n\n", false));
                for (char i = 49; i < 58; i++) {
                    kioskDisplay.getChildren().add(createMessage(i + ":00  ", true));
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
        selectButton.setOnAction(event -> {
            if(state == 1) {
                //book time slot
                a = new TextField();
                b = new TextField();
                a.setPrefSize(260, 20);
                b.setPrefSize(260, 20);

                EventHandler<ActionEvent> submit = new EventHandler<>() {
                    public void handle(ActionEvent e) {
                        kioskDisplay.getChildren().clear();
                        newGuest = 1;
                        state = 2;
                    }
                };

                // when enter is pressed
                b.setOnAction(submit);
                a.setOnAction(submit);
                kioskDisplay.getChildren().clear();
                kioskDisplay.getChildren().add(createMessage("\t\tPlease enter your name and\n\t\tCredit Card information.\t\t\t\t\n\n", false));
                kioskDisplay.getChildren().add(createMessage("\t\tFull Name", false));
                kioskDisplay.getChildren().add(a);
                kioskDisplay.getChildren().add(createMessage("Credit Card Number", false));
                kioskDisplay.getChildren().add(b);
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
        payButton.setOnAction(event -> {
            if (state == 2) {
                fullName = new Text(a.getText());
                fullName.setStroke(Color.WHITE);
                cardNum = new Text(b.getText());
                cardNum.setStroke(Color.WHITE);
                Text accepted = new Text("\t\t\tPayment Accepted\n\t\t\t" + fullName.getText() + "\n\t\t\t" + "Press the exit button to receive entry token.");
                accepted.setStroke(Color.WHITE);
                kioskDisplay.getChildren().addAll(accepted);
                Visitor visitor = new Visitor(fullName.getText(), cardNum.getText(),new Random().nextInt(10000));
                CentralManagement.visitors.add(visitor);
                CentralManagement.idle = 0;
            }
        });
        return payButton;
    }

    /**
     * Creates the cancel button and event handler
     * @return bookingButton
     */
    public Button cancelButton() {
        Button cancelButton = new Button("Exit");
        cancelButton.setStyle(buttonCSS);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                state = 0;
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
