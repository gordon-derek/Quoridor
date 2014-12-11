/*
 * Author: ******
 * AI6 - Testing game board purposes.
 * Supposed to attempt to place a wall overlapping another wall. 
 */

package quoridorAI;

public class AI6 extends AI {

	private int moveNumber;
	
	public AI6(int id, int numPlayers) {
		super(id, numPlayers);
		this.moveNumber = 0;
	}
	/**
	 * think - if first move, places a horizontal wall.
	 * 			if beyond first move, tries to place a wall overlapping the first.
	 * 
	 * @returns: String move - the wall placement move.  
	 */
	public String think(){
		String move = "";
		if(moveNumber == 0)
			move = "b2h";
		else if(moveNumber > 0)
			move = "b3h";
		moveNumber++;
		
		return move;
	}
	
}