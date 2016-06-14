package algorithms.mazeGenerators;

/**
 * This class represents a cell in a maze which has 6 walls
 */
public class Cell {
	
	private boolean visited; //true - visited, false - not visited
	private boolean right; // true - there's a wall, false - the wall was removed
	private boolean left;
	private boolean forward;
	private boolean backward;
	private boolean up;
	private boolean down;
	
	/**
	 * Default constructor which initialize this cell as unvisited.
	 * and set all 6 walls as exist.
	 */
	public Cell() {
		setVisited(false); //this cell hasn't been visited yet
		setRight(true); //6 walls surround this cell at initialization
		setLeft(true);
		setForward(true);
		setBackward(true);
		setUp(true);
		setDown(true);
	}
	
	/**
	 * getters and setters
	 */
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isForward() {
		return forward;
	}

	public void setForward(boolean forward) {
		this.forward = forward;
	}

	public boolean isBackward() {
		return backward;
	}

	public void setBackward(boolean backward) {
		this.backward = backward;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
}
