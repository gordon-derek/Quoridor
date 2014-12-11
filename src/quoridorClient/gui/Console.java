/**
 * 
 */
package quoridorClient.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author **** Used
 *         http://stackoverflow.com/questions/342990/create-java
 *         -console-inside-the-panel. As a starting point.
 */
public class Console extends JPanel {
	TextInputArea inputArea;

	public Console() {
		this.setLayout(new BorderLayout());
		JTextArea ta1 = new JTextArea();
		ta1.setEditable(false);
		TextAreaOutputStream taos = new TextAreaOutputStream(ta1, 600);
		PrintStream ps = new PrintStream(taos);
		System.setOut(ps);
		// System.setErr( ps );
		inputArea = new TextInputArea();
		this.add(new JScrollPane(ta1));
		this.add(inputArea, BorderLayout.SOUTH);
		inputArea.setMinimumSize(new Dimension(300, 30));
		inputArea.setPreferredSize(new Dimension(300, 30));
		this.setMinimumSize(new Dimension(300, 100));
		this.setPreferredSize(new Dimension(300, 100));
		this.setBackground(Color.DARK_GRAY);
	}

	/**
	 * Returns the message queue for the GUI.
	 * 
	 * @return the queue.
	 */
	public BlockingQueue<String> getMessageQueue() {
		return inputArea.getMessageQueue();
	}
}
