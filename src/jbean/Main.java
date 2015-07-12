package jbean;

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

		jshtml.jsouphtml();

	}

}
