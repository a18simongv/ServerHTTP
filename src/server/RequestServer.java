package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

import htmls.Pages;

public class RequestServer extends Thread {

	private final String IN_LINE_OK = "HTTP/1.1 200 OK";
	private final String IN_LINE_ERROR = "HTTP/1.1 404 Not Found";
	private final String HEADER = "Content-Type:text/html;charset:UTF-8";

	private Socket socket;

	public RequestServer(Socket socket) {
		this.socket = socket;
	}

	//returns dni with his letter
	private String calculateDNI(String number) {
		String dni = "";
		
		int numDni = Integer.parseInt(number);
		dni = number + "-" + Pages.dniLetters().charAt( numDni%23 );
		
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
					
					html = Pages.indexHtml();
					pw.println(IN_LINE_OK);
					pw.println(HEADER);
					pw.println("Content-length:" + html.length() + "\n");
					pw.println(html);
					
				} else if (page.equals("/dni")) {
					
					html = Pages.dniHtml();
					pw.println(IN_LINE_OK);
					pw.println(HEADER);
					pw.println("Content-length:" + html.length() + "\n");
					pw.println(html);
					
				} else if (page.contains("/dni=")) {
					
					String number = page.substring(page.indexOf("=")+1);
					boolean correctDni = Pattern.matches("[0-9]{8}", number);
					
					if(correctDni) {
						
						html = Pages.dniCompHtml( calculateDNI(number) );
						pw.println(IN_LINE_OK);
						pw.println(HEADER);
						pw.println("Content-length:" + html.length() + "\n");
						pw.println(html);
						
					} else {
						
						html = Pages.errorHtml();
						pw.println(IN_LINE_ERROR);
						pw.println(HEADER);
						pw.println("Content-length:" + (html.length()) + "\n");
						pw.println(html);
						
					}
					
				} else {
					
					html = Pages.errorHtml();
					pw.println(IN_LINE_ERROR);
					pw.println(HEADER);
					pw.println("Content-length:" + (html.length()) + "\n");
					pw.println(html);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
