import java.io.*;
import java.util.ArrayList;
// FOR FORMATTED TXT FILE: username,password
//                         username,password

public class TeacherImport {
    private String filename;
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    public TeacherImport(String filename, ArrayList<Teacher> teacherList) {
        this.filename = filename;
        this.teachers = teachers;
    }

    public ArrayList<Teacher> initializeTeachers () {
        try {
            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            String line;
            String [] lineSplit;
            while ((line = bfr.readLine()) != null) {
                lineSplit = line.split(",");
                teachers.add(new Teacher(lineSplit[0], lineSplit[1]));
            }
        } catch (Exception e) {
            System.out.println("Error accessing teacher account file");
        }
        return teachers;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void finalizeTeachers(ArrayList<Teacher> teachers) throws IOException {
        try {
            File f = new File(filename);
            f.delete();
            FileOutputStream fos = new FileOutputStream(f, false);
            PrintWriter pw = new PrintWriter(fos);

            for (int i = 0; i < teachers.size(); i++) {
                pw.println(teachers.get(i).getUsername() + "," + teachers.get(i).getPassword());
            }
            pw.close();

        } catch (Exception e) {
            System.out.println("Error writing to teacher account file");
        }

    }
}
