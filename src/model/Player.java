package model;

public class Player {
	
	private String Pseudo;
	private Boat[] ship;
	private String state; // i.e alive, dead
	
	
	public Player(String pseudo, Boat[] ship, String state) {
		Pseudo = pseudo;
		this.ship = ship;
		this.state = state;
	}

	/**
	 * Check the state of all boat for the current player
	 * Return true only if all boat are sunk
	 * @return boolean - isDead
	 */
	public boolean isDead(){
		boolean isDead = true;
		for(Boat boat : ship){
			if(!boat.isSunk()){
				isDead= false;
			}
		}
		return isDead;
	}
	
	public String getPseudo() {
		return Pseudo;
	}


	public void setPseudo(String pseudo) {
		Pseudo = pseudo;
	}


	public Boat[] getShip() {
		return ship;
	}


	public void setShip(Boat[] ship) {
		this.ship = ship;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
