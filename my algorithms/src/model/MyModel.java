package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import algorithms.demo.Maze3dSearchable;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.Bfs;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class MyModel implements Model {
	
	private Controller c;
	private HashMap<String,Maze3d> mazes;
	private HashMap<Maze3d,Solution<Position>> solutions;
	
	public MyModel(Controller c) {
		this.c=c;
		mazes = new HashMap<>();
		solutions=new HashMap<>();
	}

	@Override
	public void generate3dMaze(String name, int level, int row, int column) throws IllegalArgumentException {
		if(level<1 || row<2 || column <2){
			throw new IllegalArgumentException("illegal dimensions values");
		}
		if(mazes.containsKey(name)){
			throw new IllegalArgumentException("maze name: "+name+" already in use");
		}
		Maze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze3d = mg.generate(level, row, column);
		mazes.put(name,maze3d);
		c.display("maze "+name+" is ready");
	}

	@Override
	public void saveMaze(String name, String fileName) {
		if(!mazes.containsKey(name)){
			c.display("maze name: "+" not found");
			return;
		}
		BufferedOutputStream out = null;
		Maze3d myMaze = mazes.get(name);
		try {
			out = new BufferedOutputStream(new MyCompressorOutputStream(new FileOutputStream(fileName+".maz")));
			out.write(myMaze.toByteArray());
			out.flush();
			out.close();		
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}	
	}
	@Override
	public void loadMaze(String fileName, String name) {

		StringBuilder myBuffer= new StringBuilder();
		try {
			BufferedInputStream in=new BufferedInputStream(new MyDecompressorInputStream(new FileInputStream(fileName+".maz")));
			int c;
			while((c=in.read())!=-1){
				myBuffer.append(c);
			}
			in.close();
			byte[] byteMaze =new byte[myBuffer.length()];
			for(int i=0;i<myBuffer.length();i++){
				byteMaze[i]=(byte) Character.getNumericValue(myBuffer.charAt(i));
			}	
			Maze3d tempMaze = new Maze3d(byteMaze);
			mazes.put(name,tempMaze);
			
		} catch (FileNotFoundException e) {
			c.display(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void mazeSize(String name) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void fileSize(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void solveMaze(String name, SearchAlgo algo) {
		if(!mazes.containsKey(name)){
			c.display("maze name: "+name+" doesn't exist");
			return;
		}
		Searcher<Position> mySearcher;
		
		switch(algo){
		case ASTAR:
			mySearcher=new Astar<>(new MazeManhattanDistance());
			break;
		case BFS:
			mySearcher=new Bfs<>();
			break;
		default:
			mySearcher=null;
			break;
	}
		Solution<Position> mySolution = mySearcher.Search(new Maze3dSearchable( mazes.get(name)));
		solutions.put(mazes.get(name),mySolution);
		c.display("solution for "+name+" is ready");
	}
}
