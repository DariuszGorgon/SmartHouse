package sample;

public class UpdateGui {

    //interfesy
    private MyInterface testInterface;

    UpdateGui( MyInterface myInterface) {
        this.testInterface = myInterface;


    }

    public void checkParam(char message, String setMessage) {

        switch (message) {
            case ((char)0x2C):
                this.testInterface.setTemp1(setMessage);
                //platform.runLater(10);
                break;
            case((char)0x2D):
                this.testInterface.setTemp2(setMessage);
                break;
        }

    }
    public void visibleLog(String message){
        this.testInterface.editListwiev(message);
    }
}
