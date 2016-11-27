package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class Board extends JPanel{
	

	private static final long serialVersionUID = 1L;
	
	//Number of boxes in height and width
    private int nbBoxesHeight = 10;
    private int nbBoxesWidth = 10;

    //Creation of a table of boxes
    Box board[][] = new Box[nbBoxesHeight][nbBoxesWidth];

    // Size of the boxes
    private int boxHeight,boxWidth;

    /**
     * Create a new board (JPanel) with a GridLayout
     * @param nbBoxesHeight - number of boxes in height
     * @param nbBoxesWidth - number of boxes in width
     * @param x - position in x of the board
     * @param y - position in y of the board
     * @param width - width of the board
     * @param height - height of the board
     */
	public Board(int nbBoxesHeight, int nbBoxesWidth,int x, int y, int width, int height) {
		super();
		this.nbBoxesHeight = nbBoxesHeight;
		this.nbBoxesWidth = nbBoxesWidth;
		this.setBounds(x, y, width, height);
		this.boxHeight =this.getHeight()/nbBoxesHeight;
		this.boxWidth= this.getWidth()/nbBoxesWidth;
		this.setLayout(new GridLayout(nbBoxesHeight,nbBoxesWidth));	
	}

	/**
	 * Add the boxes of the grid on the panel
	 */
	public void addBoxes(){
		for (int col =0; col < nbBoxesHeight; col++) {
            for (int row = 0; row < nbBoxesWidth; row++) {
            	Box box = new Box(row*boxHeight,col*boxWidth,boxWidth,boxHeight,"empty");
            	box.addActionListener(box);
            	board[col][row]= box;
                this.add(box);
                }
        }
	}
	
	/**
	 * Get the table of boxes
	 *
	 * @return
	 */
	public Box[][] getBoard() {
		return board;
	}

	/**
	 * Set a table of boxes
	 * 
	 * @param board
	 */
	public void setBoard(Box[][] board) {
		this.board = board;
	}		

}
