import java.io.Serializable;

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

	public void editMessage(String message)
	{
		this.message = message;
	}

	public void addVote()
	{
		votes++;
	}

	@Override
	public String getMessage() throws ActionFailedException {
		return message;
	}

	@Override
	public User getPoster() throws ActionFailedException {
		return poster;
	}

	public int getVotes() throws ActionFailedException
	{
		return votes;
	}

}