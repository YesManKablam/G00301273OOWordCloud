package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*	This is based of the QuadgramMap from the
 * 	Distributed Systems project, but with an ArrayList
 */	

public class StopWords {
	private static ArrayList<String> stopWords = new ArrayList<String>();

	public StopWords() throws Exception {
		super();
		parse("./stopwords.txt"); // File is hardcoded, as this is the only file that is going to be used for the stopwords.
	}
	
	public void parse(String FileName) throws Exception {
 		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(FileName)));
 		StringBuffer sb = new StringBuffer();
 		
 		int i;
 		while((i = br.read()) != -1){
 			char next = (char) i;
 			
 			if(next != '\n')
 				sb.append(next);
 			
 			else{
 				String stopWord = sb.toString().toLowerCase();
 				sb = new StringBuffer();
 				stopWords.add(stopWord);
 			}
 		}
 		br.close();
	}
	
	public boolean compare(String word){
		if(stopWords.contains(word))
			return true;	
		else
			return false;
	}
}
