import java.io.*;
import java.net.*;
import java.util.ArrayList;

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
