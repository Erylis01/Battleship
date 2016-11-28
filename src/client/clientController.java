package client;

import view.Draughtboard;
import model.Board;
import model.Boat;
import model.Box;
import model.Player;

public class clientController {

	private boolean isItYourTurn;
	private static boolean isGameSet = false;
	
	private static Box[] twoBoat = new Box[2];
	private static Box[] thirdFstBoat = new Box[3];
	private static Box[] thirdSndBoat = new Box[3];
	private static Box[] fourthBoat = new Box[4];
	private static Box[] fifthBoat = new Box[5];
	private static Boat two = new Boat(twoBoat,"safe");
	private static Boat threeOne = new Boat(twoBoat,"safe");
	private static Boat threeTwo = new Boat(twoBoat,"safe");
	private static Boat four = new Boat(twoBoat,"safe");
	private static Boat five = new Boat(twoBoat,"safe");
	private Player player;
	private static Boat[] ship = {two,threeOne,threeTwo,four,five};

	
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

	public static Box[] getTwoBoat() {
		return twoBoat;
	}

	public static void setTwoBoat(Box[] twoBoat) {
		clientController.twoBoat = twoBoat;
	}

	public static Box[] getThirdFstBoat() {
		return thirdFstBoat;
	}

	public static void setThirdFstBoat(Box[] thirdFstBoat) {
		clientController.thirdFstBoat = thirdFstBoat;
	}

	public static Box[] getThirdSndBoat() {
		return thirdSndBoat;
	}

	public static void setThirdSndBoat(Box[] thirdSndBoat) {
		clientController.thirdSndBoat = thirdSndBoat;
	}

	public static Box[] getFourthBoat() {
		return fourthBoat;
	}

	public static void setFourthBoat(Box[] fourthBoat) {
		clientController.fourthBoat = fourthBoat;
	}

	public static Box[] getFifthBoat() {
		return fifthBoat;
	}

	public static void setFifthBoat(Box[] fifthBoat) {
		clientController.fifthBoat = fifthBoat;
	}

    
}
