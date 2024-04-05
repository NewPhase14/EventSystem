package sample.BE;

import java.time.LocalDate;

public class Ticket {

    private int id;
    private String barCode;

    public Ticket(int id, String barCode) {
        this.id = id;
        this.barCode = barCode;
    }

    private int getId() {
        return id;
    }
}
