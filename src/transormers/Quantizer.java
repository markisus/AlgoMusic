package transormers;

import org.jfugue.PatternTransformer;
import org.jfugue.elements.Time;
/**
 * Locks time events to the grid at intervals specified with the constructor
 * @author Mark
 *
 */
public class Quantizer extends PatternTransformer {
	private long interval;
	
	public Quantizer(long interval) {
		this.interval = interval;
	}
	@Override
	public void timeEvent(Time time) {
		long t = time.getTime();
		getResult().addElement(new Time(t - t%this.interval));
	}
}