package com.jgodara.stackoverflow.multiconnect;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface Client {
	
	public void connect() throws Exception;
	
	public void disconnect() throws Exception;
	
	public BufferedReader getReader();
	
	public BufferedWriter getWriter();

}
