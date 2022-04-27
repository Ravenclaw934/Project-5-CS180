import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable {

    Socket socket;
    public static final String STUDENT_FILE = "StudentFile.txt";
    public static final String TEACHER_FILE = "TeacherFile.txt";
    public static final String COURSE_FILE = "CourseFile.txt";
    public static final Object STUDENT_GATE = new Object();
    public static final Object TEACHER_GATE = new Object();
    public static final Object COURSE_GATE = new Object();

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                String request = reader.readLine();

                if (request.equals("Login")) {
                    StudentImport stuImp = new StudentImport(STUDENT_FILE, new ArrayList<>());
                    TeacherImport teachImp = new TeacherImport(TEACHER_FILE, new ArrayList<>());

                    ArrayList<Student> students = stuImp.initializeStudents();
                    ArrayList<Teacher> teachers = teachImp.initializeTeachers();
                    ArrayList<Course> courses = initializeCourses();

                    oos.writeObject(students);
                    oos.flush();
                    oos.writeObject(teachers);
                    oos.flush();
                    oos.writeObject(courses);
                    oos.flush();
                }
                if (request.equals("New Teacher")) {
                    String newUser = reader.readLine();
                    String newPass = reader.readLine();

                    addTeacher(newUser, newPass);
                }
                if (request.equals("New Student")) {
                    String newUser = reader.readLine();
                    String newPass = reader.readLine();

                    addStudent(newUser, newPass);
                }
                if (request.equals("Delete Student")) {
                    String user = reader.readLine();

                    deleteStudent(user);
                }
                if (request.equals("Change Student Username")) {
                    String oldUser = reader.readLine();
                    String newUser = reader.readLine();

                    changeStudentUser(oldUser, newUser);
                }
                if (request.equals("Change Student Password")) {
                    String user = reader.readLine();
                    String pass = reader.readLine();

                    changeStudentPass(user, pass);
                }
                if (request.equals("New Reply")) {
                    String course = reader.readLine();
                    String poster = reader.readLine();
                    String message = reader.readLine();
                    String student = reader.readLine();
                    String newReply = reader.readLine();

                    addReply(course, poster, message, student, newReply);

                    oos.writeObject(initializeCourses());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Course> initializeCourses() {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (COURSE_GATE) {
            try {
                File f = new File(COURSE_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String temp;
        int counter = 0;
        ArrayList<Reply> replies = new ArrayList<>();
        Course tempCourse = new Course("", new Teacher("", ""));
        ArrayList<Course> courses = new ArrayList<>();

        for (String s : lines) {
            if (!s.equals("-")) {
                if (counter == 0) {
                    String courseName = s.substring(0, s.indexOf(","));
                    temp = s.substring(s.indexOf(",") + 1);
                    String teacherName = temp.substring(0, temp.indexOf(","));
                    String discName = temp.substring(temp.indexOf(",") + 1);

                    ArrayList<Discussion> tempDiscs = new ArrayList<>();
                    tempDiscs.add(new Discussion(discName));
                    tempCourse = new Course(courseName, new Teacher(teacherName, ""), tempDiscs);
                    counter++;
                } else {
                    String studentName = s.substring(0, s.indexOf(","));
                    temp = s.substring(s.indexOf(",") + 1);
                    String replyName = temp.substring(0, temp.indexOf(","));
                    String votes = temp.substring(temp.indexOf(",") + 1);

                    Reply reply = new Reply(new Student(studentName, ""), replyName, Integer.parseInt(votes));
                    replies.add(reply);
                }
            } else {
                for (Discussion d : tempCourse.getForum()) {
                    d.setReplies(replies);
                }
                courses.add(tempCourse);
                counter = 0;
                replies = new ArrayList<>();
            }
        }

        for (Course c : courses) {
            if (!c.getCourseName().equals("")) {
                boolean saved = false;

                for (Course d : courses) {
                    if (c.getCourseName().equals(d.getCourseName())) {
                        if (saved) {
                            for (Discussion disc : d.getForum()) {
                                c.getForum().add(disc);
                            }
                            d.setName("");
                        } else {
                            saved = true;
                        }
                    }
                }
            }
        }

        ArrayList<Course> found = new ArrayList<>();
        for (Course c : courses) {
            if (c.getCourseName().equals("")) {
                found.add(c);
            }
        }
        courses.removeAll(found);

        return courses;
    }

    public void addTeacher(String user, String pass) {
        synchronized (TEACHER_GATE) {
            try {
                File f = new File(TEACHER_FILE);
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println();
                pw.print(user + "," + pass);

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addStudent(String user, String pass) {
        synchronized (STUDENT_GATE) {
            try {
                File f = new File(STUDENT_FILE);
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println();
                pw.print(user + "," + pass + "," + "0");

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeTeacherUser(String oldUser, String newUser) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (TEACHER_GATE) {
            try {
                File f = new File(TEACHER_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (String s : lines) {
                if (s.substring(0, s.indexOf(",")).equals(oldUser)) {
                    String temp = s.substring(s.indexOf(","));
                    lines.set(lines.indexOf(s), newUser + temp);
                }
            }

            try {
                File f = new File(TEACHER_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);

                boolean started = false;
                for (String s : lines) {
                    if (!started) {
                        pw.print(s);
                        started = true;
                    } else {
                        pw.print("\n" + s);
                    }
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeStudentUser(String oldUser, String newUser) {
        ArrayList<String> lines = new ArrayList<>();

        synchronized (STUDENT_GATE) {
            try {
                File f = new File(STUDENT_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (String s : lines) {
                if (s.substring(0, s.indexOf(",")).equals(oldUser)) {
                    String temp = s.substring(s.indexOf(","));
                    lines.set(lines.indexOf(s), newUser + temp);
                }
            }

            try {
                File f = new File(STUDENT_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);

                boolean started = false;
                for (String s : lines) {
                    if (!started) {
                        pw.print(s);
                        started = true;
                    } else {
                        pw.print("\n" + s);
                    }
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeTeacherPass(String user, String newPass) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (TEACHER_GATE) {
            try {
                File f = new File(TEACHER_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (String s : lines) {
                if (s.substring(0, s.indexOf(",")).equals(user)) {
                    lines.set(lines.indexOf(s), user + "," + newPass);
                }
            }

            try {
                File f = new File(TEACHER_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);

                boolean started = false;
                for (String s : lines) {
                    if (!started) {
                        pw.print(s);
                        started = true;
                    } else {
                        pw.print("\n" + s);
                    }
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeStudentPass(String user, String newPass) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (STUDENT_GATE) {
            try {
                File f = new File(STUDENT_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (String s : lines) {
                if (s.substring(0, s.indexOf(",")).equals(user)) {
                    String temp1 = s.substring(s.indexOf(",") + 1);
                    String temp2 = temp1.substring(temp1.indexOf(","));
                    lines.set(lines.indexOf(s), user + "," + newPass + temp2);
                }
            }

            try {
                File f = new File(STUDENT_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);

                boolean started = false;
                for (String s : lines) {
                    if (!started) {
                        pw.print(s);
                        started = true;
                    } else {
                        pw.print("\n" + s);
                    }
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteTeacher(String user) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (TEACHER_GATE) {
            try {
                File f = new File(TEACHER_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            ArrayList<String> found = new ArrayList<>();
            for (String s : lines) {
                if (s.substring(0, s.indexOf(",")).equals(user)) {
                    found.add(s);
                }
            }
            lines.removeAll(found);

            try {
                File f = new File(TEACHER_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);

                boolean started = false;
                for (String s : lines) {
                    if (!started) {
                        pw.print(s);
                        started = true;
                    } else {
                        pw.print("\n" + s);
                    }
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteStudent(String user) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (STUDENT_GATE) {
            try {
                File f = new File(STUDENT_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            ArrayList<String> found = new ArrayList<>();
            for (String s : lines) {
                if (s.substring(0, s.indexOf(",")).equals(user)) {
                    found.add(s);
                }
            }
            lines.removeAll(found);

            try {
                File f = new File(STUDENT_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);

                boolean started = false;
                for (String s : lines) {
                    if (!started) {
                        pw.print(s);
                        started = true;
                    } else {
                        pw.print("\n" + s);
                    }
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCourse(String courseName, String teacher) {
        synchronized (COURSE_GATE) {
            try {
                File f = new File(COURSE_FILE);
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(courseName + "," + teacher + "," + "Your First Discussion!");
                pw.println("-");

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addDiscussion(String courseName, String teacher, String discussion) {
        synchronized (COURSE_GATE) {
            try {
                File f = new File(COURSE_FILE);
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                pw.println(courseName + "," + teacher + "," + discussion);
                pw.println("-");

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void editDiscussion(String course, String teacher, String oldDiscussion, String newDiscussion) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (COURSE_GATE) {
            try {
                File f = new File(COURSE_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (String s : lines) {
                if (s.equals(course + "," + teacher + "," + oldDiscussion)) {
                    lines.set(lines.indexOf(s), course + "," + teacher + "," + newDiscussion);
                }
            }

            try {
                File f = new File(COURSE_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);
                for (String s : lines) {
                    pw.println(s);
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteDiscussion(String course, String teacher, String discussion) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (COURSE_GATE) {
            try {
                File f = new File(COURSE_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            ArrayList<String> found = new ArrayList<>();
            boolean deleteReplies = false;
            for (String s : lines) {
                if (deleteReplies && !s.equals("-")) {
                    found.add(s);
                }
                if (deleteReplies && s.equals("-")) {
                    found.add(s);
                    deleteReplies = false;
                }

                if (s.equals(course + "," + teacher + "," + discussion)) {
                    found.add(s);
                    deleteReplies = true;
                }
            }
            lines.removeAll(found);

            try {
                File f = new File(COURSE_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);
                for (String s : lines) {
                    pw.println(s);
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addReply(String course, String teacher, String discussion, String user, String reply) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (COURSE_GATE) {
            try {
                File f = new File(COURSE_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            int discussionLocation = lines.indexOf(course + "," + teacher + "," + discussion);
            int newI = 0;

            try {
                File f = new File(COURSE_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);

                for (int i = 0; i <= discussionLocation; i++) {
                    pw.println(lines.get(i));
                }
                for (int i = discussionLocation + 1; i < lines.size(); i++) {
                    if (!lines.get(i).equals("-")) {
                        pw.println(lines.get(i));
                    } else {
                        newI = i;
                        break;
                    }
                }
                pw.println(user + "," + reply + "," + "0");
                pw.println("-");
                for (int i = newI + 1; i < lines.size(); i++) {
                    if (i == lines.size() - 1) {
                        pw.print(lines.get(i));
                    } else {
                        pw.println(lines.get(i));
                    }
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void gradeStudent(String user, String grade) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (STUDENT_GATE) {
            try {
                File f = new File(STUDENT_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (String s : lines) {
                if (s.substring(0, s.indexOf(",")).equals(user)) {
                    String temp = s.substring(s.indexOf("," + 1));
                    lines.set(lines.indexOf(s), user + "," + temp.substring(0, temp.indexOf(",") + 1) + grade);
                }
            }

            try {
                File f = new File(STUDENT_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);

                boolean started = false;
                for (String s : lines) {
                    if (!started) {
                        pw.print(s);
                        started = true;
                    } else {
                        pw.print("\n" + s);
                    }
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void editReply(String course, String teacher, String discussion, String user, String oldReply, String newReply) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (COURSE_GATE) {
            try {
                File f = new File(COURSE_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            int discussionLocation = lines.indexOf(course + "," + teacher + "," + discussion);
            for (int i = discussionLocation + 1; i < lines.size(); i++) {
                String temp = lines.get(i).substring(lines.get(i).indexOf(",") + 1);
                if (lines.get(i).substring(0, lines.get(i).indexOf(",")).equals(user)) {
                    if (temp.substring(0, temp.indexOf(",")).equals(oldReply)) {
                        lines.set(i, user + "," + newReply + "," + temp.substring(temp.indexOf(",") + 1));
                        break;
                    }
                }
            }

            try {
                File f = new File(COURSE_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);
                for (String s : lines) {
                    pw.println(s);
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteReply(String course, String teacher, String discussion, String user, String reply) {
        ArrayList<String> lines = new ArrayList<>();
        synchronized (COURSE_GATE) {
            try {
                File f = new File(COURSE_FILE);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

            int discussionLocation = lines.indexOf(course + "," + teacher + "," + discussion);
            for (int i = discussionLocation + 1; i < lines.size(); i++) {
                String temp = lines.get(i).substring(lines.get(i).indexOf(",") + 1);
                if (lines.get(i).substring(0, lines.get(i).indexOf(",")).equals(user)) {
                    if (temp.substring(0, temp.indexOf(",")).equals(reply)) {
                        lines.remove(i);
                        break;
                    }
                }
            }

            try {
                File f = new File(COURSE_FILE);
                FileOutputStream fos = new FileOutputStream(f, false);
                PrintWriter pw = new PrintWriter(fos);
                for (String s : lines) {
                    pw.println(s);
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //both Arraylists for importing should come from scanning all the lines of the file
    //into an Arraylist

    public void importDiscussion(ArrayList<String> lines) {
        synchronized (COURSE_GATE) {
            try {
                File f = new File(COURSE_FILE);
                FileOutputStream fos = new FileOutputStream(f, true);
                PrintWriter pw = new PrintWriter(fos);
                for (String s : lines) {
                    pw.println(s);
                }

                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void importReply(ArrayList<String> lines, String course, String teacher, String discussion) {
        for (String s : lines) {
            String user = s.substring(0, s.indexOf(","));
            String reply = s.substring(s.indexOf(",") + 1);
            addReply(course, teacher, discussion, user, reply);
        }
    }
}