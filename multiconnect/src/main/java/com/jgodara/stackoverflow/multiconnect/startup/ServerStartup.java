package com.jgodara.stackoverflow.multiconnect.startup;

import com.jgodara.stackoverflow.multiconnect.servers.Server1;
import com.jgodara.stackoverflow.multiconnect.servers.Server2;
import com.jgodara.stackoverflow.multiconnect.servers.Server3;
import com.jgodara.stackoverflow.multiconnect.threads.ServerStartupThread;

public class ServerStartup {
	
	public static void main(String[] args) throws Exception {
		new ServerStartupThread(new Server1()).start();
		new ServerStartupThread(new Server2()).start();
		new ServerStartupThread(new Server3()).start();
	}

}
