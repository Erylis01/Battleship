package model;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Board {
	

	//Number of boxes in height and width
    int nbBriquesHauteur = 10;
    int nbBriquesLargeur = 10;

    //Creation of a table of boxes
    Box board[][] = new Box[nbBriquesHauteur][nbBriquesLargeur];


    Box  box;
	
    
	public Board(JPanel panel) {
		
		//taille d'une case
	    int boxHeight = ((panel.getHeight())/nbBriquesHauteur);
	    int boxWidth = ((panel.getWidth())/nbBriquesLargeur);
        
		for (int col =0; col < nbBriquesHauteur; col++) {
            for (int row = 0; row < nbBriquesLargeur; row++) {
            	Box box = new Box(col,row,boxWidth,boxHeight,"vide");
            	box.setBackground(Color.white);
            	box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            	box.setCursor(new Cursor(Cursor.HAND_CURSOR));
            	box.setPreferredSize(new java.awt.Dimension(boxHeight, boxWidth));
            	
                JButton b = new JButton();
                b.setBackground(Color.WHITE);
                b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                b.setCursor(new Cursor(Cursor.HAND_CURSOR));
                b.setPreferredSize(new java.awt.Dimension(boxHeight, boxWidth));
                
                panel.add(b);
            }
        }
		panel.revalidate();
        panel.repaint();
	}

	public Box[][] getBoard() {
		return board;
	}

	public void setBoard(Box[][] board) {
		this.board = board;
	}		

}
