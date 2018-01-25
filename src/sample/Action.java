package sample;

import java.awt.*;

public class Action {

    private int command;
    private String value1;
    private int valueSize;
    private int takeSetting;
    public int takeStting1;
    public int commandThs;
    //Akcje zdefiniowane
    private int action1=0x5C;
    private int action2=0x5D;
    private int action3=0x5E;

    Action (String name, float value, String threshold, String setting, String setting1) {
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
                    takeSetting = 0x50;
                    break;
                case "Wyłącz Klimatyzację":
                    takeSetting = 0x51;
                    break;
                case "Otwórz Okno":
                    takeSetting = 0x52;
                    break;
                case "Zamknij Okno":
                    takeSetting = 0x53;
                    break;
                case "Włącz Ogrzewanie":
                    takeSetting = 0x54;
                    break;
                case "Wyłącz Ogrzewanie":
                    takeSetting = 0x55;
                    break;
        }
        switch (setting1) {
            case "Zapal Swiatło":
                takeStting1 = 0x56;
                break;
            case "Wyłącz Swiatło":
                takeStting1 = 0x57;
                break;
            case "Włącz Pompę":
                takeStting1 = 0x58;
                break;
            case "Wyłącz Pompę":
                takeStting1 = 0x59;
                break;
            case "Zamknij Garaż":
                takeStting1 = 0x5A;
                break;
            case "Otwórz Garaż":
                takeStting1 = 0x5B;
                break;
        }

        value1 = Float.toString(value); //wartość float zmieniona na Stringa
        valueSize =value1.length(); //długość stringa

        //System.out.println(command+"="+value+"\n"+valueSize+"="+Setting1);

    }

    public String getThres(){
        return "$KG" + (char) command + "WF" + (char)valueSize + value1;
    }
    public String getSett(){
        return "$KG" + (char) commandThs + "WI" + (char)2 + (char)action1+(char)takeSetting;
    }
    public String getSett1(){
        return "$KG" + (char) commandThs + "WI" + (char)2  + (char)action2+(char)takeStting1;
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

