import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * The advanced AI player.
 * @author Guangling Yang
 *
 */
public class AdvancedAIPlayer extends Player {
	BoardState bState;
	int countNum;
	int turn;
	boolean isFirstHand;
	/**
	 * The advanced AI player.
	 */
	public AdvancedAIPlayer() {
		
	}
	
	/**
	 * The makeMove method for AdvancedAIPlayer
	 * @param gameBoard A 3*3 array of char.
	 * @return Next move of the Advanced AI Player
	 * */
	@Override
	protected Move makeMove(char[][] gameBoard) {
		bState = new BoardState();
		bState.initBoard();
		countNum = 0;
		for(int i = 0;i < Global.GAME_BOARD_HEIGHT;i++){
			for(int j = 0;j < Global.GAME_BOARD_WIDTH;j++){
				if(bState.updateBoard(i, j, gameBoard[i][j]))
					countNum++;
			}
		}
		if(countNum % 2 == 0){
			turn = Global.TURN_P1;
			bState.setSelf('O');		 
			bState.setOpponent('X');	
		}
		else{
			turn = Global.TURN_P2;
			bState.setSelf('X');
			bState.setOpponent('O');
		}
		bState.updateHValue();
		if (isEnded(bState)) {
			//we have no move to perform now
			return null;
		}
		miniMax(bState, 1, turn);
		return bState.getNextMove();
	}

	/**
	 * Get the next possible moves of current BoardState.
	 * @param currentBoard the current BoardState
	 * @param turn the player current player turn
	 * @return a list of BoardStates
	 */
	protected List<BoardState> getNextMove(BoardState currentBoard, int turn) {
		List<BoardState> nextBoards = new ArrayList<BoardState>();
		if(isEnded(currentBoard))
			return nextBoards;
		for (int i = 0; i < Global.GAME_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Global.GAME_BOARD_WIDTH; j++) {
				BoardState possibleNextBoard = new BoardState(currentBoard);
				if(possibleNextBoard.getBoard()[i][j].equals(' ')){
					possibleNextBoard.updateBoard(i, j, (turn == Global.TURN_P1)? 'X':'O');
					possibleNextBoard.updateHValue();
					nextBoards.add(possibleNextBoard);
				}
			}
		}
		return nextBoards;
	}
	
	/**
	 * Determine if a BoardState is ended.
	 * @param bState the BoardState to judge
	 * @return true if it is ended, false if it is not
	 */
	protected  boolean isEnded(BoardState bState) {
		//An accumulated number to judge a win game.
		int countWin = 0;
		//An accumulated number to judge the scanned grid.
		int countScan = 0;
		for (int i = 0;i < Global.GAME_BOARD_WIDTH;i++){
			for(int j = 0;j < Global.GAME_BOARD_HEIGHT;j++){
				if(bState.getBoard()[i][j] != ' '){
					countScan++;

					//horizontal
					for(countWin = 0;countWin < Global.WIN_STREAK 
							&& (j + countWin) < Global.GAME_BOARD_WIDTH;countWin++){
						if(bState.getBoard()[i][j + countWin] != bState.getBoard()[i][j])
							break;
					}
					if(countWin == Global.WIN_STREAK){
						return true;
					}
					//vertical
					for(countWin = 0;countWin < Global.WIN_STREAK 
							&& (i + countWin) < Global.GAME_BOARD_HEIGHT;countWin++){
						if(bState.getBoard()[i + countWin][j] != bState.getBoard()[i][j])
							break;
					}
					if(countWin == Global.WIN_STREAK){
						return true;
					}
					//diagonal
					for(countWin = 0;countWin < Global.WIN_STREAK 
							&& (i + countWin) < Global.GAME_BOARD_HEIGHT
							&& (j + countWin) < Global.GAME_BOARD_WIDTH;countWin++){
						if(bState.getBoard()[i + countWin][j + countWin] != bState.getBoard()[i][j])
							break;
					}
					if(countWin == Global.WIN_STREAK){
						return true;
					}
					//anti-diagonal
					for(countWin = 0;countWin < Global.WIN_STREAK
							&& (i + countWin) < Global.GAME_BOARD_HEIGHT
							&& (j - countWin) >= 0;countWin++){
						if(bState.getBoard()[i + countWin][j - countWin] != bState.getBoard()[i][j])
							break;
					}
					if(countWin == Global.WIN_STREAK){
						return true;
					}
				}
			}
		}
		if(countScan >= (Global.GAME_BOARD_HEIGHT * Global.GAME_BOARD_WIDTH)){
			return true;
		}
		return false;
	}
	
	/**
	 * Each player minimizes the maximum payoff possible for the other.
	 * @param bState the BoardState at the very start
	 * @param depth the depth you want to search
	 * @param turn turn of current state
	 * @return either the best value of next BoardState or that of current board state when a board is full
	 */
	private int miniMax(BoardState bState,int depth,int turn){
		if (isEnded(bState)) {
			return bState.getHeuristicValue();
		}
		List<BoardState> nextMoves = getNextMove(bState,turn);
		if(nextMoves.isEmpty() || depth <= 0){
			return bState.getHeuristicValue();
		};
		int a = 0;
		Iterator<BoardState> itNextMoves = nextMoves.iterator();
		if(turn == Global.TURN_P2){
			a = Integer.MAX_VALUE;
			while (itNextMoves.hasNext()) {
				BoardState nextMove = itNextMoves.next();
				int nextTurn = (turn == Global.TURN_P1) ? Global.TURN_P2 : Global.TURN_P1;
				int b = miniMax(nextMove, depth - 1, nextTurn);
				if(a > b){
					a = b;
					bState.setNextMove(nextMove.getCurMove());
				}
			}
		}else{
			a = Integer.MIN_VALUE;
			while(itNextMoves.hasNext()){
				BoardState nextMove = itNextMoves.next();
				int nextTurn = (turn == Global.TURN_P1) ? Global.TURN_P2 : Global.TURN_P1;
				int b = miniMax(nextMove, depth - 1, nextTurn);
				if(a < b){
					a = b;
					bState.setNextMove(nextMove.getCurMove());
				}
			}
		}
		return a;
	}
}