package analyzers;

import java.util.HashMap;
import java.util.Map;

public class TransitionTable<T> {
	/*
	 * transitions.get(fromstate) gives you a map M
	 * M.get(tostate) gives you an integer, which tells
	 * you how many times we've seen the transition
	 * fromstate -> tostate
	 */
	Map<T, Map<T, Integer>> transitions;
	
	public TransitionTable() {
		this.transitions = new HashMap<T, Map<T, Integer>>();
	}
	
	public void recordTransition(T from, T to) {
		System.out.print(".");
		Map<T, Integer> toStates = this.transitions.get(from);
		if (toStates == null) {
			toStates = new HashMap<T, Integer>();
			this.transitions.put(from, toStates);
		}
		
		if (toStates.containsKey(to)) {
			toStates.put(to, toStates.get(to) + 1);
		}
		else {
			toStates.put(to, new Integer(1));
		}
	}
	
	public T generateTransition(T c) {
		System.out.print(".");
		Map<T, Integer> toStates = this.transitions.get(c);
		if (toStates == null || toStates.isEmpty()) {
			return c;
		}
		
		int count = 0;
		for (T toState : toStates.keySet()) {
			count += toStates.get(toState);
		}
		//Generate a uniform random variable
		double u = Math.random();
		double curr = 0;
		//Assume the order in which we get the keys is independent from the random variable u
		for (T toState : toStates.keySet()) {
			curr += 1.0*toStates.get(toState)/count; //1.0*... upcasts the integer
			if (curr >= u) {
				return toState;
			}
		}
		
		//This line will never be reached if the laws of probability are "correct"
		return c;
	}
}