package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public interface Controller {
	public void display(String message);
	public HashMap<String, Command> getCommands();
	public void setV(View v);
	public void setM(Model M);
	public void setMaze(int[][][] maze);
	public void setMaze(int[][] maze);
}
