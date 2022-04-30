import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
// NEED TO INTIALIZE TEACHERS AND STUDENTS FIRST
// FOR FORMATTED TXT FILE: course,Teacher(username),discussion
//                         Student(username),reply
//                         Student(username),reply (and so on)
//                         "[space]"
//                         course,Teacher(username),discussion
//                         Student(username),reply

/**
 * Discussion Import
 *
 * This imports the discussions into the interface at the start
 *
 * @author Logan Knight, L02 
 *
 * @version 04/30/2022
 *
 */

public class DiscussionImport {

    private String filename;
    private ArrayList<Discussion> discussions = new ArrayList<>();
    private ArrayList<Reply> replies = new ArrayList<>();


    public DiscussionImport(String filename) {
        this.filename = filename;
    }

    public ArrayList<Course> initializeDiscussions(ArrayList<Teacher> teachers, ArrayList<Student> students) {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            File f = new File(filename);
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            String line;
            String[] lineSplit;
            Teacher currentTeach;
            Student currentStud;
            int currentDisc = -1;
            courses = new ArrayList<Course>();
            do {
                //GET TEAHCER POST
                line = bfr.readLine();
                lineSplit = line.split(",");
                for (int i = 0; i < teachers.size(); i++) {
                    if (teachers.get(i).getUsername().equals(lineSplit[1])) {
                        currentTeach = teachers.get(i);
                        discussions.add(new Discussion(lineSplit[0], lineSplit[2]));
                        currentDisc++;
                    }
                }
                do {
                    //GET REPLIES
                    line = bfr.readLine();
                    lineSplit = line.split(",");
                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).getUsername().equals(lineSplit[0])) {
                            currentStud = students.get(i);
                            replies.add(new Reply(currentStud, lineSplit[1]));
                            //discussions.get(currentDisc).increaseReply();
                        }
                    }
                    for (int j = 0; j < teachers.size(); j++) {
                        if (teachers.get(j).getUsername().equals(lineSplit[0])) {
                            currentTeach = teachers.get(j);
                            replies.add(new Reply(currentTeach, lineSplit[1]));
                            //discussions.get(currentDisc).increaseReply();
                        }
                    }

                } while (!line.equals(" "));
                discussions.get(currentDisc).setReplies(replies);
                replies = null;

            } while (line != null);
            boolean isnew = true;
            for (int i = 0; i < discussions.size(); i++) {
                for (int c = 0; c < courses.size(); c++) {
                    if (courses.get(c).getCourseName().equals(discussions.get(i).getCourse())) {
                        courses.get(c).getForum().add(discussions.get(i));
                        courses.get(c).setForum(courses.get(c).getForum());
                        isnew = false;
                    }
                }
                if (isnew) {
                    ArrayList<Discussion> temp = new ArrayList<Discussion>();
                    temp.add(discussions.get(i));
                    courses.add(new Course(discussions.get(i).getCourse(), temp));
                }
            }
        } catch (Exception e) {
            System.out.println("Error accessing discussion and replies file");
        }
        return courses;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void finalizeDiscussion(ArrayList<Course> courses) {
        try {
            File f = new File(filename);
            f.delete();
            FileOutputStream fos = new FileOutputStream(f, true);
            PrintWriter pw = new PrintWriter(fos);
            User current;
            Discussion currentdisc;

            for (int i = 0; i < courses.size(); i++) {
                for (int d = 0; d < (courses.get(i).getForum().size()); d++) {
                    currentdisc = courses.get(i).getForum().get(d);
                    pw.print("\n" + courses.get(i).getCourseName() + ",");
                    pw.println(currentdisc.getPoster().getUsername() + "," + currentdisc.getMessage());
                    for (int j = 0; j < currentdisc.getReplies(); j++)
                    {
                        current = (currentdisc.getReplyArray().get(j).getPoster());
                        if (current instanceof Teacher) {
                            pw.println(((Teacher) current).getUsername() + "," + (currentdisc.getMessage()));
                        } else {
                            pw.println(((Student) current).getUsername() + "," + 
                                       (currentdisc.getReplyArray().get(j).getMessage()));

                        }
                    }
                }

                pw.println(" ");
            }
            pw.close();

        } catch (Exception e) {
            System.out.println("Error writing to Discussion and repliess file");
        }
    }

    public ArrayList<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(ArrayList<Discussion> discussions) {
        this.discussions = discussions;
    }

    public ArrayList<Reply> getReplies() {
        return replies;
    }

    public void setReplies() {
        this.replies = replies;
    }
}
