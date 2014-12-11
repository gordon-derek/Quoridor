/*
 * Author: Jeremy Rose
 * Testing purpose AI
 * 
 * Try to place walls off the edge of the board,
 * or overlapping a current wall. 
 */
package quoridorAI;

public class AI4 extends AI {

	public AI4(int id, int numPlayers) {
		super(id, numPlayers);
	}

	/**
	 * Think, places walls off the edge of the game board	 * 
	 * @return: String move - what move the player will make.
	 */
	public String think() {
		return "i3h";
	}
	
}
