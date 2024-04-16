package sample.GUI.Controller.Pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;
import sample.BE.Admin;
import sample.BE.EventCoordinator;
import sample.GUI.Model.AdminModel;
import sample.GUI.Model.EventCoordinatorModel;
import sample.GUI.Model.EventModel;
import sample.GUI.Model.LoggedInModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileWindowController implements Initializable {

    @FXML
    private Label lblName, lblTickets;
    @FXML
    private TextField txtUsername, txtEmail, txtTicketsSold;
    @FXML
    private PasswordField pasPassword;
    private EventModel eventModel;
    private EventCoordinator eventCoordinator;
    private Admin admin;
    EventCoordinatorModel eventCoordinatorModel;

    public ProfileWindowController() throws Exception {
        eventModel = new EventModel();
        eventCoordinatorModel = new EventCoordinatorModel();
        if(LoggedInModel.getInstance().getAdmin() != null) {
            admin = LoggedInModel.getInstance().getAdmin();
        } else {
            eventCoordinator = LoggedInModel.getInstance().getEventCoordinator();
        }
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

    public void setupEventCoordinatorProfileFields() throws Exception {
        String firstName = eventCoordinator.getFirstName();
        String lastName = eventCoordinator.getLastName();
        String email = eventCoordinator.getEmail();
        String username = eventCoordinator.getUsername();
        lblName.setText(firstName + " " + lastName);
        txtTicketsSold.setText(String.valueOf(eventModel.getSoldTickets(eventCoordinator)));
        txtEmail.setText(email);
        txtUsername.setText(username);
    }

    public void setupAdminProfileFields() throws Exception {
        String firstName = admin.getFirstName();
        String lastName = admin.getLastName();
        String email = admin.getEmail();
        String username = admin.getUsername();
        lblName.setText(firstName + " " + lastName);
        txtEmail.setText(email);
        txtUsername.setText(username);
        lblTickets.setVisible(false);
        txtTicketsSold.setVisible(false);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(admin != null) {
                setupAdminProfileFields();
            } else {
                setupEventCoordinatorProfileFields();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
