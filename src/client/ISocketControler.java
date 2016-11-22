package client;


import java.util.List;

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
