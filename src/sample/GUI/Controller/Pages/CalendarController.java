package sample.GUI.Controller.Pages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class CalendarController implements Initializable {

    private ZonedDateTime dateFocus;
    private ZonedDateTime today;

    @FXML
    private Label lblYear;
    @FXML
    private Label lblMonth;
    @FXML
    private FlowPane fpCalendar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    private void drawCalendar() {
        lblYear.setText(String.valueOf(dateFocus.getYear()));
        lblMonth.setText(String.valueOf(dateFocus.getMonth()));

        double calendarWidth = fpCalendar.getPrefWidth();
        double calendarHeight = fpCalendar.getPrefHeight();
        double strokeWidth = 1;
        double spacingWidth = fpCalendar.getVgap();
        double spacingHeight = fpCalendar.getHgap();

        int monthMaxDate = dateFocus.getMonth().maxLength();
        // Leap year check
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }

        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0, dateFocus.getZone()).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                int calculateDate = (j + 2) + (7 * i);

                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.rgb(75,75,75));
                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(strokeWidth);
                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingHeight;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingWidth;
                rectangle.setHeight(rectangleHeight);
                stackPane.getChildren().add(rectangle);

                if (calculateDate > dateOffset) {
                    int currentDate = calculateDate - dateOffset;
                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        date.setFill(Color.WHITE);
                        date.setFont(Font.font("Anta"));
                        double textTranslationX = (rectangleWidth / 2) * 0.75;
                        date.setTranslateX(textTranslationX);
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);
                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        rectangle.setFill(Color.rgb(240,186,64));
                    }
                }
                fpCalendar.getChildren().add(stackPane);
            }
        }
    }

    @FXML
    private void OnClickLeftMonth(ActionEvent actionEvent) {
        dateFocus = dateFocus.minusMonths(1);
        fpCalendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    private void OnClickRightMonth(ActionEvent actionEvent) {
        dateFocus = dateFocus.plusMonths(1);
        fpCalendar.getChildren().clear();
        drawCalendar();
    }
}
