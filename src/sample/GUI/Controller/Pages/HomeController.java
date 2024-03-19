package sample.GUI.Controller.Pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomeController {

    @FXML
    private VBox window;


    @FXML
    private void openEventWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/EventWindow.fxml"));
        VBox vBox = loader.load();

        window.getChildren().setAll(vBox);
    }
}
