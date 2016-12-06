package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ServiceThread extends Thread {
	int id;
	Socket socketService;
	boolean interrupted;
	private boolean mustStop;
	private Object lock;
	private final static String TABS = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
	
	private Integer currentGameNumber = 0;
	private ArrayList<String> waitingRoom = new ArrayList<>();
	private HashMap<String,Integer> currentGame = new HashMap<>();

	public ServiceThread(int id, Socket _socketService,boolean mustStop,Object lock) {
		this.id = id;
		this.socketService = _socketService;
		this.mustStop=mustStop;
		this.lock=lock;
	}

	
	
	private void log(String mesg) {
		synchronized (lock) {
			System.out.println(mesg);
		}
	}
	
	private void taskLog(String mesg) {
		log(TABS.substring(0, id + 1) + "(client " + id + ") - " + mesg);
	}

	@Override
	public void interrupt() {
		super.interrupt();
		interrupted = true;
		taskLog("----- the task is interrupted");
	}

	private void traiteClient(Socket socketService) throws IOException {
		taskLog("le client " + socketService.getRemoteSocketAddress() + " s'est connecté");
		PrintStream output;		
		output = new PrintStream(socketService.getOutputStream(), true);// autoflush
		BufferedReader networkIn = new BufferedReader(new InputStreamReader(socketService.getInputStream()));
		
		
		
		while (!mustStop && !interrupted) {
			
			String requeteclient = networkIn.readLine(); // bloquant
			String[] requestToDo = requeteclient.split(";");
			
			if(requestToDo[0].equals("Ajout")){
				output.println("okAjout");
			}
			
/*			if (requeteclient == null) {
				taskLog("Le client s'est déconnecté. Fin de la session");
				break;
			}
			taskLog("Le client demande: " + (requeteclient == null ? "null" : requeteclient));
			if (requeteclient != null) {
				if (requeteclient.toLowerCase().contains("stop")) {
					// requéte attendue: stop
					output.println("stopping session");
					break;
				} else if (requeteclient.toLowerCase().contains("end")) {
					// requéte attendue: end
					output.println("end server");
					mustStop = true;
					break;
				} else {
					String[] req = requeteclient.split(" ");
					if (req[0].equalsIgnoreCase("date")) {
						// requéte attendue: date
						Date now = new Date();
						output.println("il est " + now.toString());
					} else
							output.println("commande inconnue ");
					}
				}*/
			}
		socketService.close();
		taskLog("arrét de la session");
	}

	@Override
	public void run() {
		if (!mustStop && !interrupted) {
			try {
				traiteClient(socketService);
				// bloquant tant que la session n'est pas terminée
			} catch (IOException e) {
				// e.printStackTrace();
				if (!((ServiceThread) this).interrupted) {
					taskLog("Le client" + id + " s'est déconnecté !");
					this.interrupt();
				}
			}
		}
	}
	
	public void addPlayer(String s){
		if(waitingRoom.size() == 1){
			currentGame.put(waitingRoom.get(0), currentGameNumber);
			currentGame.put(s, currentGameNumber);
			waitingRoom.clear();
			currentGameNumber += 1;
		} else {
			waitingRoom.add(s);
		}
	}
}