package gui;
import astar.*;
public class Programme {
	
	public static int WIDTH = 600,HEIGHT = 600;
	private static boolean[][] obstacles = new boolean[9][9] ; 

	public static void main(String[] args) {
		for(int i = 0 ; i < 9 ; i++) {
			for(int j = 0 ; j < 9 ; j++) {
				obstacles[i][j] = false;
			}
		}
		new Fenetre(WIDTH,HEIGHT,obstacles);

	}

}
