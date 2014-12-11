/**
 * 
 */
package quoridorClient.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;

/**
 * @author Cody Hamilton
 * 
 */
public class InfoPanel extends JPanel {
	JTextArea wallCounts[];
	quoridorGameLogic.Player[] players;
	public InfoPanel(quoridorGameLogic.Player[] players) {
		this.players=players;
		wallCounts= new JTextArea[players.length];
		//init the constraints.
		GridBagConstraints nameConstraints = new GridBagConstraints();
		GridBagConstraints buttonConstraints = new GridBagConstraints();
		
		//Sets the fill.
		nameConstraints.fill = GridBagConstraints.NONE;
		buttonConstraints.fill = GridBagConstraints.NONE;
		
		//Set the anchor.
		buttonConstraints.anchor = GridBagConstraints.CENTER;
		
		//Set the x grid for the constraints.
		nameConstraints.gridx = 0;
		buttonConstraints.gridx = 1;
		
		//Set the y weight for the constraints.
		nameConstraints.weighty = 0.1;
		buttonConstraints.weighty = 0.1;
		
		//Set the number of of columns the button occupies.
		buttonConstraints.gridheight = 2;
		
		//Set up the insets to the constraints.
		buttonConstraints.insets = new Insets(10, 10, 10, 10);
		nameConstraints.insets = new Insets(2, 10, 2, 10);
		
		//Creates and applies a GridBagLayout.
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		
		//Set background color.
		this.setBackground(Color.LIGHT_GRAY);
		
		//Set up variables for adding things to the info panel.
		String nameInfo;
		String wallInfo;
		JTextArea textArea;
		JButton button;
		int i = 0;
		for (int index = 0; index<players.length; index++) {
			if (players[index] != null) {
				
				//Sets up constraints.
				nameConstraints.anchor = GridBagConstraints.SOUTH;
				nameConstraints.gridy = i;
				buttonConstraints.gridy = i;
				i++;

				//Adds the name to the info panel.
				nameInfo = "Name: ";
				nameInfo += players[index].getName();
				textArea = new JTextArea();
				textArea.setBackground(Color.LIGHT_GRAY);
				textArea.setText(nameInfo);
				this.add(textArea, nameConstraints);

				//Sets up the constraint to the wall info.
				nameConstraints.anchor = GridBagConstraints.NORTH;
				nameConstraints.gridy = i;
				i++;
				
				//Adds the wall info to the panel
				wallInfo = "WallCount: ";
				wallInfo += players[index].getWalls();
				wallCounts[index] = new JTextArea();
				wallCounts[index].setBackground(Color.LIGHT_GRAY);
				wallCounts[index].setText(wallInfo);
				this.add(wallCounts[index], nameConstraints);
				
				//Adds the button in the players color to the panel.
				button = new JButton();
				button.setMinimumSize(new Dimension(20, 20));
				button.setMaximumSize(new Dimension(20, 20));
				button.setPreferredSize(new Dimension(20, 20));
				button.setBackground(players[index].getColor());
				this.add(button, buttonConstraints);
			}
		}
	}
	public void updateWallInfo(int playerNumber){
		int playerIndex = playerNumber-1;
		if(wallCounts[playerIndex]!=null){
			wallCounts[playerIndex].setText("WallCount: "+players[playerIndex].getWalls());
		}
	}
}