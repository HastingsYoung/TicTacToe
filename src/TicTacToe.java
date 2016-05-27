/**
 * @author Guangling Yang 812305 26/MAY/2016
 * <p>Programming and Software Development
 * <p>Assignment 3
 */
public class TicTacToe 
{
	private PlayerManager playerManager;
	private GameManager gameMananger;
	private String[] inputString;
	private String input;
	private final String INVALID_NUM_EXCEPTION = "Incorrect number of arguments supplied to command."; 
	public static void main(String[] args){
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.initMain();
		gameSystem.run();
	}

	/**
	 * Run the game console
	 */
	private void run(){
		System.out.println("Welcome to Tic Tac Toe!");
		do{
			System.out.println();
			System.out.print(">");
			input = Input.getKeyboard().nextLine();
			inputString = input.split(" ");
			try {
				switch (inputString[0]) {
				case "exit":
					playerManager.exit();
					System.out.println();
					System.exit(0);
					break;
				case "addplayer":
					inputString = inputString[1].split("\\,");
					if(inputString.length > Global.INPUT_COUNT_2){
						playerManager.createPlayer(inputString[0],inputString[1],inputString[2]);
						break;
						}
					else throw new InvalidNumberException(INVALID_NUM_EXCEPTION);
				case "addaiplayer":
					inputString = inputString[1].split("\\,");
					if(inputString.length > Global.INPUT_COUNT_2){
						playerManager.createAIPlayer(inputString[0],inputString[1],inputString[2]);
						break;
						}
					else throw new InvalidNumberException(INVALID_NUM_EXCEPTION);
				case "addadvancedaiplayer":
					inputString = inputString[1].split("\\,");
					if(inputString.length > Global.INPUT_COUNT_2){
						playerManager.createAdvancedAIPlayer(inputString[0],inputString[1],inputString[2]);
						break;
						}
					else throw new InvalidNumberException(INVALID_NUM_EXCEPTION);
				case "removeplayer":
					if(inputString.length > Global.INPUT_COUNT_1){
						inputString = inputString[1].split("\\,");
						playerManager.removePlayer(inputString[0]);
						break;
					}
					playerManager.removeAllPlayer();
					break;
				case "editplayer":
					inputString = inputString[1].split("\\,");
					if(inputString.length > Global.INPUT_COUNT_2){
						playerManager.modifyName(inputString[0], inputString[1], inputString[2]);
						break;
					}else throw new InvalidNumberException(INVALID_NUM_EXCEPTION);
				case "resetstats":
					if(inputString.length > Global.INPUT_COUNT_1){
						inputString = inputString[1].split("\\,");
						playerManager.resetStatistics(inputString[0]);
						break;
					}
					playerManager.resetAllStatistics();
					break;
				case "displayplayer":
					if(inputString.length > Global.INPUT_COUNT_1){
						inputString = inputString[1].split("\\,");
						playerManager.displayInfo(inputString[0]);
						break;
					}
						playerManager.displayAllInfo();
					break;
				case "rankings":
					playerManager.rankPlayer();
					break;
				case "playgame":
					inputString = inputString[1].split("\\,");
					if(inputString.length > Global.INPUT_COUNT_1){
						gameMananger.playGame(inputString[0],inputString[1]);
						break;
					}else throw new InvalidNumberException(INVALID_NUM_EXCEPTION);
				default:
					throw new InvalidCommandException("'" + inputString[0] + "' is not a valid command.");
				}
			} catch (InvalidCommandException e) {
				System.out.println(e.getMessage());
			} catch (InvalidNumberException e) {
				System.out.println(e.getMessage());
			}
		}while(true);
	}
	
	/**
	 * Initialize instances in the game
	 */
	private void initMain(){
		playerManager = new PlayerManager();
		gameMananger = new GameManager(playerManager);
	}
}