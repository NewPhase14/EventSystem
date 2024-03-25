package sample.GUI.Controller.Pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.BE.EventCoordinator;
import sample.GUI.Model.EventCoordinatorModel;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsWindowController implements Initializable {

    @FXML
    private TextField txtSearchfield, txtEventsCreated, txtTicketsSold, txtEventsManaged;

    @FXML
    private ListView<EventCoordinator> lstEventcoordinators;

    @FXML
    private Label lblEventcoordinatorName;

    private EventCoordinatorModel eventCoordinatorModel;

    public StatisticsWindowController() throws Exception {
        this.eventCoordinatorModel = new EventCoordinatorModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lstEventcoordinators.setItems(eventCoordinatorModel.getObservableEventCoordinators());

        lstEventcoordinators.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> {
                    if (newValue != null){
                        lblEventcoordinatorName.setText(newValue.getFirstName() +" "+ newValue.getLastName());
                    }
                }));
    }


}
