package core;

import controllers.MainController;
import graphics.MainView;

public class Launcher 
{
	public static void main(String[] args)
	{
		IPCore ip = new IPCore();
		MainController mc = new MainController(ip);
		MainView mv = new MainView(mc);
	}
}
