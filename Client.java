import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2222);
            Thread t = new Thread(new LoginGUI());
            t.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}