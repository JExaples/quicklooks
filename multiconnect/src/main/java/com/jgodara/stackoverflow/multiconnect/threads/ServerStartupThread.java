package com.jgodara.stackoverflow.multiconnect.threads;

import com.jgodara.stackoverflow.multiconnect.Server;

public class ServerStartupThread extends Thread {

	private Server server;

	public ServerStartupThread(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			server.start();
		} catch (Exception ex) {
			System.err.println("Server startup failed: " + server);
			ex.printStackTrace();
		}

	}

}