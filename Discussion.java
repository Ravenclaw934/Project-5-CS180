import java.util.ArrayList;

/**
 * Discussion
 *
 * Discussion object to be used in Course.
 *
 * Made for teachers to post discussions
 * 
 * @author Anthony Whittle, L02
 *
 * @version 04/08/2022
 *
 */

public class Discussion implements Post {

	Teacher poster;
	String message;
	ArrayList<Reply> replies = new ArrayList<Reply>();
	int numReplies = 0;
	String course;
	
	public Discussion(Teacher poster, String message) {
		this.poster = poster;
		this.message = message;
	}

	public Discussion(String message) {
		this.message = message;
	}
	
	public Discussion(Teacher poster, String message, ArrayList<Reply> replies)
	{
		this.poster = poster;
		this.message = message;
		this.replies = replies;
	}
	public Discussion(String course, Teacher poster, String message) {
		this.poster = poster;
		this.message = message;
		this.course = course;
	}
	
	public void editDiscussion(Teacher editer, String edited) throws InvalidUserException
	{
		if (editer == poster)
		{
			throw new InvalidUserException("You cannot edit a post if you did not originally post it!");
		}
		this.message = edited;
		
	}
	
	@Override
	public String getMessage() throws ActionFailedException
	{
		
		return message;
		
	}

	@Override
	public User getPoster() throws ActionFailedException
	{
		return poster;
		
	}
	
	public int getReplies() throws ActionFailedException
	{
		return replies.size();
	}
	
	public void setReplies(ArrayList<Reply> replies)
	{
		this.replies = replies;
	}
	
	public ArrayList<Reply> getReplyArray()
	{
		return replies;	
	}
	
	public void increaseReply() 
	{
		numReplies++;	
	}
	
	public String getCourse() 
	{
		return course;
	}

}
