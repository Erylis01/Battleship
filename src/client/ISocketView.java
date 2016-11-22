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

//v6


/**
*
* @author cs2015
*
*/
public interface ISocketView {
	void error(String mesg);
	void info(String mesg);
	void clog(String mesg);
	void setResult(String mesg);
	boolean isDisposed();
	void log(String mesg);
	void clearConnection();
	void setNoServer();
	void setConnection(String localhost, int localport);
	void refresh();
	void setControler(ISocketControler c);
	void open();

}
