import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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

public class TeacherGUI extends JComponent implements Runnable {
	
    public String username;
    public String password;
	
	
	// General variables for the GUI
	public ArrayList<Student> students = new ArrayList<Student>();
    public ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    public ArrayList<Course> courses = new ArrayList<Course>();
	public ArrayList<Discussion> discussions = new ArrayList<Discussion>();
    
    // Login text fields, labels, and buttons
    public JTextField userText;
    public JTextField passText;
    public JButton enterButton;
    public JLabel userPrompt;
    public JLabel passPrompt;

    // Main Menu button
    public JButton mainMenu = new JButton("Main Menu");
    
    // Main Menu text fields, labels, and buttons
    public JLabel accountTest = new JLabel("Account Information Menu");
    public JButton account = new JButton("Account Info");
    public JLabel courseText = new JLabel("Course Creation Menu");
    public JButton createCourse = new JButton("Create Course");
    public JLabel createDiscText = new JLabel("Create New Discussion Menu");
    public JButton createDiscussion = new JButton("Create Discussion");
    public JLabel editDiscText = new JLabel("Edit Existing Discussion Menu");
    public JButton editDiscussion = new JButton("Edit Discussion");
    public JLabel deleteDiscText = new JLabel("Delete Discussion Menu");
    public JButton deleteDiscussion = new JButton("Delete Discussion");
    public JLabel replyText = new JLabel("Create Reply Menu");
    public JButton reply = new JButton("Reply");
    public JButton popularReply = new JButton("View Popular Reply");
    public JLabel gradeText = new JLabel("Grade a Student");
    public JButton gradeStudent = new JButton("Grade");
    public JLabel editReplyText = new JLabel("Edit Student Reply Menu");
    public JButton editPost = new JButton("Edit Reply");
    public JLabel importText = new JLabel("Import Discussion Text");
    public JButton importDiscussion = new JButton("Import");
    
    // Account menu text fields, labels, and buttons
    public JLabel usernameText = new JLabel("Username: ");
    public JButton editUsername = new JButton("Change Username");
    public JLabel passwordText = new JLabel("Password: ");
    public JButton editPassword = new JButton("Change Password");
    public JLabel deleteText = new JLabel("Delete Account");
    public JButton deleteAccount = new JButton("Delete");
	public JLabel backText = new JLabel("Go Back");
	public JButton goBack = new JButton("Back");

	// Course Creation Menu TextFields, Labels, Button
	public JLabel createNewCourse = new JLabel("Create Course: ");
	public JLabel coursePrompt = new JLabel("Course Name: ");
	public JButton createCourseButton = new JButton("Create Course");

	// Discussion Creation Menu Labels and Button
	public JLabel createNewDisc = new JLabel("Create Discussion:");
	public JLabel discPrompt = new JLabel("Discussion Name: ");
	public JButton createDiscussionButton = new JButton("Create Discussion");

	// Discussion Creation Menu Labels and Button
	public JLabel editNewDisc = new JLabel("Edit Discussion:");
	public JLabel editDiscPrompt = new JLabel("Pick a discussion number to edit: ");
	public JButton editDiscussionButton = new JButton("Edit Discussion");
	public JLabel changeText = new JLabel("What are you changing it into?");

	// Discussion Delete Menu Labels and Button
	public JLabel deleteDisc = new JLabel("Delete Discussion: ");
	public JLabel deleteDiscPrompt = new JLabel("Pick a discussion number to delete: ");
	public JButton deleteDiscussionButton = new JButton("Delete Discussion");

    // Refresh Button
    public JButton refresh = new JButton("Refresh");
    
    public JButton confirm = new JButton("Confirm");
    
    JFrame frame;
    Container content;
    
    Student Anthony = new Student("ajwhittl","password");
    Teacher Jones = new Teacher("jjones", "password");
    Course history = new Course("History");

    public TeacherGUI() {
    	students = new ArrayList<Student>();
    	students.add(Anthony);
    	courses = new ArrayList<Course>();
    	courses.add(history);
    	teachers = new ArrayList<Teacher>();
    	teachers.add(Jones);
    	
    }

    public TeacherGUI(ArrayList<Student> students, ArrayList<Teacher> teachers, ArrayList<Course> courses) {
        this.students = students;
        this.teachers = teachers;
        this.courses = courses;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new TeacherGUI());
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean usernameExists = false;
            boolean login = false;

            if (e.getSource() == enterButton) {
                for (Teacher teacher : teachers) {
                    if (userText.getText().equals(teacher.getUsername())) {
                        usernameExists = true;
                        if (passText.getText().equals(teacher.getPassword())) {
                            login = true;
                            username = teacher.getUsername();
                            password = teacher.getPassword();
                            mainMenu();
                        }
                    }
                }
                if (!usernameExists) {
                    JOptionPane.showMessageDialog(null, "Username is Incorrect", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (!login) {
                    JOptionPane.showMessageDialog(null, "Password is Incorrect", "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    public void run() {
        /* set up JFrame */
        frame = new JFrame("Login");
        content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        TeacherGUI teacher = new TeacherGUI();

        JPanel jpanelInput = new JPanel();
        JPanel jpanelPrompts = new JPanel();

        jpanelInput.setLayout(new BoxLayout(jpanelInput, BoxLayout.Y_AXIS));
        jpanelPrompts.setLayout(new BoxLayout(jpanelPrompts, BoxLayout.Y_AXIS));


        userText = new JFormattedTextField();
        userPrompt = new JLabel("Username: ");
        passText = new JTextField("", 5);
        passPrompt = new JLabel("Password: ");
        enterButton = new JButton("Login");
        enterButton.addActionListener(actionListener);

        //passPrompt.setLabelFor(password);
        //userPrompt.setLabelFor(userText);


        //frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        jpanelInput.add(userPrompt);
        jpanelInput.add(userText);
        jpanelInput.add(passPrompt);
        jpanelInput.add(passText);
        jpanelInput.add(enterButton);

        //content.add(jpanelPrompts, BorderLayout.WEST);
        content.add(jpanelInput, BorderLayout.CENTER);

        //content.add(jpanelInput, BorderLayout.NORTH);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
    public void mainMenu()
    {
    	
    	frame.dispose();
    	
    	frame = new JFrame("Main Menu");
    	content = frame.getContentPane();
    	
		JPanel left = new JPanel();
		JPanel center = new JPanel();
		JPanel right = new JPanel();
		
		left.add(accountTest);
		left.add(account);
		left.add(courseText);
		left.add(createCourse);
		left.add(gradeText);
		left.add(gradeStudent);
		left.add(importText);
		left.add(importDiscussion);
		
		center.add(createDiscText);
		center.add(createDiscussion);
		center.add(editDiscText);
		center.add(editDiscussion);
		center.add(deleteDiscText);
		center.add(deleteDiscussion);
		
		right.add(replyText);
		right.add(reply);
		right.add(popularReply);
		right.add(editReplyText);
		right.add(editPost);
		
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		content.add(left, BorderLayout.WEST);
		content.add(center, BorderLayout.CENTER);
		content.add(right, BorderLayout.EAST);
    	
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    	
        account.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        accountMenu();
    	        
    	    }
    	});
        
        createCourse.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        courseMenu();
    	        
    	    }
    	});
        
        createDiscussion.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        DiscussionMenu();
    	        
    	    }
    	});
        
        editDiscussion.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        EditDiscussionMenu();
    	        
    	    }
    	});
        
        deleteDiscussion.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        deleteDiscussionMenu();
    	        
    	    }
    	});
        
        reply.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        
    	        
    	    }
    	});
        
        popularReply.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        
    	        
    	    }
    	});
        
        gradeStudent.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        
    	        
    	    }
    	});
        
        editPost.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        
    	        
    	    }
    	});
        
        importDiscussion.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	
    	        
    	        
    	    }
    	});
        
    	
    }
    
    public void accountMenu()
    {
    	
    	frame.dispose();
    	
    	frame = new JFrame("Account Settings");
    	content = frame.getContentPane();
    	
        usernameText = new JLabel("Username: " + username);
        passwordText = new JLabel("Password: " + password);
    	
    	JPanel center = new JPanel();
    	
    	center.add(usernameText);
    	center.add(editUsername);
    	center.add(passwordText);
    	center.add(editPassword);
    	center.add(deleteText);
    	center.add(deleteAccount);
		center.add(backText);
		center.add(goBack);
    	
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		content.add(center);
    	
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        editUsername.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	changeUsername();
    	        
    	        
    	    }
    	});
        
        editPassword.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	changePassword();
    	        
    	        
    	    }
    	});
        
        deleteAccount.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e) {
    	        
    	    	frame.dispose();
    	    	
    	    	for(int i = 0; i < teachers.size(); i++)
    	    	{
    	    		if(teachers.get(i).getUsername().equals(username))
    	    		{
    	    			teachers.remove(i);
    	    		}
    	    	}
    	    	
    	    	
    	    	run();
    	        
    	        
    	    }
    	});

		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainMenu();


			}
		});
    	
    }
    
    public void changeUsername()
    {
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
		        
		    	username = userText.getText();
		    	
				for(int i = 0; i < teachers.size(); i++)
				{
					if(teachers.get(i).getUsername().equals(oldUsername))
					{
						try {
							teachers.get(i).setUsername(username);
						} catch (AccountExistsException e1) {
							JOptionPane.showInternalMessageDialog(null, "Username is already in use!", "Action Failed",
		                            JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				
				accountMenu();
		        
		        
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
		
		String oldPassword = password;
		
		passText.setText("");
	
		
		confirm.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        
		    	password = passText.getText();
		    	
				for(int i = 0; i < teachers.size(); i++)
				{
					if(teachers.get(i).getPassword().equals(oldPassword))
					{
						try {
							teachers.get(i).setPassword(password);
						} catch (PasswordLimiterException ex) {
							JOptionPane.showInternalMessageDialog(null, "Password is already in use!", "Action Failed",
									JOptionPane.ERROR_MESSAGE);
						}

					}
				}
				
				accountMenu();


			}
		});

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    	
    }


	public void courseMenu() {

		frame.dispose();

		frame = new JFrame("Create Course");
		content = frame.getContentPane();


		JPanel center = new JPanel();

		center.add(createNewCourse);
		center.add(createCourseButton);
		center.add(backText);
		center.add(goBack);

		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		content.add(center);

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		createCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				createCourse();

			}
		});

		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainMenu();

			}
		});

	}


	public void createCourse(){
		frame.dispose();

		frame = new JFrame("Create Course");
		content = frame.getContentPane();

		JPanel center = new JPanel();

		center.add(coursePrompt);
		center.add(passText);
		center.add(confirm);

		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		content.add(center);


		passText.setText("");


		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Course course = new Course(passText.getText());

				courses.add(course);

				courseMenu();


			}
		});

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void DiscussionMenu(){

		frame.dispose();

		frame = new JFrame("Create Discussion");
		content = frame.getContentPane();

		JLabel discList = new JLabel("You currently have " + discussions.size() + " discussions.");
		JPanel center = new JPanel();


		center.add(discList);
		center.add(createNewDisc);
		center.add(createDiscussionButton);
		center.add(backText);
		center.add(goBack);

		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		content.add(center);

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		createDiscussionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				createDiscussion();

			}
		});

		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainMenu();

			}
		});


	}

	public void createDiscussion(){

		frame.dispose();

		frame = new JFrame("Create Discussion");
		content = frame.getContentPane();

		JPanel center = new JPanel();

		center.add(discPrompt);
		center.add(passText);
		center.add(confirm);

		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		content.add(center);


		passText.setText("");


		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Discussion disc = new Discussion(passText.getText());

				discussions.add(disc);

				DiscussionMenu();


			}
		});

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void EditDiscussionMenu(){

		frame.dispose();

		frame = new JFrame("Edit Discussion");
		content = frame.getContentPane();

		JLabel discList = new JLabel("You currently have " + discussions.size() + " discussions.");
		JPanel center = new JPanel();

		center.add(discList);
		center.add(editNewDisc);
		center.add(editDiscussionButton);
		center.add(backText);
		center.add(goBack);

		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		content.add(center);

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		editDiscussionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				editDiscussion();

			}
		});

		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainMenu();

			}
		});


	}

	public void editDiscussion() {

		frame.dispose();

		frame = new JFrame("Edit Discussion");
		content = frame.getContentPane();

		JPanel center = new JPanel();

		center.add(editDiscPrompt);
		center.add(userText);
		center.add(changeText);
		center.add(passText);
		center.add(confirm);

		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		content.add(center);


		passText.setText("");


		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int discNum = Integer.parseInt(userText.getText());

				Discussion discussion = new Discussion(passText.getText());

				discussions.set(discNum, discussion);

			}
		});

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public void deleteDiscussionMenu(){

		frame.dispose();

		frame = new JFrame("Delete Discussion");
		content = frame.getContentPane();

		JLabel discList = new JLabel("You currently have " + discussions.size() + " discussions.");
		JPanel center = new JPanel();

		center.add(discList);
		center.add(deleteDisc);
		center.add(deleteDiscussionButton);
		center.add(backText);
		center.add(goBack);

		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		content.add(center);

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		deleteDiscussionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				deleteDiscussion();

			}
		});

		goBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainMenu();

			}
		});


	}

	public void deleteDiscussion() {
		frame.dispose();

		frame = new JFrame("Delete Discussion");
		content = frame.getContentPane();

		JPanel center = new JPanel();

		center.add(deleteDiscPrompt);

		center.add(passText);
		center.add(confirm);

		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		content.add(center);


		passText.setText("");


		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int discNum = Integer.parseInt(userText.getText());


				discussions.remove(discNum);

			}
		});

		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
