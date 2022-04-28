import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable
{

	ArrayList<Discussion> forum;
	Teacher teacher;
	String name;

	public Course(String name) // Constructor for creating courses
	{
		this.name = name;
		forum = new ArrayList<Discussion>();
	}

	public Course(String name, Teacher teacher)
	{
		this.name = name;
		this.teacher = teacher;
		forum = new ArrayList<Discussion>();
	}

	public Course(String name, Teacher teacher, ArrayList<Discussion> forum)
	{
		this.name = name;
		this.teacher = teacher;
		this.forum = forum;
	}

	public Course(String name, ArrayList<Discussion> forum) // Constructor for importing courses
	{
		this.name = name;
		this.forum = forum;
	}

	public String getCourseName() // Returns course name
	{
		return name;
	}

	public void deletePost(User user, Post post) throws PermissionDeniedException, ActionFailedException // Deletes the post from the forum
	{
		if(user.getClass() != Teacher.class)
		{
			throw new PermissionDeniedException("You do not have permission to delete posts");
		} else if(forum.contains(post))
		{
			forum.remove(forum.indexOf(post));
		} else
		{
			throw new ActionFailedException("Action failed because post does not exist");
		}

		if(forum.contains(post) == true)
		{
			throw new ActionFailedException("Deletion failed");
		}
	}

	public void editDiscussion(User user, Discussion post, String message) throws PermissionDeniedException, ActionFailedException, InvalidUserException // Edits the discussion to read a new message
	{
		if(user.getClass() != Teacher.class)
		{
			throw new PermissionDeniedException("You're a student");
		} else
		{
			post.editDiscussion((Teacher)user, message);

		}
	}

	public ArrayList<Discussion> getForum()
	{
		return forum;
	}

	public void setForum (ArrayList<Discussion> forum)
	{
		this.forum = forum;
	}

	public void setName(String name) {
		this.name = name;
	}
}
