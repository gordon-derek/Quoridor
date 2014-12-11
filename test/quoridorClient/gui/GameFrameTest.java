package quoridorClient.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import quoridorClient.gui.GameFrame;
import quoridorGameLogic.GameBoard;

public class GameFrameTest {

	@Test
	public void testCreation() {
		GameFrame gameBoard = new GameFrame(new GameBoard(2));
		assertNotNull(gameBoard);
	}


}
