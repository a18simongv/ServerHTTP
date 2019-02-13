package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHttp {

	public static void main (String[] args) {
		
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			
			server = new ServerSocket(8090);
			
			while(true) {
				 socket = server.accept();
				 System.out.println("client connected");
				 RequestServer request = new RequestServer(socket);
				 request.start();
			}
			
		} catch (Exception e) {
			if(server != null)
				try { server.close(); } catch (IOException e1) {e1.printStackTrace();}

			e.printStackTrace();
		}
		
		
	}
	
}
