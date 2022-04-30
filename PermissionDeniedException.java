/**
 * PermissionDeniedException
 *
 * An exception to be thrown when permission is denied
 *
 * @author Anthony Whittle, L02
 *
 * @version 04/30/2022
 *
 */
public class PermissionDeniedException extends Exception
{

	public PermissionDeniedException(String errorMessage) {
		super(errorMessage);
	}

}
