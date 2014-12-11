package quoridorClient;

import static org.junit.Assert.*;

import org.junit.Test;

import quoridorClient.gui.GameSpace;

public class GameSpaceTest {

	@Test
	public void testCreationAndString() {
		String s = "testing123";
		GameSpace testSpace = new GameSpace(s);
		assertNotNull(testSpace);
		assertTrue(testSpace.getName().equals("testing123"));
	}
	@Test
	public void testSetter() {
		GameSpace testSpace = new GameSpace("test");
		testSpace.setName("other test");
		assertTrue(testSpace.getName().equals("other test"));
	}
	

}
