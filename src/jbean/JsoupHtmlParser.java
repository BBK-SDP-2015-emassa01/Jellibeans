package jbean;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupHtmlParser {
	String query = null;

	public void jsouphtml() {

		// System.out.println("Type your search query");
		//
		// String query = new Scanner(System.in).nextLine().replace(" ", "+");
		//
		// System.out.println("Running jSoupHtmlParser on query: " + query);
		Document doc;
		try {

			String[] searchPages = { "https://uk.search.yahoo.com/search?p=",
					"https://www.bing.com/search?q=" };
			for (String searchPage : searchPages) {
				String tempSearch = searchPage + query;

				// need http protocol
				doc = Jsoup.connect(tempSearch).get();

				// get page title
				String title = doc.title();
				// System.out.println("\ntitle : " + title);

				// yahoo = "h3.title > a"
				// bing = "h2 a"
				String selection = null;
				if (searchPage == "https://uk.search.yahoo.com/search?p=") {
					selection = "h3.title > a";
				} else if (searchPage == "https://www.bing.com/search?q=") {
					selection = "h2 a";
				}

				Elements links = doc.select(selection);

				try {
					String filename = "AllLinks.txt";
					FileWriter fw = new FileWriter(filename, true); // the true
																	// will
																	// append
																	// the new
																	// data

					fw.write("\n\nOutput from: " + searchPage + query +"\n");
					for (Element link : links) {

						// get the value from href attribute
						// System.out.println("\ntext : "
						// + Jsoup.parse(tempSearch).body().text());
						fw.write(link.attr("href") + "\n");
						// System.out.println("linkText : " + link.text());
						// System.out.println("hasClass : " +
						// link.hasClass("r"));
						// System.out.println("absHref: " +
						// link.attr("abs:href"));
						// System.out.println("tagName : " + link.tagName());

					}

					fw.close();
				} catch (IOException ioe) {
					System.err.println("IOException: " + ioe.getMessage());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	System.out.println("Completed.");
	}

}
