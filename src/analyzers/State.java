package analyzers;
/**
 * Represents a state inside a Markov chain
 * @author Mark
 *
 */
public class State<T> {
	private T data;

	public State(T data) {
		this.data = data;
	}
	
	public T data() {
		return this.data;
	}
}