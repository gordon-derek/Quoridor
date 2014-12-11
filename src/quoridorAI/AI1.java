/*
 * Author: Derek Gordon
 * Date: 2/27/2014
 * Purpose: Basic AI Implementation that will
 * just move straight from its starting position
 * to the winning position.  Thus with two of these AI's
 * whoever starts second wins.
 */
package quoridorAI;


public class AI1 extends AI{
	
	
	public AI1(int id, int numPlayers){super(id, numPlayers);}

	
	//think, perform shortest path or in this case check if can jump and go straight.
	public String think(){
		//get shortest path
		String path = gb.shortestPath(id);
		String move = "";
		for(int i = 0; i<2; i++){
			move += path.charAt(i);
		}
		return move;
	}

	
}
