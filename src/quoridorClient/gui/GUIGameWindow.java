/**
 * 
 */
package quoridorClient.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;

import quoridorGameLogic.GameBoard;

/**
 * @author Cody Hamilton
 * 
 */
public class GUIGameWindow extends JFrame {
	public GameFrame gameFrame;
	public InfoPanel infoPanel;
	public Console consol;

	public static final Color CREAM = new Color(247, 244, 198);

	// Dimensions for each JButton: gameSpace, squareWall, vWall, hWall
	static final Dimension WINDOW_DIMENSION = new Dimension(550, 560);

	public GUIGameWindow(GameBoard gameBoardBackEnd) {
		gameFrame = new GameFrame(gameBoardBackEnd);
		infoPanel = new InfoPanel(gameBoardBackEnd.getPlayers());
		consol = new Console();
		this.getContentPane().add(gameFrame, BorderLayout.CENTER);
		this.getContentPane().add(infoPanel, BorderLayout.EAST);
		this.getContentPane().add(consol, BorderLayout.SOUTH);
		this.getContentPane().setBackground(CREAM);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setSize(WINDOW_DIMENSION);
		this.setResizable(true);
	}

	/**
	 * A method to get the message queue from the gui.
	 * 
	 * @return The message queue.
	 */
	public BlockingQueue<String> getMessageQueue() {
		return consol.getMessageQueue();
	}
}
