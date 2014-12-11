/**
 * Author: ****
 * AI8
 * 
 */

package quoridorAI;

public class AI8 extends AI {

	private int moveNumber;
	
	public AI8(int id, int numPlayers) {
		super(id, numPlayers);
		this.moveNumber = 0;
	}
	/**
	 * Starts off with 3 opening moves of moving straight forward 3 places
	 * Unless blocked, where the player will move over in the direction of the odd
	 * side of the board and the continue to the 3 moves towards the center.
	 * 
	 * After the 3 opening moves, the player attempts to place a horizontal wall
	 * behind it to ensure that it is forced along its current shortest current path. 
	 * (adopted from an idea discussed at: http://rpggeek.com/thread/126895/learning-strategy )
	 * 
	 * @return: String move - the move of the player.
	 * 
	 */
	public String think(){
		//Current player = players[id-1]
		String move = "";
		int row = players[id -1].getRow();
		int col = players[id -1].getColumn();
		
		if(moveNumber < 3){
			moveNumber++;
			move = moveForward(row, col);
		}else if (moveNumber > 4 &&
				moveNumber % 2 == 0){
			moveNumber++;
			move = getShortestPath();
			
		}else if(moveNumber > 4 &&
				moveNumber % 2 != 0 &&
				players[id -1].getWalls() != 0){
			moveNumber++;
			//place a wall behind me
			move = placeWallBehindCurrentPosition(row, col);
		}
		return move;
	}
	
	/**
	 * 
	 * @param row - current row of the player
	 * @param col - current column of the player
	 * @return move - the move the player makes to move forward one spot
	 * 					unless there is a wall in front, then avoid the wall.
	 */
	private String moveForward(int row, int col){
		String move = "";
		if(id == 1){
			move = Integer.toString(row) + Integer.toString(col +1);
		}else if(id == 2){
			move = Integer.toString(row) + Integer.toString(col - 1);
		}else if(id == 3){
			move = Integer.toString(row + 1) + Integer.toString(col);
		}else if(id == 4){
			move = Integer.toString(row - 1) + Integer.toString(col);
		}
		
		if(!gb.legalMove(move)){
			move = getShortestPath();
		}
		
		return move;
	}
	
	
	/**
	 * 
	 * @param row - current row of the player.
	 * @param col - current column of the player
	 * @return - move - the move the player sends to place a wall behind it current position
	 */
	private String placeWallBehindCurrentPosition(int row, int col){
		String move = "";
		if(id == 1){
			move = "h" + Integer.toString(row-1) + Integer.toString(col);
		}else if(id == 2){
			move = "h" + Integer.toString(row+1) + Integer.toString(col);
		}else if(id == 3){
			move = "h" + Integer.toString(row) + Integer.toString(col-1);
		}else if(id == 4){
			move = "h" + Integer.toString(row) + Integer.toString(col+1);
		}
		
		//If the move is not legal just take the shortest path instead.
		if(!gb.legalMove(move)){
			move = getShortestPath();
		}
		return move;
	}
	
	/**
	 * getShortestPath - get the players next move in the shortest path.
	 * @return - move - the next move in the players shortest path
	 */
	public String getShortestPath() {
		String path = gb.shortestPath(id);
		String move = "";
		for(int i = 0; i<2; i++){
			move += path.charAt(i);
		}
		return move;
	}
}