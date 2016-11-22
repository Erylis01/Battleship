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

package server;

import java.util.List;

//v6

/**
 *
 * @author cs2015 Ecole des Mines d'Alès
 *
 */
public interface IServerGui {
	void setError(String error);
	void log(String mesg);
	void refresh();
	boolean isDisposed();
	void setHost(String host, int port);
	void setClients(List<String> clients);
	void setMode(boolean broadCast, boolean keepConnection);
	void setStarted(boolean started);
	void setControler(IServerControler c);
	void open();
}
