
public interface User 
{
	
	public Discussion postDiscussion(String message) throws PermissionDeniedException, ActionFailedException;
	
	public Reply reply(String message) throws PermissionDeniedException;
	
	public String getUsername(); // Returns the username of the user
	
	public String getPassword(); // Returns the password of the user
	
	public void setUsername(String username) throws AccountExistsException; // Sets the username
	
	public void setPassword(String password); // Sets the password
	
	public Discussion editDiscussion(String message) throws PermissionDeniedException, ActionFailedException; // Edits the message of the discussion that's been posted
	
	public Reply postReply(String message, Discussion post) throws ActionFailedException; // Posts a reply to the discussion post
	
	
}
