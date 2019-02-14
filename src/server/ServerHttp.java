package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerHttp {

	public static void main(String[] args) {

		execution();

	}

	public static void execution() {
		ServerSocket server = null;
		Socket socket = null;

		try {

			server = new ServerSocket(8090);
			// server accepts the client petition and manages it
			while (true) {
				socket = server.accept();
				RequestServer request = new RequestServer(socket);
				request.start();
			}

		} catch (Exception e) {
			// if occurs an error -> close the server
			if (server != null)
				try {
					server.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			e.printStackTrace();
		}
	}

}
