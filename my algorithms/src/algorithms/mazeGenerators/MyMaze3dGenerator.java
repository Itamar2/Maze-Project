package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Stack;

public class MyMaze3dGenerator extends CommonMaze3dGenerator {

	Stack<MyPosition> path;
	
	public MyMaze3dGenerator() {
		path = new Stack<>();
	}
	@Override
	public Maze3d generate(int level, int row, int column) {
		
		ArrayList<Directions> possibleMoves;
		maze = createInitializedMaze(level, row, column);
		MyPosition currentCell = randomizedPosition(level,row,column); //choose a random cell
		MyPosition goalPosition = currentCell,startPosition = null;
		int visitedCells = 1,guess;
		getCell(currentCell).setVisited(true); // this cell was visited
		
		while(visitedCells < level*row*column){
			possibleMoves = unvisitedNeighbors(currentCell); // we get the directions we can choose
			if(possibleMoves.size()>0){ // if there're directions to choose
				guess = randomGenerator.nextInt(possibleMoves.size()); //random one direction
				breakWalls(currentCell,possibleMoves.get(guess));
				path.push(currentCell);
				currentCell = currentCell.getNeighbor(possibleMoves.get(guess));
				getCell(currentCell).setVisited(true);
				visitedCells++;				
				if(visitedCells == level*row*column){
					startPosition = currentCell;
				}
			}
			else{ //if have no direction, we backtrack
				currentCell = path.pop();
			}
		}
		return binaryMaze(startPosition, goalPosition);
	}

}
