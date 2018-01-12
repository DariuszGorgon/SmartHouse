package sample;

import javafx.application.Platform;

import javax.xml.ws.ServiceMode;
import java.io.*;
import java.net.*;

public class Client {

    DatagramSocket newSocket;
    DatagramPacket outputStream, inputStream;
    InetAddress inetAddress;

    //interfesy
    private MyInterface myInterface;

    private byte [] message;
    byte [] messageIn = new byte[10];

     Client(String IPAddr,MyInterface myInterface) throws IOException {

        this.myInterface = myInterface;
        try {
            newSocket = new DatagramSocket();
            inetAddress = InetAddress.getByName(IPAddr);
            newSocket.setSoTimeout(100);
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");

        } catch (IOException e) {
            System.out.println(e);
            //System.exit(1);
        }

    }

    public void sendMes (String msg, int port) throws IOException {

        try {
            message = msg.getBytes();
            outputStream = new DatagramPacket(message, message.length, inetAddress,port);
            newSocket.send(outputStream);

            inputStream = new DatagramPacket(messageIn, messageIn.length);
            newSocket.receive(inputStream);
            String received = new String(
                    inputStream.getData(), 0, inputStream.getLength());
            newSocket.close();
            this.myInterface.editListwiev(received);

            //platform.runLater(10); //używać do aktualizacji gui
            //z intefejsów metody tworzę jak klasy
        }catch (IOException e )
        {
            System.out.println(e);
            this.myInterface.editListwiev(e.toString());
        }
    }

    public void close () {
        newSocket.close();
    }
}