package view;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import controller.Controller;

public class MyView implements View {
	
	private Controller c;
	private Cli cli;
	
	public MyView(Controller c) {
		this.c=c;
		cli=new Cli(new InputStreamReader(System.in),new OutputStreamWriter(System.out),c.getCommands());
	}

	@Override
	public void display(String str) {
		cli.display(str);
	}
	
	public void start(){
		cli.start();
	}

	@Override
	public void display(int[][][] maze) {
		
	}

}
