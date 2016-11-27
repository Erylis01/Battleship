package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.awt.GridBagConstraints;

import model.Board;
import model.Box;

import javax.swing.JTextArea;

public class Draughtboard extends JFrame {

	private static final long serialVersionUID = 6895220124229682988L;
	private final int LARGEUR_FENETRE = 1000, HAUTEUR_FENETRE = 600;
	private JPanel panel;
	private JTextField textFieldIp;
	private JTextField textFieldPort;
	private JTextField textField;

	public Draughtboard() {

		// Centrage de la fen�tre et choix de la taille de la fen�tre
		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		this.setPreferredSize(new Dimension(this.LARGEUR_FENETRE, this.HAUTEUR_FENETRE));
		int windowLeftPosition = screenSize.width / 2 - this.LARGEUR_FENETRE / 2;
		int windowRightPostion = screenSize.height / 2 - this.HAUTEUR_FENETRE / 2;
		this.setLocation(windowLeftPosition, windowRightPostion);

		// Emp�che la fen�tre d'�tre redimensionn�e
		setResizable(false);

		// Choix du titre et de l'icone de la JFrame
		this.setTitle("Battleship");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Log.class.getResource("/assets/logo.png")));

		// Enregistrement de l'option EXIT_ON_CLOSE lors de la fermeture de la
		// fen�tre (arr�t du proc�ssus)
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		// Ajout d'un panel qui contiendra tout
		panel = new JPanel();
		panel.setBounds(0, 0, 994, 571);
		panel.setLayout(null);
		this.setContentPane(panel);
		
		
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

		
		//Trac� de la grille de gauche
		JPanel grilleOpponent = new JPanel();
		grilleOpponent.setBounds(48, 195, 340, 340);
		grilleOpponent.setLayout(new GridLayout(10,10));
		int boxHeight = ((grilleOpponent.getHeight())/10);
	    int boxWidth = ((grilleOpponent.getWidth())/10);
	    
		for (int col =0; col < 10; col++) {
            for (int row = 0; row < 10; row++) {
            	Box box = new Box(0+row*boxHeight,0+col*boxWidth,boxWidth,boxHeight,"vide");
            	box.setBackground(Color.white);
            	box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            	box.setCursor(new Cursor(Cursor.HAND_CURSOR));
            	box.setPreferredSize(new java.awt.Dimension(boxHeight, boxWidth));
                grilleOpponent.add(box);
                }
        }
		panel.add(grilleOpponent);
		
		
		//Trac� de la grille de droite
				JPanel grilleFriend = new JPanel();
				grilleFriend.setBounds(550, 195, 340, 340);
				grilleFriend.setLayout(new GridLayout(10,10));
			    
				for (int col =0; col < 10; col++) {
		            for (int row = 0; row < 10; row++) {
		            	Box box = new Box(0+row*boxHeight,0+col*boxWidth,boxWidth,boxHeight,"vide");
		            	box.setBackground(Color.white);
		            	box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		            	box.setCursor(new Cursor(Cursor.HAND_CURSOR));
		            	box.setPreferredSize(new java.awt.Dimension(boxHeight, boxWidth));
		            	grilleFriend.add(box);
		                }
		        }
				panel.add(grilleFriend);
				
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
