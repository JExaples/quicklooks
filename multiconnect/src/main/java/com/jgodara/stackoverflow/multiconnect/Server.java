package com.jgodara.stackoverflow.multiconnect;

import java.net.Socket;
import java.util.List;

public interface Server {
	
	public void start() throws Exception;
	
	public void stop() throws Exception;
	
	public List<Socket> getClients();
	
	public void addClient(Socket client);
	
	public void deleteClient(Socket client);

}
