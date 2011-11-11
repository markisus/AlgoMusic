package transormers;

import org.jfugue.PatternTransformer;
import org.jfugue.elements.ChannelPressure;
import org.jfugue.elements.Controller;
import org.jfugue.elements.Instrument;
import org.jfugue.elements.Layer;
import org.jfugue.elements.Note;
import org.jfugue.elements.PitchBend;
import org.jfugue.elements.PolyphonicPressure;
import org.jfugue.elements.Tempo;
import org.jfugue.elements.Time;
import org.jfugue.elements.Voice;
/**
 * When you only care about note events which are set by time events
 * The stripper remover everything else
 * In other words, it pulls out only the note timings
 * @author Mark
 *
 */
public class Stripper extends PatternTransformer {
	
	private boolean primed;
	private Time time;

	public Stripper() {
		this.primed = false;
		this.time = new Time(0);
	}
	
	@Override
	public void timeEvent(Time time) {
		this.time = time;
		this.primed = true;
	}
	
	@Override
	public void noteEvent(Note note) {
		this.primed = false;
		this.getResult().add(this.time);
		this.getResult().add(note);
		
	}
	
	@Override
	public void parallelNoteEvent(Note note) {
		this.primed = false;
		this.getResult().add(this.time);
		this.getResult().add(note);
	}
	
	@Override
	public void sequentialNoteEvent(Note note) {
		this.primed = false;
		this.getResult().add(this.time);
		this.getResult().add(note);
	}
	
	@Override
	public void instrumentEvent(Instrument instrument) {
		this.primed = false;
	}

	
	@Override
	public void controllerEvent(Controller controller) {
		this.primed = false;
	}
	
	@Override
	public void voiceEvent(Voice voice) {
		this.primed = false;
	}
	
	@Override
	public void pitchBendEvent(PitchBend pitchBend) {	
		this.primed = false;
	}
	
	
	@Override
	public void channelPressureEvent(ChannelPressure cp) {
		this.primed = false;
	}
	
	@Override
	public void layerEvent(Layer l) {
		this.primed = false;
	}
	
	@Override
	public void polyphonicPressureEvent(PolyphonicPressure polyphonicPressure) {
		this.primed = false;
	}
	
	@Override
	public void tempoEvent(Tempo t) {
		this.primed = false;
		this.getResult().add(this.time);
		this.getResult().add(t);
	}
	
}