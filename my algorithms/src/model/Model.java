package model;

public interface Model {
	
	public void generate3dMaze(String name,int level,int row,int column);
	public void saveMaze(String name,String fileName);
	public void loadMaze(String fileName,String name);
	public void mazeSize(String name);
	public void fileSize(String name);
	public void solveMaze(String name,SearchAlgo algo);
	public void retrieveMaze(String name);
	public void retrieveSolution(String name);
	public void retrieveSectionMaze(char dimension,int index,String name);

}
