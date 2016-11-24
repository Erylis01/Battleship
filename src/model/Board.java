package model;

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
	    int boxHeight = (panel.getHeight()/nbBriquesHauteur);
	    int boxWidth = (panel.getWidth()/nbBriquesLargeur);
	    /*System.out.println(panel.getHeight());
	    box = new Box(0,0,boxWidth,boxHeight,"vide");
        board[0][0]= box;
        panel.add(box);*/
        
		for (int col =0; col < nbBriquesHauteur; col++) {
            for (int lig = 0; lig < nbBriquesLargeur; lig++) {
                box = new Box(col*boxWidth,lig*boxHeight,boxWidth,boxHeight,"vide");
                board[col][lig]= box;
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
