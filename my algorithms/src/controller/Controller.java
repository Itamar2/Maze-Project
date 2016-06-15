package controller;

import java.util.HashMap;

public interface Controller {
	public void display(String message);
	public HashMap<String, Command> getCommands();
}
