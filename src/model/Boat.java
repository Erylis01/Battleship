package model;

public class Boat {
	
	private Box[] position;
	private String state; //i.e safe, touched, drown
	
	public Boat(Box[] position, String state) {
		super();
		this.position = position;
		this.state = state;
	}

	public Box[] getPosition() {
		return position;
	}

	public void setPosition(Box[] position) {
		this.position = position;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
