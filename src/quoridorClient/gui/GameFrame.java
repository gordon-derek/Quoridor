package quoridorClient.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import quoridorGameLogic.GameBoard;

/**
 * 
 * This class extends JPanel to create a customized JPanel
 * 
 */

public class GameFrame extends JPanel {

	// Added serialVersionUID to suppress warning.
	// Because JPanel is Serializable, this class needs a version defined
	private static final long serialVersionUID = 1L;

	// 2D array of walls (empty space where a part of a wall can go)
	// hWalls are horizontal, vWalls are vertical
	// squareWalls are between hWalls and vWalls
	public static Wall[][] hWall = new Wall[8][9];
	public static Wall[][] vWall = new Wall[9][8];
	public static Wall[][] squareWall = new Wall[8][8];

	// 2D array of actual game spaces
	public static GameSpace[][] gameSpace = new GameSpace[9][9];

	// Colors used for Walls and GameSpaces
	public static final Color DARK_MAHOGANY = new Color(46, 3, 1);
	public static final Color MAHOGANY = new Color(115, 34, 30);
	public static final Color CREAM = new Color(247, 244, 198);
	public static final Color GREY = new Color(148, 177, 196);

	// Dimensions for each JButton: gameSpace, squareWall, vWall, hWall
	static final Dimension SQUARE_WALL_DIMENSION = new Dimension(8, 8);
	static final Dimension GAME_SPACE_DIMENSION = new Dimension(32, 32);
	static final Dimension V_WALL_DIMENSION = new Dimension(8, 32);
	static final Dimension H_WALL_DIMENSION = new Dimension(32, 8);

	// Get the instance of Game
	GameBoard theGame;

	/**
	 * This is the constructor that will set the layout to gridbag,
	 * initialize, create and add this gameframe to the 
	 * GUIGameWindow which extends JFrame
	 * 
	 * @param gameBoardBackEnd
	 * 		this is the gameboard data
	 */
	public GameFrame(GameBoard gameBoardBackEnd) {

		// Sets the gameFrame layout to a FlowLayout
		GridBagLayout GB = new GridBagLayout();
		setLayout(GB);

		createWalls();
		createSquareWall();
		createGameSpace();

		this.theGame = gameBoardBackEnd;
		this.addPlayersToBoard();
		this.setBackground(CREAM);

		addEverythingToTheGameFrame();

	}

	/**
	 * Adds gameSpaces, vWalls, hWalls, and squareWalls 
	 * to the gameFrame
	 */
	private void addEverythingToTheGameFrame() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		
		constraints.weightx = .1;
		constraints.weighty = .1;
		for (int i = 0; i < gameSpace.length; i++) {
			constraints.gridy = i * 2;
			for (int j = 0; j < gameSpace[i].length; j++) {
				constraints.gridx = j * 2;
				add(gameSpace[i][j], constraints);
			}
		}
		
		constraints.weightx = .05;
		constraints.weighty = .1;
		for (int i = 0; i < vWall.length; i++) {
			constraints.gridy = i * 2;
			for (int j = 0; j < vWall[i].length; j++) {
				constraints.gridx = j * 2 + 1;
				add(vWall[i][j], constraints);
			}
		}
		
		constraints.weightx = .1;
		constraints.weighty = .05;
		for (int i = 0; i < hWall.length; i++) {
			constraints.gridy = i * 2 + 1;
			for (int j = 0; j < hWall[i].length; j++) {
				constraints.gridx = j * 2;
				add(hWall[i][j], constraints);
			}
		}
		
		constraints.weightx = .05;
		constraints.weighty = .05;
		for (int i = 0; i < squareWall.length; i++) {
			constraints.gridy = i * 2 + 1;
			for (int j = 0; j < squareWall[i].length; j++) {
				constraints.gridx = j * 2 + 1;
				add(squareWall[i][j], constraints);
			}
		}

	}

	/**
	 * Instantiates, sets size and sets background color 
	 * of every gameSpace
	 */
	private void createGameSpace() {
		for (int i = 0; i < gameSpace.length; i++) {
			for (int j = 0; j < gameSpace[i].length; j++) {
				gameSpace[i][j] = new GameSpace("");
				gameSpace[i][j].setPreferredSize(GAME_SPACE_DIMENSION);
				gameSpace[i][j].setMinimumSize(GAME_SPACE_DIMENSION);
				gameSpace[i][j].setBackground(DARK_MAHOGANY);
			}
		}
	}

	/**
	 * Instantiates, sets size and sets background color of every 
	 * squareWall
	 */
	private void createSquareWall() {
		for (int i = 0; i < squareWall.length; i++) {
			for (int j = 0; j < squareWall[i].length; j++) {
				squareWall[i][j] = new SmallEmptySpace("");
				squareWall[i][j].setPreferredSize(SQUARE_WALL_DIMENSION);
				squareWall[i][j].setMinimumSize(SQUARE_WALL_DIMENSION);
				squareWall[i][j].setBackground(MAHOGANY);
			}
		}
	}

	/**
	 * Instantiates, sets size and sets background color of every 
	 * vWall and hWall
	 */
	private void createWalls() {
		// Creates horizontal walls
		for (int i = 0; i < hWall.length; i++) {
			for (int j = 0; j < hWall[i].length; j++) {
				hWall[i][j] = new Wall("");
				hWall[i][j].setPreferredSize(H_WALL_DIMENSION);
				hWall[i][j].setMinimumSize(H_WALL_DIMENSION);
				hWall[i][j].setBackground(MAHOGANY);
			}
		}
		// Creates vertical walls
		for (int i = 0; i < vWall.length; i++) {
			for (int j = 0; j < vWall[i].length; j++) {
				vWall[i][j] = new Wall("");
				vWall[i][j].setPreferredSize(V_WALL_DIMENSION);
				vWall[i][j].setMinimumSize(V_WALL_DIMENSION);
				vWall[i][j].setBackground(MAHOGANY);
			}
		}
	}

	/**
	 * Actually adds players to the game board
	 */
	public void addPlayersToBoard() {
		int row = 0;
		int col = 0;
		quoridorGameLogic.Player[] players = theGame.getPlayers();
		// Goes through the array of players found in class 'Game' to display
		// them on the board at their location (row and col), displaying their
		// identifying color
		for (int i = 0; i < players.length; i++) {
			if (players[i] != null) {
				row = players[i].getRow();
				col = players[i].getColumn();
				gameSpace[row][col].setBackground(players[i].getColor());
			}
		}
	}

	/**
	 * Removes player 'player' from board in order to be updated on the GUI board
	 * @param player
	 * 		Which player to remove
	 */
	public void removePlayerFromBoard(int player) {
		int row = 0;
		int col = 0;
		quoridorGameLogic.Player[] players = theGame.getPlayers();
		row = players[player].getRow();
		col = players[player].getColumn();
		gameSpace[row][col].setBackground(DARK_MAHOGANY);
	}

	/**
	 * Removes all players from GUI Board
	 * @param player
	 */
	// TODO remove the parameter because its never used
	public void removeAllPlayersFromBoard(int player) {
		int row = 0;
		int col = 0;
		quoridorGameLogic.Player[] players = theGame.getPlayers();
		for (int i = 0; i < players.length; i++) {
			row = players[i].getRow();
			col = players[i].getColumn();
			gameSpace[row][col].setBackground(DARK_MAHOGANY);
		}
	}

	/**
	 * Test method to see if any of the positions were null
	 */
	public void gameSpaceToString() {
		quoridorGameLogic.Player[] players = theGame.getPlayers();
		for (int i = 0; i < players.length; i++) {
			for (JButton[] row : gameSpace) {
				for (JButton space : row) {
					System.out.print(space + " ");
				}
				System.out.println();
			}
		}
	}

	/**
	 * Adds a wall to the gameframe
	 * 
	 * @param move
	 * 		string representation of the wall position to add
	 */
	public void addWall(String move) {
		int col = move.charAt(0) - 'a';
		int row = move.charAt(1) - '1';
		char orientation = move.charAt(2);
		squareWall[row][col].setBackground(GREY);
		squareWall[row][col].setIsWall(true);
		if (orientation == 'v') {
			vWall[row][col].setBackground(GREY);
			vWall[row][col].setIsWall(true);
			vWall[row + 1][col].setBackground(GREY);
			vWall[row + 1][col].setIsWall(true);
		} else if (orientation == 'h') {
			hWall[row][col].setBackground(GREY);
			hWall[row][col].setIsWall(true);
			hWall[row][col + 1].setBackground(GREY);
			hWall[row][col + 1].setIsWall(true);
		}

	}

}
