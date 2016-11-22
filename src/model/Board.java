package model;

public class Board {
	

	Box[][] board;
	
	public Board(Box[][] board) {
		super();
		this.board = board;
	}

	public Box[][] getBoard() {
		return board;
	}

	public void setBoard(Box[][] board) {
		this.board = board;
	}		

}
