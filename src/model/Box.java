package model;

import java.awt.Graphics;

import javax.swing.JButton;

public class Box extends JButton{
	

	private static final long serialVersionUID = 2384294667973519463L;
	
	//Position graphique
	private int x;
	private int y;
	
	//Position grille
	private int xCoord;
	private int yCoord;
	
	//Size
	private int width;
	private int height;
	
	//The state will have an impact on the drawing color
	private String state; //i.e empty, safe, touched

	public Box(int x, int y,int width,int height,String state){
		this.x=x;
		this.y=y;
		xCoord=x/width;
		yCoord=y/height;
		this.width=width;
		this.height=height;
		this.state=state;
	}
	
	
	public int getXCoordinate(){
		return xCoord;
	}
	
	public int getYCoordinate(){
		return yCoord;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	

}