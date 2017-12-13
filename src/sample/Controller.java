package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class Controller {

    public TableColumn tabInfo;
    @FXML
    TableView eventsTableView;

    @FXML
    public void initialize()
    {
        eventsTableView.setPlaceholder(new Label("Brak zdarzeń"));
    }

    public void sendInit(ActionEvent actionEvent) throws IOException {

        String newAdress = new String("192.168.0.14");
        int newPort = 8888;
        //
        Client client = new Client(newAdress);

        String newString = client.sendMes("LEDON", newPort);


        tabInfo.setText(newString);


    }
}
