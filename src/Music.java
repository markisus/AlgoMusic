import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.jfugue.Pattern;
import org.jfugue.PatternInterface;
import org.jfugue.Player;
import org.jfugue.parsers.MusicStringParser;

import transormers.Collapser;
import transormers.Quantizer;
import transormers.Stripper;
import analyzers.StatsMaker;


class Music {
	public static void main(String[] args) {
		Map<String, Double> fudgefactors = new HashMap<String, Double>();
		fudgefactors.put("jazz", .15);
		fudgefactors.put("jazz2", .15);
		fudgefactors.put("rockingboat", .15);
		fudgefactors.put("funky", .1);
		fudgefactors.put("country", .25);
		fudgefactors.put("clear", .15);
		fudgefactors.put("bach", .15);
		Player player = new Player();
		try {
			System.out.println("Please type a choice from the following list: ");
			System.out.println(fudgefactors.keySet());
			
			Scanner k = new Scanner(System.in);
			String choice = k.nextLine();
			
			System.out.println("You selected " + choice);
			
			PatternInterface song = player.loadMidi(new File(choice + ".mid"));
			//Strip the song of voice info, instrument data, controller data, and other stuff
			Stripper st = new Stripper();
			System.out.println("...Parsing note data...");
			song = st.transform((Pattern)song);
			//Slower s = new Slower();
			//song = s.transform((Pattern)song);
			System.out.println("...Quantizing...");
			Quantizer q = new Quantizer(5);
			song = q.transform((Pattern)song);
			//Collapse long silences
			System.out.println("...Collapsing...");
			Collapser c = new Collapser(1000);
			song = c.transform((Pattern)song);
			System.out.println("...Generating Markov Chain...");
			MusicStringParser msp = new MusicStringParser();
			StatsMaker smkr = new StatsMaker(fudgefactors.get(choice));
			msp.addParserListener(smkr);
			msp.parse(song);
			song = smkr.generateSample(250);
			player.play(song);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

