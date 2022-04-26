import java.util.ArrayList;

public class Student implements User 
{
	
	String username;
	String password;
	boolean voted = false;
	int grade = 0;
	ArrayList<Course> courses;

	public Student(String username, String password) 
	{
		this.username = username;
		this.password = password;
		courses = new ArrayList<Course>();
	}
	
	public Student(String username, String password, String grade) 
	{
		this.username = username;
		this.password = password;
		courses = new ArrayList<Course>();
		this.grade = Integer.parseInt(grade);
	}
	
	public Student(String username, String password, boolean voted, int grade, ArrayList<Course> courses)
	{
		this.username = username;
		this.password = password;
		this.voted = voted;
		this.grade = grade;
		this.courses = courses;
	}
	

	@Override
	public Discussion postDiscussion(String message) throws PermissionDeniedException 
	{
		
		throw new PermissionDeniedException("You do not have permission to post discussions");
		
	}

	@Override
	public Reply reply(String message) throws PermissionDeniedException 
	{
		
		Reply r = new Reply(this, message);
		
		return r;
		
	}

	@Override
	public String getUsername() 
	{
		return username;
	}

	@Override
	public String getPassword() 
	{
		return password;
	}

	@Override
	public void setUsername(String username) throws AccountExistsException {
		
		this.username = username;
		
	}

	@Override
	public void setPassword(String password)
	{
		
		this.password = password;
	}

	@Override
	public Discussion editDiscussion(String message) throws PermissionDeniedException, ActionFailedException 
	{
		
		throw new PermissionDeniedException("You do not have permission to edit discussions");
		
	}

	@Override
	public Reply postReply(String message, Discussion post) throws ActionFailedException {
		
		Reply r = new Reply(this, message);
		return r;
		
	}
	
	public int getGrade() {
		
		return grade;
	
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}

}
