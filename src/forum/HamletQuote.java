package forum;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class HamletQuote {
	public ArrayList<String> hamlet;
	
	public String getQuoteFromHamlet() {
		if(hamlet == null){
			hamlet = new ArrayList<String>();
			try{
				InputStream is = this.getClass().getResourceAsStream("Hamlet.txt");
				Scanner s = new Scanner(is);
				while(s.hasNextLine()){
					String line = s.nextLine();
					if(! line.isEmpty())
						hamlet.add(line);
				}
				s.close();
			} catch (Exception e) {
				return  "HAMLET\n" +
						"    To be, or not to be: that is the question:\n" +
						"    Whether 'tis nobler in the mind to suffer\n" +
						"    The slings and arrows of outrageous fortune,\n" +
						"    Or to take arms against a sea of troubles,\n" +
						"    And by opposing end them?";
			}
		}
		//At this point the text has been located and is stored in the arraylist.
		//Now, make a random selection
		String randomQuote = "";
		int rnd;
		while(true){
			rnd = (int)(Math.random() * (hamlet.size()-14));
			if(! hamlet.get(rnd).startsWith(" "))
				break;
		}
		int lineMin = 0;
		while(lineMin< 10 || hamlet.get(rnd+lineMin).startsWith(" ")){  //All lines of spoken dialogue begin with spaces
			randomQuote += hamlet.get(rnd+lineMin) +"\n";
			lineMin ++;
		}
		return randomQuote;
	}

	
	
}
