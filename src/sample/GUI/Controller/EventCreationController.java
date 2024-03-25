package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class EventCreationController {


    public TextField txtEventName, txtAvailableTickets, txtEventLocation, txtEventStartTime, txtEventEndTime;
    public DatePicker dpEventStart, dpEventEnd;
    public TextArea txtxaEventDescription;
    @FXML
    private VBox window;

    @FXML
    private void returnToHomeWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pages/HomeWindow.fxml"));
        VBox homeWindow = loader.load();
        BorderPane adminWindow = (BorderPane) window.getScene().getRoot();
        adminWindow.setCenter(homeWindow);
    }

    public void createEvent(ActionEvent actionEvent) {

    }
}
