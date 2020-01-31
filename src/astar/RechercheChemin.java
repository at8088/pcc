package astar;

import java.util.Iterator;
import java.util.LinkedList;
import gui.Programme;
public class RechercheChemin implements Runnable{
	
	private int startRow = -10;
	private int startColumn  = -10;
	private int finishRow = -10;
	private int finishColumn = -10;
	private boolean[][] obs;
	private LinkedList<Cellule> path;
	private LinkedList<Cellule> openList , closedList;
	private boolean finished;

	public RechercheChemin(int startRow, int startColumn, int finishRow, 
			    int finishColumn , boolean[][] obs , LinkedList<Cellule> path) {
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.finishRow = finishRow;
		this.finishColumn = finishColumn;
		this.obs = obs;
		this.path = path;
		openList = new LinkedList<>();
		closedList = new LinkedList<>();
		this.finished = false;
	}

	@Override
	public void run() {
		System.out.println("Begin pathfinding");
		System.out.println("Start @ X = "+startColumn+" , Y = "+startRow+" . \n"
				+ "Finish @ X = "+finishColumn+" , Y = "+finishRow+" .");
		
		Cellule startCell = new Cellule(startColumn, startRow,null);
		startCell.setHCost(finishColumn,finishRow);
		//gCost == 0 for the start cell
		startCell.setFCost(startCell.getHCost());
		this.openList.add(startCell);
		Cellule finishCell = new Cellule(finishColumn, finishRow,null);
		Cellule currentCell = startCell;
		while ( (! currentCell.equals(finishCell)) && (! openList.isEmpty())) {
			currentCell = cellWithMinFCost();
			if(currentCell.equals(finishCell)){
				break;
			}else{
				synchronized (this){
					openList.remove(currentCell);
					closedList.add(currentCell);
					notify();
				}
				for(Cellule cell : currentCell.getNeighbors()){
					if(!obs[cell.getX()][cell.getY()]){
						if(!closedList.contains(cell) ){
							if(!openList.contains(cell)){
								cell.setParent(currentCell);
								// could be unnecessary
								cell.setHCost(finishColumn, finishRow);
								cell.setGCost(cell.distance(currentCell) + currentCell.getGCost());
								cell.setFCost(cell.getGCost() + cell.getHCost());
								synchronized (this){
									openList.add(cell);
								}
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
		}
        this.finished = true;
		if(currentCell.equals(finishCell)){
			System.out.println("Path Found .");
			path.addFirst(currentCell);
			Cellule tmp = currentCell;
			while (!tmp.equals(startCell)) {
				path.addFirst(tmp);
				tmp = tmp.getParent();
			}
			path.addFirst(startCell);
		}else{
			System.out.println("No path found .");
		}

		

		
	}

    public LinkedList<Cellule> getOpenList() {
        return openList;
    }

    public LinkedList<Cellule> getClosedList() {
        return closedList;
    }

    public boolean isFinished() {
        return finished;
    }

    private Cellule cellWithMinFCost(){
		//O(1)
		Cellule minCell = openList.get(openList.size()-1);
		Iterator<Cellule> iter = openList.descendingIterator();
		while(iter.hasNext()){
			Cellule cell = iter.next();
			if(minCell.getFCost() > cell.getFCost()){
				minCell = cell;
			}
		}
		return minCell;
	}

	
}
