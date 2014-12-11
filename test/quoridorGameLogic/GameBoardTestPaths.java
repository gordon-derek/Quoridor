/**
 * 
 */
package quoridorGameLogic;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author ********
 *
 */
public class GameBoardTestPaths {

	@Test
	public void test_arePaths_WithValidPaths() {
		GameBoard gameboard = new GameBoard(2);
		assertTrue(gameboard.arePaths());
	}
	@Test
	public void test_ShortestPath_WithSimplePathDown() {
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("2a9");
		assertEquals("e2:e3:e4:e5:e6:e7:e8:e9",gameboard.shortestPath(1));
	}
	@Test
	public void test_ShortestPath_WithSimplePathDownButAJumpAtEnd() {
		GameBoard gameboard = new GameBoard(2);
		assertEquals("e2:e3:e4:e5:e6:e7:e8:f9",gameboard.shortestPath(1));
	}
	@Test
	public void test_shortestPath_WithSimplePathAcrossButAJumpAtEnd() {
		GameBoard gameboard = new GameBoard(4);
		assertEquals("b5:c5:d5:e5:f5:g5:h5:i4",gameboard.shortestPath(3));
	}
	@Test
	public void test_ShortestPath_WithComplexPath() {
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("1 e1h");
		assertEquals("d1:d2:d3:d4:d5:d6:d7:d8:d9",gameboard.shortestPath(1));
	}
	@Test
	public void test_ShortestPath_WithNoLegalPathsOnBoard() {
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("1e1v");
		gameboard.makeMove("1d1v");
		gameboard.makeMove("1e2h");
		assertEquals("",gameboard.shortestPath(1));
	}

}
