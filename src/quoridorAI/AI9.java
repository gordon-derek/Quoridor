/*
 * Author: Parker Sims
 * AI9 - Reeds Opening (2 player) Making the Assumption we are player 2
 * ( the _ )
 *  --------
 * |	*	|
 * |		|
 * |		|
 * |	_	|
 *  --------	
 * This opening consists in placing, during the first two moves 
 * of the game, two walls on the third row in front of the opponent
 *  with a single gap in the middle (c3h and f3h). This opening 
 *  is attributed to Dr. Scott Reed (Edinburgh, UK), a known 
 *  military strategist and frequent player.
 */

package quoridorAI;

public class AI9 extends AI {

	private int moveNumber;
	
	public AI9(int id, int numPlayers) {
		super(id, numPlayers);
		this.moveNumber = 0;
	}
	
	// starts out using Reeds opening, under the assumption that
	// we are player two. ** That will get fixed later on down
	// the road. If the two opening moves are illegal, it will
	// automatically do the shortest path algorithm.
	public String think(){
		String move = "";
		if (moveNumber == 0) {
			moveNumber++;
			move = "c3h";
			if (gb.legalMove(id +" "+move)){ // if legal move, return
				return move;
			}
			else { // else, do the shortest path
				move = getShortestPath();
			}
			
		}
		else if (moveNumber ==1) {
			moveNumber++;
			move = "a3h";
			if (gb.legalMove(id +" "+move)){ // if legal move, return
				return move;
			}
			else { // else, do the shortest path
				move = getShortestPath();
			}
		}
		else { // after the first two moves, do the shortest path
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