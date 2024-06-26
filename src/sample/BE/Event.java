package sample.BE;

import java.time.LocalDate;

public class Event {

    private int id;
    private String name;
    private int tickets;
    private int availableTickets;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startTime;
    private String endTime;
    private String description;
    private int eventCoordinatorId;

    public Event(int id, String name, int tickets, String location, LocalDate startDate, LocalDate endDate, String startTime, String endTime, String description, int eventCoordinatorId, int availableTickets) {
        this.id = id;
        this.name = name;
        this.tickets = tickets;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.eventCoordinatorId = eventCoordinatorId;
        this.availableTickets = availableTickets;
    }

    public Event(String name, int tickets, String location, LocalDate startDate, LocalDate endDate, String startTime, String endTime, String description, int eventCoordinatorId) {
        this.name = name;
        this.tickets = tickets;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.eventCoordinatorId = eventCoordinatorId;
        this.availableTickets = tickets;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEventcoordinator() {
        return eventCoordinatorId;
    }

    @Override
    public String toString() {
        return  name;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int soldTickets) {
       availableTickets -= soldTickets;
    }

    public void sellTicket() {
        availableTickets--;
    }

}
