/**
 * Incorrect Password Exception
 *
 * An exception thrown when the password is wrong
 *
 * @author Anthony Whittle, L02
 *
 * @version 04/30/2022
 *
 */
public class IncorrectPasswordException extends Exception
{

	public IncorrectPasswordException(String errorMessage) 
	{
		super(errorMessage);
	}

}
