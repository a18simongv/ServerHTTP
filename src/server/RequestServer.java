package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.regex.Pattern;

import htmls.Pages;
import utils.Utils;

public class RequestServer extends Thread {

	private final String IN_LINE_OK = "HTTP/1.1 200 OK";
	private final String IN_LINE_ERROR = "HTTP/1.1 404 Not Found";
	private final String HEADER = "Content-Type:text/html;charset:UTF-8";

	private Socket socket;
	private String addr;

	public RequestServer(Socket socket) throws Exception {
		this.socket = socket;
		if (socket.getInetAddress().equals(InetAddress.getLocalHost()))
			addr = "localhost";
		else
			addr = InetAddress.getLocalHost().getHostAddress();
	}

	// returns dni with his letter
	private String calculateDNI(String number) {
		String dni = "";

		int numDni = Integer.parseInt(number);
		dni = number + "-" + Pages.dniLetters().charAt(numDni % 23);

		return dni;
	}

	@Override
	public void run() {

		try {
			// take stream of socket received
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);// true -> autoflush

			// take url and we show the page correspontent
			String connection = br.readLine();
			connection = connection.trim();

			// we see if we can answer the request
			if (connection.startsWith("GET")) {

				// take last path of url
				String page = connection.substring(3, connection.lastIndexOf("HTTP"));
				page = page.trim();

				String html = "";

				// we look if we have the page
				if (page.length() == 0 || page.equals("/")) {

					html = Pages.indexHtml(addr);
					Utils.showPage(IN_LINE_OK, HEADER, html, pw);

				} else if (page.equals("/dni")) {

					html = Pages.dniHtml(addr);
					Utils.showPage(IN_LINE_OK, HEADER, html, pw);

				} else if (page.contains("/dni=")) {

					String number = page.substring(page.indexOf("=") + 1);
					boolean correctDni = Pattern.matches("[0-9]{8}", number);

					if (correctDni) {

						html = Pages.dniCompHtml(calculateDNI(number), addr);
						Utils.showPage(IN_LINE_OK, HEADER, html, pw);

					} else {

						html = Pages.errorDniHtml(addr);
						Utils.showPage(IN_LINE_ERROR, HEADER, html, pw);

					}

				} else {

					html = Pages.errorHtml();
					Utils.showPage(IN_LINE_ERROR, HEADER, html, pw);
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
