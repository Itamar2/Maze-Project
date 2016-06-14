package algorithms.search;

import java.util.ArrayList;

public class Solution<T> {
	private ArrayList<T> path;
	
	public Solution(ArrayList<T> path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return path.toString();
	}

	public ArrayList<T> getArraySolution() {
		return path;
	}
	public String getStrSolution(){
		return this.toString();
	}
}
