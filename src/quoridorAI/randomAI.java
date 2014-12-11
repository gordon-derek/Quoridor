/**
 * Author: Jeremy Rose 
 * Implemented random AI, picks a random move
 * out of a list of possible legal moves the player can take.
 * 
 * Note: Cody explained his new version of getLegalMove checked over
 * my implementation of the getting the legal moves.  
 *  
 */

package quoridorAI;

import java.util.List;
import java.util.Random;

public class randomAI extends AI{

	private List<String> possibleMoves;
	
	public randomAI(int id, int numPlayers){
		super(id, numPlayers);
	}
	/**
	 * Gets a list of possible legal moves from the current game board.
	 * picks a random move from that list and returns it.
	 * 
	 * @return - a random legal move.
	 *  
	 */
	public String think(){
		this.possibleMoves = gb.getLegalMoves(id);
		Random randomMove = new Random();
		String move = possibleMoves.get(randomMove.nextInt(possibleMoves.size()));
		move = move.substring(2);
		return move;
	}

}
