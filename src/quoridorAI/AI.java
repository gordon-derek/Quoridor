/*
 * Author: Derek Gordon
 * Date: 3/8/14
 * Purpose: Instance to be further
 * defined in the AI classes.  This outlines
 * the basic implementation of all AI's.
 */

package quoridorAI;

import quoridorGameLogic.GameBoard;
import quoridorGameLogic.Player;

public abstract class AI {
	public GameBoard gb;
	public int id;
	public Player[] players;
	
	public AI(int id, int numPlayers){		
		this.id = id;
		gb = new GameBoard(numPlayers);
		
		//initialize players
		players = gb.getPlayers();			
	}
	
	//updates gameboard when MOVED is sent to server
	public void updateGameboard(String move){
		gb.makeMove(move);
		updatePlayers();
	}
	
	/*
	 * Pre: Server is asked to make a move
	 * Post: a move is returned
	 * Purpose: Many different possibilities of moves
	 * depending on the algorithm used within each AI.
	 */
	abstract public String think();
	
	//removes the given player from gameboard
	//if its us thats getting kicked, returns true
	public boolean removePlayer(int aiID){
		if(aiID == id)
			return true; //we got kicked
		gb.kickPlayer(aiID);
		updatePlayers();
		return false;
	}
		
	//gets all the new players after certain operations
	public void updatePlayers(){
		players = gb.getPlayers();
	}
}
