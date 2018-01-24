package sample;

import eu.hansolo.enzo.led.Led;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class Controller implements MyInterface {

    private static Controller instance;

    public Label temp2;
    public ComboBox<String> setParam;
    public TextField setTempVar;
    public ComboBox<String> thresholdValue;
    public ComboBox<String> setAction;

    //Elementy od wyświetlania tresholdów.
    public Label lux;
    public Led led2up;
    public Label tempTres2up;
    public Led led2down;
    public Label tempTres2down;
    public Led led1up;
    public Label tempTres1up;
    public Led led1Down;
    public Label tempTres1down;

    private Action stringAction;

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

        List<String> paramList = new ArrayList<>();
        paramList.add("Temp. 1");
        paramList.add("Temp. 2");
        setParam.getItems().addAll(paramList);

        List<String> threshList = new ArrayList<>();
        threshList.add("Próg górny");
        threshList.add("Próg dolny");
        thresholdValue.getItems().addAll(threshList);

        List<String> actionList = new ArrayList<>();
        actionList.add("Włącz Klimatyzację");
        actionList.add("Otwórz okno");
        actionList.add("inne takie");
        setAction.getItems().addAll(actionList);
        }

    public void sendInit(ActionEvent actionEvent) throws IOException {

        String newAdress = LoginWindow.getInstance().ipAddres;
        int newPort = LoginWindow.getInstance().portNum;

        Client client = new Client(newAdress,myInterface);
        client.openWindow = true;
        client.port =newPort;
        client.sendMes("$KG" +0xFF+0x52+0x53+0);
        //Tworzenie nowego wątku odpowiedzialnego za ciągłe odczytywanie temperatuty
        Client tempClient = new Client(newAdress, myInterface);
        tempClient.openWindow = true;
        tempClient.port = newPort;
        Thread newThread = new Thread(()->
        {
            while(stopThread){
            try {
                tempClient.sendMes("$KG"+ (char)0x2C+"RS"+(char)0x00);
               // tempClient.sendMes("TEMP_2");
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

    public void clicButton(ActionEvent actionEvent) {

        String name = setParam.getValue();
        String threshold = thresholdValue.getValue();
        String setting = setAction.getValue();
        if (!setTempVar.getText().matches("[0-9]*.[0-9]*")| setTempVar.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Pole parametru jest puste lub nie jest liczbą");
            alert.setContentText("Sprawdź czy podałeś dobre wartości");
            alert.showAndWait();

        }else{
            float value = Float.parseFloat(setTempVar.getText());
            stringAction = new Action(name,value,threshold,setting);
//            String message = stringAction.getName() +
//                    "=" + Float.toString(stringAction.getValue()) + "\n" + stringAction.getThreshold() + "=" + stringAction.getSetting();
//            System.out.println(message);
//            if(stringAction.getValue()==11){
//                setLed2up(true);
//            }

        }
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

    public void setLux(String lux1) {
        Platform.runLater(() -> lux.setText(lux1));
    }

    public void setLed2up(boolean check) {
        Platform.runLater(() -> led2up.setOn(check));
    }

    public void setTempTres2up(String checkThres) {
        Platform.runLater(() -> tempTres2up.setText(checkThres));
    }

    public void setLed2down(boolean check) {
        Platform.runLater(() -> led2down.setOn(check));
    }

    public void setTempTres2down(String checkThres) {
        Platform.runLater(() -> tempTres2down.setText(checkThres));
    }

    public void setLed1up(boolean check) {
        Platform.runLater(() -> led1up.setOn(check));
    }

    public void setTempTres1up(String checkThres) {
        Platform.runLater(() -> tempTres1up.setText(checkThres));
    }

    public void setLed1Down(boolean check) {
        Platform.runLater(() -> led1Down.setOn(check));
    }

    public void setTempTres1down(String checkThres) {
        Platform.runLater(() -> tempTres1down.setText(checkThres));
    }
}
