package model;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class Box extends JComponent{
	

	private static final long serialVersionUID = 2384294667973519463L;
	
	//Position
	private int x;
	private int y;
	
	//Size
	private int width;
	private int height;
	
	//The state will have an impact on the drawing color
	private String state; //i.e empty, safe, touched

	public Box(int x, int y, int width, int height,String state){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.state=state;
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub   
	    g.setColor(Color.BLACK);
	    g.drawRect(x,y,width,height);

	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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


	
	
}
