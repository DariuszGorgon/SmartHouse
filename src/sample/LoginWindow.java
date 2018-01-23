package sample;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/** Controls the login screen */
public class LoginWindow {

    private static LoginWindow instance;

    @FXML private TextField user;
    @FXML private TextField password;
    @FXML private Button loginButton;
        private MyInterface checkConnect;

    public String getIpAddres() {
        return ipAddres;
    }

    String ipAddres;

    public Integer getPortNum() {
        return portNum;
    }

    Integer portNum;

    @FXML
    public void initialize() {


    }

     @FXML
     void ConnectButton(ActionEvent actionEvent) throws IOException {

        // Client connectClient = new Client(user.getText(),checkConnect);
         //ipAddres = user.getText();
        // portNum = Integer.parseInt(password.getText());
         Client connectClient = new Client("192.168.0.14",checkConnect);
         ipAddres = "192.168.0.14";
         portNum = 8888;
         connectClient.port = portNum;
         connectClient.openWindow = false;
         //"$KG" +0xFF+0x52+0x53+0 -- ramka gdzie 0x2A to "CONNECT"

         connectClient.sendMes("$KG" +"\u002A"+"RS"+"\u0000");
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
                "CONNECT_OK".equals(compare)
                        ? generateSessionID()
                        : null;
    }

    private static int sessionID = 0;

    private String generateSessionID() {
        sessionID++;
        return "xyzzy - session " + sessionID;
    }
    public LoginWindow() {
        instance = this;
    }
    static LoginWindow getInstance() {
        return instance;
    }
}
