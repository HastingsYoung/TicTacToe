
/**
 * State of a game board.
 * @author Guangling Yang
 */
public class BoardState {
	private Character[][] board;
	private Move curMove;
	/**
	 * @return the nextMove
	 */
	public Move getNextMove() {
		return nextMove;
	}

	private Move nextMove;
	private char self;
	private char opponent;
	private int heuristicValue;
	/**
	 * Default Constructor of BoardState
	 */
	public BoardState(){
		
	}
	/**
	 * Initialize a BoardState instance with another BoardState instance.
	 * @param board the board to be clone.
	 */
	public BoardState(BoardState board){
		try {
			this.board = board.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Initialize the board to empty.
	 */
	public void initBoard(){
		board = new Character[Global.GAME_BOARD_HEIGHT][Global.GAME_BOARD_WIDTH];
		for (int i = 0; i < Global.GAME_BOARD_HEIGHT; i++) {
			for (int j = 0; j < Global.GAME_BOARD_WIDTH; j++) {
				board[i][j] = ' ';
			}
		}
	}
	/**
	 * @return the board
	 */
	public Character[][] getBoard() {
		return board;
	}
	/**
	 * @return the heuristicValue
	 */
	public int getHeuristicValue() {
		return heuristicValue;
	}
	/**
	 * @return the curMove
	 */
	public Move getCurMove() {
		return curMove;
	}
	
	/**
	 * @return the self
	 */
	public char getSelf() {
		return self;
	}
	/**
	 * @return the opponent
	 */
	public char getOpponent() {
		return opponent;
	}
	
	/**
	 * @param nextMove the nextMove to set
	 */
	public void setNextMove(Move nextMove) {
		this.nextMove = nextMove;
	}
	/**
	 * @param curMove the curMove to set
	 */
	public void setCurMove(Move curMove) {
		this.curMove = curMove;
	}
	/**
	 * @param self the self to set
	 */
	public void setSelf(char self) {
		this.self = self;
	}
	/**
	 * @param opponent the opponent to set
	 */
	public void setOpponent(char opponent) {
		this.opponent = opponent;
	}
	/**
	 * @param heuristicValue the heuristicValue to set
	 */
	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	/**
	 * update the heuristic value.
	 * @return the curTurn
	 */

	public void updateHValue(){
		int value = 0;
		//Eight win cases
		value += heuristicValue(0, 0, 0, 1, 0, 2);
		value += heuristicValue(1, 0, 1, 1, 1, 2);
		value += heuristicValue(2, 0, 2, 1, 2, 2);
		value += heuristicValue(0, 0, 1, 0, 2, 0);
		value += heuristicValue(0, 1, 1, 1, 2, 1);
		value += heuristicValue(0, 2, 1, 2, 2, 2);
		value += heuristicValue(0, 0, 1, 1, 2, 2);
		value += heuristicValue(0, 2, 1, 1, 2, 0);
		
		this.heuristicValue = value;
	}
	
	/**
	 * Assume that both sides want to win
	 */
	private int heuristicValue(int rowA,int colA,int rowB,int colB,int rowC,int colC){
		
		int value = 0;
		int cross = 0;
		int cycle = 0;
		
		if(board[rowA][colA] == 'X')
			cross++;
		else if(board[rowA][colA] == 'O')
			cycle++;
		if(board[rowB][colB] == 'X')
			cross++;
		else if(board[rowB][colB] == 'O')
			cycle++;
		if(board[rowC][colC] == 'X')
			cross++;
		else if(board[rowC][colC] == 'O')
			cycle++;
		
		switch (cross) {
		case 1:
			switch (cycle) {
			case 0:
				return 1;
			case 1:
				return 0;
			case 2:
				//stop your opponent to win
				return 500;
			}
		case 2:
			switch (cycle) {
			case 0: 
				return 10;
			case 1:
				//your opponent tries to stop you win
				return -500;
			}
		case 3:
			//try to win
			return 10000;
		default:
			switch (cycle) {
			case 1:
				return -1;
			case 2:
				return -10;
			case 3:
				//your opponent tries to win
				return -10000;
			}
		}
		return value;
	}
	/** Update board.
	 * @param i move for x coordinator
	 * @param j move for y coordinator
	 * @param mark the mark of this move
	 * @return true if update succeed, vice versa.
	 */
	public boolean updateBoard(int i,int j,char mark){
		if(this.board[i][j] == ' ')
			this.board[i][j] = mark;
		else{
			System.out.println("Wrong");
			return false;
		}
		if(mark == ' ')
			return false;
		curMove = new Move();
		curMove.setRow(i);
		curMove.setColumn(j);

		self = mark;
		opponent = (mark == 'O')? 'X' : 'O';
		return true;
	}
	
	@Override
	protected final Character[][] clone() throws CloneNotSupportedException {
		Character[][] newBoard = new Character[Global.GAME_BOARD_HEIGHT][Global.GAME_BOARD_WIDTH];
		for(int i = 0;i < Global.GAME_BOARD_HEIGHT;i++){
			for(int j = 0;j < Global.GAME_BOARD_WIDTH;j++){
				newBoard[i][j] = this.board[i][j];
			}
		}
		return newBoard;
	}
}
