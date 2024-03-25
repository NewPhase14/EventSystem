package sample.GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class EventCreationController {


    @FXML
    private VBox window;

    @FXML
    private void returnToHomeWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pages/HomeWindow.fxml"));
        VBox homeWindow = loader.load();
        BorderPane adminWindow = (BorderPane) window.getScene().getRoot();
        adminWindow.setCenter(homeWindow);
    }
}
