package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searchable;
import algorithms.search.State;

public class Maze3dSearchable implements Searchable<Position> {

	private Maze3d maze;
	
	public Maze3dSearchable(Maze3d maze) {
		this.maze = maze;
	}
	
	@Override
	public State<Position> getInitialState() {
		State<Position> initial = new State<Position>(maze.getStartPosition());
		initial.setCameFrom(null); // we set cameFrom to null
		initial.setCost(0); //it costs 0 to reach the start
		return initial;
	}

	@Override
	public State<Position> getGoalState() {
		State<Position> goal = new State<Position>(maze.getGoalPosition());
		return goal;
	}

	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {
		ArrayList<State<Position>> arrayPossibleMoves = new ArrayList<>();
		String[] possibleMoves = maze.getPossibleMoves(s.getState());
		StringBuilder sb;
		for(String move : possibleMoves){
			sb = new StringBuilder(move);// enter move in this format: {x,y,z}
			sb.deleteCharAt(0); //deleting {
			sb.deleteCharAt(sb.length()-1); //deleting }
			String[] str = sb.toString().split(","); // Splitting x,y,z by ,
			Position current = new Position(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
			State<Position> currentState = new State<Position>(current); //creating a state wrapping current
			currentState.setCameFrom(s); // we got this successor from s
			currentState.setCost(s.getCost()+1); //setting its cost to s cost plus 1	
			arrayPossibleMoves.add(currentState); //adding this move to the arrayList
		}
		return arrayPossibleMoves;
	}
}
