/**
 * 
 */
package quoridorGameLogic;

/**
 * @author *********
 * The BoardSpace that the board will be made up of and where the players will be sitting.
 * A BoardSpace contains links to all surrounding spaces and is aware of when there is a player
 * its its own space.
 * 
 */
public class BoardSpace {
	private BoardSpace up;
	private BoardSpace right;
	private BoardSpace down;
	private BoardSpace left;
	private boolean hasPlayer;
	private int row;
	private int column;

	public BoardSpace() {

	}

	/**
	 * 
	 * @param row
	 *            The value for the row of the new board space.
	 * @param column
	 *            The value for the column of the new board space.
	 */
	public BoardSpace(int row, int column) {
		this.row = row;
		this.column = column;
		hasPlayer = false;
	}

	public BoardSpace(BoardSpace up, BoardSpace right, BoardSpace down,
			BoardSpace left) {
		this.up = up;
		this.right = right;
		this.down = down;
		this.left = left;
	}

	/**
	 * 
	 * @return The board space right of this one.
	 */
	public BoardSpace getRight() {
		return right;
	}

	/**
	 * 
	 * @return The board space left of this one.
	 */
	public BoardSpace getLeft() {
		return left;
	}

	/**
	 * 
	 * @return The board space above this one.
	 */
	public BoardSpace getUp() {
		return up;
	}

	/**
	 * 
	 * @return The board space below this one.
	 */
	public BoardSpace getDown() {
		return down;
	}

	/**
	 * Sets the right link of the BoardSpace.
	 * 
	 * @param right
	 *            the BoardSpace to the right.
	 */
	public void setRight(BoardSpace right) {
		this.right = right;
	}

	/**
	 * Sets the left link of the BoardSpace
	 * 
	 * @param left
	 *            The BoardSpace to the left.
	 */
	public void setLeft(BoardSpace left) {
		this.left = left;
	}

	/**
	 * Sets the down link of the BoardSpace
	 * 
	 * @param down
	 *            the BoardSpace below the current one.
	 */
	public void setDown(BoardSpace down) {
		this.down = down;
	}

	/**
	 * Sets the up link of the BoardSpace
	 * 
	 * @param up
	 *            The BoardSpace above the current one.
	 */
	public void setUp(BoardSpace up) {
		this.up = up;
	}

	/**
	 * @return hasPlayer
	 * 			true if the current BoardSpace has a player on it or not.
	 * 			false other wise. 
	 */
	public Boolean getHasPlayer() {
		return hasPlayer;
	}

	/**
	 * @param hasPlayer
	 *            the hasPlayer to set
	 */
	public void setHasPlayer(Boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}

	/**
	 * @return 
	 * 		the current row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row
	 *            the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return 
	 * 		the current column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	public String toString() {
		return "" + (char) ((column) + 'a') + (char) ((row) + '1');
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + ((down == null) ? 0 : 3907);
		result = prime * result + (hasPlayer ? 1231 : 1237);
		result = prime * result + ((left == null) ? 0 : 2477);
		result = prime * result + ((right == null) ? 0 : 4483);
		result = prime * result + row;
		result = prime * result + ((up == null) ? 0 : 6473);
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BoardSpace)) {
			return false;
		}
		BoardSpace other = (BoardSpace) obj;
		if (column != other.column) {
			return false;
		}
		if (hasPlayer != other.hasPlayer) {
			return false;
		}
		if (row != other.row) {
			return false;
		}
		return true;
	}

	

}
