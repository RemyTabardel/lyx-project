package com.rta.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.rta.gui.ServerBoard;

/**
 * The class extends the Thread class so we can receive and send messages at the same time
 */
public class Server extends Thread
{

	private static final int	SERVER_PORT	= 4444;
	private static final int	BUFFER_SIZE	= 16384;
	private boolean				running;
	private ServerSocketChannel	serverSocketChannel;
	private ServerSocket		serverSocket;
	private InetSocketAddress	inetSocketAddress;
	private Selector			selector;
	private ServerBoard			serverBoard;
	private final ByteBuffer	buffer;
	private Charset				charset;

	private List<Client>		listClients;

	public Server(ServerBoard serverBoard)
	{
		this.serverBoard = serverBoard;
		this.listClients = new ArrayList<Client>();
		this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
		this.running = false;
		this.charset = Charset.forName("ISO-8859-1");
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	private void log(String line)
	{
		serverBoard.writeLine(line);
	}

	public void send(String message)
	{
		for (Client client : listClients)
		{
			try
			{
				client.getSocketChannel().register(selector, SelectionKey.OP_WRITE, message);
			}
			catch (ClosedChannelException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * CharBuffer buffer = CharBuffer.wrap(message); while (buffer.hasRemaining()) { try { client.getSocketChannel().write(this.charset.encode(buffer)); } catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
			 */
		}
	}

	private void disconnect(SelectionKey key)
	{
		SocketChannel socketChannel = (SocketChannel) key.channel();
		try
		{
			socketChannel.finishConnect();
		}
		catch (IOException e)
		{
			// Cancel the channel's registration with our selector
			key.cancel();
		}
	}

	private void accept(SelectionKey key)
	{
		try
		{
			// C'est une nouvelle connexion, on enregistre la socket dans le selector afin de recevoir les entrées
			Socket clientSocket = serverSocket.accept();

			log("Got connection from " + clientSocket);

			// On la rend non bloquant pour l'utiliser dans le selector
			SocketChannel clientSocketChannel = clientSocket.getChannel();
			clientSocketChannel.configureBlocking(false);

			// On enregistre dans le selector pour la lecture
			clientSocketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

			CharBuffer buffer = CharBuffer.wrap("Salut");
			clientSocketChannel.write(this.charset.encode(buffer));
			
			listClients.add(new Client(clientSocket, clientSocketChannel));

		}
		catch (IOException e)
		{

		}
	}

	private void write(SelectionKey key)
	{
		/*
		 * SocketChannel channel = (SocketChannel) key.channel(); String message = (String) key.attachment();
		 * 
		 * CharBuffer buffer = CharBuffer.wrap(message); while (buffer.hasRemaining()) { try { channel.write(this.charset.encode(buffer)); } catch (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); } }
		 */
	}

	private void read(SelectionKey key)
	{
		try
		{
			SocketChannel clientSocketChannel = (SocketChannel) key.channel();
			int bytesRead = clientSocketChannel.read(buffer);

			if (bytesRead > 0)
			{
				buffer.flip();
				String message = this.charset.decode(buffer).toString();

				log(message);

				buffer.clear();
			}
			else
			{
				// the key is automatically invalidated once the
				// channel is closed
				clientSocketChannel.close();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		super.run();

		try
		{
			// Au lieu d'un SocketServer on set un ServerSocketChannel et on le rend non bloquant pour utiliser le select
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);

			// On récupère le socket du channel et on le met en écoute sur le port
			serverSocket = serverSocketChannel.socket();
			inetSocketAddress = new InetSocketAddress(SERVER_PORT);
			serverSocket.bind(inetSocketAddress);

			// On créé le selector et on enregistre le socket channel pour recevoir les nouvelles connections
			selector = Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

			log("Listening on port " + SERVER_PORT);
			running = true;

			while (running)
			{
				// On regarde si on a des activités à traiter (connexion, lecture etc..)
				int num = selector.select();

				// Si on a rien à traiter on continue
				if (num == 0)
				{
					continue;
				}

				// On récupère les clés des activités entrantes et on les traite une par une
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();

				while (it.hasNext())
				{
					// Clé de l'activité en cours de traitement
					SelectionKey key = (SelectionKey) it.next();

					// Quelle sorte d'activité doit on traiter ?
					if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT)
					{
						accept(key);
					}
					else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ)
					{
						read(key);
					}
					else if ((key.readyOps() & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE)
					{
						write(key);
					}
				}

				// On retire les clés car on a traité les activités
				keys.clear();
			}
		}
		catch (Exception e)
		{

		}
	}
}