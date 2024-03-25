package sample.GUI.Controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sample.BE.Event;
import sample.GUI.Model.EventModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EventUpdateDeleteController implements Initializable {

    @FXML
    private ListView<Event> lstEvents;

    @FXML
    private TextField txtEventName, txtAvailableTickets, txtEventLocation, txtEventStartTime, txtEventEndTime;

    @FXML
    private DatePicker dpEventStart, dpEventEnd;

    @FXML
    private TextArea txtaEventDescription;

    @FXML
    private VBox window;

    private EventModel eventModel;

    public EventUpdateDeleteController() throws Exception {
        this.eventModel = new EventModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lstEvents.setItems(eventModel.getObservableEvents());

        lstEvents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                txtEventName.setText(newValue.getName());
                txtAvailableTickets.setText(String.valueOf(newValue.getTickets()));
                txtEventLocation.setText(newValue.getLocation());
                dpEventStart.setValue(newValue.getStartDate());
                dpEventEnd.setValue(newValue.getEndDate());
                txtEventStartTime.setText(newValue.getStartTime());
                txtEventEndTime.setText(newValue.getEndTime());
                txtaEventDescription.setText(newValue.getDescription());
            } else {
                txtEventName.setText("");
                txtAvailableTickets.setText("");
                txtEventLocation.setText("");
                dpEventStart.setValue(null);
                dpEventEnd.setValue(null);
                txtEventStartTime.setText("");
                txtEventEndTime.setText("");
                txtaEventDescription.setText("");
            }

        });
    }


    public void returnToHomeWindow(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pages/HomeWindow.fxml"));
        VBox homeWindow = loader.load();
        BorderPane adminWindow = (BorderPane) window.getScene().getRoot();
        adminWindow.setCenter(homeWindow);
    }

    public void createEvent(ActionEvent actionEvent) {

    }

    public void updateEvent(ActionEvent actionEvent) {

    }
    public void deleteEvent(ActionEvent actionEvent) throws Exception {
        Event selectedEvent = lstEvents.getSelectionModel().getSelectedItem();
        if (selectedEvent != null){
            eventModel.deleteEvent(selectedEvent);
            lstEvents.getItems().remove(selectedEvent);
        }

    }
}
