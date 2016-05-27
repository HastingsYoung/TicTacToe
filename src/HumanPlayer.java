	
	/**
	 * The HumanPlayer class.
	 * @author Guangling Yang
	 *
	 */
	public class HumanPlayer extends Player
	{
		private Move move;
		/**
		 * makeMove method of a HumamPlayer
		 */
		@Override
		protected Move makeMove(char[][] gameBoard) {
			move = new Move();
			if(Input.getKeyboard().hasNext()){
				move.setRow(Input.getKeyboard().nextInt());
				move.setColumn(Input.getKeyboard().nextInt());
			}
			if(move.getColumn() < Global.GAME_BOARD_WIDTH && move.getColumn() >=0 
					&& move.getRow() < Global.GAME_BOARD_HEIGHT && move.getRow() >= 0){
				if(gameBoard[move.getRow()][move.getColumn()] == 'O'
						|| gameBoard[move.getRow()][move.getColumn()] == 'X'){
					System.out.println("Invalid move. The cell has been occupied.");
					return null;
				}
				return move;
			}
			else {
				System.out.println("Invalid move. You must place at a cell within {0,1,2} {0,1,2}.");
				return null;
			}
		}
	}