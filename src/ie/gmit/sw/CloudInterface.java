package ie.gmit.sw;

import java.awt.Color;

public interface CloudInterface {
	public void wordCloud(String outFile) throws Exception;
	
	public int drawWord(String word, int wordFreq, int x, int y);
	
	public Color randColour();
}
