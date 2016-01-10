package ie.gmit.sw;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileInputStream;

/*	
 * 	This is also based of the QuadgramMap from the Distributed Systems Project
 */	

//This extends stopwords, as there is no point in populating the map with words you are later going to remove. So, they're going to be filtered put before they get added.
public class Parser extends StopWords{	
	private HashMap<String, Integer> wordHash = new HashMap<String, Integer>();
	
	public Parser(String file) throws Exception {
		super();
		readFile(file);
	}
	
	public HashMap<String, java.lang.Integer> readFile(String file) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StringBuffer sb = new StringBuffer();
		
		int j;
		while((j = br.read()) != -1) {
			char next = (char)j;		
			if (next >= 'A' && next <= 'z' || next == '\''){
				sb.append(next);
			}
			else {
				String word = sb.toString().toLowerCase();
				sb = new StringBuffer();
				
				// This will add words and how often they occur to the hashMap, which can then be called by other classes
				if(!compare(word) && word.length() > 0){
					int freq = 0;	
					if(wordHash.containsKey(word)){
						freq = wordHash.get(word);				
					}
					freq++;
					wordHash.put(word, freq);
				}
			}
		}	
		br.close();
		setWordHash(wordHash);
		return wordHash;
	}
	
	public HashMap<String, Integer> getWordHash() {
		return wordHash;
	}
	
	private void setWordHash(HashMap<String, Integer> wordHash) {
		this.wordHash = wordHash;
	}
}
