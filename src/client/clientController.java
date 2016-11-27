package client;

import view.Draughtboard;
import model.Board;
import model.Boat;
import model.Box;
import model.Player;

public class clientController {

	private boolean isItYourTurn;
	private static boolean isGameSet = false;
	private static boolean isPlacementActive = false;
	
	
	public static Box synchronizingVar = new Box(1,1,1,1,"qsdfqsdfqsf");
	private static boolean firstCaseDone = false;
	private static int lastX;
	private static int lastY;
	
	private Player player;
	private static Boat[] ship = new Boat[5];
	
	
	private static Board board;
	private static Board boardOpponent;
	private int currentGameNumber;

	/**
	 * Controller client method - One for each player
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		/**
		 * Procédure globale du jeu : 1 - Lorsque le joueur se connecte, une
		 * fenêtre lui est crée 2 - Une méthode de connection est appellé via le
		 * bouton spécifique 3 - Il est demandé immédiatement au joueur de
		 * placer ses cinq bateaux (bateau par bateau, et case par case) 4 - Une
		 * fois que c'est fait, le joueur entre en attente d'un adversaire 5 -
		 * Lorsque deux adversaires sont connectés, le joueur va alterner entre
		 * les phases de clique sur une case et l'attente de la réception du
		 * coup adverse 6 - Après chaque coup, on vérifie si un des joueurs est
		 * mort, si c'est le cas, la partie se termine alors avec l'annonce du
		 * vainqueur
		 */
		Draughtboard dg = new Draughtboard();
		placeShip(1);
		
	}

	public static void connect() {
		// TODO Auto-generated method stub

		/**
		 * Appeller ici la méthode de connexion du threadedClient Envoi de son
		 * board au serveur pour stockage Le boardOpponent sera récupérer lors
		 * du pairing par le serveur
		 */
		if(isGameSet){
			
			
		}
	}

	public  static void placeShip(int i) throws InterruptedException{
		int k = 0;
		Box[] currentBoatPosition = new Box[i];
		Boat currentBoat = new Boat(currentBoatPosition,"safe");
		System.out.println(synchronizingVar);
		while(k<i){
		isPlacementActive = true;	
		synchronizingVar = currentBoatPosition[k];
		
		synchronized(synchronizingVar){
		synchronizingVar.wait();
		}
		
		currentBoatPosition[k] = synchronizingVar;
		k+=1;		
		synchronizingVar = new Box(1,1,1,1,"");
		}
		isPlacementActive = false;
		firstCaseDone = false;
		lastX = (Integer) null;
		lastY = (Integer) null;
		ship[i]=currentBoat;		
	}
	
	public boolean isItYourTurn() {
		return isItYourTurn;
	}

	public void setItYourTurn(boolean isItYourTurn) {
		this.isItYourTurn = isItYourTurn;
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board boardLeft) {
		board = boardLeft;
	}

	public Board getBoardOpponent() {
		return boardOpponent;
	}

	public static void setBoardOpponent(Board boardRight) {
		boardOpponent = boardRight;
	}

	public int getCurrentGameNumber() {
		return currentGameNumber;
	}

	public void setCurrentGameNumber(int currentGameNumber) {
		this.currentGameNumber = currentGameNumber;
	}

	public static boolean isGameSet() {
		return isGameSet;
	}

	public void setGameSet(boolean isGameSet) {
		this.isGameSet = isGameSet;
	}

	public static boolean isPlacementActive() {
		return isPlacementActive;
	}

	public void setPlacementActive(boolean isPlacementActive) {
		this.isPlacementActive = isPlacementActive;
	}

	public static Box getSynchronizingVar() {
		return synchronizingVar;
	}

	public static void setSynchronizingVar(Box synchronizingVarToStore) {
		synchronizingVar = synchronizingVarToStore;
	}

	public static boolean isFirstCaseDone() {
		return firstCaseDone;
	}

	public static void setFirstCaseDone(boolean firstCaseDoneToStore) {
		firstCaseDone = firstCaseDoneToStore;
	}

	public static int getLastX() {
		return lastX;
	}

	public static void setLastX(int lastXtoStore) {
		lastX = lastXtoStore;
	}

	public static int getLastY() {
		return lastY;
	}

	public static void setLastY(int lastYtoStore) {
		lastY = lastYtoStore;
	}

}
