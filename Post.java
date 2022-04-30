/**
 * Post
 *
 * This is the post interface
 *
 * @author Anthony Whittle, L02
 *
 * @version 04/30/2022
 *
 */
public interface Post 
{
	
	public String getMessage() throws ActionFailedException;
	
	public User getPoster() throws ActionFailedException;
	
	
	
	
	
	
}
