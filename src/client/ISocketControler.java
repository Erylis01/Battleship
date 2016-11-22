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


import java.util.List;

//v6


/**
 *
 * @author cs2015
 *
 */
public interface ISocketControler {
	String getResponse();
	boolean send(String cmd);
	void send(String host, int port, String cmd);
	String[] getUsage();
	void disconnect(boolean verbose);
	boolean connect(String host, int port);
	List<String> getModel();
	boolean checkConnection();
	//void setConsole(BufferedReader userIn);
	void setView(ISocketView view);
}
