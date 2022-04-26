
public class Teacher implements User {

	String username;
	String password;
	
	public Teacher(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	@Override
	public Discussion postDiscussion(String message) throws PermissionDeniedException {
		
		Discussion d = new Discussion(message);
		
		return d;
		
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
	public void setUsername(String username) throws AccountExistsException 
	{
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
		
		Discussion d = new Discussion(message);
		
		return d;
		
	}

	@Override
	public Reply postReply(String message, Discussion post) throws ActionFailedException {
		
		Reply r = new Reply(this, message);
		
		return r;
		
	}



}
