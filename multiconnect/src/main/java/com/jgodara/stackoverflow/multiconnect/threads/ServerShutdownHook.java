package com.jgodara.stackoverflow.multiconnect.threads;

import com.jgodara.stackoverflow.multiconnect.Server;

public class ServerShutdownHook extends Thread {

	private Server server;
	
	public ServerShutdownHook(Server server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		try {
			server.stop();
			System.out.println(server + ":  Shutting Down...");
		} catch (Exception ex) {
			System.err.println("Shutdown hook failed to clean up server: "
					+ server);
			ex.printStackTrace();
		}
	}

}