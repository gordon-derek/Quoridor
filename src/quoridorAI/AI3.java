package quoridorAI;

public class AI3 extends AI {
	
	public AI3(int id, int numPlayers){super(id, numPlayers);}

	// think, place walls in opposite players shortest path
	public String think() {
		String path = "";
		String move = "";
		int playerToBlock = -1;
		if (players[id-1].getWalls() != 0) {
			System.out.println("\nCALCULATING MOVE");
			for (int i = 1; i <= 4; i++) {
				if (path.equals("")||i != id && players[i - 1] != null
						&& path.length() > gb.shortestPath(i).length()) {
					playerToBlock = i - 1;
					path = gb.shortestPath(i);
				}
			}
			int i = 0;
			if(playerToBlock==0||playerToBlock==2){
				move = gb.getPlayers()[playerToBlock].toString();
				if (move.charAt(0) == path.split(":")[i].charAt(0))
					move += 'h';
				if (move.charAt(1) == path.split(":")[i].charAt(1))
					move += 'v';
			}
			// place wall in front
			
			while (!gb.legalMove(id + " " + move) && i+1 < path.split(":").length) {
				move = path.split(":")[i];
				if (move.charAt(0) == path.split(":")[i + 1].charAt(0))
					move += 'h';
				if (move.charAt(1) == path.split(":")[i + 1].charAt(1))
					move += 'v';
				i++;
			}
		}
		if(!gb.legalMove(id +" "+move)){
			path = gb.shortestPath(id);
			move = "";
			for (int i = 0; i < 2; i++) {
				move += path.charAt(i);
			}
		}				
		System.out.println("move:" + move+"\n");
		
		return move;
	}
}
