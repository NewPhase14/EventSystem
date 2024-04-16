package sample.GUI.Controller.Pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;
import sample.BE.EventCoordinator;
import sample.GUI.Model.EventCoordinatorModel;
import sample.GUI.Model.EventModel;
import sample.GUI.Model.LoggedInModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileWindowController implements Initializable {

    @FXML
    private Label lblName;
    @FXML
    private TextField txtUsername, txtEmail, txtTicketsSold;
    @FXML
    private PasswordField pasPassword;
    private EventModel eventModel;
    private EventCoordinator eventCoordinator;
    EventCoordinatorModel eventCoordinatorModel;

    public ProfileWindowController() throws Exception {
        eventModel = new EventModel();
        eventCoordinator = LoggedInModel.getInstance().getEventCoordinator();
        eventCoordinatorModel = new EventCoordinatorModel();
    }

    @FXML
    private void changeImage(ActionEvent actionEvent) {

    }

    @FXML
    private void saveProfile(ActionEvent actionEvent) throws Exception {
        String email = txtEmail.getText();
        String username = txtUsername.getText();
        String password = pasPassword.getText();
        String salt = BCrypt.gensalt(14);
        String generatedPassword = BCrypt.hashpw(password,salt);

        eventCoordinator.setUsername(username);
        eventCoordinator.setPassword(generatedPassword);
        eventCoordinator.setEmail(email);

        eventCoordinatorModel.updateEventCoordinator(eventCoordinator);
        pasPassword.clear();
    }

    public void setupProfileFields() throws Exception {
        String firstName = eventCoordinator.getFirstName();
        String lastName = eventCoordinator.getLastName();
        String email = eventCoordinator.getEmail();
        String username = eventCoordinator.getUsername();
        lblName.setText(firstName + " " + lastName);
        txtTicketsSold.setText(String.valueOf(eventModel.getSoldTickets(eventCoordinator)));
        txtEmail.setText(email);
        txtUsername.setText(username);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setupProfileFields();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
