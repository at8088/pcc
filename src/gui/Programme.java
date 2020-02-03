package gui;

public class Programme {
	
	public static int WIDTH = 700 , HEIGHT = 700 , cellSize = 20;
	private static boolean[][] obstacles = new boolean[WIDTH/cellSize][HEIGHT/cellSize] ;

	public static void main(String[] args) {
        new Fenetre(WIDTH,HEIGHT,obstacles);
	}

}
