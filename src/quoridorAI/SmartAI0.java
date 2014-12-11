package quoridorAI;

public class SmartAI0 extends AI {

	private int moveNumber;
	
	public SmartAI0(int id, int numPlayers) {
		super(id, numPlayers);
		this.moveNumber = 0;
	}
	
	public String think(){
		MoveTree tree;
		if(moveNumber == 0)
			tree = new MoveTree(gb, gb.numPlayers);
		
		return "e7h";
	}
}
