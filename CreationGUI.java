import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class CreationGUI extends JComponent implements Runnable {

    public ArrayList<Student> students;
    public ArrayList<Teacher> teachers;
    public JTextField userText;
    public JTextField passText;
    public JButton enterButton;
    public JLabel userPrompt;
    public JLabel passPrompt;

    CreationGUI create;
    public JFrame frame;
    String userType;
    Socket socket;

    ArrayList<Course> courses;

    public CreationGUI() {

    }

    public CreationGUI(ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Course> courses,
                       String userType, Socket socket) {
        this.students = students;
        this.teachers = teachers;
        this.courses = courses;
        this.userType = userType;
        this.socket = socket;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new CreationGUI());
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean usernameExists = false;
            boolean login = false;
            String username;
            String password;

            if (e.getSource() == enterButton) {

                for (Student student : students) {
                    if (userText.getText().equals(student.getUsername())) {
                        usernameExists = true;

                    }
                }
                for (Teacher teacher : teachers) {
                    if (userText.getText().equals(teacher.getUsername())) {
                        usernameExists = true;

                    }
                }
                if (usernameExists) {
                    JOptionPane.showMessageDialog(null, "This username already exists",
                            "Creation Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    if (userType.equals("T")) {
                        Teacher newTeacher = new Teacher(userText.getText(), passText.getText());
                        teachers.add(newTeacher);

                        try {
                            PrintWriter writer = new PrintWriter(socket.getOutputStream());
                            writer.println("New Teacher");
                            writer.flush();
                            writer.println(newTeacher.getUsername());
                            writer.flush();
                            writer.println(newTeacher.getPassword());
                            writer.flush();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        frame.dispose();

                       // Thread t = new Thread(new TeacherGUI(teachers, courses));
                  //      t.start();

                    } else if (userType.equals("S")) {
                        Student newStudent = new Student(userText.getText(), passText.getText());
                        students.add(newStudent);

                        try {
                            PrintWriter writer = new PrintWriter(socket.getOutputStream());
                            writer.println("New Student");
                            writer.flush();
                            writer.println(newStudent.getUsername());
                            writer.flush();
                            writer.println(newStudent.getPassword());
                            writer.flush();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        frame.dispose();

                        Thread t = new Thread(new StudentGUI(students, courses, socket));
                        t.start();
                    }
                }
            }
        }
    };

    public void run() {
        /* set up JFrame */
        frame = new JFrame("Create Account");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        create = new CreationGUI();

        JPanel jpanelInput = new JPanel();
        JPanel jpanelPrompts = new JPanel();

        jpanelInput.setLayout(new BoxLayout(jpanelInput, BoxLayout.Y_AXIS));
        jpanelPrompts.setLayout(new BoxLayout(jpanelPrompts, BoxLayout.Y_AXIS));


        userText = new JFormattedTextField();
        userPrompt = new JLabel("Username: ");
        passText = new JTextField("", 5);
        passPrompt = new JLabel("Password: ");
        enterButton = new JButton("Create Account");
        enterButton.addActionListener(actionListener);

        //passPrompt.setLabelFor(password);
        //userPrompt.setLabelFor(userText);


        //frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        jpanelInput.add(userPrompt);
        jpanelInput.add(userText);
        jpanelInput.add(passPrompt);
        jpanelInput.add(passText);
        jpanelInput.add(enterButton);

        //content.add(jpanelPrompts, BorderLayout.WEST);
        content.add(jpanelInput, BorderLayout.CENTER);

        //content.add(jpanelInput, BorderLayout.NORTH);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //needed
        frame.setVisible(true);

    }
}