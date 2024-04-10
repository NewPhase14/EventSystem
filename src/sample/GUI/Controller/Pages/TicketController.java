package sample.GUI.Controller.Pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.BE.Event;
import sample.GUI.Model.EventModel;

import javax.mail.MessagingException;
import java.awt.print.PrinterException;
import java.io.IOException;

public class TicketController {
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtEventName;
    @FXML
    private TextField txtTicketAmount;

    private EventModel eventModel;

    public TicketController() throws Exception {
        eventModel = new EventModel();
    }

    @FXML
    private void OnClickPrintAndSendMail(ActionEvent actionEvent) throws IOException, PrinterException, MessagingException {
        for (Event event : eventModel.getObservableEvents()) {
            if (event.getName().equalsIgnoreCase(txtEventName.getText())) {
                if (txtEmail.getText().contains("@")) {
                    eventModel.createTicket(event, Integer.parseInt(txtTicketAmount.getText()), txtEmail.getText());
                }
                else {
                    System.out.println("gg mand");
                }
            }
        }
    }

    public void setTxtEventName(Event event) {
        txtEventName.setText(event.getName());
    }
}
