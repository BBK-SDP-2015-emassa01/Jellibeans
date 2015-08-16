package jbean;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {

		System.out.println("Type your search query");

		@SuppressWarnings("resource")
		String query = new Scanner(System.in).nextLine().replace(" ", "+");

		System.out.println("Running search on query: " + query);

		GoogleCustomSearchApi gcse = new GoogleCustomSearchApi();
		gcse.qry = query;

		try {
			gcse.cse();
		} catch (Exception e) {
			System.out
					.println("There was an error in the Google Custom Search method.");
			e.printStackTrace();
		}

		JsoupHtmlParser jshtml = new JsoupHtmlParser();
		jshtml.query = query;

		jshtml.jsouphtmlYahoo();
		jshtml.jsouphtmlBing();
		// linkArrayList.addAll(jshtml.jSoupLinks);

		System.out.println("Completed.");

		ArrayList<String> linkArrayListYahoo = jshtml.jSoupYahooLinks;
		ArrayList<String> linkArrayListBing = jshtml.jSoupBingLinks;
		ArrayList<String> linkArrayListGoogle = gcse.jSoupGoogleLinks;

		ArrayList<String> linkArrayList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			linkArrayList.add(linkArrayListYahoo.get(i));
			linkArrayList.add(linkArrayListBing.get(i));
			linkArrayList.add(linkArrayListGoogle.get(i));
		}

		PrintWriter writer = new PrintWriter("AllLinks.txt", "UTF-8");
		writer.println("Output from Google, Yahoo and Bing, for query: "
				+ query);
		while (!linkArrayList.isEmpty()) {
			writer.println(linkArrayList.get(0)+"\n");
			linkArrayList.remove(0);
		}
		writer.close();

	}

}
