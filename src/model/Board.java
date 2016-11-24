package model;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Board {
	

	//Number of boxes in height and width
    int nbBriquesHauteur = 10;
    int nbBriquesLargeur = 10;

    //Creation of a table of boxes
    Box board[][] = new Box[nbBriquesHauteur][nbBriquesLargeur];

    //Création of a 
    Box  box;
	
    
	public Board(JPanel panel) {
		
		//taille d'une case
	    int boxHeight = (panel.getHeight()/nbBriquesHauteur);
	    int boxWidth = (panel.getWidth()/nbBriquesHauteur);
	    System.out.println(panel.getHeight());
		
		for (int i =0; i < nbBriquesHauteur; i++) {                            //On parcourt toutes les cases du tableau
            for (int j = 0; j < nbBriquesLargeur; j++) {
                box = new Box(i,j,boxWidth,boxHeight,"vide");
                panel.add(box);
            }
        }
	}

	public Box[][] getBoard() {
		return board;
	}

	public void setBoard(Box[][] board) {
		this.board = board;
	}		

}
