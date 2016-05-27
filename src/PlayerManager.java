import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * The player manager.
 * @author Guangling Yang
 *
 */
public class PlayerManager {

	private static final String ERROR = "The player does not exist.";
	private Player[] players;
	private int curNumPlayers;
	private boolean canDelete;
	PrintWriter outputWriter = null;
	Scanner localScanner = null;

	/**
	 * The player manager.
	 * */
	public PlayerManager(){
		init();
	}
	
	/**
	 * Create a player
	 * @param pUserName the user name of a player
	 * @param pFamilyName the family name of a player
	 * @param pGivenName the given name of a player
	 */
	public void createPlayer(String pUserName,String pFamilyName,String pGivenName) {
		for (int i = 0; i < curNumPlayers; i++) {
			if (this.getPlayers()[i].getUserName().equals(pUserName)) {
				System.out.println("The username has been used already.");
				return;
			}
		}

		HumanPlayer player = new HumanPlayer();
		player.setUserName(pUserName);
		player.setFamilyName(pFamilyName);
		player.setGivenName(pGivenName);
		if (curNumPlayers <= Global.PLAYERSNUM) {
			players[curNumPlayers] = player;
			curNumPlayers++;
		}else {
			System.out.println("The players have exceeded the maximum number!");
		}
	}
	/**
	 * Create an AI player.
	 * @param pUserName the user name of an AI player
	 * @param pFamilyName the family name of an AI player
	 * @param pGivenName the given name of an AI player
	 */
	public void createAIPlayer(String pUserName,String pFamilyName,String pGivenName){
		for (int i = 0; i < curNumPlayers; i++) {
			if (this.getPlayers()[i].getUserName().equals(pUserName)) {
				System.out.println("The username has been used already.");
				return;
			}
		}
		
		AIPlayer player = new AIPlayer();
		player.setUserName(pUserName);
		player.setFamilyName(pFamilyName);
		player.setGivenName(pGivenName);
		if (curNumPlayers <= Global.PLAYERSNUM) {
			players[curNumPlayers] = player;
			curNumPlayers++;
		}else {
			System.out.println("The players have exceeded the maximum number!");
		}
	}
	public void createAdvancedAIPlayer(String pUserName,String pFamilyName,String pGivenName) {
		for (int i = 0; i < curNumPlayers; i++) {
			if (this.getPlayers()[i].getUserName().equals(pUserName)) {
				System.out.println("The username has been used already.");
				return;
			}
		}
		
		AdvancedAIPlayer player = new AdvancedAIPlayer();
		player.setUserName(pUserName);
		player.setFamilyName(pFamilyName);
		player.setGivenName(pGivenName);
		if (curNumPlayers <= Global.PLAYERSNUM) {
			players[curNumPlayers] = player;
			curNumPlayers++;
		}else {
			System.out.println("The players have exceeded the maximum number!");
		}
	}

	/**
	 * Remove a player by his/her user name
	 * @param uName the user name of the player you want to remove.
	 */
	public void removePlayer(String uName) {
		for (int i = 0; i < curNumPlayers; i++){
			if (canDelete) 
				players[i-1] = players[i];
			
			if(players[i].getUserName().equals(uName) )
				canDelete = true;
		}
		if(!canDelete){
			System.out.println("The player does not exist.");
			return;	
		}
		players[curNumPlayers] = null;
		curNumPlayers--;
	}
	/**
	 * Remove all player in this game
	 */
	public void removeAllPlayer(){
		System.out.println("Are you sure you want to remove all players? (y/n)");
		String input = Input.getKeyboard().nextLine();
		if(input.equals("y")){
			players = new Player[Global.PLAYERSNUM];
			curNumPlayers = 0;
		}
	}
	/**
	 * Modify information of a player
	 * @param sourceUname the user name of the player you want to change
	 * @param changeFname the new family name of the player you want to assign
	 * @param changeGname the new given name of the player you want to assign
	 */
	public void modifyName(String sourceUname,String changeFname,String changeGname) {
		for (int i = 0; i < curNumPlayers; i++) {
			if(players[i].getUserName().equals(sourceUname)){
				players[i].setFamilyName(changeFname);
				players[i].setGivenName(changeGname);
				return;
			}
		}
		System.out.println(ERROR);
	}
	/**
	 * Reset statistics of a player
	 * @param uName the user name of the player you want to reset
	 */
	public void resetStatistics(String uName){
		for (int i = 0; i < curNumPlayers; i++) {
			if (players[i].getUserName().equals(uName)) {
				players[i].setNumGamePlayed(0);
				players[i].setNumGameWon(0);
				players[i].setNumGameDrawn(0);
				return;
			}
		}
		System.out.println(ERROR);
	}
	
	/**
	 * Reset statistics of all players.
	 */
	public void resetAllStatistics(){
		System.out.println("Are you sure you want to reset all player statistics? (y/n)");
		String input = Input.getKeyboard().nextLine();
		if (input.equals("y")) 
			for (int i = 0; i < curNumPlayers; i++) {
				players[i].setNumGamePlayed(0);
				players[i].setNumGameWon(0);
				players[i].setNumGameDrawn(0);
			}
	}
	
	/**
	 * Display information of a player
	 * @param uName the user name of the player you want to display
	 */
	public void displayInfo(String uName){
		for (int i = 0; i < curNumPlayers; i++) {
			if (players[i].getUserName().equals(uName)) {
				System.out.println(players[i].getUserName() + ","+players[i].getFamilyName() + "," 
						+ players[i].getGivenName() + "," + players[i].getNumGamePlayed() + " games," 
						+ players[i].getNumGameWon() + " wins," + players[i].getNumGameDrawn() + " draws");
				return;
			}
		}
		System.out.println(ERROR);
		return;
	}
	/**
	 * Display informations of all players
	 */
	public void displayAllInfo(){
		sortPlayerByAlphabet();
		for (int i = 0; i < curNumPlayers; i++) {
			System.out.println(players[i].getUserName() + ","+players[i].getFamilyName() + "," 
				+ players[i].getGivenName() + "," + players[i].getNumGamePlayed() + " games," 
				+ players[i].getNumGameWon() + " wins," + players[i].getNumGameDrawn() + " draws");
		}
	}
	/**
	 * Rank all the players by their winning percentage
	 */
	public void rankPlayer(){
		System.out.println(" WIN  | DRAW | GAME | USERNAME");
		sortPlayer();
		for (int i = 0; i < curNumPlayers; i++) {
			System.out.print(" ");
			if (players[i].getNumGamePlayed() > 0)
				System.out.format("%3s", Math.round(((double)players[i].getNumGameWon())/((double)players[i].getNumGamePlayed())*100));
			else 
				System.out.format("%3s","0");
			System.out.print("% | ");
			if (players[i].getNumGamePlayed() > 0)
				System.out.format("%3s", Math.round(((double)players[i].getNumGameDrawn())/((double)players[i].getNumGamePlayed())*100));
			else
				System.out.format("%3s","0");
			System.out.print("% | ");
			System.out.format("%2s", players[i].getNumGamePlayed());
			System.out.print("   | ");
			System.out.println(players[i].getUserName());
		}
	}
	
	/**
	 * Sort the player array by their winning percentage
	 */
	private void sortPlayer() {
		HashMap<Player,Double> map = new HashMap<Player,Double>();
		for(int i = 0;i < curNumPlayers;i++){
			if(players[i].getNumGamePlayed() > 0){
				map.put(players[i], ((double) players[i].getNumGameWon()/(double) players[i].getNumGamePlayed()));
			}else{
				map.put(players[i], -1.00);
			}
		}
		// keys are not allowed to be repeated
		List<Player> list = new ArrayList<Player>(map.keySet());
		Collections.sort(list,new Comparator<Player>() {
			@Override
			public int compare(Player o1,Player o2){
				if(map.get(o1) < map.get(o2)){
					return 1;
				}else if(map.get(o1) > map.get(o2)){
					return -1;
				}else {
					double o1DrawnRate,o2DrawnRate;
					o1DrawnRate = ((double)o1.getNumGameDrawn())/((double)o1.getNumGamePlayed());
					o2DrawnRate = ((double)o2.getNumGameDrawn())/((double)o2.getNumGamePlayed());
					if(o1DrawnRate < o2DrawnRate){
						return 1;
					}
					else if (o1DrawnRate > o2DrawnRate) {
						return -1;
					}
					else {
						if (o1.getUserName().compareTo(o2.getUserName()) > 0)
							return 1;
						if (o1.getUserName().compareTo(o2.getUserName()) < 0)
							return -1;
						return -1;
					}
				}
			}
		});
		
		for(int i = 0;i < curNumPlayers;i++){
			players[i] = list.get(i);
		}
	}

	private void sortPlayerByAlphabet(){
		HashMap<Player, String> map = new HashMap<Player, String>();
		for(int i = 0;i < curNumPlayers;i++){
			map.put(players[i], players[i].getUserName());
		}
		List<Player> list = new ArrayList<Player>(map.keySet());
		Collections.sort(list,new Comparator<Player>() {
			@Override
			public int compare(Player o1,Player o2){
				if(o1.getUserName().compareTo(o2.getUserName()) > 0)
					return 1;
				if(o1.getUserName().compareTo(o2.getUserName()) < 0)
					return -1;
				return -1;
			}
		});
		
		for(int i = 0;i < curNumPlayers;i++){
			players[i] = list.get(i);
		}
	}
	/**
	 * @return the players
	 */
	public Player[] getPlayers() {
		return players;
	}
	/**
	 * @return the curNum_players
	 */
	public int getCurNumPlayers() {
		return curNumPlayers;
	}
	/**
	 * Read player data from the <b>players.dat</b> file
	 */
	private void readData(){
		String[] tempStrings;
		try {
			localScanner = new Scanner(new FileInputStream(new File("./players.dat")));
			if(localScanner.hasNext())
				localScanner.nextLine();
			while(localScanner.hasNext()){
				tempStrings = localScanner.nextLine().split(" ");
				Player p;
				if(tempStrings[0].equals("Human"))
					p = new HumanPlayer();
				else if(tempStrings[0].equals("AI"))
					p = new AIPlayer();
				else{
					System.out.println("Wrong Data File!");
					break;
				}
				p.setUserName(tempStrings[1]);
				p.setGivenName(tempStrings[2]);
				p.setFamilyName(tempStrings[3]);
				p.setNumGamePlayed(Integer.parseInt(tempStrings[4]));
				p.setNumGameWon(Integer.parseInt(tempStrings[5]));
				p.setNumGameDrawn(Integer.parseInt(tempStrings[6]));
				if (curNumPlayers <= Global.PLAYERSNUM) {
					players[curNumPlayers] = p;
					curNumPlayers++;
				}else {
					System.out.println("The players have exceeded the maximum number!");
				}
			}
		} catch (FileNotFoundException e) {
			
		}
	}
	/**
	 * Write players data to the disk in <b>players.dat</b>, if it does not exist, the method will create a new file.
	 */
	private void writeData(){
		try {
			outputWriter = new PrintWriter(new FileOutputStream(new File("./players.dat")));
			outputWriter.println("Type UserName GivenName GamePlayed GameWon GameDrawn");
			for (int i = 0; i < curNumPlayers; i++) {
				if(players[i].getClass().equals(HumanPlayer.class))
					outputWriter.println("Human " + players[i].getUserName() + " " + players[i].getGivenName() + " " + players[i].getFamilyName()
						+ " " + players[i].getNumGamePlayed() + " " + players[i].getNumGameWon() + " " + players[i].getNumGameDrawn());
				else 
					outputWriter.println("AI " + players[i].getUserName() + " " + players[i].getGivenName() + " " + players[i].getFamilyName()
							+ " " + players[i].getNumGamePlayed() + " " + players[i].getNumGameWon() + " " + players[i].getNumGameDrawn());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			outputWriter.close();
		}
	}
	
	/**
	 * Exit the Player Manager.
	 */
	public void exit(){
		writeData();
	}
	/**
	 * Initialize the Player Manager every time the program launches.
	 */
	private void init() {
		players = new Player[Global.PLAYERSNUM];
		curNumPlayers = 0;
		canDelete = false;
		readData();
	}
}