package com.jgodara.stackoverflow.multiconnect.servers;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.jgodara.stackoverflow.multiconnect.ConnectionFactory;
import com.jgodara.stackoverflow.multiconnect.Server;
import com.jgodara.stackoverflow.multiconnect.threads.ClientConnection;
import com.jgodara.stackoverflow.multiconnect.threads.ServerShutdownHook;

public class Server1 implements Server {

	private ServerSocket serverSocket;
	private List<Socket> clients = new ArrayList<Socket>();
	private ServerShutdownHook shutdownHook;
	
	public synchronized final void start() throws Exception {
		serverSocket = ConnectionFactory.createServerSocket(Server1.class);
		
		// Add a shutdown hook, so that the server could be
		// clean up on VM exit.
		if (shutdownHook == null)
			shutdownHook = new ServerShutdownHook(this);
		Runtime.getRuntime().addShutdownHook(shutdownHook);
		
		System.out.println(this + ": Server Ready.");
		
		while (true) {
			Socket clientSocket = serverSocket.accept();
			new ClientConnection(this, clientSocket).start();
			clientSocket.close();
		}
	}

	public synchronized final void stop() throws Exception {
		
		// Remove the hook so that call to stop() does 
		// occur twice.
		Runtime.getRuntime().removeShutdownHook(shutdownHook);
		
		if (serverSocket != null)
			serverSocket.close();
	}

	public synchronized final List<Socket> getClients() {
		return clients;
	}

	public synchronized final void addClient(Socket client) {
		int index = clients.indexOf(client);
		if (index == -1)
			clients.add(client);
	}

	public void deleteClient(Socket client) {
		int index = clients.indexOf(client);
		if (index != -1)
			clients.remove(index);
	}
	
	@Override
	public String toString() {
		return "Server1[" + (serverSocket == null ? "unstarted" : serverSocket.getLocalPort()) + "]";
	}

}
