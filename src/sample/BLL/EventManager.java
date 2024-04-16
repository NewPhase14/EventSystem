package sample.BLL;

import sample.BE.Event;
import sample.BE.EventCoordinator;
import sample.DAL.EventDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final EventDAO eventDAO;

    private List<Event> allEvents = new ArrayList<>();

    public EventManager() throws IOException {
        eventDAO = new EventDAO();
    }

    public List<Event> getAllEvents() throws Exception {
        allEvents = eventDAO.getAllEvents();
        return allEvents;
    }

    public Event createEvent(Event newEvent) throws Exception {
        return eventDAO.createEvent(newEvent);
    }

    public void updateEvent(Event selectedEvent) throws Exception {
        eventDAO.updateEvent(selectedEvent);
    }
    public void deleteEvent(Event selectedEvent) throws Exception {
        eventDAO.deleteEvent(selectedEvent);
    }

    public int getManagedEvents(EventCoordinator eventCoordinator) throws Exception {
        return eventDAO.getManagedEvents(eventCoordinator);
    }
}
