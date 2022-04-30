/**
 * AccountExistsException
 *
 * An exception for if the account exists
 *
 * @author Anthony Whittle, L02
 *
 * @version 04/30/2022
 *
 */

public class AccountExistsException extends Exception {

	public AccountExistsException(String errorMessage) {
		super(errorMessage);
	}

}
