package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.GUI.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    private double mousePosX = 0;
    private double mousePosY = 0;
    @FXML
    private HBox hboxTopBar;
    @FXML
    private BorderPane borderAdmin;

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

    private void draggableWindow() {
        hboxTopBar.setOnMousePressed(e -> {
            mousePosX = e.getSceneX();
            mousePosY = e.getSceneY();
        });

        hboxTopBar.setOnMouseDragged(e -> {
            Main.s.setX(e.getScreenX()-mousePosX);
            Main.s.setY(e.getScreenY()-mousePosY);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            draggableWindow();
    }

    @FXML
    private void openHomeWindow(ActionEvent event) {

    }

    @FXML
    private void openAddUserWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/AddUserWindow.fxml"));
        VBox vBox = loader.load();

        borderAdmin.setCenter(vBox);
    }

    @FXML
    private void openManageUserWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ManageUserWindow.fxml"));
        VBox vBox = loader.load();

        borderAdmin.setCenter(vBox);
    }

    @FXML
    private void openCalendarWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/CalendarWindow.fxml"));
        VBox vBox = loader.load();

        borderAdmin.setCenter(vBox);
    }

    @FXML
    private void openProfileWindow() {
    }
}
