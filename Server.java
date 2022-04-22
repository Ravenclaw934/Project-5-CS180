import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * A class that runs a server for the button game
 *
 * <p>Purdue University -- CS18000 -- Spring 2022 -- Homework 11 -- Challenge</p>
 *
 * port number used is 2222
 *
 * @author Logan Knight
 * @version April 7, 2022
 */
public class Server {

    public static void main(String[] args) {

        try {
        ServerSocket serverSocket = new ServerSocket(2222);

        while (true) {
            Socket socket = serverSocket.accept();

            Thread t = new Thread(new ServerThread(socket));
            t.start();
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}