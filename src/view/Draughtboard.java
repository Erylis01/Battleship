package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import client.Client;
import controller.GameController;

public class Draughtboard implements ActionListener {

	private GameController controller;
	private JFrame frame;
	private final int LARGEUR_FENETRE = 1000, HAUTEUR_FENETRE = 600;
	private JTextField fieldIP;
	private JTextField fieldPort;
	private JPanel panel;
	private JTextField fieldPseudo;
	private JTextArea console;
	private JPanel boardOpponent;
	private JPanel boardPlayer;
	private JButton btnCoButton;

	public Draughtboard(GameController controller) {
		this.controller = controller;
		buildFrame();
	}

	private void buildFrame() {

		frame = new JFrame();

		// Centering the window and choosing the size of the window
		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));
		int windowLeftPosition = screenSize.width / 2 - LARGEUR_FENETRE / 2;
		int windowRightPostion = screenSize.height / 2 - HAUTEUR_FENETRE / 2;
		frame.setLocation(windowLeftPosition, windowRightPostion);

		// Emp�che la fen�tre d'�tre redimensionn�e
		frame.setResizable(false);

		// Prevents the window from being resized
		frame.setTitle("Battleship");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/logo.png")));

		// Saving the EXIT_ON_CLOSE option when closing the window (stopping the
		// process)
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Add an absolute layout
		frame.getContentPane().setLayout(null);

		// Add a panel that will contain everything
		panel = new JPanel();
		panel.setBounds(0, 0, 994, 571);
		panel.setLayout(null);
		frame.setContentPane(panel);

		// Add a Label for the ip TextField
		JLabel lblIp = new JLabel("IP :");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIp.setBounds(48, 35, 27, 27);
		panel.add(lblIp);

		// Add a TextField to set the ip adress
		fieldIP = new JTextField("");
		fieldIP.setColumns(10);
		fieldIP.setBounds(92, 37, 146, 27);
		panel.add(fieldIP);

		// Add a Label for the port TextField
		JLabel lblPort = new JLabel("Port :");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPort.setBounds(32, 76, 40, 21);
		panel.add(lblPort);
		
		// Add a TextField to set the port number
		fieldPort = new JTextField("8051");
		fieldPort.setBounds(92, 75, 146, 27);
		panel.add(fieldPort);
		fieldPort.setColumns(10);

		// Add a Label for the pseudo TextField
		JLabel lblPseudo = new JLabel("Pseudo :");
		lblPseudo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPseudo.setBounds(12, 120, 63, 20);
		panel.add(lblPseudo);
		
		// Add a TextField to set the pseudo
		fieldPseudo = new JTextField("");
		fieldPseudo.setColumns(10);
		fieldPseudo.setBounds(92, 113, 146, 27);
		panel.add(fieldPseudo);

		

		// Add a Button for the connection
		btnCoButton = new JButton("Connexion");
		btnCoButton.setBounds(282, 66, 123, 38);
		btnCoButton.addActionListener(this);
		panel.add(btnCoButton);

		// Drawing of the left grid
		boardPlayer = new JPanel();
		boardPlayer.setBounds(48, 195, 340, 340);
		boardPlayer.setLayout(new GridLayout(10, 10));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JButton b = new JButton();
				b.setBackground(Color.white);
				b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));
				b.setPreferredSize(new java.awt.Dimension(34, 34));
				b.addActionListener(this);
				boardPlayer.add(b);
			}
		}
		panel.add(boardPlayer);

		// Drawing of the left grid
		boardOpponent = new JPanel();
		boardOpponent.setBounds(550, 195, 340, 340);
		boardOpponent.setLayout(new GridLayout(10, 10));
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JButton b = new JButton();
				b.setBackground(Color.white);
				b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				b.setCursor(new Cursor(Cursor.HAND_CURSOR));
				b.setPreferredSize(new java.awt.Dimension(34, 34));
				boardOpponent.add(b);
			}
		}
		panel.add(boardOpponent);

		this.console = new JTextArea();
		this.console.setBounds(550, 35, 371, 87);
		this.console.setEditable(false);
		console.setText("Placer votre porte-avions (5 cases)");
		panel.add(this.console);

		// Adding a Background
		JLabel lblFond = new JLabel("fond");
		lblFond.setIcon(new ImageIcon(getClass().getResource("/assets/fond_logpan.jpg")));
		lblFond.setBounds(-10, 0, 994, 571);
		panel.add(lblFond);

		// Packing and displaying the JFrame
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		
		for(Component c : boardPlayer.getComponents() ){
			if(a.getSource()==c){
				int posX =c.getX()/(boardPlayer.getWidth()/10);
				int posY =c.getY()/(boardPlayer.getHeight()/10);
				getController().shipsPlacement(console,posX,posY);
			}		
		}
		
		if(a.getSource()==btnCoButton && GameController.isGameSet()){
			Client.connectServer(frame,console,fieldPseudo,fieldIP,fieldPort);
		}
		
		for(Component c : boardOpponent.getComponents()){
		if(a.getSource()==c && Client.isOpponentFind() && Client.isItYourTurn()){
			int posX =c.getX()/(boardOpponent.getWidth()/10);
			int posY =c.getY()/(boardOpponent.getHeight()/10);
			try {
				Client.sendHit((JButton) c,frame,console,posX,posY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	}

	public GameController getController() {
		return controller;
	}

	public void setController(GameController controller) {
		this.controller = controller;
	}

	public JPanel getBoardOpponent() {
		return boardOpponent;
	}

	public void setBoardOpponent(JPanel boardOpponent) {
		this.boardOpponent = boardOpponent;
	}

	public JPanel getBoardPlayer() {
		return boardPlayer;
	}

	public void setBoardPlayer(JPanel boardPlayer) {
		this.boardPlayer = boardPlayer;
	}

	public JTextArea getConsole() {
		return console;
	}

	public void setConsole(JTextArea console) {
		this.console = console;
	}

	public JTextField getFieldPseudo() {
		return fieldPseudo;
	}

	public void setFieldPseudo(JTextField fieldPseudo) {
		this.fieldPseudo = fieldPseudo;
	}


	
	
}
