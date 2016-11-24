package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import sun.rmi.runtime.Log;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;


import model.Board;

import javax.swing.JTextArea;

public class Draughtboard extends JFrame {

	private static final long serialVersionUID = 6895220124229682988L;
	private final int LARGEUR_FENETRE = 1000, HAUTEUR_FENETRE = 600;
	private JPanel panel;
	private JTextField textFieldIp;
	private JTextField textFieldPort;
	private JTextField textField;
	
	public Draughtboard() {
		

		// Centrage de la fenêtre et choix de la taille de la fenêtre
		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		this.setPreferredSize(new Dimension(this.LARGEUR_FENETRE, this.HAUTEUR_FENETRE));
		int windowLeftPosition = screenSize.width / 2 - this.LARGEUR_FENETRE / 2;
		int windowRightPostion = screenSize.height / 2 - this.HAUTEUR_FENETRE / 2;
		this.setLocation(windowLeftPosition, windowRightPostion);

		// Empêche la fenêtre d'être redimensionnée
		setResizable(false);

		// Choix du titre et de l'icone de la JFrame
		this.setTitle("Battleship");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Log.class.getResource("/assets/logo.png")));

		// Enregistrement de l'option EXIT_ON_CLOSE lors de la fermeture de la
		// fenêtre (arrêt du procéssus)
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Ajout d'un panel qui contiendra tout
		panel = new JPanel();
		panel.setBounds(0, 0, 994, 571);
		getContentPane().add(panel);
		panel.setLayout(null);

		textFieldIp = new JTextField("127.0.0.1");
		textFieldIp.setBounds(92, 96, 146, 27);
		panel.add(textFieldIp);
		textFieldIp.setColumns(10);

		JLabel lblIp = new JLabel("IP :");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIp.setBounds(48, 35, 27, 27);
		panel.add(lblIp);

		textFieldPort = new JTextField("8080");
		textFieldPort.setColumns(10);
		textFieldPort.setBounds(92, 37, 146, 27);
		panel.add(textFieldPort);

		JLabel lblPort = new JLabel("Port :");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPort.setBounds(35, 97, 40, 21);
		panel.add(lblPort);

		JButton btnNewButton = new JButton("Connexion");
		btnNewButton.setBounds(282, 66, 123, 38);
		panel.add(btnNewButton);

		JPanel grilleOpponent = new JPanel();
		grilleOpponent.setBounds(48, 195, 340, 335);
		//grilleOpponent.setOpaque(false);
		Board board = new Board(grilleOpponent);
		panel.add(grilleOpponent);
		grilleOpponent.setLayout(null);

		JPanel grillePlayer = new JPanel();
		grillePlayer.setBounds(612, 195, 340, 335);
		grillePlayer.setOpaque(false);
		grillePlayer.setLayout(new GridLayout(10, 10, 0, 0));
		for (int i = 0; i < (10 * 10); i++) {
			final JLabel label = new JLabel("");
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			grillePlayer.add(label);
		}
		panel.add(grillePlayer);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(541, 35, 357, 80);
		panel.add(textArea);

		// Ajout d'un fond
		JLabel lblFond = new JLabel("fond");
		lblFond.setIcon(new ImageIcon(Log.class.getResource("/assets/fond_logpan.jpg")));
		lblFond.setBounds(0, 0, 994, 571);
		panel.add(lblFond);

		// Packing et affichage de la JFrame
		this.pack();
		this.setVisible(true);

	}
}
