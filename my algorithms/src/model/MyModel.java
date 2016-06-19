package model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
	public static final String FORMAT = "maz";
	private Object lock1;
	private Object lock2;
	
	public MyModel(Controller c) {
		this.c=c;
		mazes = new HashMap<>();
		solutions=new HashMap<>();
		lock1= new Object();
		lock2=new Object();
	}

	@Override
	public void generate3dMaze(String name, int level, int row, int column) throws IllegalArgumentException {
		if(level<1 || row<2 || column <2){
			throw new IllegalArgumentException("illegal dimensions values");
		}	
		
		synchronized (lock1) {
			if(mazes.containsKey(name)){
				throw new IllegalArgumentException("maze name: "+name+" already in use");
			}
			mazes.put(name,null); // ensure this key in the hash map with null value
		}
		//generating a maze,this is the heavy action and we can generate few mazes simultaneously
		Maze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze3d = mg.generate(level, row, column);
		synchronized (lock1) {
			mazes.put(name,maze3d); // override this last null value
		}
		c.display("maze "+name+" is ready");
	}

	@Override
	public void saveMaze(String name, String fileName) {
		if(!mazes.containsKey(name)){
			c.display("maze name: "+" not found");
			return;
		}
		else if(mazes.get(name)==null){
			c.display("no such maze found");
			return;
		}
		
		try {
			BufferedOutputStream out = null;
			Maze3d myMaze = mazes.get(name);
			out = new BufferedOutputStream(new MyCompressorOutputStream(new FileOutputStream(fileName+"."+FORMAT)));
			out.write(myMaze.toByteArray());
			out.flush();
			out.close();
			c.display("maze "+fileName+"."+FORMAT+" successfully saved");
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}	
	}
	@Override
	public void loadMaze(String fileName, String name) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new MyDecompressorInputStream(new FileInputStream(fileName+"."+FORMAT))));
			String line;		
			StringBuilder str = new StringBuilder();
					
			while((line = reader.readLine()) != null){
				str.append(line);						
			}
			mazes.put(name,new Maze3d(str.toString().getBytes()));
			reader.close();
			c.display("maze loaded successfully");
		} catch (FileNotFoundException e) {
			c.display("there's no such file");
		} catch (IOException e) {
		}
	}

	@Override
	public void mazeSize(String name) {
		Maze3d tempMaze = mazes.get(name);
		int len = (tempMaze.getMaze3d()).length * ((tempMaze.getMaze3d()[0])).length * ((tempMaze.getMaze3d()[0][0])).length;
		len*=4;
		len += 8;
		c.display(len+" bytes");
	}
	
	@Override
	public void fileSize(String name) {
		File myFile = new File(name+"."+FORMAT);
		c.display(Long.toString(myFile.length())+" bytes");
	}

	@Override
	public void solveMaze(String name, SearchAlgo algo) {
		
		if(!mazes.containsKey(name)){
			c.display("no such maze found");
			return;
		}
		else if(mazes.get(name)!=null){
			synchronized (lock2) {
				if(solutions.containsKey(name)){
					c.display("there's already solution for that");
				}
				solutions.put(mazes.get(name),null);
			}	
		}
		else{
			c.display("no such maze");
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
		
		synchronized (lock2) {
			solutions.put(mazes.get(name),mySolution);
		}
		c.display("solution for "+name+" is ready");	
	}

	@Override
	public void retrieveMaze(String name) {
		if(!mazes.containsKey(name)){
			c.display("no such maze found");
			return;
		}
		else if(mazes.get(name)==null){
			c.display("no such maze found");
			return;
		}
		else{
			c.display("start position: "+mazes.get(name).getStartPosition());
			c.display("goal position: "+mazes.get(name).getGoalPosition());
			c.setMaze(mazes.get(name).getMaze3d());

		}
	}

	@Override
	public void retrieveSolution(String name) {
		if(!mazes.containsKey(name)){
			c.display("no such maze");
			return;
		}
		if(mazes.get(name)==null){
			c.display("no such maze found");
			return;
		}
		if(solutions.containsKey(mazes.get(name))){
			if(solutions.get(mazes.get(name)) != null){
				c.display(solutions.get(mazes.get(name)).toString());
			}
			else{
				c.display("no such maze found");
			}
		}
		else{
			c.display("no such solution found");
		}
	}

	@Override
	public void retrieveSectionMaze(char dimension, int index, String name) {
		if(!mazes.containsKey(name)){
			c.display("no such maze");
			return;
		}
		switch(dimension){
		case 'x':
		case 'X':
			c.setMaze(mazes.get(name).getCrossSectionByX(index));
			break;
		case 'y':
		case 'Y':
			c.setMaze(mazes.get(name).getCrossSectionByY(index));
			break;
		case 'z':
		case 'Z':
			c.setMaze(mazes.get(name).getCrossSectionByZ(index));
			break;
			default:
				break;
		}
	}


		
}	
