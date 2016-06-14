package algorithms.mazeGenerators;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MazeHeader {
	
	public static final int headerlength = 36;// bytes
	private Maze3d maze;
	private int[] headerArray;
	private byte[] headerByteArray;
	
	public MazeHeader(Maze3d maze) {
		this.maze = maze;
		headerArray = new int[headerlength/4];
		headerByteArray = new byte[headerlength];
		initIntro();
		initByte();
	}
	//init: level,rows,column,start,goal
	private void initIntro(){
		headerArray[0] = maze.getMaze3d().length;
		headerArray[1] = maze.getMaze3d()[0].length;
		headerArray[2] = maze.getMaze3d()[0][0].length;
		headerArray[3] = maze.getStartPosition().getX();
		headerArray[4] = maze.getStartPosition().getY();
		headerArray[5] = maze.getStartPosition().getZ();
		headerArray[6] = maze.getGoalPosition().getX();
		headerArray[7] = maze.getGoalPosition().getY();
		headerArray[8] = maze.getGoalPosition().getZ();		
	}
	private void initByte(){
		for(int i=0;i<headerArray.length;i++){
			System.arraycopy(ByteBuffer.allocate(4).putInt(headerArray[i]).array(),0, headerByteArray, i*4, 4);
		}
	}
	public byte[] getHeaderByteArray() {
		return headerByteArray;
	}
	public static Maze3d GenerateFromByte(byte[] byteMaze){
		int levels = ByteBuffer.wrap(Arrays.copyOfRange(byteMaze, 0,4)).getInt();
		int rows = ByteBuffer.wrap(Arrays.copyOfRange(byteMaze, 4,8)).getInt();
		int columns = ByteBuffer.wrap(Arrays.copyOfRange(byteMaze,8,12)).getInt();
		int startX = ByteBuffer.wrap(Arrays.copyOfRange(byteMaze,12,16)).getInt();
		int startY = ByteBuffer.wrap(Arrays.copyOfRange(byteMaze,16,20)).getInt();
		int startZ = ByteBuffer.wrap(Arrays.copyOfRange(byteMaze,20,24)).getInt();
		int goalX = ByteBuffer.wrap(Arrays.copyOfRange(byteMaze,24,28)).getInt();
		int goalY = ByteBuffer.wrap(Arrays.copyOfRange(byteMaze,28,32)).getInt();
		int goalZ = ByteBuffer.wrap(Arrays.copyOfRange(byteMaze,32,36)).getInt();
		
		int[][][] maze3d = new int[levels][rows][columns];
		int counter = headerlength-1;
		for(int i=0;i<maze3d.length;i++){
			for(int j=0;j<maze3d[0].length;j++){
				for(int k=0;k<maze3d[0][0].length;k++){
					maze3d[i][j][k] = (int) byteMaze[++counter];
				}
			}
		}		
		Maze3d myMaze = new Maze3d(maze3d);
		myMaze.setStartPosition(new Position(startX,startY,startZ));
		myMaze.setGoalPosition(new Position(goalX,goalY,goalZ));
		return myMaze;
	}
	

}
