package controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import server.HostAddress;
import server.ServiceThread;

public class ThreadedServer {
	
	private static final int PORT = 8051;
	List<ServiceThread> tasks = new ArrayList<>();
	private boolean mustStop;
	private Object lock = new Object();
	private int taskCount;
	
	private static Integer currentGameNumber = 0;
	private static ArrayList<String> waitingRoom = new ArrayList<>();
	private static HashMap<String,Integer> currentGame = new HashMap<>();
	
	public void log(String mesg) {
		synchronized (lock) {
			System.out.println(mesg);
		}
	}

	public void serveur() throws Exception {
		log("Démarrage du serveur multisession veuillez patienter...");
		log("Je comprends le vocabulaire suivant: [stop] [end] [date]");
		ServerSocket socketEcoute = new ServerSocket(PORT);
		String localhost = HostAddress.getHostAddress();
		log("Démarrage du serveur multisession sur: " + localhost + ":" + socketEcoute.getLocalPort());
		while (!mustStop) {
			log("En attente d'une connexion (" + taskCount + ")");
			Socket socketService = socketEcoute.accept(); // bloquant ici
			new ServiceThread(taskCount++, socketService,mustStop,lock).start();
			// ici on forke vers une t�che parall�le, le serveur peut se
			// remettre � l'�coute.
		}
		socketEcoute.close();
		log("Arrêt du serveur");
	}

	public static Integer getCurrentGameNumber() {
		return currentGameNumber;
	}

	public static void setCurrentGameNumber(Integer currentGameNumber) {
		currentGameNumber = currentGameNumber;
	}

	public static ArrayList<String> getWaitingRoom() {
		return waitingRoom;
	}

	public void setWaitingRoom(ArrayList<String> waitingRoom) {
		this.waitingRoom = waitingRoom;
	}

	public static HashMap<String, Integer> getCurrentGame() {
		return currentGame;
	}

	public void setCurrentGame(HashMap<String, Integer> currentGame) {
		this.currentGame = currentGame;
	}

	
}
