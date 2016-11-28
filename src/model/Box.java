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

	// Placing state boolean
	private static boolean isPlacementActive = true;
	private static int currentBoat = 1;
	private static boolean doneBoat = false;

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
		if (isPlacementActive && currentBoat == 1 && this.getParent().equals(clientController.getBoard())) {
			if (clientController.getTwoBoat()[0] == null) {
				this.setState("safe");
				this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				this.setBackground(Color.BLUE);
				clientController.getTwoBoat()[0] = this;
			} else {
				if (clientController.getTwoBoat()[1] == null
						&& ((Math.abs(xCoord - clientController.getTwoBoat()[0].getXCoordinate()) == 1)
								&& (Math.abs(yCoord - clientController.getTwoBoat()[0].getYCoordinate()) == 0))
						|| ((Math.abs(xCoord - clientController.getTwoBoat()[0].getXCoordinate()) == 0)
								&& (Math.abs(yCoord - clientController.getTwoBoat()[0].getYCoordinate()) == 1))) {
					this.setState("safe");
					this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
					this.setBackground(Color.BLUE);
					clientController.getTwoBoat()[1] = this;
					doneBoat = true;
				}
			}
		}

		if (isPlacementActive && currentBoat == 2 && this.getParent().equals(clientController.getBoard())) {
			if (clientController.getThirdFstBoat()[0] == null) {
				this.setState("safe");
				this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				this.setBackground(Color.BLUE);
				clientController.getThirdFstBoat()[0] = this;
			} else {
				if (clientController.getThirdFstBoat()[1] == null
						&& ((Math.abs(xCoord - clientController.getThirdFstBoat()[0].getXCoordinate()) == 1)
								&& (Math.abs(yCoord - clientController.getThirdFstBoat()[0].getYCoordinate()) == 0))
						|| ((Math.abs(xCoord - clientController.getThirdFstBoat()[0].getXCoordinate()) == 0)
								&& (Math.abs(yCoord - clientController.getThirdFstBoat()[0].getYCoordinate()) == 1))) {
					this.setState("safe");
					this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
					this.setBackground(Color.BLUE);
					clientController.getThirdFstBoat()[1] = this;
				} else if (clientController.getThirdFstBoat()[2] == null
						&& ((Math.abs(xCoord - clientController.getThirdFstBoat()[1].getXCoordinate()) == 1)
								&& (Math.abs(yCoord - clientController.getThirdFstBoat()[1].getYCoordinate()) == 0))
						|| ((Math.abs(xCoord - clientController.getThirdFstBoat()[1].getXCoordinate()) == 0)
								&& (Math.abs(yCoord - clientController.getThirdFstBoat()[1].getYCoordinate()) == 1))) {
					this.setState("safe");
					this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
					this.setBackground(Color.BLUE);
					clientController.getThirdFstBoat()[2] = this;
					doneBoat = true;
				}
			}
		}
		

		if(doneBoat){
			currentBoat += 1;
			doneBoat = false;
		}
	}
}