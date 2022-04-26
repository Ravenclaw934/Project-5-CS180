import java.io.*;
import java.util.ArrayList;
// FOR FORMATTED TXT FILE: username,password,grade(default 0)
//                         username,password,grade

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

    public void finalizeStudents(ArrayList<Student> students) {
        try {
            File f = new File(filename);
            f.delete();
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);

            for (int i = 0; i < students.size(); i++) {
                pw.println(students.get(i).getUsername() + "," + students.get(i).getPassword() + "," + students.get(i).getGrade());
                pw.flush();
            }
            pw.close();

        } catch (Exception e) {
            System.out.println("Error writing to student account file");
        }

    }
}
