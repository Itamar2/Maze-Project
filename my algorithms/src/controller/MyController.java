package controller;

import java.util.HashMap;

import model.Model;
import model.SearchAlgo;
import view.View;

public class MyController implements Controller {
	
	private View v;
	private Model m;
	private HashMap<String,Command> commands;
	
	public MyController() {
		commands=new HashMap<>();
		initHash();
	}
	
	private void initHash(){
		commands.put("generate",new Command() {
			
			@Override
			public void doCommand(String[] args) {
				String[] str = args[1].split(","); // x y z
				m.generate3dMaze(args[0],Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
			}
		});
		commands.put("display maze",new Command() {
			
			@Override
			public void doCommand(String[] args) {
				m.retrieveMaze(args[0]);
			}
		});
		commands.put("save",new Command() {
			
			@Override
			public void doCommand(String[] args) {
				m.saveMaze(args[0],args[1]);
			}
		});
		commands.put("load",new Command() {
			
			@Override
			public void doCommand(String[] args) {
				m.loadMaze(args[0],args[1]);
			}
		});
		commands.put("maze size",new Command() {
			
			@Override
			public void doCommand(String[] args) {
				m.mazeSize(args[0]);
			}
		});
		commands.put("file size",new Command() {
			
			@Override
			public void doCommand(String[] args) {
				m.fileSize(args[0]);
			}
		});
		commands.put("solve",new Command() {
			
			@Override
			public void doCommand(String[] args) {
				if(args[1].equals("astar")){
					m.solveMaze(args[0],SearchAlgo.ASTAR);
				}
				else{
					m.solveMaze(args[0],SearchAlgo.BFS);
				}
			}
		});
		
		commands.put("display solution",new Command() {
			
			@Override
			public void doCommand(String[] args) {
				m.retrieveSolution(args[0]);
			}
		});
		commands.put("display cross section",new Command() {
			
			@Override
			public void doCommand(String[] args) {
				m.retrieveSectionMaze(args[0].charAt(0),Integer.parseInt(args[1]),args[2]);
			}
		});
	}
	
	public void setV(View v) {
		this.v = v;
	}
	public void setM(Model m) {
		this.m = m;
	}
	public HashMap<String, Command> getCommands() {
		return commands;
	} 

	@Override
	public void display(String message) {
		v.display(message);
	}

	@Override
	public void setMaze(int[][][] maze) {
		v.display(maze);
	}

	@Override
	public void setMaze(int[][] maze) {
		v.display(maze);
	}

}
