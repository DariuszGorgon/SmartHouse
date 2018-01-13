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

import static java.lang.Thread.sleep;

public class Controller implements MyInterface {

    public TableColumn tabInfo;


    @FXML
    ListView<String> eventsListView;

    private MyInterface myInterface;

    //fragmenty do log4j
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    static Logger log = Logger.getLogger(Controller.class.getName());


    @FXML
    public void initialize()
    {
        eventsListView.setPlaceholder(new Label("Brak zdarzeń"));
        myInterface = this;
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
        Client client = new Client(newAdress,myInterface);
        client.openWindow = true;
        client.sendMes("LEDON", newPort);
        //Tworzenie nowego wątku odpowiedzialnego za ciągłe odczytywanie temperatuty
        Client tempClient = new Client(newAdress,myInterface);
        Thread newThread = new Thread(()->
        {
            while(true){
            try {
                tempClient.sendMes("TEMP", newPort);
                sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }

        });
        newThread.setName("Temperature Thread");
        newThread.start();

        log.info("konsola");





    }


}
