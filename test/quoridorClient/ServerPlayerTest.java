/**
 * 
 */
package quoridorClient;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author *******
 *
 */
public class ServerPlayerTest {

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

	/**
	 * Test method for {@link quoridorClient.ServerPlayer#ServerPlayer(java.lang.String)}.
	 */
	@Test
	public void testServerPlayer() {
		try{
			ServerPlayer s = new ServerPlayer("localhost:3000");
		}catch(Exception e)
		{fail("Failed Creating Server Player");}
	}


	@Test
	public void testOneWayMessage(){
		try{
			ServerPlayer s = new ServerPlayer("localhost:3000");
			s.oneWayMessage("HELLO 1");
		}catch(Exception e)
		{fail("Failed Sending One Way Message");}
	}

	/**
	 * Test method for {@link quoridorClient.ServerPlayer#makeMove(java.lang.String)}.
	 */
	@Test
	public void testTwoWayMessage() {
		try{
			ServerPlayer s = new ServerPlayer("localhost:3000");
			String r;
			r = s.twoWayMessage("QUORIDOR 1 2");
			if(r.equals("READY TeamArmoredPenguins"));
		}catch(Exception e)
		{fail("Failed Sending Two Way Message");}
	}

}
