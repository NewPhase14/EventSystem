package sample.BE;

import java.time.LocalDate;

public class Ticket {

    private int id;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private int eventId;
    private String barCode;
    private int eventCoordinatorId;

    public Ticket(int id, String barCode) {
        this.id = id;
        this.barCode = barCode;
    }

    public Ticket(int id,String customerFirstName, String customerLastName, String customerEmail, int eventId, String barCode, int eventCoordinatorId) {
        this.id = id;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.eventId = eventId;
        this.barCode = barCode;
        this.eventCoordinatorId = eventCoordinatorId;
    }
    public Ticket(String customerFirstName, String customerLastName, String customerEmail, int eventId, String barCode, int eventCoordinatorId) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.eventId = eventId;
        this.barCode = barCode;
        this.eventCoordinatorId = eventCoordinatorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int getEventCoordinatorId() {
        return eventCoordinatorId;
    }

    public void setEventCoordinatorId(int eventCoordinatorId) {
        this.eventCoordinatorId = eventCoordinatorId;
    }
}
