
/**
 * When user type in too few arguments this exception will be thrown.
 * @author Guangling Yang
 *
 */
public class InvalidNumberException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * When user type in too few arguments this exception will be thrown.
	 * @param msg the exception message to display
	 */
	public InvalidNumberException(String msg){
		super(msg);
	}
}
