package algorithms.mazeGenerators;

/**
 * 
 * This class represents the position in the maze which is suite to maze3d representation 
 *
 */
public class Position {
	
	private int x; //start with 0
	private int y;
	private int z;
	
	/**
	 * Default constructor which does nothing yet
	 */
	public Position() {

	}
	
	/**
	 * Constructor
	 * @param x - positive integer, level value
	 * @param y - positive integer, row value
	 * @param z - positive integer, column value
	 */
	public Position(int x,int y,int z) {
		setX(x);
		setY(y);
		setZ(z);
	}
	
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
	
	/**
	 * Override toString in the following format {x,y,z}
	 * @return String
	 */
	@Override
	public String toString() {
		return "{"+x+","+y+","+z+"}";
		
	}
	/**
	 * Override equals which allows to compare between two different position.
	 * @param obj - position
	 * @return true - if equals, false - otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		return toString().equals(((Position)obj).toString());
	}
	
	/**
	 * Override the hashCode method.
	 * @return integer.
	 */
	@Override
	public int hashCode(){
		return toString().hashCode();
	}
}