package model;

public class Move {

	private int xTarget;
	private int yTarget;
	private Player fighter;
	private String result;
	
	
	public Move(int xTarget, int yTarget, Player fighter, String result) {
		this.xTarget = xTarget;
		this.yTarget = yTarget;
		this.fighter = fighter;
		this.result = result;
	}


	public int getxTarget() {
		return xTarget;
	}


	public void setxTarget(int xTarget) {
		this.xTarget = xTarget;
	}


	public int getyTarget() {
		return yTarget;
	}


	public void setyTarget(int yTarget) {
		this.yTarget = yTarget;
	}


	public Player getFighter() {
		return fighter;
	}


	public void setFighter(Player fighter) {
		this.fighter = fighter;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	
	
}
