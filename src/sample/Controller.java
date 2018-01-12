package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldListCell;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class Controller implements Myinteface {

    public TableColumn tabInfo;

    private  Client client;
    Myinteface myinteface;
    @FXML
    ListView<String> eventsListView;

    //fragmenty do log4j
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    static Logger log = Logger.getLogger(Controller.class.getName());


    @FXML
    public void initialize()
    {
        eventsListView.setPlaceholder(new Label("Brak zdarzeń"));
    }
    @Override
    public void editListwiev(String loggin)
    {
        eventsListView.setCellFactory(TextFieldListCell.forListView());
        eventsListView.getItems().add(loggin);
    }

    public void sendInit(ActionEvent actionEvent) throws IOException {

        String newAdress = "127.0.0.1";
        int newPort = 56635;
        //
        client = new Client(newAdress,myinteface);
        client.sendMes("LEDON", newPort);

        log.info("konsola");





    }


}
