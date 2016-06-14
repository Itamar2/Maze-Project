package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public abstract class CommonMaze3dGenerator implements Maze3dGenerator {
	
	protected Cell[][][] maze;
	protected Random randomGenerator;
	
	public CommonMaze3dGenerator() {
		randomGenerator = new Random();
	}
	@Override
	public abstract Maze3d generate(int level, int row, int column);
	
	@Override
	public String measureAlgorithmTime(int level, int row, int column) {
		
		long sample1 = System.currentTimeMillis();
		generate(level, row, column);
		long sample2 = System.currentTimeMillis();
		return "time: "+(sample2-sample1)+" milliseconds";
	}	
	protected Cell[][][] createInitializedMaze(int x,int y,int z){
		maze = new Cell[x][y][z];
		for(int i = 0;i<x;i++){
			for(int j=0;j<y;j++){
				for(int k=0;k<z;k++){
					maze[i][j][k] = new Cell();
				}
			}
		}
		return maze;		
	}
	protected Cell getCell(MyPosition p){
		return maze[p.getX()][p.getY()][p.getZ()];
	}
	/**
	 * Give us the possible moves we can do from a given myPosition
	 * @param p - myPosition
	 * @return ArrayList<Directions> - ArrayList of possible moves which contains Directions enum type
	 * @see {@link Directions}, {@link MyPosition}
	 */
	protected ArrayList<Directions> unvisitedNeighbors(MyPosition p){
		ArrayList<Directions> moves = new ArrayList<>();
		//checking if there's right neighbor and not visited yet
		if(p.getZ()+1<maze[0][0].length && maze[p.getX()][p.getY()][p.getZ()+1].isVisited() == false){
			moves.add(Directions.RIGHT);
		}
		//checking if there's a left neighbor and not visited yet
		if(p.getZ()-1 >= 0 && maze[p.getX()][p.getY()][p.getZ()-1].isVisited() == false){
			moves.add(Directions.LEFT);
		}
		//checking if there's a forward neighbor and not visited yet
		if(p.getY()-1>= 0 && maze[p.getX()][p.getY()-1][p.getZ()].isVisited() == false){
			moves.add(Directions.FORWARD);
		}
		//checking if there's a backward neighbor and not visited yet
		if(p.getY()+1<maze[0].length && maze[p.getX()][p.getY()+1][p.getZ()].isVisited() == false){
			moves.add(Directions.BACKWARD);
		}
		//checking if there's a upper neighbor and not visited yet
		if(p.getX()+1 <maze.length && maze[p.getX()+1][p.getY()][p.getZ()].isVisited() == false){
			moves.add(Directions.UP);
		}
		//checking if there's a down neighbor and not visited yet
		if(p.getX()-1 >=0 && maze[p.getX()-1][p.getY()][p.getZ()].isVisited() == false){
			moves.add(Directions.DOWN);
		}
		return moves;
	}
	
	protected void breakWalls(MyPosition p,Directions d){	
		switch(d){
		case BACKWARD:
			getCell(p).setBackward(false);
			getCell(p.getNeighbor(d)).setForward(false);
			break;
		case DOWN:
			getCell(p).setDown(false);
			getCell(p.getNeighbor(d)).setUp(false);
			break;
		case FORWARD:
			getCell(p).setForward(false);
			getCell(p.getNeighbor(d)).setBackward(false);
			break;
		case LEFT:
			getCell(p).setLeft(false);
			getCell(p.getNeighbor(d)).setRight(false);
			break;
		case RIGHT:
			getCell(p).setRight(false);
			getCell(p.getNeighbor(d)).setLeft(false);
			break;
		case UP:
			getCell(p).setUp(false);
			getCell(p.getNeighbor(d)).setDown(false);
			break;		
		}
	}
	
	protected MyPosition randomizedPosition(int x,int y,int z){	
		MyPosition randPosition = new MyPosition();
		randPosition.setX(randomGenerator.nextInt(x));
		randPosition.setY(randomGenerator.nextInt(y));
		randPosition.setZ(randomGenerator.nextInt(z));
		return randPosition;		
	}
	protected Maze createMaze(MyPosition start,MyPosition goal){
		Maze myMaze = new Maze(maze);
		myMaze.setStartPosition(start);
		myMaze.setGoalPosition(goal);
		return myMaze;
	}
	protected Maze3d binaryMaze(MyPosition startPosition,MyPosition goalPosition){
		Maze myMaze = createMaze(startPosition, goalPosition);
		Maze3d binaryMaze = new Maze3d(myMaze.getBinaryRepresentation());
		binaryMaze.setGoalPosition(new Position(goalPosition.getX()*2+1,goalPosition.getY()*2+1,goalPosition.getZ()*2+1));
		binaryMaze.setStartPosition(new Position(startPosition.getX()*2+1,startPosition.getY()*2+1,startPosition.getZ()*2+1));
		
		return binaryMaze;
	}

}
