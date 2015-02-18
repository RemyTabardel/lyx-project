package com.rta.network;

import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class Client
{
	private Socket			socket;
	private SocketChannel	socketChannel;
	private Date			timeout;		// permet de savoir si on a rien reçu pendant un moment pour le déconnecter

	public Client(Socket socket, SocketChannel socketChannel)
	{
		super();
		this.socket = socket;
		this.socketChannel = socketChannel;
	}

	public Socket getSocket()
	{
		return socket;
	}

	public SocketChannel getSocketChannel()
	{
		return socketChannel;
	}

}
