package utils;

import java.io.PrintWriter;

public class Utils {

	private static final String HEADER = "Content-Type:text/html;charset:UTF-8";

	// put the html string in client output
	public static void showPage(String firstLine, String html, PrintWriter pw) {

		pw.println(firstLine);
		pw.println(HEADER);
		pw.println("Content-length:" + html.length() + "\n");
		pw.println(html);

	}

}
