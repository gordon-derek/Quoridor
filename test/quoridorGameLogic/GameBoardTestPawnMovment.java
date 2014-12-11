/**
 * 
 */
package quoridorGameLogic;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ******
 *
 */
public class GameBoardTestPawnMovment {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLegalMoveWithALegalMoveDown() {
		GameBoard gameboard = new GameBoard(2);
		assertTrue("The Move was said to be illegal",gameboard.legalMove("1e2"));
	}
	@Test
	public void testLegalMoveWithALegalMoveLeft() {
		GameBoard gameboard = new GameBoard(2);
		assertTrue("The Move was said to be illegal",gameboard.legalMove("1d1"));
	}
	@Test
	public void testLegalMoveWithALegalMoveRight() {
		GameBoard gameboard = new GameBoard(2);
		assertTrue("The Move was said to be illegal",gameboard.legalMove("1f1"));
	}
	@Test
	public void testLegalMoveWithALegalMoveDownJump() {
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("2e2");
		assertTrue("The Move was said to be illegal",gameboard.legalMove("1e3"));
	}
	@Test
	public void testLegalMoveWithALegalMoveRightJump() {
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("2f1");
		assertTrue("The Move was said to be illegal",gameboard.legalMove("1g1"));
	}
	@Test
	public void testLegalMoveWithALegalMoveDownDoubleJump() {
		GameBoard gameboard = new GameBoard(4);
		gameboard.makeMove("2e2");
		gameboard.makeMove("3e3");
		assertTrue("The Move was said to be illegal",gameboard.legalMove("1e4"));
	}
	@Test
	public void testLegalMoveWithALegalMoveRightDoubleJump() {
		GameBoard gameboard = new GameBoard(4);
		gameboard.makeMove("2f1");
		gameboard.makeMove("3g1");
		assertTrue("The Move was said to be illegal",gameboard.legalMove("1h1"));
	}
	@Test
	public void testLegalMoveWithAIllegalMoveWithJumpWithoutAPawnToJump() {
		GameBoard gameboard = new GameBoard(2);
		assertFalse("The Move was said to be legal",gameboard.legalMove("1e3"));
	}
	@Test
	public void testLegalMoveWithAIllegalMoveDueToBoardEdge() {
		GameBoard gameboard = new GameBoard(2);
		assertFalse("The Move was said to be legal",gameboard.legalMove("1e0"));
	}
	@Test
	public void testLegalMoveWithAIllegalMoveDueToWall() {
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("1e1h");
		assertFalse("The Move was said to be legal",gameboard.legalMove("1e2"));
	}

}
