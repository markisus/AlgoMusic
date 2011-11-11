package transormers;

import org.jfugue.PatternTransformer;
import org.jfugue.elements.Time;

public class Slower extends PatternTransformer {
	@Override
	public void timeEvent(Time time) {
		getResult().addElement(new Time((long) (time.getTime()*1.1)));
	}
}