package gui;

public class Programme {
	
	public static int WIDTH = 1000 , HEIGHT = 1000;
	private static boolean[][] obstacles = new boolean[WIDTH/60][HEIGHT/60] ;

	public static void main(String[] args) {
		new Fenetre(WIDTH,HEIGHT,obstacles);
	}

}
