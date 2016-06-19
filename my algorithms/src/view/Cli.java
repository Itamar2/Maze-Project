package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;

import controller.Command;

public class Cli extends Thread {
	
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String,Command> commands;
	
	public Cli(Reader in,Writer out,HashMap<String,Command> commands) {
		this.in = new BufferedReader(in);
		this.out=new PrintWriter(out);
		this.commands = commands;
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		in.close();
		out.close();
	}
	public void display(String str){
		out.println(str);
		out.flush();
	}
	private void runCommandInThread(Command command,String[] args){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				command.doCommand(args);
			}
		}).start();
	}
	public void display(int[][][] maze){
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze[0].length;j++){
				for(int k=0;k<maze[0][0].length;k++){
					out.print(maze[i][j][k]);
				}
				out.println();
			}
			out.println();
		}
		out.flush();
	}
	public void display(int[][] maze){
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze[0].length;j++){
				out.print(maze[i][j]);
			}
			out.println();
		}
		out.flush();
	}

	@Override
	public void run() {
		String[] str;
		String line;
		
		try {
			while((line=in.readLine()) != null){
				str=line.split(" ");
				//generate 3d maze <name> <level,row,column>
				if(line.toLowerCase().matches("generate 3d maze [a-zA-Z][a-zA-Z0-9]* [1-9][0-9]*,[1-9][0-9]*,[1-9][0-9]*")){
					String[] strs={str[3],str[4]};
					runCommandInThread(commands.get("generate"),strs);
				}
				//display <name>
				else if(line.toLowerCase().matches("display [a-zA-Z][a-zA-Z0-9]*")){
					String[] strs={str[1]};
					commands.get("display maze").doCommand(strs);
				}
				//save maze <name> <file name>
				else if(line.toLowerCase().matches("save maze [a-zA-Z][a-zA-Z0-9]* [a-zA-Z][a-zA-Z0-9]*")){
					String[] strs={str[2],str[3]};
					commands.get("save").doCommand(strs);
				}
				//load maze <file name> <name>
				else if(line.toLowerCase().matches("load maze [a-zA-Z][a-zA-Z0-9]* [a-zA-Z][a-zA-Z0-9]*")){
					String[] strs={str[2],str[3]};
					commands.get("load").doCommand(strs);
				}
				//solve <name> <algorithm>
				else if(line.toLowerCase().matches("solve [a-zA-Z][a-zA-Z0-9]* (astar|bfs)")){
					String[] strs={str[1],str[2]};
					commands.get("solve").doCommand(strs);
				}
				//display solution <name>
				else if(line.toLowerCase().matches("display solution [a-zA-Z][a-zA-Z0-9]*")){
					String[] strs={str[2]};
					commands.get("display solution").doCommand(strs);
				}
				//display cross section by {x,y,z} for <name>
				else if(line.toLowerCase().matches("display cross section by [xyzXYZ] [1-9][0-9]* for [a-zA-Z][a-zA-Z0-9]*")){
					String[] strs={str[4],str[5],str[7]};
					commands.get("display cross section").doCommand(strs);
				}
				//file size <name>
				else if(line.toLowerCase().matches("file size [a-zA-Z][a-zA-Z0-9]*")){
					String[] strs={str[2]};
					commands.get("file size").doCommand(strs);
				}
				//maze size <name>
				else if(line.toLowerCase().matches("maze size [a-zA-Z][a-zA-Z0-9]*")){
					String[] strs={str[2]};
					commands.get("maze size").doCommand(strs);
				}

				else{
					display("wrong input!");
				}
			}
		} catch (IOException e) {
			out.println(e.getMessage());
		}
	}	
}
