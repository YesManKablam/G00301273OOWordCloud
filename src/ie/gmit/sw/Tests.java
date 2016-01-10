package ie.gmit.sw;

import org.junit.*;

public class Tests {
	
	@Test
	public void workingInput() throws Exception {
		ReallySimpleWordCloud simple = new ReallySimpleWordCloud("./test.txt", "test.png");
	}
	
	@Test
	public void parseFile() throws Exception {
		Parser p = new Parser("./test.txt");
	}
	
	@Test
	public void stopTest() throws Exception {
		StopWords sw = new StopWords();
		sw.parse("./stopwords.txt");
	}
	
	@Test(expected=Exception.class)
	public void nullFile() throws Exception{
		Parser p = new Parser(null);
	}
}
