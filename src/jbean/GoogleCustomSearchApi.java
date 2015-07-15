package jbean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Esha
 */
public class GoogleCustomSearchApi {
	
	String qry=null;
	
    public void cse() throws IOException {

	    String key="AIzaSyCnAIDiZchNkR0OTBH3NMMNt4GmRiwpdnA";//server key
	    
	    String cx = "008818185974073145685:ga_fmgk9gf0";
	    URL url = new URL(
	            "https://www.googleapis.com/customsearch/v1?key="+key+ "&cx="+ cx +"&q="+ qry + "&alt=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setRequestProperty("Accept", "application/json");
	    BufferedReader br = new BufferedReader(new InputStreamReader(
	            (conn.getInputStream())));

	    String output;
	    PrintWriter writer = new PrintWriter("AllLinks.txt", "UTF-8");
	    writer.println("Output from www.google.com/search?q="+qry+"\n");
	    while ((output = br.readLine()) != null) {

	        if(output.contains("\"link\": \"")){                
	            String link=output.substring(output.indexOf("\"link\": \"")+("\"link\": \"").length(), output.indexOf("\","));
	            writer.println(link);       //Will print the google search links
	        }     
	    }
	    writer.close();
	    conn.disconnect();                              
	}


}



