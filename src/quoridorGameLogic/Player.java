/**
 * 
 */
package quoridorGameLogic;

import java.awt.Color;

/**
 * @author *******
 * 
 */
public class Player {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		result = prime * result + walls;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		if (walls != other.walls)
			return false;
		return true;
	}

	private String name = "default_name";
	private int row;
	private int column;
	private int walls;
	private Color color;

	public Player(int row, int column, int walls, Color color) {
		this.row = row;
		this.column = column;
		this.walls = walls;
		this.color = color;
	}

	/**
	 * Removes one wall from the wall count.
	 * 
	 * @return the number of walls in the private data member walls should be
	 *         one less.
	 */
	public void removeWall() {
		walls--;
	}
	
	/**
	 * Adds one wall from the wall count.
	 * 
	 * @return the number of walls in the private data member walls should be
	 *         one more.
	 */
	public void addWall() {
		walls++;
	}

	/**
	 * 
	 * @return a int of the current row of the player
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Sets the row to the passed in param.
	 * 
	 * @param row
	 *            the new value of the row.
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Sets the column to the passed in param.
	 * 
	 * @param column
	 *            the new value of the column.
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * 
	 * @return a int of the current column of the player
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 
	 * @return a int of the current count of unplaced walls
	 */
	public int getWalls() {
		return walls;
	}

	/**
	 * Returns a String representation of the current position of the player.
	 * (a,1) would be the upper left corner. (a,9) would be the bottom left
	 * corner.
	 */
	@Override
	public String toString() {
		char firstColumn = 'a';
		char columnOfPlayer = (char) (firstColumn + column);
		int firstRow = 1;
		int rowOfPlayer = firstRow + row;
		return "" + columnOfPlayer + rowOfPlayer;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
