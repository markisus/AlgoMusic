import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import org.jfugue.Pattern;
import org.jfugue.PatternInterface;
import org.jfugue.Player;

import transormers.Voicer;

/**
 * This class separates out the voices of a midi,
 * and saves them. The idea is that you should listen
 * to all the voices and intuit which one will sound
 * the best when randomized (usually the melody line)
 * @author Mark
 *
 */
public class Preprocessor {
	
	public static void main(String[] args) {
		String file_path = "California_Girls.mid";
		Player player = new Player();
		PatternInterface song;
		try {
			song = player.loadMidi(new File(file_path));
			for (int voice=0; voice<16; voice++) {
				Voicer v = new Voicer(voice);
				PatternInterface voicedSong = v.transform((Pattern)song);
				player.saveMidi(voicedSong, new File("voice" + voice + ".mid"));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidMidiDataException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}