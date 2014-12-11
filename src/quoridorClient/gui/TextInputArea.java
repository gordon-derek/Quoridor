package quoridorClient.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TextInputArea extends JPanel implements ActionListener {
	BlockingQueue<String> messageQueue;
	JButton submitButton;
	JTextArea textArea;
	public TextInputArea(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill=GridBagConstraints.BOTH;
		constraints.gridx=0;
		constraints.weighty=1;
		constraints.weightx=.6;
		submitButton = new JButton("Submit");
		textArea = new JTextArea("Enter text here");
		messageQueue = new LinkedBlockingQueue<String>();
		submitButton.addActionListener(this);
		add(textArea,constraints);
		constraints.gridx=1;
		constraints.weightx=.2;
		add(submitButton,constraints);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(submitButton)){
			messageQueue.add(textArea.getText());
			textArea.setText("");
		}
	}
	/**
	 * A method to get the message queue from the gui.
	 * 
	 * @return The message queue.
	 */
	public BlockingQueue<String> getMessageQueue(){
		return messageQueue;
	}
}
