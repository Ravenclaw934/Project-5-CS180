import java.io.*;
import java.util.ArrayList;
// FOR FORMATTED TXT FILE: username,password,grade(default 0)
//                         username,password,grade

/**
 * Student Import
 *
 * Imports the students into the program
 *
 * @author Logan Knight, L02
 *
 * @version 04/30/2022
 *
 */

public class StudentImport {

    private String filename;
    private ArrayList<Student> students;

    public StudentImport(String filename, ArrayList<Student> students) {
        this.filename = filename;
        this.students = students;
    }

    public ArrayList<Student> initializeStudents() {
        try {
            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            String line;
            String[] lineSplit;
            while ((line = bfr.readLine()) != null) {
                lineSplit = line.split(",");
                students.add(new Student(lineSplit[0], lineSplit[1], lineSplit[2]));
            }
        } catch (Exception e) {
            System.out.println("Error accessing student  account file");
        }
        return students;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void finalizeStudents(ArrayList<Student> studentsList) {
        try {
            File f = new File(filename);
            f.delete();
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);

            for (int i = 0; i < studentsList.size(); i++) {
                pw.println(studentsList.get(i).getUsername() + "," + studentsList.get(i).getPassword() 
                           + "," + studentsList.get(i).getGrade());
                pw.flush();
            }
            pw.close();

        } catch (Exception e) {
            System.out.println("Error writing to student account file");
        }

    }
}
