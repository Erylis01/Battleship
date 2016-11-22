package model;

public class Boat {
	
	private Box[] position;
	private String state; //i.e safe, touched, drown
	
	public Boat(Box[] position, String state) {
		super();
		this.position = position;
		this.state = state;
	}
	
	/**
	 * Check the state of all box for the current boat
	 * Return true only if all box are touched
	 * @return boolean - isSunk
	 */
	public boolean isSunk(){
		boolean state = true;
		for(Box box : position){
			if(!box.getState().equals("touched")){
				state = false;
			}
		}
		return state;
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
