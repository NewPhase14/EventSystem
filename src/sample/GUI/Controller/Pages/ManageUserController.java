package sample.GUI.Controller.Pages;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import sample.BE.EventCoordinator;
import sample.GUI.Model.EventCoordinatorModel;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageUserController implements Initializable {
    @FXML
    private TableView<EventCoordinator> tblUsers;
    @FXML
    private TableColumn<EventCoordinator, String> colFirstName, colLastName, colEmail;
    @FXML
    private TextField txtfFirstName;
    @FXML
    private TextField txtfLastName;
    @FXML
    private TextField txtfUsername;
    @FXML
    private PasswordField pwfPassword;
    @FXML
    private TextField txtfEmail;

    private EventCoordinatorModel eventCoordinatorModel;

    public ManageUserController() throws Exception {
        eventCoordinatorModel = new EventCoordinatorModel();
    }

    @FXML
    private void updateUser(ActionEvent event) throws Exception {
        EventCoordinator selectedEventCoordinator = tblUsers.getSelectionModel().getSelectedItem();
        selectedEventCoordinator.setFirstName(txtfFirstName.getText());
        selectedEventCoordinator.setLastName(txtfLastName.getText());
        selectedEventCoordinator.setUsername(txtfUsername.getText());
        selectedEventCoordinator.setPassword(pwfPassword.getText());
        selectedEventCoordinator.setEmail(txtfEmail.getText());

        eventCoordinatorModel.updateEventCoordinator(selectedEventCoordinator);
    }

    @FXML
    private void deleteUser(ActionEvent event) throws Exception {
        eventCoordinatorModel.deleteEventCoordinator(tblUsers.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void createUser(ActionEvent event) throws Exception {
        String firstName = txtfFirstName.getText();
        String lastName = txtfLastName.getText();
        String username = txtfUsername.getText();
        String password = pwfPassword.getText();
        String email = txtfEmail.getText();

        EventCoordinator eventCoordinator = new EventCoordinator(firstName,lastName,username,password,email);
        eventCoordinatorModel.createEventCoordinator(eventCoordinator);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblUsers.setItems(eventCoordinatorModel.getObservableEventCoordinators());

        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtfFirstName.setText(newValue.getFirstName());
            txtfLastName.setText(newValue.getLastName());
            txtfUsername.setText(newValue.getUsername());
            pwfPassword.setText(newValue.getPassword());
            txtfEmail.setText(newValue.getEmail());
        });
    }
}
