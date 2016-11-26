package com.jgodara.stackoverflow.multiconnect.threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.jgodara.stackoverflow.multiconnect.Server;

public class ClientConnection extends Thread {
	
	private BufferedReader reader;
	private Server server;
	private Socket clientSocket;

	public ClientConnection(Server server, Socket clientSocket) throws IOException {
		this.server = server;
		this.clientSocket = clientSocket;
		
		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		server.addClient(clientSocket);
		
		System.out.println(server + ": ClientImpl Connected [" + clientSocket.getLocalAddress() + "]");
	}
	
	@Override
	public void run() {
		try {
			String clientMessage = readMessage();
			broadcastMessage(clientMessage);
		} catch (Exception ex) {
			System.err.println("Error in client: " + ex.getMessage());
			server.deleteClient(clientSocket);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ex) {
				// NOOP
			}
		}
	}
	
	private String readMessage() throws IOException {
		StringBuffer message = new StringBuffer();
		while (reader.ready()) {
			message.append(reader.readLine()).append("\n");
		}
		return message.toString();
	}
	
	private void broadcastMessage(String message) throws IOException {
		for (Socket socket : server.getClients()) {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			writer.write(message);
			writer.flush();
		}
	}
	
}
