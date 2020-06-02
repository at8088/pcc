package gui;

import astar.RechercheChemin;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Programme {
	
	public static int WIDTH = 900 , HEIGHT = 700 , cellSize = 20;
	private static boolean[][] obstacles = new boolean[WIDTH/cellSize][HEIGHT/cellSize] ;

	public static void main(String[] args) {
        new Fenetre(WIDTH,HEIGHT,obstacles);
	}

	public static void reset(JPanel panel , RechercheChemin pathFinder , EventCatcher eCatcher){
		Graphics g = panel.getGraphics();
		// set cell colours to default bg colour.
		for (int i = 0 ; i < obstacles.length ; i++){
			for (int j = 0 ; j < obstacles[0].length ; j++){
				obstacles[i][j] = false;
				g.setColor(panel.getBackground());
				g.fillRect(i * cellSize , j * cellSize,cellSize,cellSize);
				g.setColor(Color.black);
				g.drawRect(i * cellSize , j * cellSize,cellSize,cellSize);
			}
		}
		if(pathFinder != null){
			pathFinder.resetAll();
		}
		eCatcher.resetAll();
		g.dispose();
	}

}
