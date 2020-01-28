package astar;

import gui.Programme;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RechercheChemin implements Runnable{
	
	private int startRow = -10;
	private int startColumn  = -10;
	private int finishRow = -10;
	private int finishColumn = -10;
	private Lock mutex ;
	private boolean[][] obs;
	private Cellule[][] cellMap;
	private ArrayList<Cellule> openList , closedList;
	
	public RechercheChemin(int startRow, int startColumn, int finishRow, 
			int finishColumn , boolean[][] obs) {
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.finishRow = finishRow;
		this.finishColumn = finishColumn;
		this.mutex = new ReentrantLock();
		this.obs = obs.clone();
		this.cellMap = Programme.getCells();
		openList = new ArrayList<Cellule>();
		closedList = new ArrayList<Cellule>();
	}

	@Override
	public void run() {
		
		System.out.println("Begin pathfinding");
		System.out.println("Start @ X = "+startColumn+" , Y = "+startRow+" . \n"
				+ "Finish @ X = "+finishColumn+" , Y = "+finishRow+" .");
		
		
		
		
		
	}

	
}
