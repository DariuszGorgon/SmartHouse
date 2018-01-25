package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Main instance;

    Stage smartStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        smartStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("login_window.fxml"));
        smartStage.setTitle("Smart House");
        smartStage.setScene(new Scene(root, 230, 120));
        smartStage.show();
            smartStage.setOnCloseRequest(event -> Controller.getInstance().setStopThread(false));
    }

    public static void main(String[] args) {
        launch(args);
    }
    void setSceneSettings() {
        try {

            smartStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("sample.fxml")),600,400));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Main() {
        instance = this;
    }
    static Main getInstance() {
        return instance;
    }
}
