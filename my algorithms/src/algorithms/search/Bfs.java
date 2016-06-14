package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class Bfs<T> extends CommonSearcher<T> {
	
	

	public Bfs() {
		super(new StateCostComparator<T>()); //states get into the queue by their cost
	}
	@Override
	public Solution<T> Search(Searchable<T> s) {
		addToOpenList(s.getInitialState());
		HashSet<State<T>> closedSet = new HashSet<>();
		
		while(openList.size()>0){
			State<T> n = popOpenList();
			closedSet.add(n);
			
			if(n.equals(s.getGoalState())){
				return backtrace(n,s.getInitialState());
			}
			ArrayList<State<T>> successors = s.getAllPossibleStates(n);
			for(State<T> state : successors){
				if(!closedSet.contains(state) && !openListContains(state)){
					addToOpenList(state);
				}
				else{
					if(openListContains(state)){
						if(state.getCost() < getState(state).getCost()){
							openList.remove(state);
							state.setCameFrom(n);
							addToOpenList(state);
						}
					}
				}
			}
		}
		return null;
	}
	protected Solution<T> backtrace(State<T> goal,State<T> initial){
		State<T> currentState = goal;
		ArrayList<T> tempSolution = new ArrayList<>();
		while(currentState != null){
			tempSolution.add(currentState.getState());
			currentState = currentState.getCameFrom();
		}
		Collections.reverse(tempSolution);//reversing the order so we'll get the solution in the right way
		Solution<T> mySolution = new Solution<>(tempSolution);
		return mySolution;
	}

}
