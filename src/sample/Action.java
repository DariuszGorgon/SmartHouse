package sample;

import java.awt.*;

public class Action {

    public int command;
    public String value1;
    public int valueSize;
    public String Setting1;
    public int commandThs;

    Action (String name, float value, String threshold, String setting) {
        switch (name) {
            case "Temp. 1":
                if (threshold.matches("Próg górny")) {
                    command = 0x2D; // nazwa parametru
                    commandThs = 0x2E;
                } else {
                    command = 0x2F;
                    commandThs = 0x30;
                }
                break;
            case "Temp. 2":
                if (threshold.matches("Próg górny")) {
                    command = 0x33;
                    commandThs = 0x34;
                } else {
                    command = 0x35;
                    commandThs = 0x36;
                }
                break;
            case "Lux":
                if (threshold.matches("Próg górny")) {
                    command = 0x39;
                    commandThs = 0x3A;
                } else {
                    command = 0x3B;
                    commandThs = 0x3C;
                }
                break;
        }
        switch (setting) {
            case "Włącz Klimatyzację":
                Setting1 = "1";
                break;
            case "Wyłącz Klimatyzację":
                Setting1 = "2";
                break;
            case "Otwórz okno":
                Setting1 = "3";
                break;
            case "Zamknij okno":
                Setting1 = "4";
                break;
        }

        value1 = Float.toString(value); //wartość float zmieniona na Stringa
        valueSize =value1.length(); //długość stringa
        //System.out.println(command+"="+value+"\n"+valueSize+"="+Setting1);

    }

    public String getThres(){
        String packetRet = new String( "$KG" +(char)command+"WI"+valueSize+value1);
        return packetRet;
    }
    public String getSett(){
        String packetRet = new String( "$KG" +(char)commandThs+"WI"+valueSize+value1);// do zmienienia wartości Settingów
        return packetRet;
    }
//
//    public void setName(String name) {
//        this.command = name;
//    }
//
//    public void setValue(int value) {
//        this.value1 = value;
//    }
//
//    public void setThreshold(String threshold) {
//        this.valueSize = threshold;
//    }
//
//    public void setSetting(String setting) {
//        Setting1 = setting;
//    }
//
//    public String getName() {
//        return command;
//    }
//
//    public float getValue() {
//        return value1;
//    }
//
//    public String getThreshold() {
//        return valueSize;
//    }
//
//    public String getSetting() {
//        return Setting1;
//    }

}

