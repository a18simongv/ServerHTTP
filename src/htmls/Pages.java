package htmls;

public class Pages {
	
	private static int port = 8090;
	
	public static String dniLetters() {
		return "TRWAGMYFPDXBNJZSQVHLCKE";
	}
	
	private static String header() {
		return "<head><title>Page title</title></head>";
	}

	public static String indexHtml() {
		String html="";
		
		html = header()
				+ "<body>"
				+ "<h1>Activity functionalities</h1>"
				+ "<p>Options:</p>"
				+ "<pre>	1.<a href='http://localhost:"+port+"/dni'>Calculate letter of DNI</a></pre>"
				+ "</body>";
		
		return html;
	}
	
	public static String dniHtml() {
		String html="";
		
		html = header()
				+ "<body>"
				+ "<h1>Activity functionalities</h1>"
				+ "<p>Options:</p>"
				+ "<pre>	1.Write your dni number on browser bar like "
				+ "<a href='http://localhost:"+port+"/dni=35879559'>Example: http://localhost:"+port+"/dni=35879559</a></pre>"
				+ "<pre>	2.<a href='http://localhost:"+port+"/'>Go back</a></pre>"
				+ "</body>";
		
		return html;
	}
	
	public static String dniCompHtml(String dni) {
		String html="";
		
		html = header()
				+ "<body>"
				+ "<h1>Corresponding dni letter</h1>"
				+ "<h5>" + dni + "</h5>"
				+ "</body>";
		
		return html;
	}
	
	public static String errorHtml() {
		String html="";
		
		html = header()
				+ "<body>"
				+ "<h1>&#33;ERROR! Page didn't found</h1>"
				+ "<p>The url you wrote isn't in our server</p>"
				+ "</body>";
		
		return html;
	}
	
}
