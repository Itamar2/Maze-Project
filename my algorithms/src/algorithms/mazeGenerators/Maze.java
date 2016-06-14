package algorithms.mazeGenerators;

/**
 * This class is an object oriented representation of a 3d maze, which will be later converted 
 * into a maze3d object as required.
 * 
 * @see {@link Cell},{@link MyPosition}
 */
public class Maze {
	
	private Cell[][][] maze3d;
	private int levels; // number of levels, start with 1 which is the lowest
	private int rows; //number of rows,start with 1
	private int columns; //number of columns,start with 1
	private MyPosition startPosition;
	private MyPosition goalPosition;
	
	public Maze(Cell[][][] maze) {
		setMaze3d(maze);
		setLevels(maze.length);
		setRows(maze[0].length);
		setColumns(maze[0][0].length);
	}
	/**
	 * Creates a binary representation of the maze.
	 * @return int[][][] -Binary Representation of the maze. 
	 */
	public int[][][] getBinaryRepresentation() {
		
		int[][][] binaryMatrix = new int[2*levels+1][2*rows+1][2*columns+1];
		for(int i=0;i<2*levels+1;i++) {
			for(int j=0;j<2*rows+1;j++) {
				for(int k=0;k<2*columns+1;k++) {
					binaryMatrix[i][j][k] = 1;
				}
			}
		}
		for(int i=0;i<levels;i++) {
			for(int j=0;j<rows;j++) {
				for(int k=0;k<columns;k++) {
						binaryMatrix[(2*i)+1][(2*j)+1][(2*k)+1] = 0; //the cell itself
					
					if(maze3d[i][j][k].isRight() ==true) {
						binaryMatrix[2*i+1][2*j+1][2*k+2] = 1;
					}
					else {
						binaryMatrix[2*i+1][2*j+1][2*k+2] = 0;
					}
					
					if(maze3d[i][j][k].isLeft() == true) {
						binaryMatrix[2*i+1][2*j+1][2*k] = 1;
					}
					else {
						binaryMatrix[2*i+1][2*j+1][2*k] = 0;					
					}
					
					if(maze3d[i][j][k].isForward() == true) {
						binaryMatrix[2*i+1][2*j][2*k+1] = 1;
					}
					else {
						binaryMatrix[2*i+1][2*j][2*k+1] = 0;
					}
					if(maze3d[i][j][k].isBackward() == true) {
						binaryMatrix[2*i+1][2*j+2][2*k+1] = 1;
					}
					else {
						binaryMatrix[2*i+1][2*j+2][2*k+1] = 0;						
					}
					if(maze3d[i][j][k].isUp() == true) {
						binaryMatrix[2*i+2][2*j+1][2*k+1] = 1;
					}
					else {
						binaryMatrix[2*i+2][2*j+1][2*k+1] = 0;						
					}
					if(maze3d[i][j][k].isDown() == true) {
						binaryMatrix[2*i][2*j+1][2*k+1] = 1;
					}
					else {
						binaryMatrix[2*i+2][2*j+1][2*k+1] = 0;				
					}				
				}
			}
		}		
		return binaryMatrix;
	}
	/**
	 * getters and setters
	 */
	public Cell[][][] getMaze3d() {
		return maze3d;
	}
	public void setMaze3d(Cell[][][] maze3d) {
		this.maze3d = maze3d;
	}
	public int getLevels() {
		return levels;
	}
	public void setLevels(int levels) {
		this.levels = levels;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	public Cell getCell(MyPosition p){
		return maze3d[p.getX()][p.getY()][p.getZ()];
	}
	public MyPosition getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(MyPosition startPosition) {
		this.startPosition = startPosition;
	}
	public MyPosition getGoalPosition() {
		return goalPosition;
	}
	public void setGoalPosition(MyPosition goalPosition) {
		this.goalPosition = goalPosition;
	}
	

}
