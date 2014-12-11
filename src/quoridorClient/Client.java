package quoridorClient;

/**
 * @author Cody Hamilton
 * 
 */
public class Client {


	/**
	 * The entry point of the client.
	 * 
	 * @param args
	 *            The ip addresses and ports of the remote servers.
	 */
	public static void main(String[] args) {
		// gets the IP Address as a command line argument might be change to a
		// GUI
		int numPlayers = args.length;
		String ipAddr0 = args[0];
		String ipAddr1 = args[1];
		String ipAddr2 = null;
		String ipAddr3 = null;
		if (numPlayers == 4) {
			ipAddr2 = args[2];
			ipAddr3 = args[3];
		}
		Game g = new Game(ipAddr0, ipAddr1, ipAddr2, ipAddr3, numPlayers);
		try {
			while(!g.playRound());
				
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
