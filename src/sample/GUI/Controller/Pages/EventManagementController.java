package sample.GUI.Controller.Pages;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sample.BE.Event;
import sample.GUI.Model.EventModel;
import sample.GUI.Model.LoggedInModel;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EventManagementController implements Initializable {

    @FXML
    private Label lblAlert;

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

    /*
    Fix Exception handling
    EventcoordinatorID er hardcoded - fix when possible
    */
    public EventManagementController() throws Exception {
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
                clearFields();
            }
        });


        //Only numbers can be written
        txtAvailableTickets.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtAvailableTickets.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }



    public void createEvent() throws Exception {
        if (!txtEventName.getText().isEmpty() && !txtAvailableTickets.getText().isEmpty() && !txtEventLocation.getText().isEmpty() && dpEventStart != null && dpEventEnd != null && !txtEventStartTime.getText().isEmpty() && !txtEventEndTime.getText().isEmpty()){
            String name = txtEventName.getText();
            int tickets = Integer.parseInt(txtAvailableTickets.getText());
            String location = txtEventLocation.getText();
            LocalDate startDate = dpEventStart.getValue();
            LocalDate endDate = dpEventEnd.getValue();
            String startTime = txtEventStartTime.getText();
            String endTime = txtEventEndTime.getText();
            String description = txtaEventDescription.getText();
            if(startTime.length() == 5 && endTime.length() == 5) {
                Event event = new Event(name, tickets, location, startDate, endDate, startTime, endTime, description, LoggedInModel.getInstance().getEventCoordinator().getId());
                eventModel.createEvent(event);
                clearFields();
            } else {
                lblAlert.setText("Wrong time format");
            }
        } else {
            lblAlert.setText("Fill out all fields");
        }
    }

    public void updateEvent() throws Exception {
        Event selectedEvent = lstEvents.getSelectionModel().getSelectedItem();
        if (selectedEvent != null){
            selectedEvent.setName(txtEventName.getText());
            selectedEvent.setTickets(Integer.parseInt(txtAvailableTickets.getText()));
            selectedEvent.setLocation(txtEventLocation.getText());
            selectedEvent.setStartDate(dpEventStart.getValue());
            selectedEvent.setEndDate(dpEventEnd.getValue());
            selectedEvent.setStartTime(txtEventStartTime.getText());
            selectedEvent.setEndTime(txtEventEndTime.getText());
            selectedEvent.setDescription(txtaEventDescription.getText());

            eventModel.updateEvent(selectedEvent);
            clearFields();
        }
    }
    public void deleteEvent() throws Exception {
        Event selectedEvent = lstEvents.getSelectionModel().getSelectedItem();
        if (selectedEvent != null){
            eventModel.deleteEvent(selectedEvent);
            lstEvents.getItems().remove(selectedEvent);
        }
        clearFields();
    }

    public void clearFields() {
        txtAvailableTickets.clear();
        txtEventLocation.clear();
        txtEventName.clear();
        txtEventLocation.clear();
        txtEventEndTime.clear();
        txtEventStartTime.clear();
        txtaEventDescription.clear();
        dpEventEnd.setValue(null);
        dpEventStart.setValue(null);
        lstEvents.getSelectionModel().clearSelection();
        lblAlert.setText("");
    }
}
