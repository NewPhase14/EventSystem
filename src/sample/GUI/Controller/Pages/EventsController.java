package sample.GUI.Controller.Pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.BE.Event;
import sample.GUI.Model.EventModel;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EventsController implements Initializable {

    private final EventModel eventModel;

    public EventsController() throws Exception {
        eventModel = new EventModel();
    }

    @FXML
    private TableView<Event> tblEvents;
    @FXML
    private TableColumn<Event, String> colName, colLocation, colStartTime, colEndTime;
    @FXML
    private TableColumn<Event, Integer> colTickets;
    @FXML
    private TableColumn<Event, Date> colStartDate, colEndDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colTickets.setCellValueFactory(new PropertyValueFactory<>("tickets"));
        tblEvents.setItems(eventModel.getObservableEvents());
    }
}
