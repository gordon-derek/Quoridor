/*
 * Author: Derek Gordon
 * Date: 3/8/14
 * Purpose: Tests movement off the gameboard for breaking.
 */

package quoridorAI;

public class AI2 extends AI{

	public AI2(int id, int numPlayers){super(id, numPlayers);}
	
	//think, perform shortest path or in this case check if can jump and go straight.
	public String think(){
		//get shortest path
		char row = 'a';
		switch(players[id-1].getRow()){
		case 0: row = 'a'; break;
		case 1: row = 'b'; break;
		case 2: row = 'c'; break;
		case 3: row = 'd'; break;
		case 4: row = 'e'; break;
		case 5: row = 'f'; break;
		case 6: row = 'g'; break;
		case 7: row = 'h'; break;
		case 8: row = 'i'; break;
		}
		int col = players[id-1].getColumn();
		col++;
		String move = "";
		move = Character.toString(row) + Integer.toString(col);
		return move;
	}
	
}
