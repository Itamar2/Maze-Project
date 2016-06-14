package algorithms.mazeGenerators;
/**
 * A class which represents a position in the maze
 * this class belongs to the Maze class and not for the maze3d
 */
public class MyPosition {
	
	private int x; //start with 0
	private int y;
	private int z;
	
	public MyPosition(int x,int y,int z) {
		setX(x);
		setY(y);
		setZ(z);
	}
	public MyPosition(){
		
	}
	/**
	 * Get the theoretical neighbor in the given Direction
	 * @param d - Direction
	 * @return myPosition
	 * @see {@link Directions}
	 */
	public MyPosition getNeighbor(Directions d){
		
		switch(d){
		case BACKWARD:
			return new MyPosition(x,y+1,z);
		case DOWN:
			return new MyPosition(x-1,y,z);
		case FORWARD:
			return new MyPosition(x,y-1,z);
		case LEFT:
			return new MyPosition(x,y, z-1);
		case RIGHT:
			return new MyPosition(x,y, z+1);
		case UP:
			return new MyPosition(x+1,y,z);
		default:
			return null;
		}
	}
		
		@Override
		public String toString(){
			return "{"+x+","+y+","+z+"}";
		}

	/**
	 * 
	 * getters and setters
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	
	

}
