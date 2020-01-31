package astar;
import gui.Programme;
public class Cellule {

	private CellType type;
	private int x , y;
	private boolean visited;
	private Cellule parent;
	private double fCost, hCost, gCost;
	public Cellule(int x , int y , Cellule parent ) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.setCellType();
	}

	public Cellule getParent() {
		return this.parent;
	}

	public void setParent(Cellule parent) {
		this.parent = parent;
	}
	
	public void setCellType() {
		if( y==0 ) {
            if( x == 0 ) {
				this.type = CellType.doubleEdgeLeftUp;
				return;
			}else if( x == Programme.WIDTH/Programme.cellSize - 1 ) {
				this.type = CellType.doubleEdgeRightUp;
				return;
			}else {
				this.type = CellType.simpleEdgeUp;
				return;
			}
		}else if( y == Programme.HEIGHT/Programme.cellSize - 1) {
            if( x == 0 ) {
				this.type = CellType.doubleEdgeLeftDown;
				return;
			}else if( x == Programme.WIDTH/Programme.cellSize - 1 ) {
				this.type = CellType.doubleEdgeRightDown;
				return;
			}else {
				this.type = CellType.simpleEdgeDown;
				return;
			}
		}else if( x == 0) {
			this.type = CellType.simpleEdgeLeft;
			return;
		}else if(x == Programme.WIDTH/Programme.cellSize - 1){
			this.type = CellType.simpleEdgeRight;
		}else{
			this.type = CellType.middle;
		}
	}

	public double getFCost() {
		return this.fCost;
	}

	public void setFCost(double fCost) {
		this.fCost = fCost;
	}

	public double getHCost() {
		return this.hCost;
	}

	public void setHCost(int finishX , int finishY) {
		this.hCost = (Math.abs(this.x - finishX) + Math.abs(this.y - finishY));
	}

	public double getGCost() {
		return this.gCost;
	}

	public void setGCost(double gCost) {
		this.gCost = gCost;
	}

	public Cellule[] getNeighbors() {
		if(getCellType() == CellType.doubleEdgeLeftDown){
            Cellule[] neighbors = {new Cellule(x+1, y ,this) ,new Cellule(x, y-1,this) };
			return neighbors;
		}else if(getCellType() == CellType.doubleEdgeLeftUp){
			Cellule[] neighbors = {new Cellule(x+1, y,this) ,new Cellule(x, y+1,this) };
			return neighbors;
		}else if(getCellType() == CellType.doubleEdgeRightDown){
			Cellule[] neighbors = {new Cellule(x-1, y,this) ,new Cellule(x, y-1,this) };
			return neighbors;
		}else if(getCellType() == CellType.doubleEdgeRightUp){
			Cellule[] neighbors = {new Cellule(x, y+1,this) ,new Cellule(x-1, y,this) };
			return neighbors;
		}else if(getCellType() == CellType.simpleEdgeDown){
			Cellule[] neighbors = {new Cellule(x+1, y , this) ,new Cellule(x, y-1, this)
                    ,new Cellule(x-1, y, this)};
			return neighbors;
		}else if(getCellType() == CellType.simpleEdgeUp){
			Cellule[] neighbors = {new Cellule(x+1, y, this) ,new Cellule(x-1, y, this),
                    new Cellule(x, y+1, this) };
			return neighbors;
		}else if(getCellType() == CellType.simpleEdgeLeft){
			Cellule[] neighbors = {new Cellule(x+1, y, this) ,new Cellule(x, y-1, this) ,
                    new Cellule(x, y+1, this)};
			return neighbors;
		}else if(getCellType() == CellType.simpleEdgeRight){
			Cellule[] neighbors = {new Cellule(x-1, y, this) ,new Cellule(x, y-1, this),
                    new Cellule(x, y+1, this) };
			return neighbors;
		}else{
			Cellule[] neighbors = {new Cellule(x-1, y, this) ,new Cellule(x+1, y, this),
                    new Cellule(x, y+1, this), new Cellule(x, y-1, this) };
			return neighbors;
		}
	}
	public CellType getCellType() {
		return type;
	}

	public boolean isVisited() {
		return visited;
	}

	public int distance(Cellule cell){
		if(cell.x > x && cell.y > y){
			return 2;
		}else{
			return 1;
		}
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public boolean equals(Object obj){
		if(obj instanceof Cellule){
			return this.x == ((Cellule)obj).x && this.y == ((Cellule)obj).y;
		}
		return false;
	}

}
