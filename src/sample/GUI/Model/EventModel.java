package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Event;
import sample.BLL.EventManager;

import java.util.List;

public class EventModel {
    private EventManager eventManager;
    private ObservableList<Event> observableEvents;

    public EventModel() throws Exception {
        eventManager = new EventManager();
        observableEvents = FXCollections.observableArrayList();
        observableEvents.addAll(eventManager.getAllEvents());
    }

    public ObservableList<Event> getObservableEvents() {
        return observableEvents;
    }

    public Event createEvent(Event newEvent) throws Exception {
        eventManager.createEvent(newEvent);
        observableEvents.add(newEvent);
        return newEvent;
    }

    public void updateEvent(Event selectedEvent) throws Exception {
        eventManager.updateEvent(selectedEvent);
    }

    public void deleteEvent(Event selectedEvent) throws Exception {
        eventManager.deleteEvent(selectedEvent);
        observableEvents.remove(selectedEvent);
    }


}
