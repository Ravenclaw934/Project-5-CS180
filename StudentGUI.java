import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

    private Image image; // the canvas
    private Graphics2D graphics2D;  // this will enable drawing
    public Color currentBack;
    public String[] courseNames = {"History","CS","math"}; // set for Testing purposes
    public String username;
    public String password;
    public Student student = new Student("bdgillis", "brunch");
    public ArrayList<Course> courseList;
    public JFrame frame;


    JButton discussionButton; // a button to view discussions
    JButton courseButton; // a button to veiw courses
    JButton accountButton; // a button to edit/view account
    JButton viewReplyButton; // a button to view replies to a discussion
    JButton writeReplyButton; // a button to write a reply
    JButton deleteButton;
    JButton userUpdate;
    JButton passUpdate;
    JComboBox<String> courseDropdown;
    JComboBox<String> discDropdown;

    StudentGUI page; // variable of the type StudentGUI

    /* action listener for buttons */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == discussionButton) {

            }
            if (e.getSource() == courseButton) {
                Course selectedCourse = null;
                String selectCourseName = courseDropdown.getItemAt(courseDropdown.getSelectedIndex());
                for (int i = 0; i < courseList.size(); i++) {
                    if (selectCourseName.equals(courseList.get(i).getCourseName())) {
                        selectedCourse = courseList.get(i);
                    }
                }
                page.displayCourse(selectedCourse, frame.getContentPane());

            }
            if (e.getSource() == accountButton) {
                page.displayAccount(frame.getContentPane());
            }
            if (e.getSource() == deleteButton) {
                int delete;
                delete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account?",
                        "Update Account", JOptionPane.YES_NO_OPTION);
                if (delete == JOptionPane.YES_OPTION) {
                    student = null;
                    JOptionPane.showMessageDialog(null, "Account Deleted", "Update Account", JOptionPane.PLAIN_MESSAGE);
                    try {
                        frame.dispose();
                    } catch (NullPointerException nul) {

                    }
                }
            }
            if (e.getSource() == userUpdate) {
                String newUser;
                do {
                    newUser = JOptionPane.showInputDialog(null, "What is your new username?",
                            "Update Account", JOptionPane.QUESTION_MESSAGE);
                    if ((newUser == null) || (newUser.isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Username cannot be empty!", "Update Account",
                                JOptionPane.ERROR_MESSAGE);
                    } //end if

                } while ((newUser == null) || (newUser.isEmpty()));
                try {
                    student.setUsername(newUser);
                } catch (AccountExistsException ex) {
                    ex.printStackTrace();
                }
                page.displayAccount(frame.getContentPane());
            }
            if (e.getSource() == passUpdate) {
                String newUser;
                do {
                    newUser = JOptionPane.showInputDialog(null, "What is your new password?",
                            "Update Account", JOptionPane.QUESTION_MESSAGE);
                    if ((newUser == null) || (newUser.isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Update Account",
                                JOptionPane.ERROR_MESSAGE);
                    } //end if
                } while ((newUser == null) || (newUser.isEmpty()));
                try {
                    student.setPassword(newUser);
                } catch (PasswordLimiterException ex) {
                    ex.printStackTrace();
                }
            }
        }
    };

    public void displayAccount(Container content) {

        JPanel accountInfo = new JPanel();
        accountInfo.setLayout(new BoxLayout(accountInfo, BoxLayout.Y_AXIS));

        JLabel userDisp = new JLabel("Username: " + student.getUsername());
        userUpdate = new JButton("Update Username");
        userUpdate.addActionListener(actionListener);
        JLabel passDisp = new JLabel("Password: " + student.getPassword());
        passUpdate = new JButton("Update Password");
        JLabel gradeDisp = new JLabel("Grade: " + student.getGrade());
        passUpdate.addActionListener(actionListener);
        deleteButton = new JButton("Delete Account");
        deleteButton.addActionListener(actionListener);
        try {
            accountInfo.add(userDisp);
            accountInfo.add(userUpdate);
            accountInfo.add(passDisp);
            accountInfo.add(passUpdate);
            accountInfo.add(deleteButton);
            content.add(BorderLayout.CENTER, accountInfo);
            content.revalidate(); // invokes layout manager
            content.repaint();
        } catch (NullPointerException e) {
            System.out.println("Null");
        }
    }

    public void displayCourse(Course course, Container content) {
        JPanel discChoice = new JPanel();
        discChoice.setLayout(new BoxLayout(discChoice, BoxLayout.Y_AXIS));

        ArrayList<Discussion> forum = course.getForum();
        String[] discNames = new String[forum.size()];

        try {
            for (int i = 0; i < forum.size(); i++) {
                discNames[i] = (forum.get(i).getMessage());
            }
        } catch (Exception e) {

        }
        discDropdown = new JComboBox<>(discNames);
        viewReplyButton = new JButton("See Replies");
        viewReplyButton.addActionListener(actionListener);

        try {
            discChoice.add(discDropdown);
            discChoice.add(viewReplyButton);
            content.add(BorderLayout.CENTER, discChoice);
            content.revalidate(); // invokes layout manager
            content.repaint();
        } catch (NullPointerException e) {
            System.out.println("Null");
        }
        
    }


        public void erase() {
        graphics2D.setPaint(currentBack);
    }

    public StudentGUI(Student student, ArrayList<Course> courseList) {
        this.student = student;
        this.courseList = courseList;
        courseNames = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            courseNames[i] = courseList.get(i).getCourseName();
        }
        this.courseNames= courseNames;
    }

    public StudentGUI() {

    }

    public StudentGUI(ArrayList<Course> courseList) {
        courseNames = new String[courseList.size()];
        for (int i = 0; i < courseList.size(); i++) {
            courseNames[i] = courseList.get(i).getCourseName();
        }
        this.courseNames= courseNames;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new StudentGUI());
    }

    public void run() {
        /* set up JFrame */
        frame = new JFrame("Student Dashboard");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        page = new StudentGUI();
        content.add(page, BorderLayout.CENTER);
        currentBack = Color.white;

        JPanel jpaneltop = new JPanel();

        courseButton = new JButton("See Course");
        courseDropdown = new JComboBox<>(courseNames);
        courseButton.addActionListener(actionListener);

        accountButton = new JButton("Account");
        accountButton.addActionListener(actionListener);
        viewReplyButton = new JButton("Erase");

        jpaneltop.add(courseDropdown);
        jpaneltop.add(courseButton);
        jpaneltop.add(accountButton);

        content.add(jpaneltop, BorderLayout.NORTH);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
