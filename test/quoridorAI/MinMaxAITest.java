package quoridorAI;
import quoridorGameLogic.*;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Derek Gordon
 *
 */
public class MinMaxAITest {

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
	 * 
	 */
	@Test
	public void testTreeBuild2Player(){
		try{
			GameBoard gb = new GameBoard(2);
			MinMaxAI ai = new MinMaxAI(2, 2);
			
			ai.updateGameboard("2" + " " + ai.think());
			
			ai.updateGameboard("1 e2");
			
			System.out.println(ai.think());
			
		}catch(Exception e){
			fail("Couldn't build tree");
		}
	}
}
