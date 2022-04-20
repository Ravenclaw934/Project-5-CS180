import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

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
    JComboBox<String> courseDropdown;

    StudentGUI page; // variable of the type StudentGUI

    /* action listener for buttons */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == discussionButton) {
                page.clear();
            }
            if (e.getSource() == courseButton) {
                courseDropdown.getItemAt(courseDropdown.getSelectedIndex());
            }
            if (e.getSource() == accountButton) {
                page.displayAccount();
            }
            if (e.getSource() == deleteButton) {
                
            }
        }
    };

    public void displayAccount() {

        JPanel accountInfo = new JPanel();
        accountInfo.setLayout(new BoxLayout(accountInfo, BoxLayout.Y_AXIS));

        JLabel userDisp = new JLabel("Username: " + student.getUsername());
        JButton userUpdate = new JButton("Update Username");
        userUpdate.addActionListener(actionListener);
        JLabel passDisp = new JLabel("Password: " + student.getPassword());
        JButton passUpdate = new JButton("Update Password");
        JLabel gradeDisp = new JLabel("Grade: " + student.getGrade());
        passUpdate.addActionListener(actionListener);
        JButton deleteButton = new JButton("Delete Account");
        deleteButton.addActionListener(actionListener);

        accountInfo.add(userDisp);
        accountInfo.add(userUpdate);
        accountInfo.add(passDisp);
        accountInfo.add(passUpdate);
        accountInfo.add(deleteButton);
        frame.revalidate();
        frame.add(accountInfo,CENTER_ALIGNMENT);
    }

    /* set up page colors */
    public void clear() {
        /* set canvas to white with default page color */
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        currentBack = Color.white;
        graphics2D.setPaint(Color.black);
        repaint();
    }


    public void erase() {
        graphics2D.setPaint(currentBack);
    }

    public StudentGUI(Student student, ArrayList<Course> courseList) {
        this.student = student;
        this.courseList = courseList;
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
