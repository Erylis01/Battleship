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
		 * Proc�dure globale du jeu :
		 * 1 - Lorsque le joueur se connecte, une fen�tre lui est cr�e
		 * 2 - Une m�thode de connection est appell� via le bouton sp�cifique
		 * 3 - Il est demand� imm�diatement au joueur de placer ses cinq bateaux (bateau par bateau, et case par case)
		 * 4 - Une fois que c'est fait, le joueur entre en attente  d'un adversaire
		 * 5 - Lorsque deux adversaires sont connect�s, le joueur va alterner entre les phases de clique sur une case et l'attente de la r�ception du coup adverse
		 * 6 - Apr�s chaque coup, on v�rifie si un des joueurs est mort, si c'est le cas, la partie se termine alors avec l'annonce du vainqueur
		 */
				Draughtboard dg = new Draughtboard();
								
	}

	public static void connect() {
		// TODO Auto-generated method stub
		
		/**
		 * Appeller ici la m�thode de connexion du threadedClient
		 * Envoi de son board au serveur pour stockage
		 * Le boardOpponent sera r�cup�rer lors du pairing par le serveur
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
