/**
 * NoDiscussionPermissionException
 *
 * An exception to be thrown when you don't have permission
 *
 * @author Anthony Whittle, L02
 *
 * @version 04/30/2022
 *
 */
public class NoDiscussionPermissionException extends Exception
{

	public NoDiscussionPermissionException(String errorMessage) 
	{
		super(errorMessage);
	}

}
