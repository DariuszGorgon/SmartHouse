package sample;

import javafx.scene.control.ListView;

public interface MyInterface {

    //metoda obsługi odpowiedzi
    void editListwiev(String loggin);
    void setTemp1(String checkT1);
    void setTemp2(String checkT2);
    void setLux(String lux1);
    void setLuxLed(boolean check);
    String getTemp2();
    String getTemp1();
    void setWaterLed(boolean check);

    void setLed2up(boolean check);
    void setTempTres2up(String checkThres);
    void setLed2down(boolean check);
    void setTempTres2down(String checkThres);
    void setLed1up(boolean check);
    void setTempTres1up(String checkThres);
    void setLed1Down(boolean check);
    void setTempTres1down(String checkThres);
    //gettery
    String getTempTres2up() ;
    String getTempTres2down() ;
    String getTempTres1up() ;
    String getTempTres1down() ;




}
