/**
 * Copyright (c) 2015 Laboratoire de Genie Informatique et Ingenierie de Production - Ecole des Mines d'Ales
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Francois Pfister (ISOE-LGI2P) - initial API and implementation
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

//v6

/**
 *
 * @author cs2015
 *
 */
public class ThreadedClient implements ISocketControler {

	private static final boolean LOG = true;
	private static final boolean INFO = false;

	private static final boolean LOCAL = false; // canvas refreshed in local or
												// from thr server respons
	private static final int DEFAULT_PORT = 8051;
	private static final String DEFAULT_HOST = "localhost";

	private ISocketView socketView;

	private String serverName = "-1";
	private int serverPort = -1;
	private String response;

	private PrintWriter out;
	private BufferedReader in;
	//private BufferedReader userIn_;

	private Socket clientSocket;

	private List<String> model_ = new ArrayList<String>();
	private String host;
	

	public void setView(ISocketView socketView) {
		this.socketView = socketView;
		if (host!=null)
		   viewSetLocal(host, serverPort);
	}

	@Override
	public boolean checkConnection() {
		boolean result = isConnected();
		if (!result)
			viewClearConnexion();// error("plus de connexion");
		return result;
	}

	private void openSocket() throws UnknownHostException, IOException {
		if (isConnected()) {
			info("déjà connecté");
			return;
		}
		info("ouverture de la socket " + serverName + "-" + serverPort);
		if (serverPort == -1)
			throw new IOException("port error");

		if (serverName == null || serverName.equals("-1"))
			throw new IOException("host error");

		try {
			clientSocket = new Socket(serverName, serverPort);

			host = clientSocket.getLocalAddress().getHostAddress();
			// int port = ((InetSocketAddress)
			// clientSocket.getLocalSocketAddress()).getPort();
			viewSetLocal(host, serverPort);// port);

		} catch (ConnectException e) {
			error("Connection refusée ");// + e.toString());
			error("Pas de serveur: " + serverName + "-" + serverPort);
			// viewSetDisConnected();
			viewClearConnexion();
			viewSetNoServer();
			in = null;
			out = null;
			throw new IOException("connection error");
		}
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream(), true); // autoflush
		info("Connected to the server");
	}

	private void closeSocket(boolean verbose) {
		if (!isConnected()) {
			if (verbose)
				info("session allready closed");
			return;
		}
		info("closing socket");
		if (clientSocket != null)
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (in != null)
			try {
				in.close();
				in = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		if (out != null) {
			out.close();
			out = null;
		}
	}

	/**
	 * A MODIFIER POUR SERIALISER NOS REQUETES
	 */
	private void handleServerResponse() {
		info(response == null ? "session ended" : response);
		if (response == null) {
			closeSocket(true);
			return;
		}
		clog("<< " + response);
		if (response.contains("STOP-SERVER OK"))
			info("server will shut down");
		if (response.contains("commande inconnue"))
			info("cmd inconnue: " + response);
		if (response != null && (response.endsWith(" OK") || response.endsWith(" TODO")))
			viewSetResult(response.substring(response.indexOf("voici la réponse:") + 17).trim());
		else if (response != null && !LOCAL) {
			if (response.startsWith("circle") || 
					response.startsWith("line") || 
					response.startsWith("rectangle") ||
					response.startsWith("servo")
					)
				addCommand(response);
			viewSetResult(response);
		}
	}

	private void readServer() {
		info("readServer waiting");
		response = null;
		try {
			response = in.readLine(); // bloquant - attendre la réponse du
										// serveur
		} catch (IOException e) {
			info("socket fermée");
			closeSocket(false);
			return;
		} catch (Exception e) {
			error("(2) while read response (" + e.toString() + ")");
			closeSocket(false);
			return;
		}
		if (response == null) {
			info("fin de la connexion");
			closeSocket(false);
			return;
		}
		//System.out.println(response);
	}

	private static void delay(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getResponse() {
		return response;
	}

	@Override
	public void disconnect(boolean verbose) {
		if (isConnected())
			closeSocket(verbose);
		else if (verbose)
			clog("allready disconnected !");
	}

	@Override
	public boolean connect(String host, int port) {
		if (isConnected()) {
			clog("allready connected !");
			return true;
		}
		new SessionThread(host, port).start();
		model_.clear();
		delay(100);
		return isConnected();
	}

	private boolean isConnected() {
		boolean result = clientSocket != null && !clientSocket.isClosed() && clientSocket.isConnected()
				&& (socketView == null || !socketView.isDisposed());
		if (!result) {
			if (clientSocket == null)
				clog("clientSocket == null");
			if (clientSocket != null && clientSocket.isClosed())
				clog("clientSocket.isClosed()");
			if (clientSocket != null && !clientSocket.isConnected())
				clog("!clientSocket.isConnected()");
			if (socketView != null && socketView.isDisposed())
				clog("socketView.isDisposed()");
		}
		return result;
	}

	private boolean openConnection() {
		try {
			openSocket();
			// out.println("hello");
			return true;
		} catch (UnknownHostException e) {
			error("serveur inconnu: " + e.getMessage());
		} catch (IOException e) {
			error("erreur de connexion");
		} catch (Exception e) {
			error("(connect 3) " + e.toString());
		}
		return false;
	}

	@Override
	public void send(String host, int port, String cmd) {
		if (cmd != null && !cmd.isEmpty()) {
			if (isConnected()) {
				if (!serverName.equals(host) || serverPort != port)
					disconnect(true);
			}
			if (!isConnected())
				connect(host, port);
			if (isConnected())
				send(cmd);
		} else
			clog("command is empty !");
	}

	@Override
	public boolean send(String cmd) {
		if (cmd != null && !cmd.isEmpty()) {
			info("send " + serverName + ":" + serverPort + "[" + cmd + "]");
			if (!isConnected())
				connect(serverName, serverPort);
			if (isConnected()) {
				out.println(cmd);
				if (LOCAL)
					addCommand(cmd); // rather will be done by the response of
										// the server
				return true;
			} else
				error("no session");
		} else
			clog("command is empty !");
		return false;
	}
/*
	private String readConsole_() { // v4
		try {
			String result = userIn_.readLine();
			if (result.equals("dump")) {
				System.out.println("model{");
				for (String line : model) {
					System.out.println("  " + line);
				}
				System.out.println("}");
			} else
				out.println(result);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return "error !!!";
		}
	}*/

	class SessionThread extends Thread {
		public SessionThread(String host, int port) {
			if (isConnected())
				disconnect(true);
			serverName = host;
			serverPort = port;
		}

		@Override
		public void run() {
			if (openConnection()) {
				while (checkConnection()) {
					/*
					if (userIn_ != null) // dans ce cas, necessité de gérer une
										// tâche
										// parallèle pour ne pas bloquer les
										// push du serveur avec l'attente sur le
										// clavier
						readConsole_();
					*/
					
					readServer();
					handleServerResponse();
				}
				disconnect(false);
			}
		}
	}

	@Override
	public String[] getUsage() {
		int i = 0;
		List<String> usags = new ArrayList<String>();
		usags.add("stop-server => arrêt du serveur");
		usags.add("bye => fin de la session");
		usags.add("connect host port => début de session");
		usags.add("quit => fin de l'application");
		String[] result = new String[usags.size()];
		for (String usag : usags)
			result[i++] = usag;
		return result;
	}

	private void viewSetLocal(String localhost, int localport) {
		if (socketView != null)
			socketView.setConnection(localhost, localport);
		else
			clog("connexion depuis " + localhost + "." + localport + " établie");
	}

	private void viewClearConnexion() {
		if (socketView != null)
			socketView.clearConnection();
		else
			clog("plus de connexion ");
	}

	private void viewSetNoServer() {
		if (socketView != null)
			socketView.setNoServer();
		else
			clog("plus de serveur ");
	}

	private void viewSetResult(String result) {
		if (socketView != null)
			socketView.setResult(result);
		else
			clog("résultat " + serverName + "." + serverPort + " [" + result + "]");
	}

	private void clog(String mesg) {
		if (LOG) {
			if (socketView != null && !socketView.isDisposed())
				socketView.log(mesg);
			else
				System.out.println(mesg);
		}
	}

	private void error(String mesg) {
		viewError(mesg);
	}

	private void info(String mesg) {
		viewInfo(mesg);
	}

	private void viewError(String mesg) {
		if (socketView != null)
			socketView.error(mesg);
		else
			System.err.println(mesg);
	}

	private void viewInfo(String mesg) {
		if (INFO) {
			if (socketView != null)
				socketView.info(mesg);
			else
				clog("i__" + mesg);
		}
	}

	@Override
	public List<String> getModel() {
		return model_;
	}
	
	/**
	 * A MODIFIER POUR SERIALISER NOS REQUETES
	 */
	private void addCommand(String cmd) {
		model_.add(cmd);
	}
/*
	@Override
	public void setConsole(BufferedReader userIn) {
		this.userIn_ = userIn;
		clog("should work in a thread since this is blocking !");
	}
*/


}
