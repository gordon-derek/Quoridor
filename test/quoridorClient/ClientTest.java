/**
 * 
 */
package quoridorClient;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ***********
 *
 */
public class ClientTest {

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
	
	@Test // test to make sure the creation of the players
	// (right now based off of any string) are successfully 
	// created
	public void testPlayerCreation(){
		try{
			Player player0 = new ServerPlayer("56");
			Player player1 = new ServerPlayer("57");
			assertNotNull(player0);
			assertNotNull(player1);
		}catch(Exception e){System.out.println(e);}
	}

}
