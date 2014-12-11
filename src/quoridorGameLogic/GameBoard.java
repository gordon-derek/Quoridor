/**
 * This Package and class will be used by both the quoridor client and the quoridor server. 
 * it will kept track of the state of the board, and check the validity of the moves. 
 */
package quoridorGameLogic;

import java.awt.Color;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Cody Hamilton
 * 
 */
public class GameBoard {
	public static final int WIDTH = 9;
	public static final int HEIGHT = 9;
	public static final int MAX_PLAYERS = 4;
	public static final int TOTAL_WALL_COUNT = 20;
	BoardSpace[][] board;
	Player[] players;
	public int numPlayers;

	/**
	 * The constructor for the game board.
	 * 
	 * @param numPlayers
	 *            the number of players in the game, (should be 2 or 4).
	 */
	public GameBoard(int numPlayers) {
		this.setPlayers(numPlayers);
		this.createBoard();
		this.numPlayers = numPlayers;
	}

	/**
	 * Inits the players for the gameBoard.
	 * 
	 * @param numPlayers
	 *            The number of players to init.
	 */
	private void setPlayers(int numPlayers) {
		players = new Player[MAX_PLAYERS];
		if (numPlayers == 2) {
			players[0] = new Player(0, 4, TOTAL_WALL_COUNT / numPlayers,
					Color.red);
			players[1] = new Player(8, 4, TOTAL_WALL_COUNT / numPlayers,
					Color.yellow);
		} else if (numPlayers == 4) {
			players[0] = new Player(0, 4, TOTAL_WALL_COUNT / numPlayers,
					Color.red);
			players[1] = new Player(8, 4, TOTAL_WALL_COUNT / numPlayers,
					Color.yellow);
			players[2] = new Player(4, 0, TOTAL_WALL_COUNT / numPlayers,
					Color.pink);
			players[3] = new Player(4, 8, TOTAL_WALL_COUNT / numPlayers,
					Color.magenta);

		} else {
			throw new IllegalArgumentException("invalid number of players:"
					+ numPlayers);
		}

	}

	/**
	 * Makes a new 2d array of BoardSpaces, and sets their links.
	 */
	private void createBoard() {
		board = new BoardSpace[HEIGHT][WIDTH];
		for (int row = 0; row < HEIGHT; row++) {
			for (int column = 0; column < WIDTH; column++) {
				board[row][column] = new BoardSpace(row, column);
			}
		}
		BoardSpace[] prevRow = null;
		for (int row = 0; row < HEIGHT; row++) {
			for (int column = 0; column < WIDTH; column++) {
				if (column > 0) {
					board[row][column].setLeft(board[row][column - 1]);
				}
				if (column < WIDTH - 1) {
					board[row][column].setRight(board[row][column + 1]);
				}
				if (prevRow != null) {
					prevRow[column].setDown(board[row][column]);
					board[row][column].setUp(prevRow[column]);
				}
			}
			prevRow = board[row];
		}
		for (Player player : players) {
			if (player != null) {
				board[player.getRow()][player.getColumn()].setHasPlayer(true);
			}
		}
	}

	/**
	 * 
	 * @param move
	 *            Should be a 3 or 4 character string. <id 1-4><letter
	 *            a-i><number 1-9> and if it is a wall move should be followed
	 *            by <v or h>");
	 * @return whether or not the move is legal with the current board state
	 */
	public boolean legalMove(String move) {

		move = move.replace(" ", "");
		move = move.toLowerCase();

		boolean legal = false;
		if (!validMoveFormat(move)) {
			return false;
		}
		if (move.length() == 4) {
			legal = legalWallMove(move);
		}
		if (move.length() == 3) {
			legal = legalPawnMove(move);
		}
		return legal;
	}

	/**
	 * Checks weather a pawn move is legal.
	 * 
	 * @param move
	 *            The move to check.
	 * @param player
	 *            The player to check for.
	 * @return Whether or not the move is legal.
	 */
	private boolean legalPawnMove(String move) {

		// Covert the move to 0 indexed values.
		Player player = players[move.charAt(0) - '1'];
		int moveRow = move.charAt(2) - '1';
		int moveColumn = move.charAt(1) - 'a';

		// get the current row and column. (0 indexed).
		int currRow = player.getRow();
		int currColumn = player.getColumn();

		// get the spaces the player wants to move to and is currently at.
		BoardSpace space = board[currRow][currColumn];
		BoardSpace moveSpace = board[moveRow][moveColumn];

		// get the list of spaces adjacent to the current space.
		List<BoardSpace> adjSpaces = adjacentSquares(space);

		// Check whether the adj spaces contains the desired move and return the
		// result.
		return adjSpaces.contains(moveSpace);
	}

	/**+
	 * 
	 * @param move
	 *            - the wall placement move that the player wishes to place.
	 * @return: true if the move being passed in is a legal wall move. false if
	 *          the move being passed in breaks wall placement rules of the
	 *          game.
	 */
	private boolean legalWallMove(String move) {
		boolean legal = true;
		char orientation = move.charAt(3);
		int row = move.charAt(2) - '1';
		int column = move.charAt(1) - 'a';
		if (row < 0 || row >= HEIGHT - 1 || column < 0 || column >= WIDTH - 1) {
			return false;
		}
		if (players[move.charAt(0) - '1'].getWalls() <= 0) {
			return false;
		}
		BoardSpace space1 = board[row][column];
		BoardSpace space2 = board[space1.getRow()][space1.getColumn() + 1];
		BoardSpace space3 = board[space1.getRow() + 1][space1.getColumn() + 1];
		BoardSpace space4 = board[space1.getRow() + 1][space1.getColumn()];
		
		//If its a vertical wall - places check the spaces to the right and left of the current position
		//Also checks to make sure this wall placement will not run off the edge of the board.
		if (orientation == 'v') {
			if (space1.getRight() == null || space4.getRight() == null) {
				return false;
			} else if (space1.getDown() == null && space2.getDown() == null) {
				int count = 0;
				BoardSpace tempSpace = space1;
				while (tempSpace.getDown() == null && column > 0) {
					count++;
					column--;
					tempSpace = board[row][column];
				}
				if (column == 0) {
					if (tempSpace.getDown() == null) {
						count++;
					}
				}
				if (count % 2 == 1) {
					return false;
				}
			}
			
		//If its a horizontal wall - places check the spaces above and below of the current position
		//Also checks to make sure this wall placement will not run off the edge of the board.
		} else if (orientation == 'h') {
			if (space1.getDown() == null || space2.getDown() == null) {
				return false;
			} else if (space1.getRight() == null && space4.getRight() == null) {
				int count = 0;
				BoardSpace tempSpace = space1;
				while (tempSpace.getRight() == null && row > 0) {
					count++;
					row--;
					tempSpace = board[row][column];
				}
				if (row == 0) {
					if (tempSpace.getRight() == null) {
						count++;
					}
				}
				if (count % 2 == 1) {
					return false;
				}
			}
		}
		makeWallMove(move);
		legal = arePaths();
		unmakeWallMove(move);
		return legal;
	}

	/**
	 * Should be passed a legal move that a wall is located at to be removed.
	 * 
	 * @param move
	 *            the legal move.
	 */
	public void unmakeWallMove(String move) {
		players[move.charAt(0) - '1'].addWall();;
		char orientation = move.charAt(3);
		int row = move.charAt(2) - '1';
		int column = move.charAt(1) - 'a';
		BoardSpace space = board[row][column];
		if (orientation == 'h') {
			space.setDown(board[space.getRow() + 1][space.getColumn()]);
			BoardSpace tempSpace = space.getDown();
			tempSpace.setUp(board[space.getRow()][space.getColumn()]);

			space = board[space.getRow()][space.getColumn() + 1];
			space.setDown(board[space.getRow() + 1][space.getColumn()]);
			tempSpace = space.getDown();
			tempSpace.setUp(board[space.getRow()][space.getColumn()]);
		} else if (orientation == 'v') {
			space.setRight(board[space.getRow()][space.getColumn() + 1]);
			BoardSpace tempSpace = space.getRight();
			tempSpace.setLeft(board[space.getRow()][space.getColumn()]);

			space = board[space.getRow() + 1][space.getColumn()];
			space.setRight(board[space.getRow()][space.getColumn() + 1]);
			tempSpace = space.getRight();
			tempSpace.setLeft(board[space.getRow()][space.getColumn()]);

		}
	}

	/**
	 * Should be passed a legal move will change the board.
	 * 
	 * @param move
	 *            the legal move
	 */
	public void makeMove(String move) {
		move = move.replace(" ", "");
		move = move.toLowerCase();
		if (!validMoveFormat(move)) {
			throw new IllegalArgumentException(
					"move Had an invalid format was:"
							+ move
							+ "\nShould be <id 1-4><letter a-i><number 1-9> and if it is a wall move should be followed by <v or h>");
		}
		if (move.length() == 3) {
			makePawnMove(move);
		}
		if (move.length() == 4) {
			makeWallMove(move);
		}

	}

	/**
	 * Should be passed a legal wall placement, will change the board. Sets the
	 * links between gameboard boardspaces where the wall will be placed to
	 * null.
	 * 
	 * @param move
	 *            the legal move
	 */
	private void makeWallMove(String move) {
		players[move.charAt(0) - '1'].removeWall();
		char orientation = move.charAt(3);
		int row = move.charAt(2) - '1';
		int column = move.charAt(1) - 'a';
		BoardSpace space = board[row][column];
		if (orientation == 'h') {
			BoardSpace tempspace = space.getDown();
			space.setDown(null);
			tempspace.setUp(null);
			space = board[space.getRow()][space.getColumn() + 1];
			tempspace = space.getDown();
			space.setDown(null);
			tempspace.setUp(null);
		} else if (orientation == 'v') {
			BoardSpace tempSpace = space.getRight();
			space.setRight(null);
			tempSpace.setLeft(null);
			space = board[space.getRow() + 1][space.getColumn()];
			tempSpace = space.getRight();
			space.setRight(null);
			tempSpace.setLeft(null);

		}

	}

	/**
	 * Moves the pawn specified in the move. To the place specified in the move.
	 * Should be in the format <id 1-4><column a-i><row 1-9>.
	 * 
	 * @param move
	 */
	private void makePawnMove(String move) {
		Player player = players[move.charAt(0) - '1'];
		int moveColumn = move.charAt(1) - 'a';
		int moveRow = move.charAt(2) - '1';
		board[player.getRow()][player.getColumn()].setHasPlayer(false);
		player.setRow(moveRow);
		player.setColumn(moveColumn);
		board[moveRow][moveColumn].setHasPlayer(true);
	}

	/**
	 * Checks whether or not a move had a valid format.
	 * 
	 * @param move
	 *            The move to check.
	 * @return Whether the move has a legal format.
	 */
	private boolean validMoveFormat(String move) {
		if (move.length() < 3 || move.length() > 4) {
			return false;
		}
		if (move.charAt(0) < '1' || move.charAt(0) > '4') {
			return false;
		}
		if (move.charAt(1) < 'a' || move.charAt(1) > 'i') {
			return false;
		}
		if (move.charAt(2) < '1' || move.charAt(2) > '9') {
			return false;
		}
		if (move.length() == 4) {
			if (move.charAt(3) != 'v' && move.charAt(3) != 'h') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param id
	 *            the id of the player to be kicked will remove the player from
	 *            the board state (1-4).
	 */
	public void kickPlayer(int id) {
		int index = id - 1;
		Player player = players[index];
		board[player.getRow()][player.getColumn()].setHasPlayer(false);
		players[index] = null;
	}

	/**
	 * Weather or not there are paths for all players in the game.
	 * 
	 * @return
	 */
	public boolean arePaths() {
		boolean paths = true;
		if (players[0] != null) {
			paths = paths && !shortestPath(1).equals("");
		}
		if (players[1] != null) {
			paths = paths && !shortestPath(2).equals("");
		}
		if (players[2] != null) {
			paths = paths && !shortestPath(3).equals("");
		}
		if (players[3] != null) {
			paths = paths && !shortestPath(4).equals("");
		}
		return paths;
	}

	/**
	 * 
	 * @param playerNum
	 *            the player number to generate the shortest path for (1-4)
	 * @return a string rep of the shortest path to the goal like so
	 *         <a-i><1-9>:<a-i><1-9>:...
	 */
	public String shortestPath(int playerNum) {
		if (playerNum == 1) {
			return ShortestPathToRow(1, 8);
		}
		if (playerNum == 2) {
			return ShortestPathToRow(2, 0);
		}
		if (playerNum == 3) {
			return ShortestPathToColumn(3, 8);
		}
		if (playerNum == 4) {
			return ShortestPathToColumn(4, 0);
		}
		return "cat";

	}

	/**
	 * 
	 * @param playerNum
	 *            the player number to generate the shortest path for (1-4)
	 * @return a string rep of the shortest path to the goal like so
	 *         <a-i><1-9>:<a-i><1-9>:...
	 */
	public String ShortestPathToRow(int playerNum, int row) {
		List<BoardSpace> spaces = new ArrayList<BoardSpace>();
		for (int i = 0; i < WIDTH; i++) {
			spaces.add(board[row][i]);
		}
		return ShortestPath(playerNum, spaces);
	}

	/**
	 * 
	 * @param playerNum
	 *            the player number to generate the shortest path for (1-4)
	 * @return a string rep of the shortest path to the goal like so
	 *         <a-i><1-9>:<a-i><1-9>:...
	 */
	public String ShortestPathToColumn(int playerNum, int column) {
		List<BoardSpace> spaces = new ArrayList<BoardSpace>();
		for (int i = 0; i < HEIGHT; i++) {
			spaces.add(board[i][column]);
		}
		return ShortestPath(playerNum, spaces);
	}

	/**
	 * 
	 * @param playerNumber
	 *            the player number to generate the shortest path for (1-4)
	 * @param spaces
	 *            The spaces you are trying to find the shortest path for.
	 * @return a string rep of the shortest path to the goal like so
	 *         "<a-i><1-9>:<a-i><1-9>":.. will return "" if no path.
	 */
	private String ShortestPath(int playerNumber, List<BoardSpace> spaces) {
		int playerIndex = playerNumber - 1;
		BoardSpace[][] prevSpace = new BoardSpace[HEIGHT][WIDTH];
		List<BoardSpace> visted = new ArrayList<BoardSpace>();
		Queue<BoardSpace> toVist = new ArrayBlockingQueue<BoardSpace>(WIDTH
				* HEIGHT);
		BoardSpace startingSpace = board[players[playerIndex].getRow()][players[playerIndex]
				.getColumn()];
		BoardSpace space = startingSpace;
		BoardSpace space2 = startingSpace;
		Iterator<BoardSpace> adjSquares;
		toVist.add(space);
		while ((space = toVist.poll()) != null && !spaces.contains(space)) {
			if (!visted.contains(space)) {
				adjSquares = adjacentSquares(space).iterator();
				while (adjSquares.hasNext() && !spaces.contains(space2)) {
					space2 = adjSquares.next();
					if (!visted.contains(space2) && !toVist.contains(space2)) {
						toVist.add(space2);
						prevSpace[space2.getRow()][space2.getColumn()] = space;
					}
				}
				visted.add(space);

			}
		}
		String path = "";

		if (spaces.contains(space)) {
			path = space.toString();
			space = prevSpace[space.getRow()][space.getColumn()];
		}
		while (space != null && !(space.equals(startingSpace))) {
			path = space + ":" + path;
			space = prevSpace[space.getRow()][space.getColumn()];
		}
		return path;

	}

	/**
	 * A method to find the spaces next to (ie one move away) from a given space
	 * (including jumps).
	 * 
	 * @param space
	 *            to find the spaces adjacent to.
	 * @return the list of spaces that are adjacent (ie reachable by one move)
	 */
	public List<BoardSpace> adjacentSquares(BoardSpace space) {
		BoardSpace space2 = space;
		List<BoardSpace> adjSquares = new ArrayList<BoardSpace>();
		List<BoardSpace> exasutedSquares = new ArrayList<BoardSpace>();
		Queue<BoardSpace> unvistedPlayerSquares = new ArrayBlockingQueue<BoardSpace>(
				MAX_PLAYERS);
		unvistedPlayerSquares.add(space);

		while ((space = unvistedPlayerSquares.poll()) != null) {

			if (space.getUp() != null && !exasutedSquares.contains(space)
					&& !adjSquares.contains(space)) {
				if (space.getUp().getHasPlayer() && space.getUp() != space2) {
					unvistedPlayerSquares.add(space.getUp());
				} else if (space.getUp() != space2) {
					adjSquares.add(space.getUp());
				}
			}
			if (space.getRight() != null && !exasutedSquares.contains(space)
					&& !adjSquares.contains(space)) {
				if (space.getRight().getHasPlayer()
						&& space.getRight() != space2) {
					unvistedPlayerSquares.add(space.getRight());
				} else if (space.getRight() != space2) {
					adjSquares.add(space.getRight());
				}
			}
			if (space.getDown() != null && !exasutedSquares.contains(space)
					&& !adjSquares.contains(space)) {
				if (space.getDown().getHasPlayer() && space.getDown() != space2) {
					unvistedPlayerSquares.add(space.getDown());
				} else if (space.getDown() != space2) {
					adjSquares.add(space.getDown());
				}
			}
			if (space.getLeft() != null && !exasutedSquares.contains(space)
					&& !adjSquares.contains(space)) {
				if (space.getLeft().getHasPlayer()) {
					unvistedPlayerSquares.add(space.getLeft());
				} else if (space.getLeft() != space2) {
					adjSquares.add(space.getLeft());
				}
			}
			exasutedSquares.add(space);
		}
		return adjSquares;
	}

	/**
	 * @return the players
	 */
	public Player[] getPlayers() {
		return players;
	}
	/**
	 * Returns a list of all the legal moves for the id of the player passed in (1-4)
	 * @param id the id
	 * @return the List containing the stings of the legal moves. In the format (id move) example (1 a3h) or (1 b2).
	 */
	public List<String> getLegalMoves(int id){
		List<String> legalMoves = getLegalPawnMoves(id);
		legalMoves.addAll(getLegalWallMoves(id));
		return legalMoves;
	}
	
	/**
	 * Returns a list of all the legal pawn moves for the id of the player passed in (1-4)
	 * @param id the id
	 * @return the List containing the stings of the legal pawn moves. In the format (id move) example (1 a3).
	 */
	public List<String> getLegalPawnMoves(int id) {
		Player player = players[id-1];
		List<BoardSpace> legalSpaces = adjacentSquares(board[player.getRow()][player.getColumn()]);

		ArrayList<String> legalMoves = new ArrayList<String>();
		for(BoardSpace space:legalSpaces){
			legalMoves.add(id+" "+space.toString());
		}
		return legalMoves;
	}

	/**
	 * Returns a list of all the legal wall moves for the id of the player passed in (1-4)
	 * @param id the id
	 * @return the List containing the stings of the legal wall moves. In the format (id move) example (1 a3h).
	 */
	public List<String> getLegalWallMoves(int id){
		ArrayList<String> legalMoves = new ArrayList<String>(128);
		if(players[id-1].getWalls()>0){
			char height = HEIGHT+'1';
			char width = WIDTH+'a';
			for(char row = '1'; row < height; row++){
				for(char column = 'a'; column-'a' < width; column++){
					String movePrefix = Integer.toString(id) + " " + column + row;
					if(legalMove(movePrefix + "h")){
						legalMoves.add(movePrefix + "h");
					}
					if(legalMove(movePrefix + "v")){
						legalMoves.add(movePrefix + "v");
					}
					
				}
			}
		}
		return legalMoves;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		result = prime * result + Arrays.hashCode(players);
		result = prime * result + numPlayers;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameBoard other = (GameBoard) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		if (numPlayers != other.numPlayers)
			return false;
		if (!Arrays.equals(players, other.players))
			return false;
		return true;
	}

}