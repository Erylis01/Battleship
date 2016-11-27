package model;


public class Board {
	

	//Number of boxes in height and width
    int nbBriquesHauteur = 10;
    int nbBriquesLargeur = 10;

    //Creation of a table of boxes
    Box board[][] = new Box[nbBriquesHauteur][nbBriquesLargeur];


    Box  box;
	
   

	public Board(int nbBriquesHauteur, int nbBriquesLargeur, Box[][] board, Box box) {
		super();
		this.nbBriquesHauteur = nbBriquesHauteur;
		this.nbBriquesLargeur = nbBriquesLargeur;
		this.board = board;
		this.box = box;
	}

	public Box[][] getBoard() {
		return board;
	}

	public void setBoard(Box[][] board) {
		this.board = board;
	}		

}
