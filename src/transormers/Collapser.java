package transormers;

import org.jfugue.PatternTransformer;
import org.jfugue.elements.Time;
/**
 * Strips out long periods of time where there are no time events in the pattern
 * This is useful when all notes are specified with time events and the pattern
 * has been run through the Voicer transformer
 * @author Mark
 *
 */
public class Collapser extends PatternTransformer {
	private long interval;
	private long lastTimeEvent;
	private int cursor;
	
	public Collapser(long interval) {
		this.interval = interval;
		this.lastTimeEvent = 0;
		this.cursor = 0;
	}
	
	@Override
	public void timeEvent(Time time) {
		long t = time.getTime();
		long delta = t - this.lastTimeEvent;
		if (delta >= 0) {
			if (delta > this.interval) {
				//Do nothing (?)
				//Increase cursor by a small default amount (?)
			}
			else {
				//The delta is small, increase the cursor
				this.cursor += delta;
			}
			getResult().addElement(new Time(this.cursor));
			this.lastTimeEvent = t;
		}
		else {
			//Shit, the midi events are coming out of order. Ignore this event?
		}
	}
}