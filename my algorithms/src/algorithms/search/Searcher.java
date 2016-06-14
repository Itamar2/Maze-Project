package algorithms.search;

public interface Searcher<T> {
	public Solution<T> Search(Searchable<T> s);
	public int getNumberOfNodesEvaluated();
}
