
/**
 * When user type in an invalid command this exception will be thrown.
 * @author Guangling Yang
 *
 */
public class InvalidCommandException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * When user type in an invalid command this exception will be thrown.
	 * @param msg the exception message to display
	 */
	public InvalidCommandException(String msg) {
		super(msg);
	}
}
