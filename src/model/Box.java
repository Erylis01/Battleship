package model;

public class Box {
	
	private int x;
	private int y;
	//The state will have an impact on the drawing color
	private String state; //i.e empty, safe, touched
	
	
	public Box(int x, int y, String state) {
		this.x = x;
		this.y = y;
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


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
