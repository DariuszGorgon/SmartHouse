package sample;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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

    private static Controller instance;

    public Label temp2;


    public void setStopThread(boolean stopThread) {
        this.stopThread = stopThread;
    }

    private volatile boolean stopThread;
    public Label temp1;
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
        stopThread = true;
        }
    @Override
    public void editListwiev(String loggin) {
        eventsListView.setCellFactory(TextFieldListCell.forListView());
        Platform.runLater(() -> eventsListView.getItems().add(loggin));
    }

    public void setTemp1(String checkT1) {
        Platform.runLater(() -> temp1.setText(checkT1));
    }

    public void setTemp2(String checkT2) {
        Platform.runLater(() -> temp2.setText(checkT2));
    }

    public void sendInit(ActionEvent actionEvent) throws IOException {

        String newAdress = "192.168.0.14";
        int newPort = 8888;
        //
        Client client = new Client(newAdress,myInterface);
        client.openWindow = true;
        client.sendMes("LEDON", newPort);
        //Tworzenie nowego wątku odpowiedzialnego za ciągłe odczytywanie temperatuty
        Client tempClient = new Client(newAdress,myInterface);
        tempClient.openWindow = true;
        Thread newThread = new Thread(()->
        {
            while(stopThread){
            try {

                tempClient.sendMes("TEMP_1", newPort);
                tempClient.sendMes("TEMP_2",newPort);
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

    public Controller() {
        instance = this;
    }
    static Controller getInstance() {
        return instance;
    }

}
