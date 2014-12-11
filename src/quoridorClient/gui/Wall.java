package quoridorClient.gui;

import javax.swing.JButton;

public class Wall extends JButton {

	public String name = "";
	private Boolean isWall = false;
	
	public Wall(String name) {
		this.name = name;
	}
	
	public Boolean getIsWall() {
		return isWall;
	}
	
	public void setIsWall(Boolean newIsWall) {
		isWall = newIsWall;
	}

}
