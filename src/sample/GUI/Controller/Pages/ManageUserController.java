package sample.GUI.Controller.Pages;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import org.mindrot.jbcrypt.BCrypt;
import sample.BE.EventCoordinator;
import sample.GUI.Model.EventCoordinatorModel;


import java.net.URL;
import java.util.ResourceBundle;


/*
Fix Exception handling
*/
public class ManageUserController implements Initializable {
    @FXML
    private TableView<EventCoordinator> tblUsers;
    @FXML
    private TableColumn<EventCoordinator, String> colFirstName, colLastName, colEmail;
    @FXML
    private TextField txtfFirstName, txtfLastName, txtfUsername, txtfEmail;
    @FXML
    private PasswordField pwfPassword;

    private EventCoordinatorModel eventCoordinatorModel;

    public ManageUserController() throws Exception {
        eventCoordinatorModel = new EventCoordinatorModel();
    }

    @FXML
    private void updateUser() throws Exception {
        EventCoordinator selectedEventCoordinator = tblUsers.getSelectionModel().getSelectedItem();
        selectedEventCoordinator.setFirstName(txtfFirstName.getText());
        selectedEventCoordinator.setLastName(txtfLastName.getText());
        selectedEventCoordinator.setUsername(txtfUsername.getText());
        String oldpw = pwfPassword.getText();
        String salt = BCrypt.gensalt(14);
        String newpw = BCrypt.hashpw(oldpw,salt);
        selectedEventCoordinator.setPassword(newpw);
        selectedEventCoordinator.setEmail(txtfEmail.getText());
        pwfPassword.clear();

        eventCoordinatorModel.updateEventCoordinator(selectedEventCoordinator);

    }

    @FXML
    private void deleteUser() throws Exception {
        eventCoordinatorModel.deleteEventCoordinator(tblUsers.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void createUser() throws Exception {
        String firstName = txtfFirstName.getText();
        String lastName = txtfLastName.getText();
        String username = txtfUsername.getText();
        String password = pwfPassword.getText();
        String salt = BCrypt.gensalt(14);
        String pw = BCrypt.hashpw(password,salt);
        String email = txtfEmail.getText();
        clearFields();
        EventCoordinator eventCoordinator = new EventCoordinator(firstName,lastName,username,pw,email);
        eventCoordinatorModel.createEventCoordinator(eventCoordinator);
    }

    public void clearFields() {
        txtfFirstName.clear();
        txtfLastName.clear();
        txtfEmail.clear();
        txtfUsername.clear();
        pwfPassword.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblUsers.setItems(eventCoordinatorModel.getObservableEventCoordinators());
        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtfFirstName.setText(newValue.getFirstName());
                txtfLastName.setText(newValue.getLastName());
                txtfUsername.setText(newValue.getUsername());
                txtfEmail.setText(newValue.getEmail());
            } else {
                clearFields();
            }
        });
    }
}
