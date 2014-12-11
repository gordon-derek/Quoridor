package quoridorClient.gui;

import static org.junit.Assert.*;

import org.junit.Test;

import quoridorClient.gui.SmallEmptySpace;

public class SmallEmptySpaceTest {

	@Test
	public void testCreation() {
		SmallEmptySpace testSpace = new SmallEmptySpace("");
		assertNotNull(testSpace);
	}

}
