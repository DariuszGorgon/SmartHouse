package sample;

public class UpdateGui {

    //interfesy
    private MyInterface testInterface;

    UpdateGui(MyInterface myInterface) {
        this.testInterface = myInterface;


    }

    public void checkParam(String message, String setMessage) {
        String tempValue = setMessage.substring(0, 6);
        String thresValue = message.substring(7);
        switch (message.charAt(3)) {
            //Wartości dla Temp1
            case ((char) 0x2C):
                this.testInterface.setTemp1(tempValue);
                this.testInterface.setLed1up(Float.parseFloat(tempValue)>Float.parseFloat(this.testInterface.getTempTres1up()));
                this.testInterface.setLed1Down(Float.parseFloat(tempValue)<Float.parseFloat(this.testInterface.getTempTres1down()));
                break;
            case ((char) 0x2D):
                this.testInterface.setTempTres1up(thresValue);
                this.testInterface.setLed1up(Float.parseFloat(thresValue)<Float.parseFloat(this.testInterface.getTemp1()));
                break;
            case ((char) 0x2F):
                this.testInterface.setTempTres1down(thresValue);
                this.testInterface.setLed1Down(Float.parseFloat(thresValue)>Float.parseFloat(this.testInterface.getTemp1()));
                break;
            //Wartości dla Temp2
            case ((char) 0x32):
                this.testInterface.setTemp2(setMessage);
                break;
            case ((char) 0x33):
                this.testInterface.setTempTres2up(thresValue);
                break;
            case ((char) 0x35):
                this.testInterface.setTempTres2down(message.substring(7));
                break;

        }


    }

    public void visibleLog(String message) {
        this.testInterface.editListwiev(message);
    }
}
