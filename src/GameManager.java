import java.io.IOException;

/**
 * Game Manager class.
 * @author Guangling Yang
 *
 */
public class GameManager {

	private PlayerManager playerManager;
	private boolean p1Mark,p2Mark;
	private boolean correctMove;
	private Player p1,p2;
	private Move move;

	//A 2-D array to store move on the game board.
	private char[][] grid;
	//The result of game.
	private String result = null;
	//To specify the turn of current player, 0 for player 1 , 1 for player 2, 2 for player 3, etc.
	private int turn = 0;
	//Mark to specify when the game is over.
	private boolean quit = false;
	
	/**
	 * Set it private so the instance could not be initialized without PlayerManager
	 */
	@SuppressWarnings("unused")
	private GameManager(){
		
	}
	
	/**
	 * The constructor of GameManager Class
	 * @param playerManager
	 */
	public GameManager(PlayerManager playerManager) {
		this.playerManager = playerManager;
		p1Mark = false;
		p2Mark = false;
	}
	
	/**
	 * Play a TicTacToe game between the two players
	 * @param p1Uname user name of the first player
	 * @param p2Uname user name of the second player
	 */
	public void playGame(String p1Uname,String p2Uname){
		//initialize the game settings
		initGame();
		
		for (int i = 0; i < playerManager.getCurNumPlayers(); i++) {
			if (p1Uname.equals(playerManager.getPlayers()[i].getUserName())) {
				p1Mark = true;
				p1 = playerManager.getPlayers()[i];
				p1.setMark('O');
			}else if (p2Uname.equals(playerManager.getPlayers()[i].getUserName())) {
				p2Mark = true;
				p2 = playerManager.getPlayers()[i];
				p2.setMark('X');
			}
		}
		if(p1Mark && p2Mark){
			turn = 0;
			gameLoop();
		}
		else
			System.out.println("Player does not exist.");
	}
	
	/**
	 * <p>Main body of the Game Tic_Tac_Toe including three phases:
	 * <p>MovePhase,PrintPhase and JudgePhase.
	 */
	private void gameLoop() {
		printGrid();
		System.out.println();
		do{
			//Decide the current move turn of players.
			switch (turn) {
			case 0:
				turn = 1;
				correctMove = move(p1);
				result = "Game over. " + p1.getGivenName() + " won!";
				break;
			case 1:	
				turn = 0;
				correctMove = move(p2);
				result = "Game over. " + p2.getGivenName() + " won!";
				break;
			default:
				break;
			}
			if(correctMove){
				//Print the current state of game board.
				printGrid();

				//Judge and output the result
				System.out.println();
				result = judge();
				if (!result.equals("")) {
					System.out.println(result);
				}
			}else{
				
			}
		}while (!quit);
		Input.getKeyboard().nextLine();
	}
	

	/**
	 * During judge phase the accumulated streaks of players will be reviewed, and thereby decides who is exactly
	 * the winner of this game.
	 * @return a result after the judge: 
	 * <p>The <b>winner message</b> if there is a winner; 
	 * <p><b>''</b> if the game board has not yet been filled;
	 * <p><b>'Draw'</b> if there is no winner with a full-filled game board.
	 */
	private String judge(){
		//An accumulated number to judge a win game.
		int countWin = 0;
		//An accumulated number to judge the scanned grid.
		int countScan = 0;
		
		for (int i = 0;i < Global.GAME_BOARD_WIDTH;i++){
			for(int j = 0;j < Global.GAME_BOARD_HEIGHT;j++){
				if(grid[i][j] != ' '){
					countScan++;
					
					//horizontal
					for(countWin = 0;countWin < Global.WIN_STREAK 
							&& (j + countWin) < Global.GAME_BOARD_WIDTH;countWin++){
						if(grid[i][j + countWin] != grid[i][j])
							break;
					}
					if(countWin == Global.WIN_STREAK){
						quit = true;
						return result();
					}
					//vertical
					for(countWin = 0;countWin < Global.WIN_STREAK 
							&& (i + countWin) < Global.GAME_BOARD_HEIGHT;countWin++){
						if(grid[i + countWin][j] != grid[i][j])
							break;
					}
					if(countWin == Global.WIN_STREAK){
						quit = true;
						return result();
					}
					//diagonal
					for(countWin = 0;countWin < Global.WIN_STREAK 
							&& (i + countWin) < Global.GAME_BOARD_HEIGHT
							&& (j + countWin) < Global.GAME_BOARD_WIDTH;countWin++){
						if(grid[i + countWin][j + countWin] != grid[i][j])
							break;
					}
					if(countWin == Global.WIN_STREAK){
						quit = true;
						return result();
					}
					//anti-diagonal
					for(countWin = 0;countWin < Global.WIN_STREAK
							&& (i + countWin) < Global.GAME_BOARD_HEIGHT
							&& (j - countWin) >= 0;countWin++){
						if(grid[i + countWin][j - countWin] != grid[i][j])
							break;
					}
					if(countWin == Global.WIN_STREAK){
						quit = true;
						return result();
					}
				}
			}
		}

		if(countScan >= (Global.GAME_BOARD_HEIGHT * Global.GAME_BOARD_WIDTH)){
			p1.setNumGameDrawn(p1.getNumGameDrawn() + 1);
			p2.setNumGameDrawn(p2.getNumGameDrawn() + 1);
			p1.setNumGamePlayed(p1.getNumGamePlayed() + 1);
			p2.setNumGamePlayed(p2.getNumGamePlayed() + 1);
			quit = true;
			return "Game over. It was a draw!";
		}
		return "";
	}

	/**
	 * This phase is intended to print all the moves on game board.
	 */
	private void printGrid(){
		for(int i = 0;i < Global.GAME_BOARD_WIDTH;i++){
			for(int j = 0;j < Global.GAME_BOARD_HEIGHT;j++){
				System.out.print(grid[i][j]);
				if((j+1) % 3 == 0 && (i+1) < Global.GAME_BOARD_HEIGHT){
					System.out.println();
					System.out.println("-----");
				}
				else if((j+1) % 3 == 0 && (i+1) == Global.GAME_BOARD_HEIGHT){
					
				}
				else{
					System.out.print("|");
				}
			}
		}
	}
	
	/**
	 * During the move phase, a player performs his/her move by typing in the coordinates, and the coordinates
	 * should not exceeds the limit of this game board.
	 * @param player The Player instance that performs this move.
	 * @throws IOException 
	 */
	private boolean move(Player player){
		System.out.println(player.getGivenName() +"'s move:");
		move = player.makeMove(grid);
		if(move == null){
			turn = (turn == 1) ? 0:1;
			return false;
		}else {
			grid[move.getRow()][move.getColumn()] = player.getMark();
			return true;
		}
	}
	
	/**
	 * If one of the two players wins the game, deal with their statistics
	 * @return the winning message
	 */
	private String result(){
		if (turn == 1) {
			p1.setNumGamePlayed(p1.getNumGamePlayed() + 1);
			p1.setNumGameWon(p1.getNumGameWon() + 1);
			p2.setNumGamePlayed(p2.getNumGamePlayed() + 1);
			turn = 0;
			return result;
		}
		p2.setNumGamePlayed(p2.getNumGamePlayed() + 1);
		p2.setNumGameWon(p2.getNumGameWon() + 1);
		p1.setNumGamePlayed(p1.getNumGamePlayed() + 1);
		turn = 1;
		return result;
	}
	
	/**
	 * Initialize game 
	 */
	private void initGame(){
		grid = new char[Global.GAME_BOARD_WIDTH][Global.GAME_BOARD_HEIGHT];
		for(int i = 0; i < Global.GAME_BOARD_HEIGHT; i++)
			for(int j = 0; j< Global.GAME_BOARD_WIDTH; j++)
				grid[i][j] = ' ';
		quit = false;
	}
}
