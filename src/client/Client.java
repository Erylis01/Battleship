package client;

import controller.GameController;
import model.BoardPlayer;

public class Client {

	public static void main(String[] args) {
		BoardPlayer boardPlayer = new BoardPlayer();
		GameController game = new GameController(boardPlayer);
	}

}
