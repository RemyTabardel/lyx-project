package com.rta.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * The class extends the Thread class so we can receive and send messages at the same time
 */
public class Server extends Thread implements MessageReceiver
{

	public static final int		SERVERPORT	= 4444;
	private boolean				running		= false;
	private PrintWriter			mOut;
	private OnMessageReceived	messageListener;

	private List<Client> listClients = new ArrayList<Client>();
	
	public void setRunning(boolean running)
	{
		this.running = running;
	}

	/**
	 * Constructor of the class
	 * 
	 * @param messageListener
	 *            listens for the messages
	 */
	public Server(OnMessageReceived messageListener)
	{
		this.messageListener = messageListener;
	}

	/**
	 * Method to send the messages from server to client
	 * 
	 * @param message
	 *            the message sent by the server
	 */
	public void sendMessage(String message)
	{
		if (mOut != null && !mOut.checkError())
		{
			mOut.println(message);
			mOut.flush();
		}
	}

	@Override
	public void run()
	{
		super.run();

		running = true;

		try
		{
			System.out.println("S: Waiting connections...");

			// create a server socket. A server socket waits for requests to
			// come in over the network.
			ServerSocket serverSocket = new ServerSocket(SERVERPORT);

			while (running)
			{
				// create client socket... the method accept() listens for a connection to be made to this socket and accepts it.
				Socket socket = serverSocket.accept();
				Client client = new Client(socket, this);
				
				listClients.add(client);
				
				client.start();
			}
		}
		catch (Exception e)
		{

		}
		/*try
		{
			System.out.println("S: Waiting connections...");

			// create a server socket. A server socket waits for requests to come in over the network.
			ServerSocket serverSocket = new ServerSocket(SERVERPORT);

			// create client socket... the method accept() listens for a connection to be made to this socket and accepts it.
			Socket client = serverSocket.accept();
			System.out.println("S: Receiving...");

			try
			{

				// sends the message to the client
				mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);

				// read the message received from client
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

				// in this while we wait to receive messages from client (it's an infinite loop)
				// this while it's like a listener for messages
				while (running)
				{
					String message = in.readLine();

					if (message != null && messageListener != null)
					{
						// call the method messageReceived from ServerBoard class
						messageListener.messageReceived(message);
					}
				}
				
				System.out.println("S: Close");

			}
			catch (Exception e)
			{
				System.out.println("S: Error");
				e.printStackTrace();
			}
			finally
			{
				client.close();
				System.out.println("S: Done.");
			}

		}
		catch (Exception e)
		{
			System.out.println("S: Error");
			e.printStackTrace();
		}*/
		
		
		

		/*
		 * 
			private static byte[]		data		= new byte[255];
			try
			{
				for (int i = 0; i < data.length; i++)
					data[i] = (byte) i;

				ServerSocketChannel server = ServerSocketChannel.open();
				server.configureBlocking(false);

				server.socket().bind(new InetSocketAddress(4444));
				Selector selector = Selector.open();
				server.register(selector, SelectionKey.OP_ACCEPT);

				while (true)
				{
					selector.select(500);
					Set<SelectionKey> readyKeys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = readyKeys.iterator();
					
					while (iterator.hasNext())
					{
						SelectionKey key = (SelectionKey) iterator.next();
						iterator.remove();
						if (key.isAcceptable())
						{
							SocketChannel client = server.accept();
							System.out.println("Accepted connection from " + client);
							client.configureBlocking(false);
							ByteBuffer source = ByteBuffer.wrap(data);
							SelectionKey key2 = client.register(selector, SelectionKey.OP_WRITE);
							key2.attach(source);
						}
						else if (key.isWritable())
						{
							SocketChannel client = (SocketChannel) key.channel();
							ByteBuffer output = (ByteBuffer) key.attachment();
							if (!output.hasRemaining())
							{
								output.rewind();
							}
							client.write(output);
						}
						else if (key.isReadable())
						{

						}

						key.channel().close();
					}
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		 */

	}

	
	// Declare the interface. The method messageReceived(String message) will must be implemented in the ServerBoard
	// class at on startServer button click
	public interface OnMessageReceived
	{
		public void messageReceived(String message);
	}

	@Override
	public void onReceive(String message)
	{
		messageListener.messageReceived(message);
		
	}

}