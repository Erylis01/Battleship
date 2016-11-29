package view;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;

import sun.rmi.runtime.Log;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import client.clientController;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.Board;
import javax.swing.JTextPane;


public class Draughtboard extends JFrame {

	private static final long serialVersionUID = 6895220124229682988L;
	private final int LARGEUR_FENETRE = 1000, HAUTEUR_FENETRE = 600;
	private JPanel panel;
	private JTextField textFieldIp;
	private JTextField textFieldPort;
	private JTextField textField;
	private JTextField textField_pseudo;
	private JTextArea console;
	private Board boardLeft;

	public Draughtboard() {

		// Centering the window and choosing the size of the window
		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		this.setPreferredSize(new Dimension(this.LARGEUR_FENETRE, this.HAUTEUR_FENETRE));
		int windowLeftPosition = screenSize.width / 2 - this.LARGEUR_FENETRE / 2;
		int windowRightPostion = screenSize.height / 2 - this.HAUTEUR_FENETRE / 2;
		this.setLocation(windowLeftPosition, windowRightPostion);

		// Empêche la fenêtre d'être redimensionnée
		setResizable(false);

		// Prevents the window from being resized
		this.setTitle("Battleship");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Log.class.getResource("/assets/logo.png")));

		// Saving the EXIT_ON_CLOSE option when closing the window (stopping the
		// process)
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Add an absolute layout
		this.getContentPane().setLayout(null);

		// Add a panel that will contain everything
		panel = new JPanel();
		panel.setBounds(0, 0, 994, 571);
		panel.setLayout(null);
		this.setContentPane(panel);

		// Add a TextField to set the ip address
		textFieldIp = new JTextField("127.0.0.1");
		textFieldIp.setBounds(92, 75, 146, 27);
		panel.add(textFieldIp);
		textFieldIp.setColumns(10);

		// Add a Label for the ip TextField
		JLabel lblIp = new JLabel("IP :");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIp.setBounds(48, 35, 27, 27);
		panel.add(lblIp);

		// Add a TextField to set the port
		textFieldPort = new JTextField("8080");
		textFieldPort.setColumns(10);
		textFieldPort.setBounds(92, 37, 146, 27);
		panel.add(textFieldPort);

		// Add a Label for the port TextField
		JLabel lblPort = new JLabel("Port :");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPort.setBounds(32, 76, 40, 21);
		panel.add(lblPort);
		
		textField_pseudo = new JTextField("");
		textField_pseudo.setColumns(10);
		textField_pseudo.setBounds(92, 113, 146, 27);
		panel.add(textField_pseudo);
		
		JLabel lblPseudo = new JLabel("Pseudo :");
		lblPseudo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPseudo.setBounds(12, 120, 63, 20);
		panel.add(lblPseudo);

		// Add a Button for the connection
		JButton btnNewButton = new JButton("Connexion");
		btnNewButton.setBounds(282, 66, 123, 38);
		panel.add(btnNewButton);

		// Drawing of the left grid
		this.boardLeft = new Board(10, 10, 48, 195, 340, 340);
		this.boardLeft.addBoxes();
		panel.add(this.boardLeft);
		clientController.setBoard(this.boardLeft);

		// Drawing of the right grid
		Board boardRight = new Board(10, 10, 550, 195, 340, 340);
		boardRight.addBoxes();
		panel.add(boardRight);
		clientController.setBoardOpponent(boardRight);
		
		this.console = new JTextArea();
		this.console.setBounds(550, 35, 371, 87);
		this.console.setEditable(false);
		panel.add(this.console);
		
		// Adding a Background
		JLabel lblFond = new JLabel("fond");
		lblFond.setIcon(new ImageIcon(Log.class.getResource("/assets/fond_logpan.jpg")));
		lblFond.setBounds(-10, 0, 994, 571);
		panel.add(lblFond);

		// Packing and displaying the JFrame
		this.pack();
		this.setVisible(true);

	}

	public JTextField getTextFieldIp() {
		return textFieldIp;
	}

	public void setTextFieldIp(JTextField textFieldIp) {
		this.textFieldIp = textFieldIp;
	}

	public JTextField getTextFieldPort() {
		return textFieldPort;
	}

	public void setTextFieldPort(JTextField textFieldPort) {
		this.textFieldPort = textFieldPort;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public JTextField getTextField_pseudo() {
		return textField_pseudo;
	}

	public void setTextField_pseudo(JTextField textField_pseudo) {
		this.textField_pseudo = textField_pseudo;
	}

	public JTextArea getConsole() {
		return console;
	}

	public void setConsole(JTextArea console) {
		this.console = console;
	}

	public Board getBoardleft() {
		return boardLeft;
	}

	public void setBoardleft(Board boardleft) {
		this.boardLeft = boardleft;
	}
}
