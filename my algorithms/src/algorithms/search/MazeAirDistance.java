package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeAirDistance implements Heuristic <Position> {

	@Override
	public double h(State<Position> state, State<Position> goal) {
		double sum = Math.pow(goal.getState().getX()-state.getState().getX(),2)+Math.pow(goal.getState().getY()-state.getState().getY(), 2)+ 
						Math.pow(goal.getState().getZ()-state.getState().getZ(), 2);
		sum = Math.sqrt(sum);
		return sum;
	}



}
