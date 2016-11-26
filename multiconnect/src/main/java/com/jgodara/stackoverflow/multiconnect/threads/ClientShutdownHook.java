package com.jgodara.stackoverflow.multiconnect.threads;

import com.jgodara.stackoverflow.multiconnect.Client;

public class ClientShutdownHook extends Thread {

	private Client client;
	
	public ClientShutdownHook(Client client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			client.disconnect();
			System.out.println(client + ":  Disconnected.");
		} catch (Exception ex) {
			System.err.println("Shutdown hook failed to clean up client: "
					+ client);
			ex.printStackTrace();
		}
	}

}