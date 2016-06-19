package algorithms.demo;

import controller.Controller;
import controller.MyController;
import model.Model;
import model.MyModel;
import view.MyView;
import view.View;

public class Demo {

	public static void main(String[] args) {
		Controller c=new MyController();
		View v=new MyView(c);
		Model m=new MyModel(c);
		c.setM(m);
		c.setV(v);
		v.start();	
	}
}