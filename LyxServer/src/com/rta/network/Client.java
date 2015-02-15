package com.rta.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class Client extends Thread
{
	private Socket			socket;
	private boolean			running;
	private MessageReceiver	messageReceiver;
	private Date			timeout;			// permet de voir si le joueur
												// est absent au bouton d'un
												// moment

	public Client(Socket socket, MessageReceiver messageReceiver)
	{
		this.socket = socket;
		this.messageReceiver = messageReceiver;
		this.running = true;
		timeout = new Date();
	}

	@Override
	public void run()
	{
		try
		{
			// read the message received from client
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// in this while we wait to receive messages from client (it's an
			// infinite loop)
			// this while it's like a listener for messages
			while (running)
			{
				String message = in.readLine();

				if (message != null)
				{
					messageReceiver.onReceive(message);
					System.out.println(message);
				}
			}
		}
		catch (Exception e)
		{

		}
	}
}
