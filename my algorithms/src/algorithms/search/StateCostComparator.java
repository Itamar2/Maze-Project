package algorithms.search;

import java.util.Comparator;

public class StateCostComparator<T> implements Comparator<State<T>> {

	@Override
	public int compare(State<T> arg0, State<T> arg1){
		return Double.compare(arg0.getCost(), arg1.getCost());
	}	
}
