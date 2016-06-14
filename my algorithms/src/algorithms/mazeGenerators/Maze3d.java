package algorithms.mazeGenerators;

import java.util.ArrayList;

public class Maze3d {
	
	private int[][][] maze3d;
	private Position startPosition;
	private Position goalPosition;
	
	public Maze3d(int[][][] maze) {
		maze3d=maze;
	}
	
	public Maze3d(byte[] maze) {
		Maze3d tempMaze = MazeHeader.GenerateFromByte(maze);
		startPosition = tempMaze.getStartPosition();
		goalPosition = tempMaze.getGoalPosition();
		maze3d = tempMaze.getMaze3d();
	}
	
	@Override
	public boolean equals(Object obj) {
		//checking dimensions
		if(maze3d.length!=((Maze3d)obj).maze3d.length || maze3d[0].length!=((Maze3d)obj).maze3d[0].length
				|| maze3d[0][0].length != ((Maze3d)obj).maze3d[0][0].length){
			return false;
		}
		//checking start position and goal position
		if(!startPosition.equals(((Maze3d)obj).startPosition) 
				|| !goalPosition.equals(((Maze3d)obj).goalPosition)){
			return false;
		}
		//checking the maze itself
		for(int i=0;i<maze3d.length;i++){
			for(int j=0;j<maze3d[0].length;j++){
				for(int k=0;k<maze3d[0][0].length;k++){
					if(maze3d[i][j][k] != ((Maze3d)obj).maze3d[i][j][k]){
						return false;
					}
				}
			}
		}		
		return true; //if we got here it means they're equal
	}
	
	public String[] getPossibleMoves(Position p){
		
		ArrayList<Position> moves = new ArrayList<Position>();
		//check if there's a right neighbor
		if(p.getZ()+2 <= maze3d[p.getX()][p.getY()].length -2  && maze3d[p.getX()][p.getY()][p.getZ()+1] == 0){
			moves.add(new Position(p.getX(), p.getY(),p.getZ()+2));
		}	
		//check if there's a left neighbor
		if(p.getZ()-2 >=1 && maze3d[p.getX()][p.getY()][p.getZ()-1] == 0){
			moves.add(new Position(p.getX(),p.getY(),p.getZ()-2));
		}
		//check if there's a forward neighbor
		if(p.getY()-2 >=1 && maze3d[p.getX()][p.getY()-1][p.getZ()] == 0){
			moves.add(new Position(p.getX(),p.getY()-2,p.getZ()));
		}
		//check if there's a backward neighbor
		if(p.getY()+2 <= maze3d[p.getX()].length-2 && maze3d[p.getX()][p.getY()+1][p.getZ()] ==0){
			moves.add(new Position(p.getX(),p.getY()+2,p.getZ()));
		}
		//check if there's a up neighbor
		if(p.getX()+2 <= maze3d.length-2 && maze3d[p.getX()+1][p.getY()][p.getZ()] ==0){
			moves.add(new Position(p.getX()+2, p.getY(),p.getZ()));
		}
		//check if there's a down neighbor
		if(p.getX()-2 >=1 && maze3d[p.getX()-1][p.getY()][p.getZ()] == 0){
			moves.add(new Position(p.getX()-2,p.getY(),p.getZ()));
		}	
		String[] strs = new String[moves.size()];
		int i = 0;
		for(Position pos : moves){
			strs[i++] = pos.toString();
		}	
		return strs;
	}
	
	public byte[] toByteArray(){
		MazeHeader header = new MazeHeader(this);
		int counter = MazeHeader.headerlength;
		int length = mazeLength()+ MazeHeader.headerlength; //calculating maze size
		byte[] byteArray = new byte[length];

		System.arraycopy(header.getHeaderByteArray(),0,byteArray, 0,MazeHeader.headerlength);
		for(int i=0;i<maze3d.length;i++){
			for(int j=0;j<maze3d[0].length;j++){
				for(int k=0;k<maze3d[0][0].length;k++){
					byteArray[counter++] = (byte)maze3d[i][j][k];
				}
			}
		}
		return byteArray;
	}
	public int[][] getCrossSectionByX(int num)throws IndexOutOfBoundsException {
		
		if(num<0){
			throw new IndexOutOfBoundsException();
		}
		return maze3d[num];
		
	}

	public int[][] getCrossSectionByY(int num) throws IndexOutOfBoundsException {
		
		if(num < 0){
			throw new IndexOutOfBoundsException();
		}
		
		int[][] mazeByY = new int[maze3d.length][maze3d[0][0].length];
		
		for(int i=0; i<maze3d.length ;i++){
			for(int j=0; j<maze3d[0][0].length ;j++){
				mazeByY[i][j] = maze3d[i][num][j];
			}
			
		}
		
		return mazeByY;
	}

	public int[][] getCrossSectionByZ(int num) throws IndexOutOfBoundsException {
		
		if(num<0){
			throw new IndexOutOfBoundsException();
		}
		
		int[][] mazeByZ = new int[maze3d.length][maze3d[0].length];
		
		for(int i=0; i<maze3d.length ; i++){
			for(int j=0; j<maze3d[0].length ; j++){
				mazeByZ[i][j] = maze3d[i][j][num];
			}
		}
		return mazeByZ;
		
	}
	
	public int mazeLength(){
		return maze3d.length*maze3d[0].length*maze3d[0][0].length;
	}
	public void print() {
		System.out.println("start: "+startPosition);
		System.out.println("goal: "+goalPosition);
		for(int i=0;i<maze3d.length;i++) {
			for(int j=0;j<maze3d[i].length;j++) {
				for(int k=0;k<maze3d[i][j].length;k++) {
					System.out.print(maze3d[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	public Position getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	public Position getGoalPosition() {
		return goalPosition;
	}

	public void setGoalPosition(Position goalPosition) {
		this.goalPosition = goalPosition;
	}
	
	public int[][][] getMaze3d() {
		return maze3d;
	}

}
