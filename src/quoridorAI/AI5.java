package quoridorAI;

/**
 * 
 * @author *******
 *
 * Purpose: Testing rules by placing two walls in a cross
 *
 */

public class AI5 extends AI {
	private int moveCount;
	
	public AI5(int id, int numPlayers){
		super(id, numPlayers);
		moveCount = 0;
	}

	// Think then places walls to intersect a wall 
	// to make a cross (+) in the first two moves
	public String think() {
		String move = "";
		String path = "";
		
		if(moveCount == 0)
			move = "b1V";
		else
			move = "b1H";
		moveCount++;
		
		return move;
	}

}
