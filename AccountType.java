import javax.swing.*;

public class AccountType {


    public static void main(String[] args) {
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

        if (acctType.equals("Student")) {
            StudentGUI studentGUI = new StudentGUI(students, courses);
        } else if (acctType.equals("Teacher")) {
            TeacherGUI teacherGUI = new TeacherGUI(teachers, courses);
        } else if (acctType.equals("Create Teacher Account")){
            CreationGUI creationGUI = new CreationGUI(teachers, courses, "T");
        } else {
            CreationGUI creationGUI = new CreationGUI(students, courses, "S");
        }

        } //showUniversityInputDialog
}
