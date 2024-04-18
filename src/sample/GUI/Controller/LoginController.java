package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import sample.BE.Admin;
import sample.BE.EventCoordinator;
import sample.GUI.Main;
import sample.GUI.Model.AdminModel;
import sample.GUI.Model.EventCoordinatorModel;
import sample.GUI.Model.LoggedInModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private double mousePosX = 0;
    private double mousePosY = 0;
    @FXML
    private PasswordField pwfPassword;
    @FXML
    private TextField txfUsername;
    @FXML
    private HBox hBoxTopBar;
    @FXML
    private Label lblError;
    private final LoggedInModel loggedInModel;
    private final EventCoordinatorModel eventCoordinatorModel;
    private final AdminModel adminModel;
    public LoginController() throws Exception {
        loggedInModel = LoggedInModel.getInstance();
        eventCoordinatorModel = new EventCoordinatorModel();
        adminModel = new AdminModel();
    }
    @FXML
    private void minimizeButton(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @FXML
    private void closeButton(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    private void loginButton() throws IOException {
        for (Admin admin : adminModel.getObservableEventCoordinators()) {
            if (txfUsername.getText().equals(admin.getUsername())) {
                if (BCrypt.checkpw(pwfPassword.getText(),admin.getPassword())) {
                    loggedInModel.setUser(admin, null);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu/AdminWindow.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    Stage stage = Main.s;
                    stage.setScene(scene);
                    stage.getIcons().add(new Image("/icon/EASVLogo.png"));
                    stage.centerOnScreen();
                    stage.show();
                }
            } else if (txfUsername.getText().isEmpty() || pwfPassword.getText().isEmpty()){
                lblError.setText("Fill in both Username and Password");
            }
            else {
                lblError.setText("Wrong Username or Password");
            }
        }
        for (EventCoordinator eventCoordinator : eventCoordinatorModel.getObservableEventCoordinators()) {
            if (txfUsername.getText().equals(eventCoordinator.getUsername())) {
                if (BCrypt.checkpw(pwfPassword.getText(), eventCoordinator.getPassword())) {
                    loggedInModel.setUser(null, eventCoordinator);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu/EventCoordinatorWindow.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    Stage stage = Main.s;
                    stage.setScene(scene);
                    stage.getIcons().add(new Image("/icon/EASVLogo.png"));
                    stage.centerOnScreen();
                    stage.show();
                }
            } else if (txfUsername.getText().isEmpty() || pwfPassword.getText().isEmpty()){
                lblError.setText("Fill in both Username and Password");
            }
            else {
                lblError.setText("Wrong Username or Password");
            }
        }

    }

    private void draggableWindow(){
        hBoxTopBar.setOnMousePressed(e -> {
            mousePosX = e.getSceneX();
            mousePosY = e.getSceneY();
        });

        hBoxTopBar.setOnMouseDragged(e -> {
            Main.s.setX(e.getScreenX()-mousePosX);
            Main.s.setY(e.getScreenY()-mousePosY);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txfUsername.setText("JMoritz");
        pwfPassword.setText("hej123");
        draggableWindow();
    }
}
