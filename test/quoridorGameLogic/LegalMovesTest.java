package quoridorGameLogic;

import static org.junit.Assert.*;

import org.junit.Test;

public class LegalMovesTest {

	@Test
	public void testGetLegalMoves() {
		String expetedMoves = "[1 f1, 1 e2, 1 d1, 1 a1h, 1 a1v, 1 b1h, 1 b1v, 1 c1h, 1 c1v, 1 d1h, 1 d1v, 1 e1h, 1 e1v, 1 f1h, 1 f1v, 1 g1h, 1 g1v, 1 h1h, 1 h1v, 1 a2h, 1 a2v, 1 b2h, 1 b2v, 1 c2h, 1 c2v, 1 d2h, 1 d2v, 1 e2h, 1 e2v, 1 f2h, 1 f2v, 1 g2h, 1 g2v, 1 h2h, 1 h2v, 1 a3h, 1 a3v, 1 b3h, 1 b3v, 1 c3h, 1 c3v, 1 d3h, 1 d3v, 1 e3h, 1 e3v, 1 f3h, 1 f3v, 1 g3h, 1 g3v, 1 h3h, 1 h3v, 1 a4h, 1 a4v, 1 b4h, 1 b4v, 1 c4h, 1 c4v, 1 d4h, 1 d4v, 1 e4h, 1 e4v, 1 f4h, 1 f4v, 1 g4h, 1 g4v, 1 h4h, 1 h4v, 1 a5h, 1 a5v, 1 b5h, 1 b5v, 1 c5h, 1 c5v, 1 d5h, 1 d5v, 1 e5h, 1 e5v, 1 f5h, 1 f5v, 1 g5h, 1 g5v, 1 h5h, 1 h5v, 1 a6h, 1 a6v, 1 b6h, 1 b6v, 1 c6h, 1 c6v, 1 d6h, 1 d6v, 1 e6h, 1 e6v, 1 f6h, 1 f6v, 1 g6h, 1 g6v, 1 h6h, 1 h6v, 1 a7h, 1 a7v, 1 b7h, 1 b7v, 1 c7h, 1 c7v, 1 d7h, 1 d7v, 1 e7h, 1 e7v, 1 f7h, 1 f7v, 1 g7h, 1 g7v, 1 h7h, 1 h7v, 1 a8h, 1 a8v, 1 b8h, 1 b8v, 1 c8h, 1 c8v, 1 d8h, 1 d8v, 1 e8h, 1 e8v, 1 f8h, 1 f8v, 1 g8h, 1 g8v, 1 h8h, 1 h8v]";
		GameBoard game = new GameBoard(4);
		assertEquals(expetedMoves,game.getLegalMoves(1).toString());
	}

	@Test
	public void testGetLegalPawnMoves() {
		String expetedMoves = "[1 f1, 1 e2, 1 d1]";
		GameBoard game = new GameBoard(4);
		assertEquals(expetedMoves, game.getLegalPawnMoves(1).toString());
	}

	@Test
	public void testGetLegalWallMoves() {
		String expetedMoves = "[1 a1h, 1 a1v, 1 b1h, 1 b1v, 1 c1h, 1 c1v, 1 d1h, 1 d1v, 1 e1h, 1 e1v, 1 f1h, 1 f1v, 1 g1h, 1 g1v, 1 h1h, 1 h1v, 1 a2h, 1 a2v, 1 b2h, 1 b2v, 1 c2h, 1 c2v, 1 d2h, 1 d2v, 1 e2h, 1 e2v, 1 f2h, 1 f2v, 1 g2h, 1 g2v, 1 h2h, 1 h2v, 1 a3h, 1 a3v, 1 b3h, 1 b3v, 1 c3h, 1 c3v, 1 d3h, 1 d3v, 1 e3h, 1 e3v, 1 f3h, 1 f3v, 1 g3h, 1 g3v, 1 h3h, 1 h3v, 1 a4h, 1 a4v, 1 b4h, 1 b4v, 1 c4h, 1 c4v, 1 d4h, 1 d4v, 1 e4h, 1 e4v, 1 f4h, 1 f4v, 1 g4h, 1 g4v, 1 h4h, 1 h4v, 1 a5h, 1 a5v, 1 b5h, 1 b5v, 1 c5h, 1 c5v, 1 d5h, 1 d5v, 1 e5h, 1 e5v, 1 f5h, 1 f5v, 1 g5h, 1 g5v, 1 h5h, 1 h5v, 1 a6h, 1 a6v, 1 b6h, 1 b6v, 1 c6h, 1 c6v, 1 d6h, 1 d6v, 1 e6h, 1 e6v, 1 f6h, 1 f6v, 1 g6h, 1 g6v, 1 h6h, 1 h6v, 1 a7h, 1 a7v, 1 b7h, 1 b7v, 1 c7h, 1 c7v, 1 d7h, 1 d7v, 1 e7h, 1 e7v, 1 f7h, 1 f7v, 1 g7h, 1 g7v, 1 h7h, 1 h7v, 1 a8h, 1 a8v, 1 b8h, 1 b8v, 1 c8h, 1 c8v, 1 d8h, 1 d8v, 1 e8h, 1 e8v, 1 f8h, 1 f8v, 1 g8h, 1 g8v, 1 h8h, 1 h8v]";
		GameBoard game = new GameBoard(4);
		System.out.println(game.getLegalWallMoves(1));
		assertEquals(expetedMoves, game.getLegalWallMoves(1).toString());
	}

}
