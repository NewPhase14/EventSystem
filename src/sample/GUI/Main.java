package sample.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage s = new Stage();
    public static void main(String[] args) { Application.launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LoginWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/icon/CuteOtter.png"));
        primaryStage.setTitle("Login Screen");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        s = primaryStage;
        primaryStage.show();
    }
}