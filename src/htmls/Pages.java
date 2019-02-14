package htmls;

//Strings with html of pages
public class Pages {

	private static int port = 8090;

	public static String dniLetters() {
		return "TRWAGMYFPDXBNJZSQVHLCKE";
	}

	private static String header() {
		return "<head><title>Page title</title></head>";
	}

	public static String indexHtml(String addrs) {
		String html = "";

		html = header() + "<body>" + "<h1>Activity functionalities</h1>" + "<p>Options:</p>"
				+ "<pre>	1.<a href='http://" + addrs + ":" + port + "/dni'>Calculate letter of DNI</a></pre>"
				+ "</body>";

		return html;
	}

	public static String dniHtml(String addrs) {
		String html = "";

		html = header() + "<body>" + "<h1>Activity functionalities</h1>" + "<p>Options:</p>"
				+ "<pre>	1.Write your dni number on browser bar like " + "<a href='http://" + addrs + ":" + port
				+ "/dni=35879559'>Example: http://" + addrs + ":" + port + "/dni=35879559</a></pre>"
				+ "<pre>	2.<a href='http://" + addrs + ":" + port + "/'>Go back</a></pre>" + "</body>";

		return html;
	}

	public static String dniCompHtml(String dni, String addrs) {
		String html = "";

		html = header() + "<body>" + "<h1>Corresponding dni letter</h1>" + "<h2><pre>		" + dni + "</pre></h2>"
				+ "<p><a href='http://" + addrs + ":" + port + "/'>Go back</a></p>" + "</body>";

		return html;
	}

	public static String errorHtml(String addrs) {
		String html = "";

		html = header() + "<body>" + "<h1>&#33;ERROR! Page didn't found</h1>"
				+ "<p>The url you wrote isn't in our server</p>" + "<p><a href='http://" + addrs + ":" + port
				+ "/'>Go back</a></p>" + "</body>";

		return html;
	}

	public static String errorDniHtml(String addrs) {
		String html = "";

		html = header() + "<body>" + "<h1>&#33;ERROR! DNI didn't valid</h1>" + "<p><a href='http://" + addrs + ":"
				+ port + "/'>Go back</a></p>" + "</body>";

		return html;
	}

}
