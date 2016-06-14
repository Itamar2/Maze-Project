package algorithms.mazeGenerators;

public interface Maze3dGenerator {
	
	public Maze3d generate(int level,int row,int column);
	public String measureAlgorithmTime(int level,int row,int column);

}
