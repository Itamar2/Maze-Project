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
	public void display(String str){
		out.println(str);
		out.flush();
	}
	@Override
	public void run() {
		
		String line;
		try {
			while((line=in.readLine()) != null){
				System.out.println(line);
			}
		} catch (IOException e) {
			out.println(e.getMessage());
		}
	}
	
}
