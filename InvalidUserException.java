/**
 * Invalid User Exception
 *
 * An exception to be thrown if the User is invalid
 *
 * @author Anthony Whittle, L02
 *
 * @version 04/30/2022
 *
 */
public class InvalidUserException extends Exception 
{

	public InvalidUserException(String errorMessage) 
	{
		super(errorMessage);
	}

}
