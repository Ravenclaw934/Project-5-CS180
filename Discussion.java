import java.io.Serializable;
import java.util.ArrayList;

public class Discussion implements Post, Serializable {

	String message;
	ArrayList<Reply> replies = new ArrayList<Reply>();
	int numReplies = 0;
	String course;

	public Discussion(String message) {
		this.message = message;
	}

	public Discussion(String message, ArrayList<Reply> replies)
	{

		this.message = message;
		this.replies = replies;
	}
	public Discussion(String course, String message) {
		this.message = message;
		this.course = course;
	}

	public void editDiscussion(Teacher editer, String message)
	{

		this.message = message;

	}

	@Override
	public String getMessage()
	{

		return message;

	}

	public int getReplies()
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

	@Override
	public User getPoster() {
		return null;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
