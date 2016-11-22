package server;

import model.Player;
import model.Board;

import java.util.HashMap;

/**
 * Server Controller - Only one running - Hast be started before the game
 * @param args
 */
public class serverController {
	
	//Necessary to hold on many game
	private HashMap<Player,Board> connectedClient = new HashMap<Player,Board>();
	private HashMap<Integer,Player[]> runningGame = new HashMap<Integer,Player[]>();
	
	

}
