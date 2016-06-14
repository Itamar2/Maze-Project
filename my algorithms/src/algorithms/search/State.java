package algorithms.search;

public class State<T> {
	private T state;
	private double cost;
	private State<T> cameFrom;
	
	public State(T state) {
		this.state = state;
	}
	/*
	 * getters and setters
	 */
	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		return state.equals(((State<T>) obj).state);
	}
	@Override
	public int hashCode() {
		return state.toString().hashCode();
	}
	@Override
	public String toString() {
		return state.toString();
	}
	
	

}
