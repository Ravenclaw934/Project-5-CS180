import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {

    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {

    }
}