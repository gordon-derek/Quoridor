/**
 * 
 */
package quoridorServer;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Derek Gordon
 *
 */
public class ServerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try{
			Socket playerSocket = new Socket("localhost", 3999);
			PrintStream outToServer = new PrintStream(playerSocket.getOutputStream());
			Scanner inFromServer = new Scanner(playerSocket.getInputStream());
			String r = "";
			testMove(outToServer, inFromServer);
			testClose(outToServer);
			
		}catch(Exception e)
		{fail("Connection Failed");}
	}
	
	
	public void testMove(PrintStream out, Scanner in){
		out.println("MOVE?");
		String move = in.nextLine();
		if(!move.contains("MOVE "))
			fail("Incorrect server output");
	}
	
	
	public void testClose(PrintStream out){
		out.println("WINNER p1");
		try{
			out.println("MOVE?");
			fail("Server not closing");
		}catch(Exception e){System.out.println(e);}
		
	}

}
