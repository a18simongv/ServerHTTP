package htmls;

public class Pages {
	
	private static String header() {
		return "<head><title>Page title</tittle></head>";
	}

	public static String indexHtml() {
		String html="";
		
		html = header()
				+ "<body>"
				+ "<h1>Activity functionalities</h1><br/>"
				+ ""
				+ ""
				+ "</body>";
		
		return html;
	}
	
}
