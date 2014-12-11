package quoridorClient;

import static org.junit.Assert.*;

import org.junit.Test;

import quoridorClient.gui.Wall;

public class WallTest {

	@Test
	public void testCreation() {
		Wall testSpace = new Wall("");
		assertNotNull(testSpace);
	}

}
