package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import client.clientController;

public class Box extends JButton implements ActionListener {

	private static final long serialVersionUID = 2384294667973519463L;

	// Position graphique
	private int x;
	private int y;

	// Position grille
	private int xCoord;
	private int yCoord;

	// Size
	private int width;
	private int height;

	// The state will have an impact on the drawing color
	private String state; // i.e empty, safe, touched

	/**
	 * Create a Box (JButton)
	 * 
	 * @param x
	 *            - position in x of the box
	 * @param y
	 *            - position in y of the box
	 * @param width
	 *            - width of the box
	 * @param height
	 *            - height of the box
	 * @param state
	 *            - state of the box
	 */
	public Box(int x, int y, int width, int height, String state) {
		this.x = x;
		this.y = y;
		this.xCoord = x / width;
		this.yCoord = y / height;
		this.width = width;
		this.height = height;
		this.state = state;
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setPreferredSize(new java.awt.Dimension(height, width));
	}

	/**
	 * Get the position in x in the grid of the box
	 * 
	 * @return
	 */
	public int getXCoordinate() {
		return xCoord;
	}

	/**
	 * Get the position in y in the grid of the box
	 * 
	 * @return
	 */
	public int getYCoordinate() {
		return yCoord;
	}

	/**
	 * Get the position in x in the panel of the box
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the position in x in the panel of the box
	 * 
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the position in y in the panel of the box
	 * 
	 * @param
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the position in y in the panel of the box
	 * 
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get the width of the box
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the box
	 * 
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Get the height of the box
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the height of the box
	 * 
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Get the state of the box
	 * 
	 * @return
	 */
	public String getState() {
		return state;
	}

	/**
	 * Set the state of the box
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (clientController.isPlacementActive() && this.getParent().equals(clientController.getBoard()) && (!clientController.isFirstCaseDone() || (clientController.getLastX() - xCoord <= 1 && clientController.getLastY() - yCoord <= 1 ))) {
			this.setState("safe");
			this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			this.setBackground(Color.BLUE);
			clientController.setSynchronizingVar(clientController.getBoard().board[xCoord][yCoord]);
			clientController.setFirstCaseDone(true);
			clientController.setLastX(xCoord);
			clientController.setLastY(yCoord);
			clientController.synchronizingVar.notify();
		}
	}

}