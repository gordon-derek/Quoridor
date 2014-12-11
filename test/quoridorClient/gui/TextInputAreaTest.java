package quoridorClient.gui;

import static org.junit.Assert.*;

import java.awt.AWTEvent;

import org.junit.BeforeClass;
import org.junit.Test;

import quoridorClient.gui.TextInputArea;

public class TextInputAreaTest {


	@Test
	public void testActionPerformed() {
		TextInputArea inputArea= new TextInputArea();
		inputArea.textArea.setText("Hello");
		inputArea.submitButton.doClick();
		assertTrue(inputArea.messageQueue.contains("Hello"));
	}

	@Test
	public void testGetMessageQueue() {
		TextInputArea inputArea= new TextInputArea();
		assertEquals(inputArea.messageQueue,inputArea.getMessageQueue());
	}

}
