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
import sample.GUI.Main;
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
        if (txfUsername.getText().equals("1") && pwfPassword.getText().equals("123")) {
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
        else if (txfUsername.getText().isEmpty() || pwfPassword.getText().isEmpty()){
            lblError.setText("Fill in both Username and Password");
        }
        else {
            lblError.setText("Wrong Username or Password");
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
        txfUsername.setText("1");
        pwfPassword.setText("123");
        draggableWindow();
    }
}
