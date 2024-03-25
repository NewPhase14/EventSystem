package sample.GUI.Controller.Pages;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.BE.EventCoordinator;
import sample.GUI.Model.EventCoordinatorModel;

import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsWindowController implements Initializable {

    public TextField txtSearchfield, txtEventsCreated, txtTicketsSold, txtEventsManaged;
    public ListView<EventCoordinator> lstEventcoordinators;
    public Label lblEventcoordinatorName;

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
