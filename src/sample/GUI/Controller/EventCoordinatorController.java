package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.GUI.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventCoordinatorController implements Initializable {
    private double mousePosX = 0;
    private double mousePosY = 0;
    @FXML
    private HBox hBoxTopBar;
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
        hBoxTopBar.setOnMousePressed(e -> {
            mousePosX = e.getSceneX();
            mousePosY = e.getSceneY();
        });

        hBoxTopBar.setOnMouseDragged(e -> {
            Main.s.setX(e.getScreenX()-mousePosX);
            Main.s.setY(e.getScreenY()-mousePosY);
        });
    }


    @FXML
    private void openEventManagementWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/pages/EventManagementWindow.fxml"));
        VBox vBox = loader.load();

        borderAdmin.setCenter(vBox);
    }


    @FXML
    private void openCalendarWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/pages/CalendarWindow.fxml"));
        VBox vBox = loader.load();

        borderAdmin.setCenter(vBox);
    }
    @FXML
    private void openEventsWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/pages/EventsWindow.fxml"));
        VBox vBox = loader.load();

        borderAdmin.setCenter(vBox);
    }

    @FXML
    private void openProfileWindow() {

    }

    @FXML
    private void logout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu/LoginWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        Stage stage = Main.s;
        stage.setScene(scene);
        stage.getIcons().add(new Image("/icon/CuteOtter.png"));
        stage.centerOnScreen();
        stage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        draggableWindow();
        try {
            openEventsWindow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openStatisticsWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/pages/StatisticsWindow.fxml"));
        VBox vBox = loader.load();

        borderAdmin.setCenter(vBox);
    }


}
