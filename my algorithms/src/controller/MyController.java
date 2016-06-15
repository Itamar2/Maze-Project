package controller;

import java.util.HashMap;

import model.Model;
import view.View;

public class MyController implements Controller {
	
	private View v;
	private Model m;
	private HashMap<String,Command> commands;
	
	public MyController() {
		commands=new HashMap<>();
		
	}
	
	private void initHash(){
		
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
		// TODO Auto-generated method stub
		
	}

}
