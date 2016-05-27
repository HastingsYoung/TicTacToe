
/**
 * The move of a player.
 * @author Guangling Yang
 *
 */
public class Move {

	private int curMoveX;
	private int curMoveY;
	/**
	 * The move of a player.
	 */
	public Move(){
	}
	/**
	 * The move of a player.
	 * @param x the x coordinate on the game board
	 * @param y the y coordinate on the game board
	 */
	public Move(int x,int y){
		curMoveX = x;
		curMoveY = y;
	}
	/**
	 * @return the curMoveX
	 */
	public int getRow() {
		return curMoveX;
	}
	/**
	 * @return the curMoveY
	 */
	public int getColumn() {
		return curMoveY;
	}
	/**
	 * @param curMoveX the curMoveX to set
	 */
	public void setRow(int curMoveX) {
		this.curMoveX = curMoveX;
	}
	/**
	 * @param curMoveY the curMoveY to set
	 */
	public void setColumn(int curMoveY) {
		this.curMoveY = curMoveY;
	}
}
