import java.io.Serializable;

/**
 * Reply
 *
 * The reply object
 *
 * @author Anthony Whittle, L02 
 *
 * @version 04/30/2022
 *
 */

public class Reply implements Post, Serializable
{

	User poster;
	String message;
	int votes = 0;

	public Reply(User poster, String message) {
		this.poster = poster;
		this.message = message;
		this.votes = 0;
	}

	public Reply(User poster, String message, int votes)
	{
		this.poster = poster;
		this.message = message;
		this.votes = votes;
	}

	public void editMessage(String test)
	{
		this.message = test;
	}

	public void addVote()
	{
		votes++;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public User getPoster() {
		return poster;
	}

	public int getVotes()
	{
		return votes;
	}

}
