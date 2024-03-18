package sample.GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    private ZonedDateTime date;
    private ZonedDateTime today;
    
    @FXML
    private Label lblMonth;
    @FXML
    private FlowPane fpCalendar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    private void drawCalendar() {
        lblMonth.setText(String.valueOf(date.getMonth()));

        double calendarWidth = fpCalendar.getPrefWidth();
        double calendarHeight = fpCalendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingWidth = 1;

        int monthMaxDate = date.getMonth().maxLength();
        // Leap year check
        if (date.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }


    }
}
