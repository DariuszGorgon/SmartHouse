package sample;

import javax.xml.ws.ServiceMode;
import java.io.*;
import java.net.*;

public class Client extends Thread {

    DatagramSocket newSocket;
    DatagramPacket outputStream, inputStream;
    InetAddress inetAddress;

    private byte [] message;
    byte [] messageIn = new byte[10];

    public Client(String IPAddr) throws IOException {
        try {
            newSocket = new DatagramSocket();
            inetAddress = InetAddress.getByName(IPAddr);
        }
        catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");

        } catch (IOException e) {
            System.out.println(e);
            //System.exit(1);
        }


    }

    public String sendMes (String msg, int port) throws IOException {

        try {
            message = msg.getBytes();
            outputStream = new DatagramPacket(message, message.length, inetAddress,port);
            newSocket.send(outputStream);

            inputStream = new DatagramPacket(messageIn, messageIn.length);
            newSocket.receive(inputStream);
            String received = new String(
                    inputStream.getData(), 0, inputStream.getLength());
            newSocket.close();
            return received;

        }catch (IOException e )
        {
            System.out.println(e);
            return e.toString();
        }

    }


    public void close () {
        newSocket.close();
    }
}