package sample.BLL;

import sample.BE.EventCoordinator;
import sample.BLL.util.StatisticsSearcher;
import sample.DAL.EventCoordinatorDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventCoordinatorManager {
    private final EventCoordinatorDAO eventCoordinatorDAO;
    private StatisticsSearcher statisticsSearcher;

    private List<EventCoordinator> allEventCoordinators = new ArrayList<>();

    public EventCoordinatorManager() throws IOException {
        eventCoordinatorDAO = new EventCoordinatorDAO();
    }

    public List<EventCoordinator> getAllEventCoordinators() throws Exception {
        allEventCoordinators = eventCoordinatorDAO.getAllEventCoordinators();
        return allEventCoordinators;
    }

    public EventCoordinator createEventCoordinator(EventCoordinator newEventCoordinator) throws Exception {
        return eventCoordinatorDAO.createEventCoordinator(newEventCoordinator);
    }

    public void updateEventCoordinator(EventCoordinator selectedEventCoordinator) throws Exception {
        eventCoordinatorDAO.updateEventCoordinator(selectedEventCoordinator);
    }
    public void deleteEventCoordinator(EventCoordinator selectedEventCoordinator) throws Exception {
        eventCoordinatorDAO.deleteEventCoordinator(selectedEventCoordinator);
    }

    public List<EventCoordinator> searchEventCoordinator(String query) throws Exception {
        List<EventCoordinator> allEventCoordinators = getAllEventCoordinators();
        return statisticsSearcher.search(allEventCoordinators, query);
    }
}
