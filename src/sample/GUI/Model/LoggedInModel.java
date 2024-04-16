package sample.GUI.Model;

import sample.BE.Admin;
import sample.BE.EventCoordinator;

public class LoggedInModel {
    private static LoggedInModel instance;
    private boolean loggedIn;
    private Admin admin;
    private EventCoordinator eventCoordinator;

    // Private constructor to prevent instantiation from outside
    private LoggedInModel() {
        loggedIn = false;
    }

    // Method to get the instance of LoggedInModel
    public static LoggedInModel getInstance() {
        if (instance == null) {
            instance = new LoggedInModel();

        }
        return instance;
    }

    // Method to set the login status
    public void setLoggedIn(boolean status) {
        loggedIn = status;
    }

    // Method to get the login status
    public boolean isLoggedIn() {
        return loggedIn;
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
