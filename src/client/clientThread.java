package client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import controller.ThreadedServer;
import server.ServiceThread;
import view.Draughtboard;

public class clientThread extends Thread{
	int id;
	boolean interrupted = false;
	private boolean mustStop;
	private Object lock;
	private final static String TABS = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
	private boolean isDead = false;
	private PrintStream output;
	private JFrame fenetre;
	private JTextArea console;

	public clientThread(int id, JFrame fenetre, JTextArea console,Socket socketService,boolean mustStop,Object lock) {
		this.id = id;
		this.mustStop=mustStop;
		this.lock=lock;
		this.fenetre = fenetre;
		this.console = console;
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

	private void traiteClient() throws IOException {

		taskLog("le client " + Client.getTheSocket().getRemoteSocketAddress() + " s'est connecté");
//		out = new PrintStream(Client.getTheSocket().getOutputStream(), true);// autoflush
//		BufferedReader networkIn = new BufferedReader(new InputStreamReader(socketService.getInputStream()));
		PrintWriter out = Client.getOut();
		BufferedReader networkIn = Client.getNetworkIn();
		
		
		while (!isDead) {
			
			String requeteclient = networkIn.readLine(); // bloquant
			
			
			if(requeteclient != null) {
				
					String[] requeteclientsplit = requeteclient.split(";");
					
					if(requeteclientsplit[0].equals("Hit")){
					out.println("Touched;lol");
					out.flush();
					taskLog("LE THREAD A BIEN RECUPERE LE HIT");
					Client.setItYourTurn(true);
					console.setText("Impact en "+requeteclientsplit[1]+requeteclientsplit[2]+ "! A vous de jouer");
					}
					
					if(requeteclientsplit[0].equals("Touched")){
						//b.setBackground(Color.RED);
						taskLog("LE THREAD A BIEN RECUPERE LA REPONSE");
						console.setText("Touché ! Au tour adverse");
						fenetre.requestFocus();
						fenetre.revalidate();
						fenetre.repaint();
					}
					
				
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
		//socketService.close();
		taskLog("arrét de la session");
	}

	@Override
	public void run() {
		if (!mustStop && !interrupted) {
			try {
				traiteClient();
				// bloquant tant que la session n'est pas terminée
			} catch (IOException e) {
				 e.printStackTrace();
				//if (!((clientThread) this).interrupted) {
					//taskLog("Le client" + id + " s'est déconnecté !");
					//this.interrupt();
				
				//}
			}
		}
	}
	

	public PrintStream getOutput() {
		return output;
	}



	public void setOutput(PrintStream output) {
		this.output = output;
	}
	
}
