/*
 * Project: Quoridor
 * Author: Derek Gordon
 * Date: 3/11/14
 * 
 * Notice: Many of this code is not the authors, this is a compilation of a previous
 * class file in which all team members were a part of designing.
 * 
 * Purpose: Allows the instantiation of a game object for a client to
 * use to play the quoridor game.
 */

package quoridorClient;

import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;

import quoridorClient.gui.GUIGameWindow;
import quoridorClient.gui.GameFrame;
import quoridorGameLogic.GameBoard;

public class Game {
	public static final int[] PLAY_ORDER = { 0, 3, 1, 2 };
	private Player[] AIPlayers;
	private GameBoard gameBoardBackEnd;
	private GameFrame gameBoardGUIFrame;
	private GUIGameWindow gameWindow;
	private BlockingQueue<String> messageQueue;

	/**
	 * creates a game object that will be used to communicate with the players
	 * to run the game.
	 * 
	 * @param ipAddr0
	 *            ipaddress:port for player 1
	 * @param ipAddr1
	 *            ipaddress:port for player 2
	 * @param ipAddr2
	 *            ipaddress:port for player 3
	 * @param ipAddr3
	 *            ipaddress:port for player 4
	 * @param numPlayers
	 *            number of players playing the game
	 */
	public Game(String ipAddr0, String ipAddr1, String ipAddr2, String ipAddr3,
			int numPlayers) {
		gameBoardBackEnd = new GameBoard(numPlayers);

		try {
			// initialize quoridor players
			AIPlayers = new Player[4];
			AIPlayers[0] = new ServerPlayer(ipAddr0);
			AIPlayers[1] = new ServerPlayer(ipAddr1);
			if (numPlayers == 4) {
				AIPlayers[2] = new ServerPlayer(ipAddr2);
				AIPlayers[3] = new ServerPlayer(ipAddr3);
			}

			System.out.println(ipAddr0 + "   " + ipAddr1);
			
			// initialize game
			for (int i = 0; i < numPlayers; i++) 
				AIPlayers[i].setID(AIPlayers[i].getMessage().replace("HELLO ", ""));
			System.err.println("Init Messages Received");

			displayNames(numPlayers);
			// Creates a instance of the gameframe which is a gui for the game.
			gameWindow = new GUIGameWindow(gameBoardBackEnd);
			gameBoardGUIFrame = gameWindow.gameFrame;
			messageQueue = gameWindow.getMessageQueue();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the display names for each server then print.
	 * 
	 * @param numPlayers
	 *            number of players in game
	 * @throws Exception
	 */
	private void displayNames(int numPlayers) throws Exception {
		String[] names = new String[4];
		quoridorGameLogic.Player[] backEndPlayers = gameBoardBackEnd
				.getPlayers();
		System.err.println("Sending Player ID's and waiting for Display Names");
		if (numPlayers == 2) {
			names[0] = AIPlayers[0].twoWayMessage("QUORIDOR 1 " + AIPlayers[1].getID());
			backEndPlayers[0].setName(names[0].replace("READY ", ""));
			names[1] = AIPlayers[1].twoWayMessage("QUORIDOR 2 " + AIPlayers[0].getID());
			backEndPlayers[1].setName(names[1].replace("READY ", ""));
		}
		if (numPlayers == 4) {
			names[0] = AIPlayers[0].twoWayMessage("QUORIDOR 1 " + AIPlayers[1].getID() + " " 
												+ AIPlayers[2].getID() + " " + AIPlayers[3].getID());
			backEndPlayers[0].setName(names[0].replace("READY ", ""));
			names[1] = AIPlayers[1].twoWayMessage("QUORIDOR 2 " + AIPlayers[0].getID() + " "
												+ AIPlayers[2].getID() + " " + AIPlayers[3].getID());
			backEndPlayers[1].setName(names[1].replace("READY ", ""));
			names[2] = AIPlayers[2].twoWayMessage("QUORIDOR 3 " + AIPlayers[0].getID() + " "
												+ AIPlayers[1].getID() + " " + AIPlayers[3].getID());
			backEndPlayers[2].setName(names[2].replace("READY ", ""));
			names[3] = AIPlayers[3].twoWayMessage("QUORIDOR 4 " + AIPlayers[0].getID() + " "
												+ AIPlayers[1].getID() + " " + AIPlayers[2].getID());
			backEndPlayers[3].setName(names[3].replace("READY ", ""));
		}
		System.err.println("Display Names Received");
		printNames(names, numPlayers);
	}

	/**
	 * Prints the Servers' Display names to the client
	 * 
	 * @param names
	 *            array of names to print.
	 * @param numPlayers
	 *            number of players playing the game.
	 */
	private void printNames(String[] names, int numPlayers) {
		// Set display names in client
		System.out.println(names[0]);
		System.out.println(names[1]);
		if (numPlayers == 4) {
			System.out.println(names[2]);
			System.out.println(names[3]);
		}
	}

	/**
	 * Plays one round of the game.
	 * 
	 * @return returns whether someone was announce a winner in this round.
	 * @throws Exception
	 */
	public boolean playRound() throws Exception {
		// int currentPlayer;
		String move;
		int winner = -1;

		// ask every player for a move
		for (int currentPlayer : PLAY_ORDER) {
			if (AIPlayers[currentPlayer] != null && winner == -1) {
				try {
					move = AIPlayers[currentPlayer].twoWayMessage("MOVE?");
					System.out.print("PASSED STRING:" + move + "\t\t");
					move = move.replace("MOVE ", (currentPlayer + 1) + " ");
					move = move.toLowerCase();
					// if legal, make the legal move. kick otherwise
					if (gameBoardBackEnd.legalMove(move))
						makeMove(move, currentPlayer);
					else
						kick(currentPlayer);

				} catch (NoSuchElementException e) {
					System.out
							.print("Server connection closed kicking player.   ");
					kick(currentPlayer);
				}
				winner = getWinner();
			}
		}
		return gameWon(winner);
	}

	/**
	 * Checks if a winner was determined this round. Acknowledges winner if it
	 * has.
	 * 
	 * @param winner
	 *            ID of the winner, -1 if no winner
	 * @return if winner, returns true, else false
	 */
	private boolean gameWon(int winner) {
		if (winner != -1) {
			printWinner(winner);
			return true;
		}
		return false;
	}

	/**
	 * Sends Winner Message to Server
	 * 
	 * @param winner
	 *            ID of the winner to be acknowledged
	 */
	private void printWinner(int winner) {
		System.out.println("\n\n");
		System.out.println("The Game has ended.\n");
		System.out.println("Player number:" + winner + " has won.");
		System.out.println("Winners name:"
				+ gameBoardBackEnd.getPlayers()[winner - 1].getName());
		for (int j = 0; j < AIPlayers.length; j++)
			if (AIPlayers[j] != null)
				AIPlayers[j].oneWayMessage("WINNER " + winner);
	}

	/**
	 * 
	 * @param players
	 *            a array of the Player interface.
	 * @param playerToKick
	 *            Index of the player to kick.
	 */
	private void kick(int playerToKick) {
		for (int j = 0; j < AIPlayers.length; j++)
			if (AIPlayers[j] != null)
				AIPlayers[j].oneWayMessage("REMOVED " + (playerToKick + 1));

		System.out.println("REMOVED " + (playerToKick + 1));
		gameBoardGUIFrame.removePlayerFromBoard(playerToKick);
		gameBoardBackEnd.kickPlayer(playerToKick + 1);
		AIPlayers[playerToKick] = null;
	}

	/**
	 * Makes for the move for the currentPlayer and updates the graphics and
	 * logic of the game.
	 * 
	 * @param move
	 *            The string of the move.
	 * @param players
	 *            a array of the Player interface.
	 * @param currentPlayer
	 *            The index of the current player.
	 */
	private void makeMove(String move, int currentPlayer) {
		if (move.length() == 4) {
			gameBoardGUIFrame.removePlayerFromBoard(currentPlayer);
			gameBoardBackEnd.makeMove(move);
			gameBoardGUIFrame.addPlayersToBoard();
		} else if (move.length() == 5) {
			gameBoardBackEnd.makeMove(move);
			gameBoardGUIFrame.addWall(move.replace((currentPlayer + 1) + " ",
					""));
			gameWindow.infoPanel.updateWallInfo(currentPlayer + 1);
		}

		for (int j = 0; j < AIPlayers.length; j++)
			if (AIPlayers[j] != null)
				AIPlayers[j].oneWayMessage("MOVED " + move);

		System.out.println("MOVED " + move);

	}

	/**
	 * Gets the winner number for the current game. returns -1 if there is no
	 * winner.
	 * 
	 * @return the number of the winner (1-4) or -1;
	 */
	public int getWinner() {
		int winner = -1;
		int playerCount = 0;
		quoridorGameLogic.Player[] glPlayers = gameBoardBackEnd.getPlayers();
		if (glPlayers[0] != null && glPlayers[0].getRow() == 8) {
			winner = 1;
		}
		if (glPlayers[1] != null && glPlayers[1].getRow() == 0) {
			winner = 2;
		}
		if (glPlayers[2] != null && glPlayers[2].getColumn() == 8) {
			winner = 3;
		}
		if (glPlayers[3] != null && glPlayers[3].getColumn() == 0) {
			winner = 4;
		}
		for (int i = 0; i < glPlayers.length; i++) {
			if (glPlayers[i] != null) {
				playerCount++;
			}
		}
		if (playerCount == 1) {
			for (int i = 0; i < glPlayers.length; i++) {
				if (glPlayers[i] != null) {
					winner = i + 1;
				}
			}
		}
		return winner;
	}
}
