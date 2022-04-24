import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable {

    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                String request = reader.readLine();

                if (request.equals("Login")) {
                    StudentImport stuImp = new StudentImport("StudentFile.txt", new ArrayList<>());
                    TeacherImport teachImp = new TeacherImport("TeacherFile.txt", new ArrayList<>());
                    DiscussionImport discImp = new DiscussionImport("CourseFile.txt");

                    ArrayList<Student> students = stuImp.initializeStudents();
                    ArrayList<Teacher> teachers = teachImp.initializeTeachers();
                    ArrayList<Course> courses = discImp.initializeDiscussions(teachers, students);

                    oos.writeObject(students);
                    oos.flush();
                    oos.writeObject(teachers);
                    oos.flush();
                    oos.writeObject(courses);
                    oos.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
