package com.rta.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.rta.network.Server;

public class ServerBoard extends JFrame
{
	private JTextArea	textAreaMessage;
	private JTextField	textFieldMessage;
	private JButton		buttonSend;
	private JButton		buttonStart;
	private JButton		buttonStop;
	private Server		server;

	private static String getHostAddress()
	{
		String hostAddress = "Unknown";

		try
		{
			InetAddress inetAddress = InetAddress.getLocalHost();
			hostAddress = inetAddress.getHostAddress();
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hostAddress;
	}

	public ServerBoard()
	{

		super("LyxServer : " + getHostAddress());

		JPanel panelFields = new JPanel();
		panelFields.setLayout(new BoxLayout(panelFields, BoxLayout.X_AXIS));

		JPanel panelFieldsButtons = new JPanel();
		panelFieldsButtons.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(2, 2, 2, 2);

		JPanel panelFields2 = new JPanel();
		panelFields2.setLayout(new BoxLayout(panelFields2, BoxLayout.X_AXIS));

		// here we will have the text messages screen
		textAreaMessage = new JTextArea();
		textAreaMessage.setColumns(30);
		textAreaMessage.setRows(10);
		textAreaMessage.setEditable(false);
		JScrollPane scroll = new JScrollPane(textAreaMessage);
		scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e)
			{
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});

		buttonSend = new JButton("Send");
		buttonSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// get the message from the text view
				String messageText = textFieldMessage.getText();
				// add message to the message area
				textAreaMessage.append("\n" + messageText);
				// send the message to the client
				server.sendMessage(messageText);
				// clear text
				textFieldMessage.setText("");
			}
		});

		buttonStop = new JButton("Stop");
		buttonStop.setEnabled(false);
		buttonStop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0)
			{
				buttonStart.setEnabled(true);
				buttonStop.setEnabled(false);

				// server.stop();
				server.setRunning(false);
			}
		});

		buttonStart = new JButton("Start");
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				// disable the start button
				buttonStart.setEnabled(false);
				buttonStop.setEnabled(true);
				// creates the object OnMessageReceived asked by the TCPServer constructor
				server = new Server(new Server.OnMessageReceived() {

					// this method declared in the interface from TCPServer class is implemented here
					// this method is actually a callback method, because it will run every time when it will be called from
					// TCPServer class (at while)
					public void messageReceived(String message)
					{
						textAreaMessage.append("\n " + message);
					}
				});
				server.start();

			}
		});

		// the box where the user enters the text (EditText is called in Android)
		textFieldMessage = new JTextField();
		textFieldMessage.setSize(200, 20);

		// add the buttons and the text fields to the panel
		panelFieldsButtons.add(buttonStart, gbc);
		panelFieldsButtons.add(buttonStop, gbc);

		panelFields.add(scroll);
		panelFields.add(panelFieldsButtons);

		panelFields2.add(textFieldMessage);
		panelFields2.add(buttonSend);

		getContentPane().add(panelFields);
		getContentPane().add(panelFields2);

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		setSize(300, 170);
		setVisible(true);
	}
}