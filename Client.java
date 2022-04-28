import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {
        try {
            String hostname = JOptionPane.showInputDialog(null, "Please enter the ip of the server " +
                            "(localhost if running the server locally)", "Setting up", JOptionPane.QUESTION_MESSAGE);
            if (hostname.equals("")) {
                throw new UnknownHostException();
            }

            Socket socket = new Socket(hostname, 2222);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            String acctType;
            String[] options = {"Student", "Teacher", "Create Student Account", "Create Teacher Account"};
            do {
                acctType = (String) JOptionPane.showInputDialog(null, "What kind of account do you have?",
                        "Discussion Board", JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
                if (acctType == null) {
                    JOptionPane.showMessageDialog(null, "Choice cannot be empty!", "Discussion Board",
                            JOptionPane.ERROR_MESSAGE);

                } //end if

            } while (acctType == null);

            writer.println("Login");
            writer.flush();
            ArrayList<Student> students = (ArrayList<Student>) ois.readObject();
            ArrayList<Teacher> teachers = (ArrayList<Teacher>) ois.readObject();
            ArrayList<Course> courses = (ArrayList<Course>) ois.readObject();

            if (acctType.equals("Student")) {
                StudentGUI studentGUI = new StudentGUI(students, courses, socket, ois);
                Thread t = new Thread(studentGUI);
                t.start();
            } else if (acctType.equals("Teacher")) {
                TeacherGUI teacherGUI = new TeacherGUI(students, teachers, courses, socket, ois);
                Thread t = new Thread(teacherGUI);
                t.start();
            } else if (acctType.equals("Create Teacher Account")){
                CreationGUI creationGUI = new CreationGUI(students, teachers, courses, "T", socket, ois);
                Thread t = new Thread(creationGUI);
                t.start();
            } else {
                CreationGUI creationGUI = new CreationGUI(students, teachers, courses, "S", socket, ois);
                Thread t = new Thread(creationGUI);
                t.start();
            }

        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Unknown host!", "Unknown Host Exception",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}