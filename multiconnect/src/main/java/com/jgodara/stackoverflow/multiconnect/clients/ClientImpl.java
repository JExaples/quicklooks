package com.jgodara.stackoverflow.multiconnect.clients;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;

import com.jgodara.stackoverflow.multiconnect.Client;
import com.jgodara.stackoverflow.multiconnect.ConnectionFactory;
import com.jgodara.stackoverflow.multiconnect.threads.ClientShutdownHook;

public class ClientImpl implements Client {
	
	private Class<?> serverClass;
	private String clientId = "";
	
	private BufferedWriter writer;
	private BufferedReader reader;
	private Socket socket;
	
	private ClientShutdownHook shutdownHook;
	
	public ClientImpl(Class<?> serverClass) {
		this.serverClass = serverClass;
		this.clientId = UUID.randomUUID().toString();
	}
	
	public synchronized final void connect() throws Exception {		
		socket = ConnectionFactory.createClientSocket(serverClass);
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		// Add a shutdown hook to disconnect on VM shutdown
		if (shutdownHook == null)
			shutdownHook = new ClientShutdownHook(this);
		Runtime.getRuntime().addShutdownHook(shutdownHook);
		
		System.out.println(this + ": Ready.");
	}

	public void disconnect() throws Exception {
		
		// Remove the hook to prevent calling disconnect()
		// twice
		Runtime.getRuntime().removeShutdownHook(shutdownHook);
		
		reader.close();
		writer.close();
		socket.close();
	}

	public BufferedReader getReader() {
		return reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}
	
	@Override
	public String toString() {
		return "ClientImpl[" + clientId + "::" + (socket == null ? "unconnected" : socket.getPort()) +"]";
	}

}
