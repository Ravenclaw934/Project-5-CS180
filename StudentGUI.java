import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.EventListener;

/**
 * A program makes creates a page GUI.
 *
 * <p>
 * Purdue University -- CS18000 -- Spring 2022 -- Project 5 -- StudentGUI
 * </p>
 *
 * @author BrianGillis (bdgillis) Purdue CS
 * @version 1.0 April 12th, 2021
 */

public class StudentGUI extends JComponent implements Runnable {

    public Color currentBack;
    public String[] courseNames ; // set for Testing purposes
    public String username;
    public String password;
    public Student student = new Student("bdgillis", "brunch", "5");
    public ArrayList<Course> courseList = new ArrayList<>();
    public JFrame frame;
    public Container content;
    public Container studentContent;
    public JFrame studentpage;
    public ArrayList<Student> students;
    public ArrayList<Teacher> teachers;
    public JTextField userText;
    public JTextField passText;
    public JButton enterButton;
    public JLabel userPrompt;
    public JLabel passPrompt;
    public Course currentCourse;
    public JPanel jpaneltop;
    public JLabel replyDisp;
    public JButton editDeleteRep = new JButton("Edit or Delete a Reply");

    Socket socket;
    ObjectInputStream ois;

    //Account page
    public JLabel usernameText = new JLabel("Username: ");
    public JButton editUsername = new JButton("Change Username");
    public JLabel passwordText = new JLabel("Password: ");
    public JButton editPassword = new JButton("Change Password");
    public JLabel deleteText = new JLabel("Delete Account");
    public JButton deleteAccount = new JButton("Delete");
    public JButton confirm = new JButton("Confirm");
    public JLabel grade;

    // Discussion page
    public JFileChooser fileImport;
    public JButton fileReply = new JButton("Upload reply text file");
    public JButton addReply = new JButton("Add Reply");
    public JTextField newReplyText;


    public StudentGUI() {

    }

    public JButton discussionButton; // a button to view discussions
    public JButton courseButton; // a button to veiw courses
    public JButton accountButton; // a button to edit/view account
    public JButton viewReplyButton; // a button to view replies to a discussion
    public JButton writeReplyButton; // a button to write a reply
    public JButton deleteButton;
    public JButton userUpdate;
    public JButton passUpdate;
    JComboBox<String> courseDropdown;
    JComboBox<String> discDropdown;
    public JButton refresh = new JButton("Refresh");

    StudentGUI page; // variable of the type StudentGUI

    /* action listener for buttons */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == refresh) {
                try {
                    PrintWriter writer = new PrintWriter(socket.getOutputStream());

                    writer.println("Login");
                    writer.flush();
                    ArrayList<Student> newStud = (ArrayList<Student>) ois.readObject();
                    ArrayList<Teacher> newTeach = (ArrayList<Teacher>) ois.readObject();
                    ArrayList<Course> newCor = (ArrayList<Course>) ois.readObject();
                    System.out.println("Info refreshed");
                    students = newStud;
                    teachers = newTeach;
                    courseList = newCor;

                    frame.dispose();
                    mainPageDisplay();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            if (e.getSource() == courseButton) {
                String selectCourseName = courseDropdown.getItemAt(courseDropdown.getSelectedIndex());

                for (int i = 0; i < courseList.size(); i++) {
                    if (selectCourseName.equals(courseList.get(i).getCourseName())) {
                        currentCourse = courseList.get(i);
                    }
                }
                displayCourse(currentCourse, frame.getContentPane());
                System.out.println(currentCourse.getCourseName());
            }
            if (e.getSource() == accountButton) {
                displayAccount(frame.getContentPane());
            }

            if (e.getSource() == enterButton) {
                checkLogin();
            }
            if (e.getSource() == viewReplyButton) {
                Discussion selectDisc = null;
                String selectDiscName = discDropdown.getItemAt(discDropdown.getSelectedIndex());

                try {
                    if (currentCourse != null) {
                        System.out.println("COURSE EXISTS!!");
                    }
                    for (int i = 0; i < currentCourse.getForum().size(); i++) {
                        if (selectDiscName.equals(currentCourse.getForum().get(i).getMessage())) {
                            selectDisc = currentCourse.getForum().get(i);
                        }
                    }
                    displayDisc(selectDisc, frame.getContentPane());
                } catch (NullPointerException nul) {
                    System.out.println("Null");
                    nul.printStackTrace();
                }
            }
        }
    };

    public StudentGUI(Student student, ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Course> courses) {
        this.student = student;
        this.students = students;
        this.teachers = teachers;
        this.courseList = courses;
    }

    public StudentGUI(ArrayList<Student> students, ArrayList<Course> courses, Socket socket, ObjectInputStream ois) {
        this.students = students;
        this.courseList = courses;
        this.socket = socket;
        this.ois = ois;
        courseNames = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            courseNames[i] = courseList.get(i).getCourseName();
        }
    }

    public void checkLogin() {
        boolean usernameExists = false;
        boolean login = false;

        for (Student stud : students) {
            if (userText.getText().equals(stud.getUsername())) {
                usernameExists = true;
                if (passText.getText().equals(stud.getPassword())) {
                    login = true;
                    this.student = stud;
                    username = stud.getUsername();
                    password = stud.getPassword();
                    mainPageDisplay();
                }
            }
        }

        //FOR TESTING (USE ABOVE CODE FOR ACTUAL)

        /*if (userText.getText().equals("ggg")) {
            usernameExists = true;
            if (passText.getText().equals("fff")) {
                login = true;
                mainPageDisplay();
                System.out.println("LOGGED IN LFGGGGGG ");
            }
        }*/

        if (!usernameExists) {
            JOptionPane.showMessageDialog(null, "Username is Incorrect", "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        } else if (!login) {
            JOptionPane.showMessageDialog(null, "Password is Incorrect", "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public void mainPageDisplay() {
        /* set up JFrame */
        frame.dispose();
        frame = new JFrame("Student Dashboard");
        content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        page = new StudentGUI();
        content.add(page, BorderLayout.CENTER);
        currentBack = Color.white;

        jpaneltop = new JPanel();

        refresh.addActionListener(actionListener);
        courseButton = new JButton("See Course");
        courseDropdown = new JComboBox<>(courseNames);
        courseButton.addActionListener(actionListener);

        accountButton = new JButton("Account");
        accountButton.addActionListener(actionListener);

        jpaneltop.add(refresh);
        jpaneltop.add(courseDropdown);
        jpaneltop.add(courseButton);
        jpaneltop.add(accountButton);

        content.add(jpaneltop, BorderLayout.NORTH);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void displayDisc(Discussion discussion, Container con) {
        frame.dispose();
        frame = new JFrame("Student Dashboard");
        content = frame.getContentPane();

        content.add(jpaneltop, BorderLayout.NORTH);
        JPanel discussionLayout = new JPanel();
        JPanel replyOptions = new JPanel();
        discussionLayout.setLayout(new BoxLayout(discussionLayout, BoxLayout.Y_AXIS));
        replyOptions.setLayout(new BoxLayout(replyOptions, BoxLayout.Y_AXIS));


        JLabel discussPrompt = new JLabel(discussion.getMessage());

        Reply[] replies = new Reply[discussion.getReplies()];
        String allReplies = "";
        for (int i = 0; i < discussion.getReplies(); i++) {
            replies[i] = discussion.getReplyArray().get(i);
            allReplies += "<html>" + replies[i].getPoster().getUsername() + ": " + replies[i].getMessage() + "<br>";
        }
        allReplies += "</html>";

        replyDisp = new JLabel(allReplies);
        addReply = new JButton("Write Reply");
        addReply.addActionListener(actionListener);


        discussionLayout.add(discussPrompt);
        discussionLayout.add(replyDisp);
        replyOptions.add(addReply);
        replyOptions.add(fileReply);
        replyOptions.add(editDeleteRep);
        content.add(discussionLayout, BorderLayout.CENTER);
        content.add(replyOptions, BorderLayout.EAST);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);


        addReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newReplyFrame(discussion);
                for (ActionListener a : addReply.getActionListeners()) {
                    addReply.removeActionListener(a);
                }
            }
        });

        fileReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileSelect(discussion);
                //for (ActionListener a : fileReply.getActionListeners()) {
                //    fileReply.removeActionListener(a);
                //}
            }
        });
        editDeleteRep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public void fileSelect(Discussion current) {
        String filename = JOptionPane.showInputDialog(null, "What is your email?", "University Card", JOptionPane.QUESTION_MESSAGE);
        String fRep = "";
        try {
            File f = new File(filename);
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();

            while (line != null) {
                fRep += line;
                line = bfr.readLine();
            }

            Reply newRep = new Reply(student, fRep);
            ArrayList<Reply> temp = current.getReplyArray();
            temp.add(newRep);
            current.setReplies(temp);

            try {
                for (Course c : courseList) {
                    for (Discussion d : c.getForum()) {
                        if (d.getMessage().equals(current.message)) {
                            current.setCourse(c.getCourseName());
                        }
                    }
                }

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println("New Reply");
                writer.flush();
                writer.println(current.getCourse());
                writer.flush();
                writer.println("Teacher");
                writer.flush();
                writer.println(current.getMessage());
                writer.flush();
                writer.println(student.getUsername());
                writer.flush();
                writer.println(newRep.getMessage());
                writer.flush();

                courseList = (ArrayList<Course>) ois.readObject();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Back to discussion page");

            displayDisc(current, content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void newReplyFrame(Discussion current) {
        frame.dispose();

        frame = new JFrame("New Reply");
        content = frame.getContentPane();

        content.add(jpaneltop, BorderLayout.NORTH);
        JPanel center = new JPanel();
        JLabel replyPrompt = new JLabel("Write your reply");

        JLabel discussPrompt = new JLabel(current.getMessage());
        center.add(discussPrompt);

        center.add(replyPrompt);
        center.add(userText);
        center.add(confirm);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);

        userText.setText("");

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reply newRep = new Reply(student, userText.getText());
                ArrayList<Reply> temp = current.getReplyArray();
                temp.add(newRep);
                current.setReplies(temp);

                try {
                    for (Course c : courseList) {
                        for (Discussion d : c.getForum()) {
                            if (d.getMessage().equals(current.message)) {
                                current.setCourse(c.getCourseName());
                            }
                        }
                    }

                    PrintWriter writer = new PrintWriter(socket.getOutputStream());
                    writer.println("New Reply");
                    writer.flush();
                    writer.println(current.getCourse());
                    writer.flush();
                    writer.println("Teacher");
                    writer.flush();
                    writer.println(current.getMessage());
                    writer.flush();
                    writer.println(student.getUsername());
                    writer.flush();
                    writer.println(newRep.getMessage());
                    writer.flush();
                    System.out.println("356");

                    courseList = (ArrayList<Course>) ois.readObject();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("362");

                for (ActionListener a : confirm.getActionListeners()) {
                    confirm.removeActionListener(a);
                }
                System.out.println("Back to discussion page");

                displayDisc(current, content);
            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void displayAccount(Container con) {

        frame.dispose();

        frame = new JFrame("Account Settings");
        content = frame.getContentPane();

        usernameText = new JLabel("Username: " + username);
        passwordText = new JLabel("Password: " + password);
        grade = new JLabel("Current Grade: " + student.getGrade());


        content.add(jpaneltop, BorderLayout.NORTH);

        JPanel center = new JPanel();

        center.add(usernameText);
        center.add(editUsername);
        center.add(passwordText);
        center.add(editPassword);
        center.add(grade);
        center.add(deleteText);
        center.add(deleteAccount);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        editUsername.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                changeUsername();

                for (ActionListener a : courseButton.getActionListeners()) {
                    courseButton.removeActionListener(a);
                }
                for (ActionListener a : accountButton.getActionListeners()) {
                    accountButton.removeActionListener(a);
                }
                for (ActionListener a : editUsername.getActionListeners()) {
                    editUsername.removeActionListener(a);
                }
                for (ActionListener a : editPassword.getActionListeners()) {
                    editPassword.removeActionListener(a);
                }
                for (ActionListener a : deleteAccount.getActionListeners()) {
                    deleteAccount.removeActionListener(a);
                }

            }
        });
        editPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                changePassword();

                for (ActionListener a : courseButton.getActionListeners()) {
                    courseButton.removeActionListener(a);
                }
                for (ActionListener a : accountButton.getActionListeners()) {
                    accountButton.removeActionListener(a);
                }
                for (ActionListener a : editUsername.getActionListeners()) {
                    editUsername.removeActionListener(a);
                }
                for (ActionListener a : editPassword.getActionListeners()) {
                    editPassword.removeActionListener(a);
                }
                for (ActionListener a : deleteAccount.getActionListeners()) {
                    deleteAccount.removeActionListener(a);
                }

            }
        });

        deleteAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

                for(int i = 0; i < students.size(); i++)
                {
                    if(students.get(i).getUsername().equals(username))
                    {
                        try {
                            PrintWriter writer = new PrintWriter(socket.getOutputStream());
                            writer.println("Delete Student");
                            writer.flush();
                            writer.println(students.get(i).getUsername());
                            writer.flush();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        students.remove(i);
                    }
                }


                run();

                for (ActionListener a : courseButton.getActionListeners()) {
                    courseButton.removeActionListener(a);
                }
                for (ActionListener a : accountButton.getActionListeners()) {
                    accountButton.removeActionListener(a);
                }
                for (ActionListener a : editUsername.getActionListeners()) {
                    editUsername.removeActionListener(a);
                }
                for (ActionListener a : editPassword.getActionListeners()) {
                    editPassword.removeActionListener(a);
                }
                for (ActionListener a : deleteAccount.getActionListeners()) {
                    deleteAccount.removeActionListener(a);
                }

            }
        });

    }

    public void changeUsername() {
        frame.dispose();

        frame = new JFrame("Change Username");
        content = frame.getContentPane();

        JPanel center = new JPanel();

        center.add(userPrompt);
        center.add(userText);
        center.add(confirm);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);

        String oldUsername = username;

        userText.setText("");

        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    for (Student s : students) {
                        if (s.getUsername().equals(userText.getText())) {
                            throw new AccountExistsException("");
                        }
                    }

                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).getUsername().equals(oldUsername)) {
                            username = userText.getText();
                            students.get(i).setUsername(username);

                            try {
                                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                                writer.println("Change Student Username");
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

                for (ActionListener a : confirm.getActionListeners()) {
                    confirm.removeActionListener(a);
                }
                mainPageDisplay();
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
        center.add(confirm);

        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        content.add(center);

        passText.setText("");


        confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                password = passText.getText();

                for(int i = 0; i < students.size(); i++)
                {
                    if(students.get(i).getUsername().equals(student.getUsername()))
                    {
                        students.get(i).setPassword(password);

                        try {
                            PrintWriter writer = new PrintWriter(socket.getOutputStream());
                            writer.println("Change Student Password");
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

                for (ActionListener a : confirm.getActionListeners()) {
                    confirm.removeActionListener(a);
                }
                mainPageDisplay();


            }
        });

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


    public void displayCourse(Course course, Container con) {
        frame.dispose();
        frame = new JFrame("Student Dashboard");
        content = frame.getContentPane();

        JPanel discChoice = new JPanel();
        discChoice.setLayout(new BoxLayout(discChoice, BoxLayout.Y_AXIS));

        ArrayList<Discussion> forum = course.getForum();

        String[] discNames = new String[forum.size()];

        try {
            for (int i = 0; i < forum.size(); i++) {
                discNames[i] = (forum.get(i).getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        content.add(jpaneltop, BorderLayout.NORTH);

        discDropdown = new JComboBox<>(discNames);
        viewReplyButton = new JButton("See Replies");
        viewReplyButton.addActionListener(actionListener);

        try {
            discChoice.add(discDropdown);
            discChoice.add(viewReplyButton);
            content.add(discChoice,BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println("Null");
            e.printStackTrace();
        }
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }


    public StudentGUI(Student student, ArrayList<Course> courseList) {
        this.student = student;
        this.courseList = courseList;
        courseNames = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            courseNames[i] = courseList.get(i).getCourseName();
        }
        this.courseNames = courseNames;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StudentGUI());
    }

    public void run() {

        /* set up JFrame */
        frame = new JFrame("Login");
        content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        page = new StudentGUI();

        JPanel jpanelInput = new JPanel();
        JPanel jpanelPrompts = new JPanel();

        jpanelInput.setLayout(new BoxLayout(jpanelInput, BoxLayout.Y_AXIS));
        jpanelPrompts.setLayout(new BoxLayout(jpanelPrompts, BoxLayout.Y_AXIS));


        userText = new JTextField();
        userPrompt = new JLabel("Username: ");
        passText = new JTextField("", 5);
        passPrompt = new JLabel("Password: ");
        enterButton = new JButton("Login");
        enterButton.addActionListener(actionListener);

        jpanelInput.add(userPrompt);
        jpanelInput.add(userText);
        jpanelInput.add(passPrompt);
        jpanelInput.add(passText);
        jpanelInput.add(enterButton);

        content.add(jpanelInput, BorderLayout.CENTER);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}