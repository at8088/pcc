package astar;

public class Cellule {

	private CellType type;
	private int x , y;
	private boolean visited;
	private double fCost, hCost, gCost;

	public Cellule(int x , int y) {
		this.x = x;
		this.y = y;
		this.setCellType();
	}
	
	public void setCellType() {
		if( y==0 ) {
			if( x == 0 ) {
				this.type = CellType.doubleEdgeLeftUp;
				return;
			}else if( x == 8) {
				this.type = CellType.doubleEdgeRightUp;
				return;
			}else {
				this.type = CellType.simpleEdgeUp;
				return;
			}
		}else if( y == 8) {
			if( x == 0 ) {
				this.type = CellType.doubleEdgeLeftDown;
				return;
			}else if( x == 8) {
				this.type = CellType.doubleEdgeRightDown;
				return;
			}else {
				this.type = CellType.simpleEdgeDown;
				return;
			}
		}else if( x == 0) {
			this.type = CellType.simpleEdgeLeft;
			return;
		}else {
			this.type = CellType.simpleEdgeRight;
		}
	}
	public CellType getCellType() {
		return type;
	}

	public boolean isVisited() {
		return visited;
	}

}
