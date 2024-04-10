package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import sample.BE.Event;
import sample.BLL.EventManager;
import sample.BLL.TicketManager;

import javax.mail.MessagingException;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.List;

public class EventModel {
    private TicketManager ticketManager;
    private EventManager eventManager;
    private ObservableList<Event> observableEvents;

    public EventModel() throws Exception {
        ticketManager = new TicketManager();
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

    public void createTicket(Event event, int amount, String email) throws IOException, PrinterException, MessagingException, BarcodeException, OutputException {
        ticketManager.createTicket(event, amount, email);
    }


}
