package server;

import controller.ThreadedServer;

public class Server {

	public static void main(String[] args) {
		ThreadedServer s = new ThreadedServer();
		try {
			s.serveur();
		} catch (Exception e) {
			s.log(e.toString());
		}
	}

}
