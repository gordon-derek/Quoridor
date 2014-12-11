package quoridorAI;

import java.util.List;
import java.util.ArrayList;

import quoridorGameLogic.GameBoard;
import quoridorGameLogic.Player;

/**
 * 
 * @author Derek Gordon
 * 
 *         Implementation of a MinMax algorithm for calculating a given value on
 *         a possible move. Once the values modify the min and max enough to
 *         where the minimum value is actually less than the maximum value, we
 *         have found a viable move.
 */
public class MinMaxAI extends AI {

	private List<String> realMoves;
	private final int DESIRED_DEPTH = 5;
	private final int NUM_BEST = 10;

	public MinMaxAI(int id, int numPlayers) {
		super(id, numPlayers);
		realMoves = new ArrayList<String>();
	}

	class MovePair {
		int value;
		String move;

		public MovePair(int v, String m) {
			value = v;
			move = m;
		}
	}

	/**
	 * starts the minmax implementation to calculate a good move.
	 */
	public String think() {
		MovePair move;
			
		move = maxValue(realMoves, 0, (int) Double.NEGATIVE_INFINITY,
				(int) Double.POSITIVE_INFINITY, id);
		move.move = move.move.replace(Integer.toString(id) + " ", "");
		System.out.println(move.move);
		return move.move;
	}

	/**
	 * Adds the move to the movelist for future use in calculating moves.
	 * 
	 * @param move
	 *            The move that was legalized and sent to the server to be
	 *            counted.
	 */
	@Override
	public void updateGameboard(String move) {
		realMoves.add(move);
	}

	/**
	 * initially set to lowest possible value, increases as moves get a better
	 * calculated value.
	 * 
	 * @param depth
	 *            current depth in the tree.(will only go to a desired depth)
	 * @param alpha
	 *            the high value(initially very low)
	 * @param beta
	 *            the low value(initially very high)
	 * @return returns a desireable move
	 */
	private MovePair maxValue(List<String> moves, int depth, int alpha,
			int beta, int pId) {
		GameBoard temp = createTempGame(moves);
		List<String> mList;
		String bestMove;
		int value = (int) Double.NEGATIVE_INFINITY;
		if (isGoal(moves)){
			List<String> m = temp.getLegalMoves(pId);
			return new MovePair((int)Double.POSITIVE_INFINITY, m.get(0));
		}
		if (depth == DESIRED_DEPTH) {
			List<String> m = temp.getLegalMoves(pId);
			return new MovePair(calculate(moves), m.get(0));
		}
		mList = temp.getLegalMoves(pId);
		mList = sortMoves(mList, moves, pId);
		bestMove = mList.get(0);
		
		for (int i = 0; i < mList.size(); i++) {
			GameBoard t2 = createTempGame(moves);
			t2.makeMove(mList.get(i));
			List<String> tMoves = new ArrayList<String>(moves);
			tMoves.add(mList.get(i));
			if(isGoal(tMoves))
				return new MovePair((int)Double.POSITIVE_INFINITY, mList.get(i));
			value = minValue(tMoves, depth + 1, alpha, beta, (pId % 2) + 1);
			if (value > alpha) {
				alpha = value;
				bestMove = mList.get(i);
			}

			if (alpha >= beta)
				return new MovePair(alpha, bestMove);
		}

		return new MovePair(alpha, bestMove);
	}

	/**
	 * 
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @return
	 */
	private int minValue(List<String> moves, int depth, int alpha, int beta,
			int pId) {
		MovePair mp;
		if(isGoal(moves))
			return (int)Double.NEGATIVE_INFINITY;
		if (depth == DESIRED_DEPTH)
			return calculate(moves);

		GameBoard temp = createTempGame(moves);
		List<String> mList = temp.getLegalMoves(pId);
		mList = sortMoves(mList, moves, pId);

		for (int i = 0; i < mList.size(); i++) {
			List<String> t2Moves = new ArrayList<String>(moves);
			GameBoard t2 = createTempGame(moves);
			t2.makeMove(mList.get(i));
			t2Moves.add(mList.get(i));
			mp = maxValue(t2Moves, depth + 1, alpha, beta, (pId % 2) + 1);
			beta = Math.min(mp.value, beta);
			if (alpha >= beta)
				return beta;
		}

		return beta;
	}
	
	/**
	 * To improve speed, creates an ordered list of the {NUM_BEST}
	 * best moves for the current gamestate. 
	 * @param mList
	 * 	List of the possible moves that can be performed.
	 * @param moves
	 *  List of current moves in the tree(Both real moves and "try" moves)
	 * @return
	 *  List of the top {NUM_BEST} moves
	 */
	private List<String> sortMoves(List<String> mList, List<String> moves, int pId){
		MovePair[] list = new MovePair[NUM_BEST];
		//initialize the list
		for(int i = 0; i < list.length; i++)
			list[i] = new MovePair((int)Double.NEGATIVE_INFINITY, "");
		
		for(String m:mList){
			//make the move on temporary game.
			GameBoard t = createTempGame(moves);
			List<String>tList = new ArrayList<String>(moves);
			tList.add(m);
			t.makeMove(m);
			//calculate game state
			MovePair mp;
			if(isGoal(tList) && pId == id)
				mp = new MovePair((int)Double.POSITIVE_INFINITY, m);
			else
				mp = new MovePair(calculate(tList), m);
			
			for(int i = 0; i < list.length; i++){
				if(mp.value > list[i].value){
					for(int j = list.length - 1; j > i; j--)
						list[j] = list[j-1];
					list[i] = mp;
					break;
				}
			}	
		}
		List<String> result = new ArrayList<String>(NUM_BEST);
		for(int i = 0; i < list.length; i++){
			if(list[i].move == "")
				break;
			result.add(list[i].move);
		}
		
		return result;
	}

	/**
	 * Calculates a value which represents our current status on the gameboard.
	 * How well the game is looking in our favor.
	 * 
	 * @param tmoves
	 *	list of moves done in the game(both real and possibly some
	 *  temporary "try" moves)
	 * @return
	 */
	private int calculate(List<String> tmoves) {
		int value = 0;
		GameBoard temp = createTempGame(tmoves);
		//System.err.println(temp.shortestPath(id));

		if (id == 1)
			value = 1
					* (temp.shortestPath(2).length())
					- (temp.shortestPath(1).length())
					+ 1
					* (temp.getPlayers()[0].getWalls() - temp.getPlayers()[1]
							.getWalls());
		else
			value = 1
					* (temp.shortestPath(1).length())
					- (temp.shortestPath(2).length())
					+ 1
					* (temp.getPlayers()[1].getWalls() - temp.getPlayers()[0]
							.getWalls());

		return value;
	}

	/**
	 * creates a temperary game based on the moves in moveList
	 * 
	 * @param moveList
	 *            list of moves done in the game(both real and possibly some
	 *            temperary "try" moves)
	 * @return
	 */
	private GameBoard createTempGame(List<String> moveList) {
		GameBoard game = new GameBoard(2);
		for (int i = 0; i < moveList.size(); i++)
			game.makeMove(moveList.get(i));

		return game;
	}

	/**
	 * 
	 * @param moveList
	 * @return
	 */
	private boolean isGoal(List<String> moveList) {
		GameBoard temp = createTempGame(moveList);
		Player me = temp.getPlayers()[id - 1];

		if (id == 1 && me.getRow() == 8)
			return true;
		else if (id == 2 && me.getRow() == 0)
			return true;

		return false;
	}
}
