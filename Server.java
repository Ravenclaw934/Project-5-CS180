import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Server
 *
 * This is the server side of the program
 *
 * @author Logan Knight, L02
 *
 * @version 04/30/2022
 *
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
