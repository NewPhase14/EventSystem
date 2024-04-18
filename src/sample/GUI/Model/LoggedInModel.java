package sample.GUI.Model;

import sample.BE.Admin;
import sample.BE.EventCoordinator;

public class LoggedInModel {
    private static LoggedInModel instance;
    private Admin admin;
    private EventCoordinator eventCoordinator;

    public static LoggedInModel getInstance() {
        if (instance == null) {
            instance = new LoggedInModel();
        }
        return instance;
    }

    public void setUser(Admin admin, EventCoordinator eventCoordinator){
        if (admin == null) {
            this.eventCoordinator = eventCoordinator;
            this.admin = null;
        }
        if (eventCoordinator == null) {
            this.admin = admin;
            this.eventCoordinator = null;
        }
    }
    
    public Admin getAdmin(){
        return admin;
    }

    public EventCoordinator getEventCoordinator(){
        return eventCoordinator;
    }
}
