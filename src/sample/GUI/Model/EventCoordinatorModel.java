package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.EventCoordinator;
import sample.BLL.EventCoordinatorManager;

import java.util.List;

public class EventCoordinatorModel {
    private EventCoordinatorManager eventCoordinatorManager;
    private ObservableList<EventCoordinator> observableEventCoordinators;

    public EventCoordinatorModel() throws Exception {
        eventCoordinatorManager = new EventCoordinatorManager();
        observableEventCoordinators = FXCollections.observableArrayList();
        observableEventCoordinators.addAll(eventCoordinatorManager.getAllEventCoordinators());
    }

    public ObservableList<EventCoordinator> getObservableEventCoordinators() {
        return observableEventCoordinators;
    }

    public EventCoordinator createEventCoordinator(EventCoordinator newEventCoordinator) throws Exception {
        eventCoordinatorManager.createEventCoordinator(newEventCoordinator);
        observableEventCoordinators.add(newEventCoordinator);
        return newEventCoordinator;
    }

    public void updateEventCoordinator(EventCoordinator selectedEventCoordinator) throws Exception {
        eventCoordinatorManager.updateEventCoordinator(selectedEventCoordinator);
    }

    public void deleteEventCoordinator(EventCoordinator selectedEventCoordinator) throws Exception {
        eventCoordinatorManager.deleteEventCoordinator(selectedEventCoordinator);
        observableEventCoordinators.remove(selectedEventCoordinator);
    }

    public void searchEventCoordinator(String query) throws Exception {
        List<EventCoordinator> searchresult = eventCoordinatorManager.searchEventCoordinator(query);
        observableEventCoordinators.clear();
        observableEventCoordinators.addAll(searchresult);

    }
}
