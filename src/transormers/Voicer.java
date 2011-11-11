package transormers;

import org.jfugue.PatternTransformer;
import org.jfugue.elements.Note;
import org.jfugue.elements.Time;
import org.jfugue.elements.Voice;
/**
 * Selects out a single voice (1 .. 15) specified by the constructor
 * @author Mark
 *
 */
public class Voicer extends PatternTransformer {
	private int voice;
	private boolean on;
	
	public Voicer(int voice) {
		this.voice = voice;
		this.on = false;
	}
	
	@Override
	public void voiceEvent(Voice voice) {
		boolean voiceMatch = voice.getVoice() == this.voice;
		if (this.on && (!voiceMatch)) this.on = false;
		else if ((!this.on) && voiceMatch) this.on = true;
		if (this.on) {
			getResult().addElement(voice);
		}
	}
	
	@Override
	public void timeEvent(Time time) {
		if (this.on) {
			super.timeEvent(time);
		}
	}
	
	@Override
	public void noteEvent(Note note) {
		if (this.on) {
			super.noteEvent(note);
		}
	}
}