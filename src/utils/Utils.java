package utils;

import java.io.PrintWriter;

public class Utils {

	public static void showPage(String firstLine, String header,String html, PrintWriter pw) {
		
		pw.println(firstLine);
		pw.println(header);
		pw.println("Content-length:" + html.length() + "\n");
		pw.println(html);
		
	}
	
}
