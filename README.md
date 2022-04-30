Anthony Whittle - Submitted Presentation via Brightspace
Anthony Whittle - Submitted Report via Brightspace
Anthony Whittle - Submitted Code via Vocareum.

DISCUSSION BOARD README


Compiling Information

When you are asked for a discussion prompt, they are labeled by numbers starting at 1. All files must be present for the program to run correctly. 


StudentImport.java

The function of this class is to maintain data persistence for all student data from prior uses of the program. Upon the program's beginning, a text file titled “Student.txt”, which contains the username, password, and grade of all existing students will be read and an ArrayList containing objects of the class Student (see Student.java) will be created, and sent back to Menu.java.
Upon the user exiting the program, the updated ArrayList of Students, containing new students or without any deleted students, will be received and written back into “Student.txt”. This is done in order to preserve any of the users for when the program is run again. 

Global Variables: 
	
private String filename - the file that contains student login data
	
Constructors: 
	
String filename
		
Methods:
	
initializeStudents - ArrayList<Student>
Parameters: None
This method reads the inputted file and from each line (which is in the form “username,password,grade”, creates a Student object that is added to an ArrayList. This ArrayList is returned from the method.

getFilename - String
Parameters : None
Returns name of the file containing student login information 
	
setFilename - void
Parameters : String filename
Sets the filename of this object to inputted String
	
finalizeStudents - void
Parameters : ArrayList<Student> students
Recieves list of students that has been updated in Menu.java after the user has exited the program. The updated student information is written into the set file to be used when the next user opens the file


TeacherImport.java

The function of this class is to maintain data persistence for all teacher data from prior uses of the program. Upon the program's beginning, a text file containing the username and password of all existing teachers will be read and an ArrayList containing objects of the class Teacher (see Teacher.java) will be created and sent back to Menu.java. 
Upon the user exiting the program, the updated ArrayList of Teachers, containing new teachers or without any deleted teachers, will be received and written back into the .txt file. This is done in order to preserve any of the users for when the program is run again. 

Global Variables: 

private String filename - the file that contains teacher login data
Constructors: 

String filename
		
Methods:

initializeTeachers - ArrayList<Teacher>
Parameters: None
This method reads the inputted file and from each line (which is in the form “username,password”, creates a Teacher object that is added to an ArrayList. This ArrayList is returned from the method.

getFilename 
Return type - String
Parameters : None
Returns name of the file containing teacher login information 

setFilename 
Return type - void
Parameters : String filename
Sets the filename of this object to inputted String

finalizeTeachers 
Return type - void
Parameters : ArrayList<Teacher> teachers
Receives list of teachers that has been updated in Menu.java after the user has exited the program. The updated teacher information is written into the set file to be used when the next user opens the file. 
DiscussionImport.java

The function of this class is to maintain data persistence for all Discussion and reply data from prior uses of the program. Upon the program's beginning, a text file containing the existing discussions and replies will be read and an ArrayList containing objects of the class Discussion (see Discussion.java) will be created and sent back to Menu.java. 
Upon the user exiting the program, the updated ArrayList of discussions and replies, containing new teachers or without any deleted teachers, will be received and written back into the .txt file. This is done in order to preserve any of the users for when the program is run again. 

Global Variables: 

private String filename - the file that contains teacher login data
Constructors: 

String filename
		
Methods:

initializeDiscussions - void
Parameters: None
This method reads the inputted file and from each line (which is in the form “username,password”, creates a Teacher object that is added to an ArrayList. This ArrayList is returned from the method.

getFilename 
Return type - String
Parameters : None
Returns name of the file containing teacher login information 

setFilename 
Return type - void
Parameters : String filename
Sets the filename of this object to inputted String

finalizeDiscussions 
Return type -  void
Parameters:
ArrayList<Discussion> discussions
ArrayList<Reply> replies

Receives a list of discussions and their replies that has been updated in Menu.java after the user has exited the program. The updated discussion and reply information is combined and written into the set file to be used when the next user opens the program. 



Post.java

Post is an interface that is used to define methods for all types of posts in the discussion board, both discussions and replies

Methods: 

getMessage - String (throws ActionFailedException)

getPoster - User (throws ActionFailedException)


Menu.java

Menu is the class that drives the program when a user is running the discussion board. It operates the welcoming, and through this class, the user can navigate through the project. 

Methods:

accountExists 
Return type - boolean
Parameters - User user

This method checks if the User object that the user is attempting to log in to exists in the program. If the username, password, and type of User all agree, the method returns true. Otherwise it will return false. 

courseExists 
Return type - boolean 
Parameters - User 

This method checks if the Course object already exists in the program data. If the name of the parameter Course agrees with the name of an existing course, the method remains true.  

Main 
Return type - void
Parameters - String[]

This method is where all the action occurs for the user. Upon beginning the program, this method calls the StudentImport, TeacherImport, and DiscussionImport classes to receive the data from prior uses of the program. Then, a menu is shown to the user asking them to either login or create an account for the discussion board. Once the user is logged in, either by logging in to an existing account or creating a new account, they are guided to the menu. Teachers can view, edit, and create discussions or replies on the discussion board. Students cannot edit or create discussions but can create replies. The users can also edit their account details. The menu will continue to loop until the user decides to exit, at which point the data is sent to the three Import classes, where it is preserved until the next user runs Menu.java. 


Reply.java

Variables
User poster - The user that posts the reply. Can be a teacher or a student
String message - The actual message for the reply.
Int votes - the number of votes that a particular message has.

Constructors
Reply
Parameters - User poster, String message

Reply
Parameters - User poster, String message, Int votes

Methods

editMessage
Return type - void
Parameters - String message

This method edits the message parameter for the reply and changes it to the string given in the parameter of the method.

addVote
Return type - void
Parameters - none

This method is pretty simple, it just increments the vote variable for the reply.

getMessage
Return type - String
Parameters - none

This method returns the message variable for the reply.

getPoster
Return type - User
Parameters - none

This method returns the user that posted the reply.

getVotes
Return type - int
Parameters - none

This method returns the number of votes that the reply has.

Student.java
Implements User

Student is the class that defines the role of student as a User of this program. This class stores the username and password of the student users, as well as the courses that they are enrolled in. It contains the restrictions of the students in the discussion forum, which include being able to edit their own posts and not others, and they cannot create discussions. 

Global Variables : 
String username
String password
boolean voted
int grade
ArrayList<Course> courses

Constructors:
String username, String password
String username, String password, int grade
String username, String password, boolean voted, int grade, ArrayList<Course> courses

Methods:

postDiscussion
Throws PermissionDeniedException
Return type - void 
Parameters - String

Throws an exception if the student attempts to create a discussion, which is reserved only for the teacher users

reply
Throws PermissionDeniedException
Return type - Reply
Parameters - String

This method returns a new Reply object (see Reply.java) of the new reply that the student creates.


getUsername
Return type - String
Parameters - none

This method returns the Student user’s username. 
setUsername
Return type - void
Parameters - String

This method sets the Student user’s username. 


getPassword
Return type - String
Parameters - none

This method returns the Student user’s password. 

setPassword
Throws PasswordLimiterException
Return type - void
Parameters - String

This method ensures that the String parameter fits the limits set for passwords, and if it fits the requirements, sets the Student user’s new password. 

editDiscussion
Throws PermissionDeniedException
Return type - void 
Parameters - String

Throws an exception if the student attempts to edit a discussion, which is reserved only for the teacher users. 


postReply 
Throws ActionFailedException
Return type - Reply
Parameters - String, Discussion

Creates a new Reply object with the Student and the message which is added to the specific Discussion thread that was passed in. 

getGrade
Return type - int
Parameters - none

Returns the student’s grade

setGrade 
Return type - void
Parameters - int

Sets the student’s grade. 


Teacher.java

Variables
String username
String password

Constructors
Teacher
Parameters - String username, String password

Methods
postDiscussion
Throws PermissionDeniedException
Return type - Discussion
Parameters - String message

Returns a discussion to be added to a course.

reply
Throws PermissionDeniedException
Return type - Reply
Parameters - String message

Returns a reply to be added to a discussion.

getUsername
Return type - String
Parameters - none

Returns the username for the user.

getPassword
Return type - String
Parameters - none

Returns the password for the user.

setUsername
Return type - void
Parameters - String username

Sets the username for the user and throws an error if the username is in use.

setPassword
Throws PasswordLimiterException
Return type - void
Parameters - String password

Sets the password for the user and throws a PasswordLimiterException if the password is too short, too long, or contains invalid characters.

editDiscussion
Throws PermissionDeniedException, ActionFailedException
Return type - Discussion
Parameters - String message

Returns a new discussion to replace a discussion that already exists. Throws a PermissionDeniedException if the person editing is not the original poster.

postReply
Return type - Reply
Parameters - String message, Discussion post

Returns a reply to be added to a Discussion.


User.java 
Interface

Methods

postDiscussion
Throws PermissionDeniedException, ActionFailedException
Return type - Discussion
Parameters - String

Returns a new discussion for the discussion board

reply
Throws PermissionDeniedException
Return type - Reply
Parameters - String

Returns a new reply to a discussion

getUsername
Return type - String
Parameters - none

Returns the username for the user.

getPassword
Return type - String
Parameters - none

Returns the password for the user.

setUsername
Throws AccountExistsException
Return type - void
Parameters - String username

Sets the username for the user and throws an error if the username is in use.

setPassword
Throws PasswordLimiterException
Return type - void
Parameters - String password

Sets the password for the user and throws a PasswordLimiterException if the password is too short, too long, or contains invalid characters.

editDiscussion
Throws PermissionDeniedException, ActionFailedException
Return type - Discussion
Parameters - String message

Returns a new discussion to replace a discussion that already exists. Throws a PermissionDeniedException if the person editing is not the original poster.

postReply
Return type - Reply
Parameters - String message, Discussion post

Returns a reply to be added to a Discussion.

AccountExistsException.java 

Extends Exception
This exception is thrown when the user attempts to create an account that already exists and prints out an error message to the user. 

Constructor: String errorMessage


ActionFailedException.java
 
Extends Exception
This exception is thrown when an error that does not have its own specific error class occurs and it prints out an error message to the user. 

Constructor: String errorMessage


IncorrectPasswordException.java
 
Extends Exception
This exception is thrown when an error that does not have its own specific error class occurs and it prints out an error message to the user. 

Constructor: String errorMessage


InvalidUserException.java
 
Extends Exception
This exception is thrown when the user attempts to access a feature that is not allowed based on their permissions in the discussion board and prints out an error message to the user. 

Constructor: String errorMessage

NoDiscussionExistsException.java
 
Extends Exception
This exception is thrown when the user attempts to access a feature that is not allowed based on their permissions in the discussion board and prints out an error message to the user. 

Constructor: String errorMessage

PasswordLimiterException.java
 
Extends Exception
This exception is thrown when the user attempts to access a feature that is not allowed based on their permissions in the discussion board and prints out an error message to the user. 

Constructor: String errorMessage


PermissionDeniedException.java
 
Extends Exception
This exception is thrown when the user attempts to access a feature that is not allowed based on their permissions in the discussion board and prints out an error message to the user. 

Constructor: String errorMessage


Server.java

Establishes a server socket.

Client.java

Connects the local client to the server depending on the type of identity the user chooses.

TeacherGUI.java

Extends JComponent implements Runnable

The function of this program is to build the teacher GUI with all the buttons included.

Constructors:

ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Course> courses,
                 Socket socket, ObjectInputStream ois

Methods:

run - void
Parameters: None
Sets up the login page.


mainMenu - void
Parameters: None
Sets up the main menu with all the buttons included.

accountMenu - void
Parameters: None
Sets up the account menu displaying current username and password, and three buttons: change username and change password, delete account.

changeUsername - void
Parameters: None
Changes the current user name.

changePassword - void
Parameters: None
Changes the current password.

courseMenu - void
Parameters: None
Sets up the course menu with two buttons: Create Course and Go Back.

createCourse - void
Parameters: None
Creates a course in the course ArrayList.

DiscussionMenu - void
Parameters: None
Sets up the discussion Menu with a box layout that allows one to select courses.

gradeMenu - void
Parameters: None
Sets up the grade menu with a box layout that allows you to select a student 

gradeMenu - void
Parameters: Student user
Allows you to select a student and give them a grade.

createDiscussion - void
Parameters: None
Sets up the discussion menu with a box layout that allows you to select a course

discussionCreator - void
Parameters: String Course
Allows you to create a discussion in the the course you have selected.
editDiscussionMenu - void
Parameters: None
Sets up the discussion menu that allows one to select a course to edit.


editDiscussionOne - void
Parameters: String course
Allows one to select a discussion under the course they have selected.

editDiscussionMenu - void
Parameters: String course, String discussion
Allows one to edit the discussion.

deleteDiscussionMenu - void
Parameters: void
Sets up the discussion menu that allows one to select a course to delete the discussion inside.

deleteDiscussion - void
Parameters: void
Allows one to choose a discussion thread and delete it.

replyMenu - void
Parameters: void
Sets up the reply Menu that allows one to choose a course to reply to.

replyPicker - void
Parameters: String course
Allows one to pick a discussion thread to reply to

createReply - void
Parameters: Course courses, Discussion discussion
Allows one to reply to a discussion thread

importDiscussion - void
Parameters: None
Allows one to select a .txt file and import it as a reply in a discussion.

removeListeners - void
Parameters: None
Removes the extra ActionListeners that causes the program to become slower every run.

StudentGUI.java

Extends JComponent implements Runnable

The function of this program is to build the student GUI with all the buttons included.

Constructors:

ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Course> courses,
                 Socket socket, ObjectInputStream ois

(Also contains a constructor with no arguments)

Methods:

run - void
Parameters: None
Sets up the login page.

mainPageDisplay - void
Parameters: None
Sets up the main menu with all the buttons included.

displayAccount - void
Parameters: None
Sets up the account menu displaying current username and password, and three buttons: change username and change password, delete account.

changeUsername - void
Parameters: None
Changes the current user name.

changePassword - void
Parameters: None
Changes the current password.

displayDisc - void
Parameters: Discussion
Displays the selected course’s discussion, showing the prompt and all existing replies. Shows the menu that allows the user to either type a new reply or upload a file containing their reply. 

checklogin - void
Parameters: None
Checks the username and password and allows a user to login if the information is correct. Otherwise, an error window pops up

dispCourse - void
Parameters: None
For a selected course, the existing discussion prompts are placed in a dropdown menu so the user can select the course they would like to view the replies of.

fileSelect - void
Parameters: Discussion
Prompts the user for the filename of their reply file and reads the file and adds it to the selected discussion as a new reply from that user. 
replyMenu - void
Parameters: void
Sets up the reply Menu that allows one to choose a course to reply to.

replyPicker - void
Parameters: String course
Allows one to pick a discussion thread to reply to

createReply - void
Parameters: Course courses, Discussion discussion
Allows one to reply to a discussion thread

importDiscussion - void
Parameters: None
Allows one to select a .txt file and import it as a reply in a discussion.

removeListeners - void
Parameters: None
Removes the extra ActionListeners that causes the program to become slower every run.

newReplyFrame - void
Parameters: Discussion
Opens a new GUI window that prompts the user to type their new reply to the discussion. Once the enter button is clicked, the reply is added to the discussion and the discussion is displayed

Main - void
Parameters: String[]
This method calls the GUI to launch and wait until the user interacts with the GUI. 

CreationGUI.java

extends JComponent implements Runnable

This .java file ensures that users can create/register their identity as either a student/teacher and then register themselves into the system.

Methods:

run - void
Parameters: void
Sets up the create account page.

ServerThread.java

This .java file ensures that everything is updated on the server side of the program.

Methods:

InitializeCourses - ArrayList<Courses>
Parameters: None
Initializes the courses from reading the course file to the gate.

addTeacher - void
Parameters: String user, String pass
Allows people to add teachers username and password to the server.

addStudent - void
Parameters: String user, String pass
Allows people to add students username and password to the server.

changeTeacherUser - void
Parametes: String oldUser, String newUser
Allows people to update their teacher username 

changeStudentUser - void
Parametes: String oldUser, String newUser
Allows people to update their student username

changeTeacherPass - void
Parametes: String oldUser, String newPass
Allows people to update their teacher password

changeStudentPass - void
Parametes: String oldUser, String newPass
Allows people to update their student password

deleteTeacher - void
Parameters: String user
Deletes the current teacher user.

deleteStudent - void
Parameters: String user
Deletes the current student user.

addCourse - void
Parameters: String courseName, String teacher
Allows teacher to add a course to the server.

addDiscussion - void
Parameters: String courseName, String teacher, String discussion
Allows teacher to add a discussion to the server.

editDiscussion - void
Parameters: String courseName, String teacher, String oldDiscussion, String newDiscussion
Allows teacher to edit a discussion in the server.

deleteDiscussion - void
Parameters: String courseName, String teacher, String discussion
Allows teacher to delete a discussion in the server.

addReply - void
Parameters: String course, String teacher, String discussion, String user, String reply
Allows user (Student or teacher) to add a reply to a specific discussion within a specific course. Sends information to server for data persistence

gradeStudent - void
Parameters: String user, String grade
Allows teacher to update grade for a specific student in server.

editReply - void
Parameters: String course, String teacher, String discussion, String user, String oldReply, String                  newReply
Allows user to edit their reply in a discussion within the server. 

deleteReply - void
Parameters: String course, String teacher, String discussion, String user, String reply
Allows student to delete one of their replies in the server or teacher to delete anyone’s reply

importDiscussion - void
Parameters: ArrayList<String> lines
Accepts the contents of an inputted file from a teacher and adds them to the server as a new discussion prompt


importReply - void
Parameters: ArrayList<String> lines, String course, String teacher, String discussion
Accepts the contents of an inputted file from a user and adds them to the server as a new reply to a discussion in a course





