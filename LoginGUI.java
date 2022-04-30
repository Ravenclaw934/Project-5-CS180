import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Login GUI
 *
 * This is the login screen for the client
 *
 * @author Logan Knight, L02
 *
 * @version 04/30/2022
 *
 */

public class LoginGUI extends JComponent implements Runnable {

    public ArrayList<Student> students;
    public ArrayList<Teacher> teachers;
    public JTextField userText;
    public JTextField passText;
    public JButton enterButton;
    public JLabel userPrompt;
    public JLabel passPrompt;

    LoginGUI login;
    public JFrame frame;

    ArrayList<Course> courses;

    public LoginGUI() {

    }

    public LoginGUI(ArrayList<Student> students, ArrayList<Teacher> teachers) {
        this.students = students;
        this.teachers = teachers;
    }

    public LoginGUI(ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Course> courses) {
        this.students = students;
        this.teachers = teachers;
        this.courses = courses;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new LoginGUI());
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
                        if (passText.getText().equals(student.getPassword())) {
                            login = true;
                            StudentGUI studentPage = new StudentGUI(student, courses);
                            Thread t = new Thread(studentPage);
                            t.start();
                            frame.dispose();
                        }
                    }
                }
                for (Teacher teacher : teachers) {
                    if (userText.getText().equals(teacher.getUsername())) {
                        usernameExists = true;
                        if (passText.getText().equals(teacher.getPassword())) {
                            login = true;
                            username = teacher.getUsername();
                            password = teacher.getPassword();
                            //TeacherGUI studentPage = new TeacherGUI(username, password);
                        }
                    }
                }
                if (!usernameExists) {
                    JOptionPane.showMessageDialog(null, "Username is Incorrect", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!login) {
                    JOptionPane.showMessageDialog(null, "Password is Incorrect", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    public void run() {
        /* set up JFrame */
        frame = new JFrame("Login");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        login = new LoginGUI();

        JPanel jpanelInput = new JPanel();
        JPanel jpanelPrompts = new JPanel();

        jpanelInput.setLayout(new BoxLayout(jpanelInput, BoxLayout.Y_AXIS));
        jpanelPrompts.setLayout(new BoxLayout(jpanelPrompts, BoxLayout.Y_AXIS));


        userText = new JFormattedTextField();
        userPrompt = new JLabel("Username: ");
        passText = new JTextField("", 5);
        passPrompt = new JLabel("Password: ");
        enterButton = new JButton("Login");
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
