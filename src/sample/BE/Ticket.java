package sample.BE;

import java.time.LocalDate;

public class Ticket {

    private int id;
    private String eventName;
    private LocalDate eventStart;
    private String eventLocation;
    private String eventNote;
    private LocalDate eventEnd;
    private String eventAccess;

    public Ticket(int id, String eventName, LocalDate eventStart, String eventLocation, String eventNote) {
        this.id = id;
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventLocation = eventLocation;
        this.eventNote = eventNote;
    }

    public Ticket(int id, String eventName, LocalDate eventStart, String eventLocation, String eventNote, LocalDate eventEnd) {
        this.id = id;
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventLocation = eventLocation;
        this.eventNote = eventNote;
        this.eventEnd = eventEnd;
    }

    public Ticket(int id, String eventName, LocalDate eventStart, String eventLocation, String eventNote, LocalDate eventEnd, String eventAccess) {
        this.id = id;
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventLocation = eventLocation;
        this.eventNote = eventNote;
        this.eventEnd = eventEnd;
        this.eventAccess = eventAccess;
    }

    public Ticket(int id, String eventName, LocalDate eventStart, String eventLocation, String eventNote, String eventAccess) {
        this.id = id;
        this.eventName = eventName;
        this.eventStart = eventStart;
        this.eventLocation = eventLocation;
        this.eventNote = eventNote;
        this.eventAccess = eventAccess;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventStart() {
        return eventStart;
    }

    public void setEventStart(LocalDate eventStart) {
        this.eventStart = eventStart;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

    public LocalDate getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(LocalDate eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String getEventAccess() {
        return eventAccess;
    }

    public void setEventAccess(String eventAccess) {
        this.eventAccess = eventAccess;
    }
}
