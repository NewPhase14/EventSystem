package sample.GUI.Controller.Pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import sample.BE.Event;
import sample.BE.Ticket;
import sample.GUI.Model.EventModel;
import sample.GUI.Model.LoggedInModel;

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
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;

    private EventModel eventModel;

    public TicketController() throws Exception {
        eventModel = new EventModel();
    }

    @FXML
    private void OnClickSendEmail(ActionEvent actionEvent) throws Exception {
        for (Event event : eventModel.getObservableEvents()) {
            if (event.getName().equalsIgnoreCase(txtEventName.getText())) {
                if (txtEmail.getText().contains("@")) {
                    Ticket ticket = new Ticket(txtFirstName.getText(),txtLastName.getText(), txtEmail.getText(),event.getId(),"", LoggedInModel.getInstance().getEventCoordinator().getId());
                    eventModel.createTicket(event, Integer.parseInt(txtTicketAmount.getText()), ticket);
                }
                else {
                    System.out.println("Enter a valid email address");
                }
            }
        }
    }

    public void setTxtEventName(Event event) {
        txtEventName.setText(event.getName());
    }
}
