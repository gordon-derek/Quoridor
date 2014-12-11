package quoridorClient;

import java.io.IOException;
/**
 * 
 */

/**
 * @author hamiltcw196
 *
 */
public interface Player {
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String twoWayMessage(String move) throws Exception;
	/**
	 * 
	 * @param move
	 */
	public void oneWayMessage(String move);
	
	public String getMessage();
	
	public String getID();
	
	public void setID(String id);
}
