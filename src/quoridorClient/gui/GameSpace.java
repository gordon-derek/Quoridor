package quoridorClient.gui;

import javax.swing.JButton;

public class GameSpace extends JButton {
	
	// collection of buttons surrounding the particular GameSpace (in order to 
	// make the walls visible)
	public static String name = "";
	public static Wall north, south;
	public static Wall east, west;
	public static SmallEmptySpace ne, nw, se, sw;

	// constructor takes String as a name
	public GameSpace(String name) {
		this.name = name;
		//determinePosition();
		
	}
	
	// name setter
	public void setName(String newName) {
		name = newName;
	}
	
	// name getter
	public String getName() {
		return name;
	}
	
	// have not yet determined where/when to implement this code
	public void determinePosition(){
		
	}
	

}
