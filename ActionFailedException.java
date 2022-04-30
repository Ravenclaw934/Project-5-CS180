/**
 * ActionFailedException
 *
 * Exception for if the action fails
 *
 * @author Anthony Whittle, L02 
 *
 * @version 04/30/2022
 *
 */
public class ActionFailedException extends Exception
{

	public ActionFailedException(String errorMessage) {
		super(errorMessage);
	}

}
