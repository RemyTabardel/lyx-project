package com.rta.lyxclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class TCPClient
{

	private String				serverMessage;
	public static final String	SERVERIP			= "192.168.71.239";	// your computer IP address
	public static final int		SERVERPORT			= 4444;
	private OnMessageReceived	mMessageListener	= null;
	private boolean				mRun				= false;
	private Charset				charset;
	 SocketChannel channel;
	  
	PrintWriter					out;
	BufferedReader				in;

	/**
	 * Constructor of the class. OnMessagedReceived listens for the messages received from server
	 */
	public TCPClient(OnMessageReceived listener)
	{
		mMessageListener = listener;
		this.charset = Charset.forName("ISO-8859-1");
	}

	/**
	 * Sends the message entered by client to the server
	 * 
	 * @param message
	 *            text entered by client
	 */
	public void sendMessage(final String message)
	{
		new Thread(new Runnable() {
			
			@Override
			public void run()
			{
		        CharBuffer buffer = CharBuffer.wrap(message);
		        while (buffer.hasRemaining()) {
		            try
					{
						channel.write(charset.encode(buffer));
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        }
				
			}
		}).start();

		/*
		if (out != null && !out.checkError())
		{
			out.println(message);
			out.flush();
		}*/
	}

	public void stopClient()
	{
		mRun = false;
	}

	public void run()
	{

		mRun = true;

		try
		{
		channel = SocketChannel.open();
		 
        // we open this channel in non blocking mode
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(SERVERIP, SERVERPORT));
        
        while (!channel.finishConnect()) {
            // System.out.println("still connecting");
        }
        
		while (mRun)
		{
	           ByteBuffer bufferA = ByteBuffer.allocate(20);
	            int count = 0;
	            String message = "";
	            while ((count = channel.read(bufferA)) > 0) {
	                // flip the buffer to start reading
	                bufferA.flip();
	                message += charset.decode(bufferA);
	 
	            }
	            
				if (message != null && mMessageListener != null && message.equals("")==false)
				{
					// call the method messageReceived from MyActivity class
					mMessageListener.messageReceived(message);
				}
				message = null;
		}
		}
		catch(Exception e)
		{
			
		}
        
		/*try
		{
			// here you must put your computer's IP address.
			InetAddress serverAddr = InetAddress.getByName(SERVERIP);

			Log.e("TCP Client", "C: Connecting...");

			// create a socket to make the connection with the server
			Socket socket = new Socket(serverAddr, SERVERPORT);

			try
			{

				// send the message to the server
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

				Log.e("TCP Client", "C: Sent.");

				Log.e("TCP Client", "C: Done.");

				// receive the message which the server sends back
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				// in this while the client listens for the messages sent by the server
				while (mRun)
				{
					serverMessage = in.readLine();

					if (serverMessage != null && mMessageListener != null)
					{
						// call the method messageReceived from MyActivity class
						mMessageListener.messageReceived(serverMessage);
					}
					serverMessage = null;

				}

				Log.e("RESPONSE FROM SERVER", "S: Received Message: '" + serverMessage + "'");

			}
			catch (Exception e)
			{

				Log.e("TCP", "S: Error", e);

			}
			finally
			{
				// the socket must be closed. It is not possible to reconnect to this socket
				// after it is closed, which means a new socket instance has to be created.
				socket.close();
			}

		}
		catch (Exception e)
		{

			Log.e("TCP", "C: Error", e);

		}*/

	}

	// Declare the interface. The method messageReceived(String message) will must be implemented in the MyActivity
	// class at on asynckTask doInBackground
	public interface OnMessageReceived
	{
		public void messageReceived(String message);
	}
}
