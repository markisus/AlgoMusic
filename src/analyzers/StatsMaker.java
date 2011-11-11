package analyzers;

import org.jfugue.ParserListenerAdapter;
import org.jfugue.Pattern;
import org.jfugue.elements.Note;
import org.jfugue.elements.Time;

public class StatsMaker extends ParserListenerAdapter {
	private TransitionTable<Byte> notes;
	private TransitionTable<Long> deltas;
	private Note startNote;
	private Note currentNote;
	private long currentDelta;
	private long startDelta;
	private Time currentTime;
	private double fudgeFactor = .15;
	
	public StatsMaker(double fudgeFactor) {
		this.currentNote = null;
		this.currentDelta = 0;
		this.currentTime = null;
		this.notes = new TransitionTable<Byte>();
		this.deltas = new TransitionTable<Long>();
		this.startNote = null;
		this.startDelta = -1;
		this.fudgeFactor = fudgeFactor;
	}
	
	public StatsMaker() {
		this.currentNote = null;
		this.currentDelta = 0;
		this.currentTime = null;
		this.notes = new TransitionTable<Byte>();
		this.deltas = new TransitionTable<Long>();
		this.startNote = null;
		this.startDelta = -1;
	}

	
	@Override
	public void noteEvent(Note note) {
		if (this.currentNote != null) {
			this.notes.recordTransition(this.currentNote.getValue(), note.getValue());
		}
		else {
			this.startNote = note;
		}
		this.currentNote = note;
	}
	
	@Override
	public void timeEvent(Time time) {
		if (this.currentTime != null) {
			long newDelta = time.getTime() - currentTime.getTime();
			if (this.startDelta == -1) this.startDelta = newDelta;
			
			//(Try to) ignore transitions to 0
			//if (newDelta == 0) newDelta = this.startDelta;
			this.deltas.recordTransition(this.currentDelta, newDelta);
	
			this.currentDelta = newDelta;
		}
		
		this.currentTime = time;
	}
	/**
	 * Generate a random pattern, given the current data inside notes and times
	 * @param n the number of notes in this pattern
	 */
	public Pattern generateSample(int n) {
		Pattern p = new Pattern();
		long currentTime = 0;
		long currentDelta = this.startDelta;
		byte currentNote = this.startNote.getValue();
		for (int i=0; i<n; i++) {
			currentNote = this.notes.generateTransition(currentNote);
			currentDelta = this.deltas.generateTransition(currentDelta);
			Time time = new Time(currentTime);
			currentTime += currentDelta*fudgeFactor; //Speed up or slow down the music to make it sound nice
			Note note = new Note(currentNote);
			p.addElement(time);
			p.addElement(note);
			
		}
		return p;
	}
}