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
