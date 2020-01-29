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
		
		Cellule startCell = new Cellule(startColumn, startRow);
		startCell.setHCost(finishColumn,finishRow);
		//gCost == 0 for the start cell
		startCell.setFCost(startCell.getHCost());
		this.openList.add(startCell);
		Cellule finishCell = new Cellule(finishColumn, finishRow);
		Cellule currentCell = startCell;
		while ( (! currentCell.equals(finishCell)) && (! openList.isEmpty())) {
			currentCell = cellWithMinFCost();
			if(currentCell.equals(finishCell)){
				continue;
			}else{
				openList.remove(currentCell);
				closedList.add(currentCell);
				for(Cellule cell : currentCell.getNeighbors()){
					if(!closedList.contains(cell)){
						if(!openList.contains(cell)){
							cell.setParent(currentCell);
							// could be unnecessary
							cell.setHCost(finishColumn, finishRow);
							cell.setGCost(cell.distance(currentCell) + currentCell.getGCost());
							cell.setFCost(cell.getGCost() + cell.getHCost());
							openList.add(cell);
						}else if(cell.getGCost() > cell.distance(currentCell) + currentCell.getGCost()){
							// could be unnecessary
							cell.setParent(currentCell);
							cell.setGCost(cell.distance(currentCell) + currentCell.getGCost());
							cell.setFCost(cell.getGCost() + cell.getHCost());
						}
					}
				}
			}
		}

		if(currentCell.equals(finishCell)){
			Cellule tmp = finishCell;
			System.out.print("Finish = ");
			while (!tmp.equals(startCell)) {
				System.out.print(" x = "+tmp.getX() + " y = "+tmp.getY());
				tmp = tmp.getParent();
			}
			System.out.println("Start =   x="+startColumn+"  y = "+startRow);
		}else{
			System.out.println("no path found");
		}

		
		
	}

	private Cellule cellWithMinFCost(){
		//O(1)
		Cellule minCell = openList.get(0);
		for(Cellule cell : openList){
			if(minCell.getFCost() > cell.getFCost()){
				minCell = cell;
			}
		}
		return minCell;
	}

	
}
