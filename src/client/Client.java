package client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.GameController;
import model.BoardPlayer;
import view.Draughtboard;

public class Client {

	private static int port = 8051;
	private static boolean partnerAwait = false;
	private static boolean opponentFind = false;
	private static String hostname = null; // adapter au
	private static Socket theSocket = new Socket();															// serveur
	private static boolean isItYourTurn;
	private static boolean isDead = false;
	
	public static void main(String[] args) {
		BoardPlayer boardPlayer = new BoardPlayer();
		GameController game = new GameController(boardPlayer);
	}

	public static void connectServer(JFrame fenetre, JTextArea console, JTextField fieldPseudo,JTextField fieldIP, JTextField fieldPort) {
		PrintWriter out = null;
		BufferedReader networkIn = null;
		hostname = fieldIP.getText();
		port = Integer.parseInt(fieldPort.getText());
		
		console.setText("Connexion et recherche d'un adversaire ...");
		System.out.println("(démarrage du client) veuillez patienter...");
		System.out.println("(le serveur est) " + hostname + ":" + port);
		try {

			
			theSocket.connect(new InetSocketAddress(hostname, port), 200);
			int localPort = theSocket.getLocalPort();
			System.out.println("Client démarré sur le port" + ":" + localPort);

			networkIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));

			BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

			out = new PrintWriter(theSocket.getOutputStream());
			
			while (!partnerAwait) {
																									// [fin]
				String theLine = "Ajout;"+fieldPseudo.getText();
				if (theLine.equals("."))
					break;
				out.println(theLine);
				out.flush();
				Thread.sleep(10);
				
				String ajoutAns = networkIn.readLine();
				if(ajoutAns != null) {
					String[] ajoutAnsTab = ajoutAns.split(";");
				if(ajoutAnsTab[0].equals("okAjout") && ajoutAnsTab[1].equals("true")){
				partnerAwait = true;
				isItYourTurn=true;
				} else {
				partnerAwait = true;
				isItYourTurn=false;	
				}
				}
			}
			fenetre.requestFocus();
			fenetre.revalidate();
			fenetre.repaint();
			
			while(!opponentFind){
				
				String theLine = "Adversaire;"+fieldPseudo.getText();
				if (theLine.equals("."))
					break;
				out.println(theLine);
				out.flush();
				Thread.sleep(10);
				
				String answer = networkIn.readLine();
				if(!answer.equals("no")){
				String[] response = answer.split(";");
				console.setText("Votre adversaire sera "+response[1]);
				if(isItYourTurn){
					console.setText(console.getText()+"\n C'est votre tour");	
				} else {
					console.setText(console.getText()+"\n C'est le tour de votre adversaire");	
				}
				opponentFind = true;
				}
				
			}
			fenetre.requestFocus();
			fenetre.revalidate();
			fenetre.repaint();
			
			/*while(!isDead){
				Thread.sleep(400);
				fenetre.requestFocus();
				fenetre.revalidate();
				fenetre.repaint();
				if(isItYourTurn){
					break;
				} else {
					String answer = networkIn.readLine();
					if(answer != null ){
					String[] answerSplit = answer.split(";");
					if(answerSplit[0].equals("Hit")){
					console.setText("Votre adversaire a tapé en  "+answerSplit[1]+" "+answerSplit[2]);
					out.println("Touched");
					isItYourTurn = true;
					}
					}
				}
			}*/
			
		} catch (IOException e) {
			System.err.println(e);
			System.out.println("plus de connexion");
		} catch (InterruptedException e) {
			System.err.println(e);
			System.out.println("plus de connexion");
		} finally {
			try {
				if (networkIn != null)
					networkIn.close();
				if (out != null)
					out.close();
			} catch (IOException ex) {
			}
		}
	}

	public static boolean portIsOpen(String ip, int port, int timeout) {
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(ip, port), timeout);
			socket.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	private static final String prefix = "192.168.1";

	public static void scanOpenedPorts(String[] args) {
		for (int j = 1; j < 255; j++) {
			String ip = prefix + "." + j;
			System.out.println("\n" + ip);
			for (int i = 1; i < 100; i++) {
				if (portIsOpen(ip, i, 200))
					System.out.println("port " + i + "  is open");
				else
					System.out.print(".");
			}
		}
	}

	public static int getPort() {
		return port;
	}

	public static String getHostname() {
		return hostname;
	}

	public static boolean isPartnerAwait() {
		return partnerAwait;
	}

	public static void setPartnerAwait(boolean partnerAwait) {
		Client.partnerAwait = partnerAwait;
	}

	public static boolean isOpponentFind() {
		return opponentFind;
	}

	public static void setOpponentFind(boolean opponentFind) {
		Client.opponentFind = opponentFind;
	}

	public static void sendHit(JButton b,JFrame frame, JTextArea console, int posX, int posY) throws InterruptedException {	
		PrintWriter out = null;
		BufferedReader networkIn = null;
		
		try {
			networkIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
			BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(theSocket.getOutputStream());
			
			out.println("Hit;"+posX+";"+posY);
			out.flush();
			Thread.sleep(10);
			
			String answer = networkIn.readLine();
			if(!answer.equals("Touched")){
			b.setBackground(Color.RED);
			console.setText("Touché !");
			} else {
			b.setBackground(Color.BLUE);
			console.setText("Raté !");	
			}
			
		isItYourTurn = false;
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}

	public static boolean isItYourTurn() {
		return isItYourTurn;
	}

	public static void setItYourTurn(boolean isItYourTurn) {
		Client.isItYourTurn = isItYourTurn;
	}

	public static boolean isDead() {
		return isDead;
	}

	public static void setDead(boolean isDead) {
		Client.isDead = isDead;
	}
	
	
	
}
