package sample;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/** Controls the login screen */
public class LoginWindow {
    @FXML private TextField user;
    @FXML private TextField password;
    @FXML private Button loginButton;
        private MyInterface checkConnect;

    @FXML
    public void initialize() {

       // checkConnect = this;
    }

     @FXML
     void ConnectButton(ActionEvent actionEvent) throws IOException {

         Client connectClient = new Client(user.getText(),checkConnect);
         connectClient.openWindow = false;
         connectClient.sendMes("CONNECT", Integer.parseInt(password.getText()));

         String sessionID = authorize(connectClient.reciveMessage);
         if (sessionID != null) {


             Main.getInstance().setSceneSettings();

         } else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("SmartHouse");
             alert.setHeaderText("Błąd połączenia");
             alert.setContentText("Sprawdź czy podałeś dobry adres i port");
             alert.showAndWait();

         }

    }

    private String authorize(String compare) {
        return
                "CONNECT_OK\n".equals(compare)
                        ? generateSessionID()
                        : null;
    }

    private static int sessionID = 0;

    private String generateSessionID() {
        sessionID++;
        return "xyzzy - session " + sessionID;
    }
}
