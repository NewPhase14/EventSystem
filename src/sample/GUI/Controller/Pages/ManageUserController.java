package sample.GUI.Controller.Pages;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.HBox;
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
    private TextField txtfFirstName, txtfLastName, txtfUsername, txtfEmail, searchEventcoordinator;
    @FXML
    private PasswordField pwfPassword;

    private EventCoordinatorModel eventCoordinatorModel;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button createButton;
    @FXML
    private Label lblAlert;

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
        if (!txtfFirstName.getText().isEmpty() && !txtfLastName.getText().isEmpty() && !txtfUsername.getText().isEmpty() && !txtfEmail.getText().isEmpty() && !pwfPassword.getText().isEmpty()) {
            String firstName = txtfFirstName.getText();
            String lastName = txtfLastName.getText();
            String username = txtfUsername.getText();
            String password = pwfPassword.getText();
            String salt = BCrypt.gensalt(14);
            String pw = BCrypt.hashpw(password, salt);
            String email = txtfEmail.getText();
            clearFields();
            EventCoordinator eventCoordinator = new EventCoordinator(firstName, lastName, username, pw, email);
            eventCoordinatorModel.createEventCoordinator(eventCoordinator);
        } else {
            lblAlert.setText("Fill out all fields");
        }
        lblAlert.setText("");
    }

    public void clearFields() {
        txtfFirstName.clear();
        txtfLastName.clear();
        txtfEmail.clear();
        txtfUsername.clear();
        pwfPassword.clear();
    }

    private void selectedOptions() {
        createButton.setVisible(false);
        updateButton.setVisible(true);
        deleteButton.setVisible(true);
    }

    private void createOptions() {
        createButton.setVisible(true);
        deleteButton.setVisible(false);
        updateButton.setVisible(false);
    }

    private void setupTableView() {
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblUsers.setItems(eventCoordinatorModel.getObservableEventCoordinators());
    }

    private void selectedUserListener() {
        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedOptions();
                txtfFirstName.setText(newValue.getFirstName());
                txtfLastName.setText(newValue.getLastName());
                txtfUsername.setText(newValue.getUsername());
                txtfEmail.setText(newValue.getEmail());
            } else {
                clearFields();
                createOptions();
            }
        });
    }

    private void searchBarListener() {
        searchEventcoordinator.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    eventCoordinatorModel.searchEventCoordinator(newValue);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableView();
        createOptions();
        selectedUserListener();
        searchBarListener();
    }

}
