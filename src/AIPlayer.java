
/**
 * The AIPlayer class.
 * @author Guangling Yang
 *
 */
public class AIPlayer extends Player {
	private Move move;
	
	/**
	 * makeMove method of an AI Player
	 * */
	@Override
	protected Move makeMove(char[][] gameBoard) {
		for (int i = 0;i < Global.GAME_BOARD_HEIGHT;i++){
			for(int j = 0;j < Global.GAME_BOARD_WIDTH;j++){
				if(gameBoard[i][j] == 'O'
						|| gameBoard[i][j] == 'X')
					continue;
				if(gameBoard[i][j] == ' '){
					move = new Move();
					move.setRow(i);
					move.setColumn(j);
					return move;
				}
			}
		}
		System.out.println("The game board is already full!");
		return null;
	}
}
