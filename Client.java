import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2222);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            writer.println("Login");
            writer.flush();
            ArrayList<Student> students = (ArrayList<Student>) ois.readObject();
            ArrayList<Teacher> teachers = (ArrayList<Teacher>) ois.readObject();
            ArrayList<Course> courses = (ArrayList<Course>) ois.readObject();

            writer.close();
            ois.close();
            Thread t = new Thread(new LoginGUI(students, teachers, courses));
            t.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
