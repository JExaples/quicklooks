package com.jgodara.stackoverflow.multiconnect;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionFactory {

	private static final Map<String, Integer> ports = new HashMap<String, Integer>();

	static {
		// These Servers would listen to these ports.
		ports.put("com.jgodara.stackoverflow.multiconnect.servers.Server1", 5001);
		ports.put("com.jgodara.stackoverflow.multiconnect.servers.Server2", 5502);
		ports.put("com.jgodara.stackoverflow.multiconnect.servers.Server3", 8080);
	}

	public static ServerSocket createServerSocket(Class<?> serverClass)
			throws IOException {
		return createServerSocket(serverClass.getName());
	}

	public static ServerSocket createServerSocket(String fqcn)
			throws IOException {
		Integer port = ports.get(fqcn);
		if (port == null)
			throw new IllegalArgumentException("No server registery found for " + fqcn);
		return new ServerSocket(port);
	}

	public static Socket createClientSocket(Class<?> serverClass)
			throws UnknownHostException, IOException {
		return createClientSocket(serverClass.getName());
	}

	public static Socket createClientSocket(String fqcn)
			throws UnknownHostException, IOException {
		Integer port = ports.get(fqcn);
		if (port == null)
			throw new IllegalArgumentException("No server registery found for " + fqcn);
		return new Socket("localhost", port);
	}

}
