package com.rta.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * The class extends the Thread class so we can receive and send messages at the same time
 */
public class Server extends Thread implements MessageReceiver
{

	public static final int		SERVERPORT	= 4444;
	private boolean				running		= false;
	private PrintWriter			mOut;
	private OnMessageReceived	messageListener;
	  private final ByteBuffer buffer = ByteBuffer.allocate( 16384 );
	  
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

		try
		{
		     // Instead of creating a ServerSocket,
		      // create a ServerSocketChannel
		      ServerSocketChannel ssc = ServerSocketChannel.open();

		      // Set it to non-blocking, so we can use select
		      ssc.configureBlocking( false );

		      // Get the Socket connected to this channel, and bind it
		      // to the listening port
		      ServerSocket ss = ssc.socket();
		      InetSocketAddress isa = new InetSocketAddress( SERVERPORT );
		      ss.bind( isa );

		      // Create a new Selector for selecting
		      Selector selector = Selector.open();

		      // Register the ServerSocketChannel, so we can
		      // listen for incoming connections
		      ssc.register( selector, SelectionKey.OP_ACCEPT );
		      System.out.println( "Listening on port "+SERVERPORT );
			
		      while (true) {
		          // See if we've had any activity -- either
		          // an incoming connection, or incoming data on an
		          // existing connection
		          int num = selector.select();

		          // If we don't have any activity, loop around and wait
		          // again
		          if (num == 0) {
		            continue;
		          }

		          // Get the keys corresponding to the activity
		          // that has been detected, and process them
		          // one by one
		          Set keys = selector.selectedKeys();
		          Iterator it = keys.iterator();
		          while (it.hasNext()) {
		            // Get a key representing one of bits of I/O
		            // activity
		            SelectionKey key = (SelectionKey)it.next();

		            // What kind of activity is it?
		            if ((key.readyOps() & SelectionKey.OP_ACCEPT) ==
		              SelectionKey.OP_ACCEPT) {

		  System.out.println( "acc" );
		              // It's an incoming connection.
		              // Register this socket with the Selector
		              // so we can listen for input on it

		              Socket s = ss.accept();
		              System.out.println( "Got connection from "+s );

		              // Make sure to make it non-blocking, so we can
		              // use a selector on it.
		              SocketChannel sc = s.getChannel();
		              
		              sc.configureBlocking( false );

		              // Register it with the selector, for reading
		              sc.register( selector, SelectionKey.OP_READ );
		            } else if ((key.readyOps() & SelectionKey.OP_READ) ==
		              SelectionKey.OP_READ) {

		            	  ByteBuffer buffer = ByteBuffer.allocate(20);
		                  SocketChannel clientChannel = (SocketChannel) key.channel();
		                  int bytesRead = 0;
		                  
		                  if ((bytesRead = clientChannel.read(buffer)) > 0) {
	                            buffer.flip();
	                            String message = Charset.defaultCharset().decode(
	                                    buffer).toString();
	                            onReceive(message);
	                            System.out.println(Charset.defaultCharset().decode(
	                                    buffer));
	                            buffer.clear();
	                        }
		                  
		                  //clientChannel.write(buffer);
		                  
	                        if (bytesRead < 0) {
	                            // the key is automatically invalidated once the
	                            // channel is closed
	                            clientChannel.close();
	                        }
		    
	
		          
		          }

		          // We remove the selected keys, because we've dealt
		          // with them.
		          }
		          keys.clear();
		        
		      }
		}
		catch(Exception e)
		{
			
		}
		
		/*
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

		}*/
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
		  private boolean processInput( SocketChannel sc ) throws IOException {
			    buffer.clear();
			    sc.read( buffer );
			    buffer.flip();

			    // If no data, close the connection
			    if (buffer.limit()==0) {
			      return false;
			    }

			    // Simple rot-13 encryption
			    for (int i=0; i<buffer.limit(); ++i) {
			      byte b = buffer.get( i );

			      if ((b>='a' && b<='m') || (b>='A' && b<='M')) {
			        b += 13;
			      } else if ((b>='n' && b<='z') || (b>='N' && b<='Z')) {
			        b -= 13;
			      }

			      buffer.put( i, b );
			    }

			    sc.write( buffer );

			    System.out.println( "Processed "+buffer.limit()+" from "+sc );

			    return true;
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