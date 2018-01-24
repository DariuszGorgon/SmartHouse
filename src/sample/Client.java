package sample;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import javafx.application.Platform;

import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.ServiceMode;
import java.io.*;
import java.net.*;

public class Client {

    DatagramSocket newSocket;
    DatagramPacket outputStream, inputStream;
    InetAddress inetAddress;
    String reciveMessage;
    boolean openWindow;
    Integer port;


    //interfesy
    private MyInterface myInterface;

    private byte [] message;
    byte [] messageIn = new byte[40];
    UpdateGui updateGui;

     Client(String Addr,MyInterface myInterface) throws IOException {

        this.myInterface = myInterface;
         //myInterface =this;

        try {
            newSocket = new DatagramSocket();
            inetAddress = InetAddress.getByName(Addr);
            newSocket.setSoTimeout(100);
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");

        } catch (IOException e) {
            System.out.println(e);
            //System.exit(1);
        }

    }

    public void sendMes (String msg) throws  IOException{

        try {
            message = msg.getBytes();
            System.out.println(message);
            outputStream = new DatagramPacket(message, message.length, inetAddress,port);
            newSocket.send(outputStream);

            inputStream = new DatagramPacket(messageIn, messageIn.length);
            newSocket.receive(inputStream);

            String received = new String(
                    inputStream.getData(), 0, inputStream.getLength());

            String test = received.startsWith("$KG") ? received.substring(7) : "Problem z Połączeniem";
            reciveMessage =test;
            updateGui = new UpdateGui(myInterface);

            if (openWindow) {

                updateGui.visibleLog(test);
                updateGui.checkParam(msg,test);
            }
        }catch (IOException e )
        {
            System.out.println(e);
            if (openWindow) {
                updateGui.visibleLog(e.toString());
            }
        }
    }
    public void connectSocket () {
        newSocket.connect(inetAddress,port);
    }


}