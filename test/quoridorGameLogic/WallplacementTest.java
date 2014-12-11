package quoridorGameLogic;
import static org.junit.Assert.*;
import quoridorGameLogic.GameBoard;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WallplacementTest {

	
	//Test placing a vertical wall anywhere on a empty board
	@Test
	public void testVerticalWallPlacementWithEmptyBoard(){
		GameBoard gameboard = new GameBoard(2);
		assertTrue("Vertical wall was not placed on the gameboard at b5", gameboard.legalMove("1b5v"));
		
	}
	//Test placing a vertical wall anywhere on a board with one wall
	@Test
	public void testVerticalWallPlacementWithAlmostEmptyBoard(){
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("1e8h");
		assertTrue("Vertical wall was not placed on the gameboard at b5", gameboard.legalMove("1b5v"));
		
	}
	
	//Test placking a horzontal wall anywhere on an empty board. 
	@Test
	public void testHorzWallPlacementWithEmptyBoard(){
		GameBoard gameboard = new GameBoard(2);
		assertTrue("Horizontal wall was not placed on the board at e3", gameboard.legalMove("1e3h"));
	}

	@Test
	public void testPlaceWallOffBottomOfTheBoard(){
		GameBoard gameboard = new GameBoard(2);
		assertFalse("Is a legal move and it shouldn't be.", gameboard.legalMove("1e9v"));
	}
	
	@Test
	public void testPlaceWallOffTopOfTheBoard(){
		GameBoard gameboard = new GameBoard(2);
		assertFalse("Is a legal move and it should not be", gameboard.legalMove("1e1v"));
	}
	
	@Test
	public void testPlaceWallOffRightOfBoard(){
		GameBoard gameboard = new GameBoard(2);
		assertFalse("Is a legal move and it should not be", gameboard.legalMove("1i3h"));
	}
	
	@Test
	public void testPlaceWallOffLeftOfBoard(){
		GameBoard gameboard = new GameBoard(2);
		assertFalse("Is a legal move and it should not be", gameboard.legalMove("1a4h"));
	}
	
	@Test
	public void testPlaceHorizontalWallOnOtherWall(){
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("1e3v");
		assertFalse("Wall placement is legal move.", gameboard.legalMove("1e3h"));
		
	}
	
	@Test
	public void testPlaceVerticalWallOnOtherWall(){
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("1e3h");
		assertFalse("Wall placement is legal move.", gameboard.legalMove("1e3v"));
	}

	@Test
	public void testBlockGameBoard(){
		GameBoard gameboard = new GameBoard(2);
		gameboard.makeMove("1b2h");
		gameboard.makeMove("1c3v");
		gameboard.makeMove("1d4h");
		gameboard.makeMove("1e5v");
		gameboard.makeMove("1f6h");
		gameboard.makeMove("1g7h");
		assertFalse("Wall placement is legal move.", gameboard.legalMove("1h8h"));		
		
	}
	
	
}
