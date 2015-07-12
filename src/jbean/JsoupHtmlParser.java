package jbean;

import java.io.IOException;

import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupHtmlParser {

	public static void main(String[] args) {
		System.out.println("Type your search query");

		String query = new Scanner(System.in).nextLine().replace(" ", "+");

		System.out.println("Running jSoupHtmlParser on query: " + query);
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
				System.out.println("\ntitle : " + title);

				// yahoo = "h3.title > a"
				// bing = "h2 a"
				String selection = null;
				if (searchPage == "https://uk.search.yahoo.com/search?p=") {
					selection = "h3.title > a";
				} else if (searchPage == "https://www.bing.com/search?q="){
					selection = "h2 a";
				}

				Elements links = doc.select(selection);
				System.out.println("length of links: " + links.size());
				for (Element link : links) {

					// get the value from href attribute
					System.out.println("\ntext : "
							+ Jsoup.parse(tempSearch).body().text());
					System.out.println("linkHref : " + link.attr("href"));
					System.out.println("linkText : " + link.text());
					System.out.println("hasClass : " + link.hasClass("r"));
					System.out.println("absHref: " + link.attr("abs:href"));
					System.out.println("tagName : " + link.tagName());

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
