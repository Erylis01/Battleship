package client;

import view.Draughtboard;
import model.Board;

public class clientController {
	
	private boolean isItYourTurn;
	private Board board;
	private Board boardOpponent;
	private int currentGameNumber;

	/**
	 * Controller client method - One for each player
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		/**
		 * Procédure globale du jeu :
		 * 1 - Lorsque le joueur se connecte, une fenêtre lui est crée
		 * 2 - Une méthode de connection est appellé via le bouton spécifique
		 * 3 - Il est demandé immédiatement au joueur de placer ses cinq bateaux (bateau par bateau, et case par case)
		 * 4 - Une fois que c'est fait, le joueur entre en attente  d'un adversaire
		 * 5 - Lorsque deux adversaires sont connectés, le joueur va alterner entre les phases de clique sur une case et l'attente de la réception du coup adverse
		 * 6 - Après chaque coup, on vérifie si un des joueurs est mort, si c'est le cas, la partie se termine alors avec l'annonce du vainqueur
		 */
				Draughtboard dg = new Draughtboard();
								
	}

	public static void connect() {
		// TODO Auto-generated method stub
		
		/**
		 * Appeller ici la méthode de connexion du threadedClient
		 * Envoi de son board au serveur pour stockage
		 * Le boardOpponent sera récupérer lors du pairing par le serveur
		 */
		
	}

	public boolean isItYourTurn() {
		return isItYourTurn;
	}

	public void setItYourTurn(boolean isItYourTurn) {
		this.isItYourTurn = isItYourTurn;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Board getBoardOpponent() {
		return boardOpponent;
	}

	public void setBoardOpponent(Board boardOpponent) {
		this.boardOpponent = boardOpponent;
	}

	public int getCurrentGameNumber() {
		return currentGameNumber;
	}

	public void setCurrentGameNumber(int currentGameNumber) {
		this.currentGameNumber = currentGameNumber;
	}
	
	
}
