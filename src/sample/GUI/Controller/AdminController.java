package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.GUI.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private double mousePosX = 0;
    private double mousePosY = 0;
    @FXML
    private HBox hboxTopBar;

    @FXML
    private void closeButton(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }

    @FXML
    private void minimizeButton(ActionEvent event) {
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            hboxTopBar.setOnMousePressed(e -> {
                mousePosX = e.getSceneX();
                mousePosY = e.getSceneY();
            });

            hboxTopBar.setOnMouseDragged(e -> {
                Main.s.setX(e.getScreenX()-mousePosX);
                Main.s.setY(e.getScreenY()-mousePosY);
            });
    }
}
