package sample.GUI.Controller.Pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.BE.EventCoordinator;
import sample.BE.Ticket;
import sample.GUI.Model.EventCoordinatorModel;
import sample.GUI.Model.EventModel;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsWindowController implements Initializable {

    @FXML
    private TextField txtSearchfield, txtTicketsSold, txtEventsManaged;

    @FXML
    private ListView<EventCoordinator> lstEventcoordinators;

    @FXML
    private Label lblEventcoordinatorName;

    private EventCoordinatorModel eventCoordinatorModel;

    private EventModel eventModel;

    @FXML
    private BarChart<String, Integer> barTicketsSold;

    public StatisticsWindowController() throws Exception {
        this.eventCoordinatorModel = new EventCoordinatorModel();
        this.eventModel = new EventModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchBar();

        lstEventcoordinators.setItems(eventCoordinatorModel.getObservableEventCoordinators());

        lstEventcoordinators.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    if (newValue != null){
                        lblEventcoordinatorName.setText(newValue.getFirstName() +" "+ newValue.getLastName());
                        try {
                            txtTicketsSold.setText(String.valueOf(eventModel.getSoldTicketsByEventCoordinator(newValue)));
                            txtEventsManaged.setText(String.valueOf(eventModel.getManagedEvents(newValue)));
                            drawBarChart(newValue);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }));
        txtEventsManaged.setFocusTraversable(false);
        txtTicketsSold.setFocusTraversable(false);
    }

    private void searchBar(){
        txtSearchfield.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                eventCoordinatorModel.searchEventCoordinator(newValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void drawBarChart(EventCoordinator eventCoordinator) throws Exception {
        barTicketsSold.getData().clear();

        Axis<Integer> yAxis = barTicketsSold.getYAxis();
        yAxis.setLabel("Tickets sold");

        XYChart.Series<String, Integer> selectedEventCBar = new XYChart.Series<>();
        selectedEventCBar.setName(eventCoordinator.getFirstName());
        selectedEventCBar.getData().add(new XYChart.Data<>(eventCoordinator.getFirstName(), eventModel.getSoldTicketsByEventCoordinator(eventCoordinator)));
        barTicketsSold.getData().add(selectedEventCBar);

        XYChart.Series<String, Integer> totalTicketsBar = new XYChart.Series<>();
        totalTicketsBar.getData().add(new XYChart.Data<>("Total", eventModel.getAllSoldTickets()));
        totalTicketsBar.setName("Total");
        barTicketsSold.getData().add(totalTicketsBar);

    }

}
