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

        String newAdress = new String("127.0.0.1");
        int newPort = 56635;
        //
        Client client = new Client(newAdress);

        String newString = client.sendMes("LEDON", newPort);


        tabInfo.setText(newString);


    }
}
