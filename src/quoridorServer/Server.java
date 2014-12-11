package quoridorServer;
import quoridorAI.*;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.Scanner;

/**
 * @author Derek Gordon
 * Takes in Messages, converts the messages into operations
 * for the AI to complete
 */
public class Server {
	private static final int DEFAULT_PORT = 3000;
	private static final int DEFAULT_AI = 0;
	private static final String INIT_MESSAGE = "HELLO penguins";
	private static final String NAME = "Team_Armored_Penguins";
	
	
	/**
	 * Service that runs to accept connections from clients.
	 * @param serverPort
	 * 			Port number to listen on
	 * @param aiNum
	 * 			AI to run
	 * @throws Exception
	 */
    public static void startService(int serverPort, int aiNum) throws Exception {
			
		//run until shutdown
		while (true){
	    	//open server socket
	    	ServerSocket svr = new ServerSocket(serverPort);
			Socket s = svr.accept();
			boolean winner = false;
			boolean kicked = false;
			
			try{
				PrintStream outToClient = new PrintStream(s.getOutputStream());
	    		Scanner inFromClient = new Scanner(s.getInputStream());
	    		String message;
	    		int numP = 0;
	    		System.err.println("Sending Init Message");
	    		//send Hello Message
	    		outToClient.println(INIT_MESSAGE);
	    		
	    		//initialize ai, starting game
	    		message = inFromClient.nextLine();
	    		
	    		if(message.contains("KILL"))
	    			return;
	   
	    		
	    		//replace Quoridor so our ID is the first number
	    		message = message.replace("QUORIDOR ", "");
	    		
	    		int id = Character.getNumericValue(message.charAt(0));
	    		
	    		//get num Players
	    		String [] args = message.split(" ");
	    		numP = args.length;
	    		System.err.println(numP);
	    		
	    		outToClient.println("READY " + NAME);
	    		
	    		//pick an AI
	    		AI ai = new AI1(id, numP);
	    	   	switch(aiNum){
	    	   	//shortest path
	        	case 0: ai = new AI1(id, numP); break;
	        	//try to move off gameboard (breaker)
	        	case 1: ai = new AI2(id, numP); break;
	        	//ruin other players shortest path(smarter)
	        	case 2: ai = new AI3(id, numP); break;
	        	//place wall of end of gameboard(breaker);
	        	case 3: ai = new AI4(id, numP); break;
	        	//intersecting walls(breaker)
	        	case 4: ai = new AI5(id, numP); break;
	        	//intersecting walls(breaker)
	        	case 5: ai = new AI6(id, numP); break;
	        	
	        	case 6: ai = new SmartAI0(id, numP); break;
	        	
	        	case 7: ai = new MinMaxAI(id, numP); break;
	        	
	        	case 8: ai = new FourPlayerAI(id, numP); break;
	        	
	        	case 9: ai = new randomAI(id, numP);break;
	        	}
	    	   	
	    	   	
	    	   	
	    		//run the game loop
	    		while(!winner && !kicked){
	    			message = inFromClient.nextLine();
	    			System.out.println(message);
	    			
	    			//Process Message
	    			if(message.equals("MOVE?")){
	    				String move = ai.think();
	    				outToClient.println("MOVE " + move);
	    			}
	    			else if(message.startsWith("MOVED")){ //one way
	    				//remove "MOVED "
	    				String move = message.replace("MOVED ", "");
	    				//adjust gameboard
	    				ai.updateGameboard(move);
	    				System.out.println("Moved Completed");
	    				
	    			}
	    			else if(message.contains("REMOVED")){ //one way
	    				
	    				String aiID = message.replace("REMOVED ", "");
	    				//remove player from gameboard
	    				kicked = ai.removePlayer(Integer.parseInt(aiID));	    					
	    			}
	    			else if(message.contains("WINNER"))
	    				winner = true;
	    			else
	    				outToClient.println("FAILED: message not recognized");
	    			
	    			System.out.println(kicked);
	    		}
	    		
	    	}catch(IOException e){throw e;}
			svr.close();
			s.close();
			
			//break;
		}
				
    	
    }

	/**
	 * Starts server using the arguments passed into the client
	 * or operate using the defaults if not all arguments are passed.
	 * @param args
	 * 		contains the port number it should run on
	 *      the number for the AI which it should run.
	 */
	public static void main(String[] args) {
		//Defaults if no arguments
		int serverPort = DEFAULT_PORT;
		int aiNum = DEFAULT_AI;
		
		int nargs = args.length;
		
		//take in arguments
		if (nargs == 1)
		    serverPort = Integer.parseInt(args[0]);
		else if(nargs == 2){
			serverPort = Integer.parseInt(args[0]);
			aiNum = Integer.parseInt(args[1]);
		}
		
		//start server
		try{
			startService(serverPort, aiNum);
		}catch(Exception e){
			System.err.println("Server: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

	}

}
