package sample.GUI.Controller.Pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sample.BE.Event;
import sample.GUI.Model.EventModel;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EventsController implements Initializable {

    private final EventModel eventModel;

    @FXML
    private VBox window;

    public EventsController() throws Exception {
        eventModel = new EventModel();
    }

    @FXML
    private TableView<Event> tblEvents;
    @FXML
    private TableColumn<Event, String> colName, colLocation, colStartTime, colEndTime;
    @FXML
    private TableColumn<Event, Integer> colTickets, colAvailableTickets;
    @FXML
    private TableColumn<Event, Date> colStartDate, colEndDate;

    @FXML
    private void OnClickCreateTicket(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/pages/TicketWindow.fxml"));
        VBox vBox = loader.load();
        TicketController ticketController = loader.getController();
        Event event = tblEvents.getSelectionModel().getSelectedItem();
        ticketController.setTxtEventName(event);
        BorderPane parent = (BorderPane) window.getParent();
        parent.setCenter(vBox);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colTickets.setCellValueFactory(new PropertyValueFactory<>("tickets"));
        colAvailableTickets.setCellValueFactory(new PropertyValueFactory<>("availableTickets"));
        tblEvents.setItems(eventModel.getObservableEvents());
    }
}
