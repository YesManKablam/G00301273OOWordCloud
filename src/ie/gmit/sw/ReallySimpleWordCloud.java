package ie.gmit.sw;

import java.awt.*;
import java.awt.image.*; 
import javax.imageio.*; 
import java.io.*;
import java.util.HashMap;
import java.util.Random; 

public class ReallySimpleWordCloud implements CloudInterface{  
	
	private Parser p; // This will allow us to get the wordHash with all of our filtered words.
	private Graphics graphics;
	private Color c;
	private BufferedImage image;
	private final Random rand = new Random();
	
	public ReallySimpleWordCloud(String inFile, String outFile) throws Exception {
		super();
		p = new Parser(inFile);
		wordCloud(outFile);
	}
	
	public void wordCloud(String outFile) throws Exception{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map = p.getWordHash();					
		image = new BufferedImage(1920, 1080, BufferedImage.TYPE_4BYTE_ABGR);
		graphics = image.getGraphics();
		
		// This will draw a black background behind everything, otherwise it will be transparent. 
		// If left as transparent, sometimes you will not be able to see words as they may be the same colour as the background
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, 1920, 1080);
		
		// This will cycle through the HashMap 
		int x = 0, y=100, i=0, count=0;
		for (String word : map.keySet()) {
			if (map.get(word) > 1 && i < map.size()) {
				int fontHeight = drawWord(word, map.get(word),x, y);
				//System.out.println(word);
				y+=fontHeight;
				i++;
				
				// After a certain number of words are written out vertically,
				// words are then moved on the x coordinate and the counter resets
				count++;
				if(count >= 25){
					x+=250;
					y=100;
					count = 0;
				}
			}
		}
		ImageIO.write(image, "png", new File(outFile));
	}
	
	public int drawWord(String word, int wordFreq, int x, int y) {
		int fontSize = (int)(Math.log(wordFreq)*20);	
		Font font = new Font(Font.DIALOG_INPUT, Font.PLAIN, fontSize);
		c = randColour();
		graphics.setColor(c);
		graphics.setFont(font);
		
		// Spacing between words
		FontMetrics fm = graphics.getFontMetrics(font);
		int height = fm.getHeight();
		graphics.drawString(word + "", x, y + fm.getAscent());
		return height;
	}
	
	/*
	 * Generates random numbers, which is then feed into the colour generator.
	 * Rand numbers are limited, so that it only will result in lighter colours, making them easy to see on the dark background
	 */
	public Color randColour() {
		float r = (float) (rand.nextFloat() / 2f + 0.5);
		float g = (float) (rand.nextFloat() / 2f + 0.5);
		float b = (float) (rand.nextFloat() / 2f + 0.5);
		
		Color colour = new Color(r, g, b);
		return colour;
	}
}