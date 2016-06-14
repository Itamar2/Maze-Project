package algorithms.search;


import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {

	protected PriorityQueue<State<T>> openList;
	private int evaluatedNodes;
	
	public CommonSearcher(Comparator<State<T>> comp) {
		openList = new PriorityQueue<>(comp);
		evaluatedNodes = 0;
	}
	protected State<T> popOpenList(){
		evaluatedNodes++;
		return openList.poll();
	}
	protected void addToOpenList(State<T> t){
		openList.add(t);
	}
	protected boolean openListContains(State<T> t){
		return openList.contains(t);
	}
	protected State<T> getState(State<T> t){
		for(State<T> state : openList){
			if(state.equals(t)){
				return state;
			}
		}
		return null;
	}
	@Override
	public abstract Solution<T> Search(Searchable<T> s);

	@Override
	public int getNumberOfNodesEvaluated() {

		return evaluatedNodes;
	}
}
