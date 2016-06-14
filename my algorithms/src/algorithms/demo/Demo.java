package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.Bfs;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class Demo {

	public static void main(String[] args) {
		/*Maze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze = mg.generate(10, 10, 10);
		maze.print();
		Maze3dSearchable mazeSearchable = new Maze3dSearchable(maze);
		Searcher<Position> bfsSearcher = new Bfs<>();
		Solution<Position> mySolution1 = bfsSearcher.Search(mazeSearchable);
		System.out.println("bfs");
		System.out.println(bfsSearcher.getNumberOfNodesEvaluated());
		System.out.println(mySolution1);
		
		
		try{
		Searcher<Position> astarSearcher = new Astar<>(new MazeManhattanDistance());
		Solution<Position> mySolution2 = astarSearcher.Search(mazeSearchable);
		System.out.println("Astar");
		System.out.println(astarSearcher.getNumberOfNodesEvaluated());
		System.out.println(mySolution2);
		
		}catch(IllegalArgumentException e){
			System.err.println(e.getMessage());
		} */
		
		Maze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d maze = mg.generate(1,3,3);
		maze.print();
		byte[] b = null;
		//save it to file
		OutputStream out;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
			InputStream in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
			b = new byte[maze.toByteArray().length];
			in.read(b);
			in.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Maze3d loaded = new Maze3d(b);
		loaded.print();
		System.out.println(loaded.equals(maze));
		
	}

}