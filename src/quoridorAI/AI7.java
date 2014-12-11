/*
 * Author: *****
 * AI7 - Ala Opening (2 player) Making the Assumption we are player 2
 * ( the _ )
 *  --------
 * |	*	|
 * |		|
 * |		|
 * |	_	|
 *  --------	
 * This opening uses the shortest path for the first three moves, then places a wall
 * directly (horizontally) behind itself 
 */

package quoridorAI;

public class AI7 extends AI {

	private int moveNumber;
	
	public AI7(int id, int numPlayers) {
		super(id, numPlayers);
		this.moveNumber = 0;
	}
	
	// Using Ala opening
	public String think(){
		String move = "";
		if (moveNumber == 3) { // on the third move, place a wall behind player
			moveNumber++;
			move = "f6h";
		}
		else {
			moveNumber++;
			move = getShortestPath();
		}
		return move; 
	}
	
	// gets the shortest path algorithm
	public String getShortestPath() {
		String path = gb.shortestPath(id);
		String move = "";
		for(int i = 0; i<2; i++){
			move += path.charAt(i);
		}
		return move;
	}
	
}