import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * A program makes creates a page GUI.
 *
 * <p>
 * Purdue University -- CS18000 -- Spring 2022 -- Project 5 -- TeacherGUI
 * </p>
 *
 * @author Anthony Whittle (ajwhittl) Purdue CS
 * @version 1.0 April 12th, 2021
 */

public class TeacherGUI extends JComponent implements Runnable {

    public String username;
    public String password;

    public Teacher user;


    // General variables for the GUI
    public ArrayList<Student> students = new ArrayList<Student>();
    public ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    public ArrayList<Course> courses = new ArrayList<Course>();
    public ArrayList<Discussion> discussions = new ArrayList<Discussion>();
    public ArrayList<JButton> buttons = new ArrayList<JButton>();

    // Login text fields, labels, and buttons
    public JTextField userText;
    public JTextField passText;
    public JButton enterButton;
    public JLabel userPrompt;
    public JLabel passPrompt;

    // Main Menu button
    public JButton mainMenu = new JButton("Main Menu");

    // Main Menu text fields, labels, and buttons
    public JLabel accountTest = new JLabel("Account Information Menu");
    public JButton account = new JButton("Account Info");
    public JLabel courseText = new JLabel("Course Creation Menu");
    public JButton createCourse = new JButton("Create Course");
    public JLabel createDiscText = new JLabel("Create New Discussion Menu");
    public JButton createDiscussion = new JButton("Create Discussion");
    public JLabel editDiscText = new JLabel("Edit Existing Discussion Menu");
    public JButton editDiscussion = new JButton("Edit Discussion");
    public JLabel deleteDiscText = new JLabel("Delete Discussion Menu");
    public JButton deleteDiscussion = new JButton("Delete Discussion");
    public JLabel replyText = new JLabel("Create Reply Menu");
    public JButton reply = new JButton("Reply");
    public JButton popularReply = new JButton("View Popular Reply");
    public JLabel gradeText = new JLabel("Grade a Student");
    public JButton gradeStudent = new JButton("Grade");
    public JLabel importText = new JLabel("Import Discussion Text");
    public JButton importDiscussion = new JButton("Import");

    // Account menu text fields, labels, and buttons
    public JLabel usernameText = new JLabel("Username: ");
    public JButton editUsername = new JButton("Change Username");
    public JLabel passwordText = new JLabel("Password: ");
    public JButton editPassword = new JButton("Change Password");
    public JLabel deleteText = new JLabel("Delete Account");
    public JButton deleteAccount = new JButton("Delete");
    public JLabel backText = new JLabel("Go Back");
    public JButton goBack = new JButton("Back");

    // Course Creation Menu TextFields, Labels, Button
    public JLabel createNewCourse = new JLabel("Create Course: ");
    public JLabel coursePrompt = new JLabel("Course Name: ");
    public JButton createCourseButton = new JButton("Create Course");

    // Discussion Creation Menu Labels and Button
    public JLabel createNewDisc = new JLabel("Create Discussion:");
    public JLabel discPrompt = new JLabel("Discussion Name: ");
    public JButton createDiscussionButton = new JButton("Create Discussion");

    // Discussion Creation Menu Labels and Button
    public JLabel editNewDisc = new JLabel("Edit Discussion:");
    public JLabel editDiscPrompt = new JLabel("Pick a discussion number to edit: ");
    public JButton editDiscussionButton = new JButton("Edit Discussion");
    public JLabel changeText = new JLabel("What are you changing it into?");

    // Discussion Delete Menu Labels and Button
    public JLabel deleteDisc = new JLabel("Delete Discussion: ");
    public JLabel deleteDiscPrompt = new JLabel("Pick a discussion number to delete: ");
    public JButton deleteDiscussionButton = new JButton("Delete Discussion");

    // Refresh Button
    public JButton refresh = new JButton("Refresh");

    public JButton confirm = new JButton("Confirm");
    public JButton confirmUser = new JButton("Confirm");
    public JButton confirmPass = new JButton("Confirm");
    public JButton confirmReply = new JButton("Confirm");
    public JButton confirmDiscussionReply = new JButton("Confirm");
    public JButton confirmCreateDiscussion = new JButton("Confirm");
    public JButton confirmDiscussionCreate = new JButton("Confirm");
    public JButton confirmGradeStudentMenu = new JButton("Confirm");
    public JButton confirmGradeFinal = new JButton("Confirm");
    public JButton confirmDiscussionEdit = new JButton("Confirm");
    public JButton confirmDiscussionEdit2 = new JButton("Confirm");
    public JButton confirmDiscussionEdit3 = new JButton("Confirm");



    JComboBox<String> courseDropdown;
    JComboBox<String> discDropdown;
    JComboBox<String> studentDropdown;

    JFrame frame;
    Container content;

    Student Anthony = new Student("ajwhittl","password");
    Teacher Jones = new Teacher("jjones", "password");
    Course history = new Course("History");

    Socket socket;
    ObjectInputStream ois;

    public TeacherGUI() {
        students = new ArrayList<Student>();
        students.add(Anthony);
        courses = new ArrayList<Course>();
        courses.add(history);
        teachers = new ArrayList<Teacher>();
        teachers.add(Jones);

        buttons.add(confirm);
        buttons.add(refresh);
        buttons.add(confirmUser);
        buttons.add(confirmPass);
        buttons.add(confirmReply);
        buttons.add(confirmDiscussionReply);
        buttons.add(confirmCreateDiscussion);
        buttons.add(confirmDiscussionCreate);
        buttons.add(confirmGradeStudentMenu);
        buttons.add(confirmGradeFinal);
        buttons.add(confirmDiscussionEdit);
        buttons.add(confirmDiscussionEdit2);
        buttons.add(confirmDiscussionEdit3);
        buttons.add(createCourseButton);
        buttons.add(createDiscussionButton);
        buttons.add(createDiscussionButton);
        buttons.add(editDiscussionButton);
        buttons.add(deleteDiscussionButton);
        buttons.add(deleteAccount);
        buttons.add(goBack);
        buttons.add(editPassword);
        buttons.add(editUsername);
        buttons.add(mainMenu);
        buttons.add(account);
        buttons.add(createCourse);
        buttons.add(createDiscussion);
        buttons.add(editDiscussion);
        buttons.add(deleteDiscussion);
        buttons.add(reply);
        buttons.add(popularReply);
        buttons.add(gradeStudent);
        buttons.add(importDiscussion);
    }

    public TeacherGUI(ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Course> courses,
                      Socket socket, ObjectInputStream ois) {
        this.students = students;
        this.teachers = teachers;
        this.courses = courses;
        this.socket = socket;
        this.ois = ois;

        buttons.add(confirm);
        buttons.add(refresh);
        buttons.add(confirmUser);
        buttons.add(confirmPass);
        buttons.add(confirmReply);
        buttons.add(confirmDiscussionReply);
        buttons.add(confirmCreateDiscussion);
        buttons.add(confirmDiscussionCreate);
        buttons.add(confirmGradeStudentMenu);
        buttons.add(confirmGradeFinal);
        buttons.add(confirmDiscussionEdit);
        buttons.add(confirmDiscussionEdit2);
        buttons.add(confirmDiscussionEdit3);
        buttons.add(createCourseButton);
        buttons.add(createDiscussionButton);
        buttons.add(createDiscussionButton);
        buttons.add(editDiscussionButton);
        buttons.add(deleteDiscussionButton);
        buttons.add(deleteAccount);
        buttons.add(goBack);
        buttons.add(editPassword);
        buttons.add(editUsername);
        buttons.add(mainMenu);
        buttons.add(account);
        buttons.add(createCourse);
        buttons.add(createDiscussion);
        buttons.add(editDiscussion);
        buttons.add(deleteDiscussion);
        buttons.add(reply);
        buttons.add(popularReply);
        buttons.add(gradeStudent);
        buttons.add(importDiscussion);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new TeacherGUI());
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean usernameExists = false;
            boolean login = false;

            if (e.getSource() == enterButton) {
                for (Teacher teacher : teachers) {
                    if (userText.getText().equals(teacher.getUsername())) {
                        usernameExists = true;
                        if (passText.getText().equals(teacher.getPassword())) {
                            login = true;
                            username = teacher.getUsername();
                            password = teacher.getPassword();
                            user = teacher;
                            removeListeners();
                            mainMenu();
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
        content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        TeacherGUI teacher = new TeacherGUI();

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void mainMenu()
    {

        frame.dispose();

        frame = new JFrame("Main Menu");
        content = frame.getContentPane();

        JPanel left = new JPanel();
        JPanel center = new JPanel();
        JPanel right = new JPanel();

        left.add(accountTest);
        left.add(account);
        left.add(courseText);
        left.add(createCourse);
        left.add(gradeText);
        left.add(gradeStudent);
        left.add(importText);
        left.add(importDiscussion);

        center.add(createDiscText);
        center.add(createDiscussion);
        center.add(editDiscText);
        center.add(editDiscussion);
        center.add(deleteDiscText);
        center.add(deleteDiscussion);

        right.add(replyText);
        right.add(reply);

        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));

        content.add(left, BorderLayout.WEST);
        content.add(center, BorderLayout.CENTER);
        content.add(right, BorderLayout.EAST);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        account.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                accountMenu();

            }
        });

        createCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                courseMenu();

            }
        });

        createDiscussion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                DiscussionMenu();

            }
        });

        editDiscussion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                editDiscussionMenu();

            }
        });

        deleteDiscussion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                deleteDiscussionMenu();

            }
        });

        reply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                replyMenu();


            }
        });

        popularReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();



            }
        });

        gradeStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                gradeMenu();


            }
        });

        importDiscussion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                importDiscussion();


            }
        });


    }

    public void accountMenu()
    {

        frame.dispose();

        frame = new JFrame("Account Settings");
        content = frame.getContentPane();

        usernameText = new JLabel("Username: " + username);
        passwordText = new JLabel("Password: " + password);

        JPanel center = new JPanel();

        center.add(usernameText);
        center.add(editUsername);
        center.add(passwordText);
        center.add(editPassword);
        center.add(deleteText);
        center.add(deleteAccount);
        center.add(backText);
        center.add(goBack);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        editUsername.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                changeUsername();


            }
        });

        editPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                changePassword();


            }
        });

        deleteAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

                for(int i = 0; i < teachers.size(); i++)
                {
                    if(teachers.get(i).getUsername().equals(username))
                    {
                        try {
                            PrintWriter writer = new PrintWriter(socket.getOutputStream());
                            writer.println("Delete Teacher");
                            writer.flush();
                            writer.println(teachers.get(i).getUsername());
                            writer.flush();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        teachers.remove(i);
                    }
                }

                removeListeners();
                run();


            }
        });

        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                mainMenu();


            }
        });

    }

    public void changeUsername()
    {
        frame.dispose();

        frame = new JFrame("Change Username");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        center.add(userPrompt);
        center.add(userText);
        center.add(confirmUser);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);

        String oldUsername = username;

        userText.setText("");


        confirmUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    for (Teacher t : teachers) {
                        if (t.getUsername().equals(userText.getText())) {
                            throw new AccountExistsException("");
                        }
                    }

                    for (int i = 0; i < teachers.size(); i++) {
                        if (teachers.get(i).getUsername().equals(oldUsername)) {
                            username = userText.getText();
                            teachers.get(i).setUsername(username);

                            try {
                                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                                writer.println("Change Teacher Username");
                                writer.flush();
                                writer.println(oldUsername);
                                writer.flush();
                                writer.println(username);
                                writer.flush();

                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        }
                    }
                } catch (AccountExistsException e1) {
                    JOptionPane.showInternalMessageDialog(null, "Username is already in use!", "Action Failed",
                            JOptionPane.ERROR_MESSAGE);
                }

                removeListeners();
                accountMenu();


            }
        });



        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void changePassword()
    {
        frame.dispose();

        frame = new JFrame("Change Password");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        center.add(passPrompt);
        center.add(passText);
        center.add(confirmPass);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);

        String oldPassword = password;

        passText.setText("");


        confirmPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                password = passText.getText();

                for(int i = 0; i < teachers.size(); i++)
                {
                    if(teachers.get(i).getPassword().equals(oldPassword))
                    {
                        teachers.get(i).setPassword(password);

                        try {
                            PrintWriter writer = new PrintWriter(socket.getOutputStream());
                            writer.println("Change Teacher Password");
                            writer.flush();
                            writer.println(username);
                            writer.flush();
                            writer.println(password);
                            writer.flush();

                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
                removeListeners();
                accountMenu();


            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


    public void courseMenu() {

        frame.dispose();

        frame = new JFrame("Create Course");
        content = frame.getContentPane();


        JPanel center = new JPanel();

        center.add(createNewCourse);
        center.add(createCourseButton);
        center.add(backText);
        center.add(goBack);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        createCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                createCourse();

            }
        });

        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                mainMenu();

            }
        });

    }


    public void createCourse(){
        frame.dispose();

        frame = new JFrame("Create Course");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        center.add(coursePrompt);
        center.add(passText);
        center.add(confirm);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);


        passText.setText("");


        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Course course = new Course(passText.getText());

                courses.add(course);

                try {
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println("Create Course");
                    writer.flush();
                    writer.println(course.getCourseName());
                    writer.flush();
                    writer.println("Teacher");
                    writer.flush();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                removeListeners();
                courseMenu();


            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void DiscussionMenu(){

        frame.dispose();

        frame = new JFrame("Create Discussion");
        content = frame.getContentPane();

        JLabel discList = new JLabel("You currently have " + discussions.size() + " discussions.");
        JPanel center = new JPanel();


        center.add(discList);
        center.add(createNewDisc);
        center.add(createDiscussionButton);
        center.add(backText);
        center.add(goBack);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        createDiscussionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                createDiscussion();

            }
        });

        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeListeners();
                mainMenu();

            }
        });


    }

    public void gradeMenu()
    {

        frame.dispose();

        frame = new JFrame("Grade Student");
        content = frame.getContentPane();

        JPanel center = new JPanel();


        String[] names = new String[students.size()];


        for(int i = 0; i < students.size(); i++)
        {
            names[i] = students.get(i).getUsername();

        }

        studentDropdown = new JComboBox<String>(names);

        center.add(studentDropdown);
        center.add(confirmGradeStudentMenu);

        confirmGradeStudentMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                for(int i = 0; i < students.size(); i++)
                {
                    if(students.get(i).getUsername().equals(studentDropdown.getSelectedItem()))
                    {
                        removeListeners();
                        gradeStudent(students.get(i));
                    }
                }

            }
        });

        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }


    public void gradeStudent(Student user)
    {
        frame.dispose();

        frame = new JFrame("Student Grading");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        center.add(new JLabel(user.getUsername()));

        String[] options = {"0", "1", "2", "3", "4", "5"};

        JComboBox<String> grades = new JComboBox<String>(options);

        center.add(grades);

        center.add(confirmGradeFinal);

        confirmGradeFinal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                for(int i = 0; i < students.size(); i++)
                {
                    if(students.get(i).equals(user))
                    {
                        students.get(i).setGrade(Integer.parseInt(grades.getItemAt(grades.getSelectedIndex())));

                        try {
                            PrintWriter writer = new PrintWriter(socket.getOutputStream());
                            writer.println("Grade");
                            writer.flush();
                            writer.println(students.get(i).getUsername());
                            writer.flush();
                            writer.println(students.get(i).getGrade());
                            writer.flush();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        removeListeners();
                        mainMenu();
                    }
                }


            }
        });

        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }




    public void createDiscussion(){

        frame.dispose();

        frame = new JFrame("Create Discussion");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        String[] names = new String[courses.size()];

        for(int i = 0; i < courses.size(); i++)
        {
            names[i] = courses.get(i).getCourseName();
        }

        courseDropdown = new JComboBox<String>(names);

        center.add(courseDropdown);
        center.add(confirmCreateDiscussion);

        content.add(center);

        confirmCreateDiscussion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                discussionCreator(courseDropdown.getItemAt(courseDropdown.getSelectedIndex()));

            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void discussionCreator(String course)
    {
        frame.dispose();

        frame = new JFrame("Discussion Creator");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        center.add(new JLabel(course));

        passText.setText("");

        center.add(passText);

        center.add(confirmDiscussionCreate);

        content.add(center);

        confirmDiscussionCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                String message = passText.getText();

                for(int i = 0; i < courses.size(); i++)
                {
                    if(courses.get(i).getCourseName().equals(course))
                    {
                        courses.get(i).forum.add(new Discussion(message));

                        try {
                            PrintWriter writer = new PrintWriter(socket.getOutputStream());
                            writer.println("Create Discussion");
                            writer.flush();
                            writer.println(courses.get(i).getCourseName());
                            writer.flush();
                            writer.println("Teacher");
                            writer.flush();
                            writer.println(message);
                            writer.flush();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        removeListeners();
                        mainMenu();
                    }
                }

            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void editDiscussionMenu(){

        frame.dispose();

        frame = new JFrame("Edit Discussion");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        String[] names = new String[courses.size()];

        for(int i = 0; i < courses.size(); i++)
        {
            names[i] = courses.get(i).getCourseName();
        }

        courseDropdown = new JComboBox<String>(names);

        center.add(courseDropdown);
        center.add(confirmDiscussionEdit);

        content.add(center);

        confirmDiscussionEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                editDiscussionOne(courseDropdown.getItemAt(courseDropdown.getSelectedIndex()));

            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }

    public void editDiscussionOne(String course)
    {

        frame.dispose();

        frame = new JFrame("Edit Discussion");

        content = frame.getContentPane();

        JPanel center = new JPanel();

        center.add(new JLabel(course));

        Course place = null;

        for(int i = 0; i < courses.size(); i++)
        {
            if(courses.get(i).getCourseName().equals(course))
            {
                place = courses.get(i);
            }
        }

        String[] names = new String[place.getForum().size()];

        for(int i = 0; i < place.getForum().size(); i++)
        {
            names[i] = place.getForum().get(i).getMessage();
        }

        discDropdown = new JComboBox<String>(names);

        center.add(discDropdown);
        center.add(confirmDiscussionEdit2);


        confirmDiscussionEdit2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                editDiscussion(course, discDropdown.getItemAt(discDropdown.getSelectedIndex()));

            }
        });

        content.add(center);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void editDiscussion(String course, String discussion) {

        frame.dispose();

        frame = new JFrame("Discussion Creator");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        center.add(new JLabel(course));

        passText.setText("");

        center.add(passText);

        center.add(confirmDiscussionEdit3);

        content.add(center);

        int index = 0;

        confirmDiscussionEdit3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                String message = passText.getText();

                for(int i = 0; i < courses.size(); i++)
                {
                    if(courses.get(i).getCourseName().equals(course))
                    {
                        for(int j = 0; j < courses.get(i).getForum().size(); j++)
                        {
                            if(courses.get(i).getForum().get(j).getMessage().equals(discussion))
                            {
                                try {
                                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                                    writer.println("Edit Discussion");
                                    writer.flush();
                                    writer.println(courses.get(i).getCourseName());
                                    writer.flush();
                                    writer.println("Teacher");
                                    writer.flush();
                                    writer.println(discussion);
                                    writer.flush();
                                    writer.println(message);
                                    writer.flush();

                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                                courses.get(i).getForum().set(j, new Discussion(message));

                                removeListeners();
                                mainMenu();
                            }
                        }
                    }
                }

            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void deleteDiscussionMenu(){

        frame.dispose();

        frame = new JFrame("Delete Discussion");
        content = frame.getContentPane();

        JLabel discList = new JLabel("You currently have " + discussions.size() + " discussions.");
        JPanel center = new JPanel();

        center.add(discList);
        center.add(deleteDisc);
        center.add(deleteDiscussionButton);
        center.add(backText);
        center.add(goBack);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        deleteDiscussionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                deleteDiscussion();

            }
        });

        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                mainMenu();


            }
        });


    }

    public void deleteDiscussion() {
        frame.dispose();

        frame = new JFrame("Delete Discussion");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        center.add(deleteDiscPrompt);

        center.add(passText);
        center.add(confirm);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);


        passText.setText("");


        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int discNum = Integer.parseInt(passText.getText());
                for (Course c : courses) {
                    for (Discussion d : c.getForum()) {
                        discussions.add(d);
                    }
                }

                for (Course c : courses) {
                    for (Discussion d : c.getForum()) {
                        if (d.getMessage().equals(discussions.get(discNum).getMessage())) {
                            discussions.get(discNum).setCourse(c.getCourseName());
                        }
                    }
                }

                try {
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println("Delete Discussion");
                    writer.flush();
                    writer.println(discussions.get(discNum).getCourse());
                    writer.flush();
                    writer.println("Teacher");
                    writer.flush();
                    writer.println(discussions.get(discNum).getMessage());
                    writer.flush();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                discussions.remove(discNum);
                removeListeners();
                mainMenu();

            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void replyMenu()
    {

        frame.dispose();

        frame = new JFrame("Reply Menu");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        String[] names = new String[courses.size()];

        for(int i = 0; i < courses.size(); i++)
        {
            names[i] = courses.get(i).getCourseName();
        }

        courseDropdown = new JComboBox<String>(names);

        center.add(courseDropdown);
        center.add(confirmReply);



        content.add(center);

        confirmReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                replyPicker(courseDropdown.getItemAt(courseDropdown.getSelectedIndex()));


            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void replyPicker(String course)
    {


        frame.dispose();

        frame = new JFrame("Reply Menu");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        Course discussions = null;

        for(int i = 0; i < courses.size(); i++)
        {
            if(courses.get(i).getCourseName().equals(course))
            {
                discussions = courses.get(i);
            }
        }

        final Course discussion = discussions;

        if(discussions != null)
        {

            String[] list = new String[discussions.getForum().size()];

            for(int i = 0; i < list.length; i++)
            {
                list[i] = discussions.getForum().get(i).getMessage();
            }

            discDropdown = new JComboBox<String>(list);
        }
        center.add(new JLabel(course));
        center.add(discDropdown);
        center.add(confirmDiscussionReply);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));


        confirmDiscussionReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                createReply(discussion, discussion.getForum().get(discDropdown.getSelectedIndex()));

            }
        });

        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);



    }

    public void createReply(Course courses, Discussion discussion)
    {


        frame.dispose();

        frame = new JFrame("Reply Menu");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        passText.setText("");

        center.add(passText);
        center.add(confirm);

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Reply tempReply = new Reply(user, passText.getText());
                courses.getForum().get(courses.getForum().indexOf(discussion))
                        .getReplyArray().add(tempReply);

                try {
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println("New Reply");
                    writer.flush();
                    writer.println(courses.getCourseName());
                    writer.flush();
                    writer.println("Teacher");
                    writer.flush();
                    writer.println(courses.getForum().get(courses.getForum().indexOf(discussion)).getMessage());
                    writer.flush();
                    writer.println(user.getUsername());
                    writer.flush();
                    writer.println(tempReply.getMessage());
                    writer.flush();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                removeListeners();
                mainMenu();

            }
        });

        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public void popularReplyCourse()
    {

        frame.dispose();

        frame = new JFrame("Reply Menu");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        String[] names = new String[courses.size()];

        for(int i = 0; i < courses.size(); i++)
        {
            names[i] = courses.get(i).getCourseName();
        }

        courseDropdown = new JComboBox<String>(names);

        center.add(courseDropdown);
        center.add(confirmReply);



        content.add(center);

        confirmReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                popularReplyDiscussion(courseDropdown.getItemAt(courseDropdown.getSelectedIndex()));


            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void popularReplyDiscussion(String course)
    {

        frame.dispose();

        frame = new JFrame("Reply Menu");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        Course discussions = null;

        for(int i = 0; i < courses.size(); i++)
        {
            if(courses.get(i).getCourseName().equals(course))
            {
                discussions = courses.get(i);
            }
        }

        final Course discussion = discussions;

        if(discussions != null)
        {

            String[] list = new String[discussions.getForum().size()];

            for(int i = 0; i < list.length; i++)
            {
                list[i] = discussions.getForum().get(i).getMessage();
            }

            discDropdown = new JComboBox<String>(list);
        }
        center.add(new JLabel(course));
        center.add(discDropdown);
        center.add(confirmDiscussionReply);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));


        confirmDiscussionReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                popularReply(discussion, discussion.getForum().get(discDropdown.getSelectedIndex()));

            }
        });

        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void popularReply(Course discussion, Discussion forum)
    {
        frame.dispose();

        frame = new JFrame("Reply Menu");
        content = frame.getContentPane();

        JPanel center = new JPanel();


        if(discussion.getForum().get(discussion.getForum().indexOf(forum)).getReplyArray().size() <= 0)
        {
            JOptionPane.showInternalMessageDialog(null, "Discussion has no replies", "Action Failed",
                    JOptionPane.ERROR_MESSAGE);
        } else
        {
            Reply reply = discussion.getForum().get(discussion.getForum().indexOf(forum)).getReplyArray().get(0);

            for(int i = 0; i < discussion.getForum().get(discussion.getForum().indexOf(forum)).getReplyArray().size(); i++)
            {
                if(reply.getVotes() < discussion.getForum().get(discussion.getForum().indexOf(forum)).getReplyArray().get(i).getVotes())
                {
                    reply = discussion.getForum().get(discussion.getForum().indexOf(forum)).getReplyArray().get(i);
                }
            }

            center.add(new JLabel(reply.message));
        }

        center.add(goBack);


        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                removeListeners();
                mainMenu();

            }
        });

        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public void importDiscussion()
    {

        frame.dispose();

        frame = new JFrame("Reply Menu");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        String[] names = new String[courses.size()];

        for(int i = 0; i < courses.size(); i++)
        {
            names[i] = courses.get(i).getCourseName();
        }

        courseDropdown = new JComboBox<String>(names);

        center.add(courseDropdown);
        center.add(passText);
        center.add(confirmReply);

        confirmReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> lines = new ArrayList<>();
                try {
                    File f = new File(passText.getText());
                    FileReader fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);

                    while (true) {
                        String s = bfr.readLine();
                        if (s == null) {
                            break;
                        }
                        lines.add(s);
                    }
                    bfr.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                String courseName = courseDropdown.getItemAt(courseDropdown.getSelectedIndex());
                //put server stuff here

                try {
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println("Import Discussion");
                    writer.flush();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(lines);
                    oos.flush();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                removeListeners();
                mainMenu();


            }
        });


        content.add(center);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }



    public void removeListeners()
    {
        for(int i = 0; i < buttons.size(); i++)
        {
            try {
                if(buttons.get(i).getActionListeners().length > 0)
                {
                    buttons.get(i).removeActionListener(buttons.get(i).getActionListeners()[0]);
                }
            } catch (NullPointerException e)
            {

            }
        }
    }
}