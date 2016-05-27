
/**
 * The abstract player class.
 * @author Guangling Yang
 *
 */
public abstract class Player {

	protected String userName;
	protected String familyName;
	protected String givenName;
	protected int numGamePlayed;
	protected int numGameWon;
	protected int numGameDrawn;
	protected char mark;
	
	/**
	 * The abstract player class.
	 */
	public Player() {
		
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}

	/**
	 * @return the givenRame
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @return the numGamePlayed
	 */
	public int getNumGamePlayed() {
		return numGamePlayed;
	}

	/**
	 * @return the numGameWon
	 */
	public int getNumGameWon() {
		return numGameWon;
	}

	/**
	 * @return the numGameDrawn
	 */
	public int getNumGameDrawn() {
		return numGameDrawn;
	}

	/**
	 * @return the mark
	 */
	public char getMark() {
		return mark;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**
	 * @param givenRame the givenRame to set
	 */
	public void setGivenName(String givenRame) {
		this.givenName = givenRame;
	}

	/**
	 * @param numGamePlayed the numGamePlayed to set
	 */
	public void setNumGamePlayed(int numGamePlayed) {
		this.numGamePlayed = numGamePlayed;
	}

	/**
	 * @param numGameWon the numGameWon to set
	 */
	public void setNumGameWon(int numGameWon) {
		this.numGameWon = numGameWon;
	}

	/**
	 * @param numGameDrawn the numGameDrawn to set
	 */
	public void setNumGameDrawn(int numGameDrawn) {
		this.numGameDrawn = numGameDrawn;
	}

	/**
	 * @param mark the mark to set
	 */
	public void setMark(char mark) {
		this.mark = mark;
	}
	
	/**
	 * @param gameBoard the game board to make move
	 * @return an object of the Move class
	 */
	protected abstract Move makeMove(char[][] gameBoard);
}
