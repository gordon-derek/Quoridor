package quoridorAI;

import java.util.ArrayList;

import quoridorAI.MoveTree.Node;
import quoridorGameLogic.*;

/**
 * 
 * @author Derek Gordon
 *
 */
public class MoveTree {
	private int id;
	private Node root;
	private final double ALPHA = .05;
	
	/**
	 * 
	 * 
	 */
	class Node{
		double value;
		GameBoard gb;
		ArrayList<Node> children;
		
		Node(){
			value = 0;
			gb = null;
			children = new ArrayList<Node>();
		}
	}
	
	/**
	 * 
	 * @param numPlayers
	 * @param id
	 */
	MoveTree(int numPlayers, int id){
		this.id = id;
		this.root = new Node();
		buildTree(numPlayers);
	}
	
	/**
	 * 
	 * @param gb
	 * @param id
	 */
	MoveTree(GameBoard gb, int id){
		this.id = id;
		this.root = new Node();
		buildTree(gb);
	}
	
	/**
	 * taking the numplayers, creates a root to the tree
	 * then continues building a tree of all possible moves
	 * starting on move 3.
	 * 
	 * @param numP
	 * 		number of players playing the game.
	 */
	private void buildTree(int numP){
		root.gb = new GameBoard(numP);
	}
	
	private void buildTree(GameBoard gb){
		root.gb = gb;
		root.value = 0;
		getPossibleMoves(root, id);		
	}
	
	/**
	 * Gets all possible moves from the gamestate of
	 * node n.  For each 
	 * 
	 * @param n
	 * 		Parent Node of next group of children.
	 * @param mId
	 * 		id of current player to move
	 * @return
	 * 		returns the win value of this path.
	 */
	private double getPossibleMoves(Node n, int mId){
		//get player by ID
		Player[] p = n.gb.getPlayers();
		int row = 0;
		char col = ' ';
		String move = "";
		int sum = 0;
		
		for(int i = 0; i < 4; i++){
			switch(i){
			case 0: //move north
				col = colToChar(p[mId-1].getColumn());
				row = p[mId-1].getRow();
				row--;
				move = mId + " " + Character.toString(col) + Integer.toString(row+1);
				if(n.gb.legalMove(move)){
					Node tempN = n;
					tempN.children = new ArrayList<Node>();
					tempN.gb.makeMove(move);
					sum += getPossibleMoves(tempN, (mId+1) % 2 + 1);
				}
				break;
			case 1: //east
				col = colToChar(p[mId-1].getColumn());
				row = p[mId-1].getRow();
				col++;
				move = mId + " " + Character.toString(col) + Integer.toString(row+1);
				if(n.gb.legalMove(move)){
					//test if this move is a winner
					if(isWinner(mId-1, p[mId -1]))
						if(mId == id)//if its us or other player
							sum += 1;
						else
							sum -= 1;
					else{
						Node tempN = n;
						tempN.children = new ArrayList<Node>();
						tempN.gb.makeMove(move);
						sum += getPossibleMoves(tempN, (mId+1) % 2 + 1);
					}
				}
				break;
			case 2: //south
				col = colToChar(p[mId-1].getColumn());
				row = p[mId-1].getRow();
				row++;
				move = mId + " " + Character.toString(col) + Integer.toString(row+1);
				if(n.gb.legalMove(move)){
					//test if this move is a winner
					if(isWinner(mId-1, p[mId -1]))
						if(mId == id)//if its us or other player
							sum += 1;
						else
							sum -= 1;
					else{
						Node tempN = n;
						tempN.children = new ArrayList<Node>();
						tempN.gb.makeMove(move);
						sum += getPossibleMoves(tempN, (mId+1) % 2 + 1);
					}
				}
				break;
			case 3: //west
				col = colToChar(p[mId-1].getColumn());
				row = p[mId-1].getRow();
				col--;
				move = mId + " " + Character.toString(col) + Integer.toString(row+1);
				if(n.gb.legalMove(move)){
					//test if this move is a winner
					if(isWinner(mId-1, p[mId -1]))
						if(mId == id)//if its us or other player
							sum += 1;
						else
							sum -= 1;
					else{
						Node tempN = n;
						tempN.children = new ArrayList<Node>();
						tempN.gb.makeMove(move);
						sum += getPossibleMoves(tempN, (mId+1) % 2 + 1);
					}
				}
				break;
			}
		}
		return sum - ALPHA;
	}
	
	
	
	/**
	 * takes in the integer representation of row and
	 * converts it to its letter representation for moving
	 * on the gameboard.
	 * @param row
	 * 		integer representation of row on gameboard
	 * @return
	 */
	private char colToChar(int col){
		return (char) (col + 'a');
	}
	
	/**
	 * Checks to see if the given id is currently in
	 * the endzone
	 * @param id
	 * 		id of the player
	 * @param p
	 * 		player object to get location
	 * @return
	 */
	private boolean isWinner(int id, Player p){
		switch(id-1){
		case 0:
			if(p.getRow() == 8)
				return true;
			break;
		case 1:
			if(p.getRow() == 0)
				return true;
			break;
		}
		return false;
	}
}


