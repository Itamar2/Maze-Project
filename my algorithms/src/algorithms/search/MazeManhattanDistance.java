package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeManhattanDistance implements Heuristic<Position> {

	@Override
	public double h(State<Position> state, State<Position> goal) {
		
		double heuristicValue = (goal.getState().getX()-state.getState().getX())+(goal.getState().getY()-state.getState().getY())
				+(goal.getState().getZ()-state.getState().getZ());
		
		return heuristicValue;
	}


}
