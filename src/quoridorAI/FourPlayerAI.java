/**
 * 
 */
package quoridorAI;

/**
 * @author Team
 * 
 */
public class FourPlayerAI extends AI {

	/**
	 * The constructor for the AI.
	 * 
	 * @param id
	 *            the id of the player the AI is playing.
	 * @param numPlayers
	 *            the number of players in the game (should be 2 or 4).
	 */
	public FourPlayerAI(int id, int numPlayers) {
		super(id, numPlayers);
	}

	/**
	 * Generates a move for the AI.
	 */
	public String think() {
		String move = "";
		int playerToBlock = getPlayerToBlock(gb.shortestPath(id).length());
		if (playerToBlock != -1) {
			move = generateWallMove(playerToBlock);
		}
		//System.out.println("\nplayerToBlock:" + playerToBlock);
		//System.out.println("move:" + move + "\n");
		if (!gb.legalMove(id + " " + move)) {
			move = gb.shortestPath(id).split(":")[0];
		}
		//System.out.println("move:" + move + "\n");

		return move;
	}

	/**
	 * Generate a wall move for the player number passed in.
	 * 
	 * @param playerToBlock
	 *            the player we are blocking (if they have a shorter
	 *            shortest path than we do)
	 * @return the generated wall move in order to block above player
	 */
	private String generateWallMove(int playerToBlock) {
		String path = gb.shortestPath(playerToBlock);
		path = players[playerToBlock - 1].toString() + ":" + path;
		String[] pawnMoves = path.split(":");
		String pMove1 = "";
		String pMove2 = "";
		String move = "";
		//System.out.println("\nCALCULATING WALL MOVE TO MESS WITH PLAYER");
		for (int i = 0; i < pawnMoves.length - 1
				&& !gb.legalMove(playerToBlock + pMove1); i++) {
			//System.out.println("Trying to stop:" + pawnMoves[i] + "-"
			//		+ pawnMoves[i + 1]);
			// the player will be trying to move right.
			// if he is following his shortest path.
			if (pawnMoves[i].charAt(0) < pawnMoves[i + 1].charAt(0)) {
				pMove1 = pawnMoves[i] + 'v';
				pMove2 = "" + pMove1.charAt(0) + (char) (pMove1.charAt(1) - 1)
						+ pMove1.charAt(2);
			}
			// the player will be trying to move left.
			// if he is following his shortest path.
			if (pawnMoves[i].charAt(0) > pawnMoves[i + 1].charAt(0)) {
				pMove1 = pawnMoves[i + 1] + 'v';
				pMove2 = "" + pMove1.charAt(0) + (char) (pMove1.charAt(1) - 1)
						+ pMove1.charAt(2);
			}
			// the player will be trying to move down.
			// if he is following his shortest path.
			if (pawnMoves[i].charAt(1) < pawnMoves[i + 1].charAt(1)) {
				pMove1 = pawnMoves[i] + 'h';
				pMove2 = "" + (char) (pMove1.charAt(0) + 1) + pMove1.charAt(1)
						+ pMove1.charAt(2);
			}
			// the player will be trying to move up.
			// if he is following his shortest path.
			if (pawnMoves[i].charAt(1) > pawnMoves[i + 1].charAt(1)) {
				pMove1 = pawnMoves[i + 1] + 'h';
				pMove2 = "" + (char) (pMove1.charAt(0) + 1) + pMove1.charAt(1)
						+ pMove1.charAt(2);
			}
			//System.out.println("Seeing if:" + pMove1 + " " + pMove2);
			move = checkWallMoves(pMove1, pMove2, playerToBlock);
		}
		return move;
	}

	/**
	 * Checks two wall moves and returns the better one (the placement
	 * that will leave a longer shortest path for the opposing player).
	 * 
	 * @param pMove1
	 *            the first wall move.
	 * @param pMove2
	 *            the second wall move.
	 * @param playerToBlock
	 *            the player we are trying to block
	 * @return the better move. will return empty string if both moves are bad.
	 */
	public String checkWallMoves(String pMove1, String pMove2, int playerToBlock) {
		String move = "";
		int ourPathLength = gb.shortestPath(id).length();
		int thierPathLength = gb.shortestPath(playerToBlock).length();
		if (gb.legalMove(playerToBlock + pMove1)) {
			gb.makeMove(playerToBlock + pMove1);
			if (!(ourPathLength < gb.shortestPath(id).length())) {
				thierPathLength = gb.shortestPath(playerToBlock).length();
				move = pMove1;
			}
			gb.unmakeWallMove(playerToBlock + pMove1);
		}
		if (gb.legalMove(playerToBlock + pMove2) && move.equals("")) {
			gb.makeMove(playerToBlock + pMove2);
			if (!(ourPathLength < gb.shortestPath(id).length())
					&& thierPathLength < gb.shortestPath(playerToBlock)
					.length()) {
				move = pMove2;
			}
			gb.unmakeWallMove(playerToBlock + pMove2);
		}
		return move;
	}

	/**
	 * Gets the player to block(the player with the shortest path) only if 
	 * said player has a shorter path than you. If you have the shortest path 
	 * method will return -1.
	 * 
	 * @param pathLength
	 *            your path length.
	 * @return the player to block.
	 */
	public int getPlayerToBlock(int pathLength) {
		int playerToBlock = -1;
		if (players[id - 1].getWalls() != 0) {
			//System.out.println("\nCALCULATING PLAYER TO MESS WITH");
			for (int i = 1; i <= 4; i++) {
				System.out.println("i:" + i + "\t\tid:" + id);
				if (i != id && players[i - 1] != null
						&& pathLength > gb.shortestPath(i).length()) {
					System.out.println("gb.shortestPath(i).length():"
							+ gb.shortestPath(i).length() + "\t\tpathlength:"
							+ pathLength);
					playerToBlock = i;
					pathLength = gb.shortestPath(i).length();
				}
			}
		}
		return playerToBlock;
	}

}
