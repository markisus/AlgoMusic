package analyzers;

import java.util.HashMap;

import org.jfugue.ParserListenerAdapter;
import org.jfugue.PatternInterface;
import org.jfugue.elements.Note;
import org.jfugue.elements.Time;

/**
 * Given a pattern, tries to infer a Markov chain for it
 * @author Mark
 *
 */
public class MarkovMaker {
	private PatternInterface pattern;

	
	public MarkovMaker(PatternInterface pattern) {
		this.pattern = pattern;
		//2D array for transition function
			//State i: Current state
			//State j: Next state
			//Value(toString(i,j)): Probability of transitioning
	}
	

	
}