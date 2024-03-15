package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.GUI.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    private double x = 0, y = 0;
    @FXML
    private PasswordField pwfPassword;
    @FXML
    private TextField txfUsername;
    @FXML
    private HBox hboxTopBar;

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
    private void loginButton(ActionEvent event) throws IOException {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (txfUsername.getText().equals("1") && pwfPassword.getText().equals("123")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminWindow.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image("/icon/CuteOtter.png"));
            stage.setTitle("Login Screen");
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
            s.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hboxTopBar.setOnMousePressed(e -> {
            x = e.getSceneX();
            y = e.getSceneY();
        });

        hboxTopBar.setOnMouseDragged(e -> {
            Main.s.setX(e.getScreenX()-x);
            Main.s.setY(e.getScreenY()-y);
        });
    }
}
