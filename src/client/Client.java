package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.GameController;
import model.BoardPlayer;
import view.Draughtboard;

public class Client {

	private static int port = 8051;
	private static boolean partnerAwait = false;
	private static String hostname = null; // adapter au
																// serveur

	public static void main(String[] args) {
		BoardPlayer boardPlayer = new BoardPlayer();
		GameController game = new GameController(boardPlayer);
	}

	public static void connectServer(JTextArea console, JTextField fieldPseudo,JTextField fieldIP, JTextField fieldPort) {
		PrintWriter out = null;
		BufferedReader networkIn = null;
		hostname = fieldIP.getText();
		port = Integer.parseInt(fieldPort.getText());

		System.out.println("(démarrage du client) veuillez patienter...");
		System.out.println("(le serveur est) " + hostname + ":" + port);
		try {

			Socket theSocket = new Socket();
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
				
				if(networkIn.readLine().equals("okAjout")){
				console.setText("\n"+networkIn.readLine());
				partnerAwait = true;
				}

			}
			console.getParent().revalidate();
			console.getParent().repaint();
			
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

	
	
}
