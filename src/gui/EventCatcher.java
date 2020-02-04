package gui;

import astar.Cellule;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JPanel;

public class EventCatcher implements MouseListener , KeyListener , MouseMotionListener{
	
	private JPanel pan ;
	private boolean[][] obs;
	private boolean s_keyClicked = false;
	private int sClicks = 0;
	private boolean f_keyClicked = false;
	private boolean finishSelected = false , startSelected = false ;
	private int fClicks = 0;
	private int startX = -10 ,startY = -10,finishX = -10,finishY = -10;
	private LinkedList<Cellule> path;
	
	public EventCatcher(JPanel p , boolean[][] ob , LinkedList<Cellule> path) {
	    this.path = path;
		this.pan = p ;
		this.obs = ob;
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int x = e.getX() / Programme.cellSize ;
		int y = e.getY() / Programme.cellSize;
		if( x < obs.length && y < obs.length && !(x == startX && y == startY)
				&& !(x == finishX && y == finishY) &&  !path.contains(new Cellule(x,y,null))){
			Graphics g = pan.getGraphics();
			if(obs[x][y]) {
				g.setColor(pan.getBackground());
				obs[x][y] = false;
			}else {
				if(s_keyClicked && sClicks == 1) {
					g.setColor(Color.BLUE);
					s_keyClicked = false ;
					startX = x ;
					startY = y ;
					startSelected = true;
				}else if(f_keyClicked && fClicks == 1) {
					g.setColor(Color.GREEN);
					f_keyClicked = false;
					finishX = x ;
					finishY = y ;
					finishSelected = true;
				}else {
					obs[x][y] = true;
				}
			}
			g.fillRect((x * Programme.cellSize)+1, (y * Programme.cellSize) + 1, Programme.cellSize - 1,
					Programme.cellSize - 1);
			g.dispose();
		}
	}
	public boolean isFinishSelected() {
		return finishSelected;
	}

	public boolean isStartSelected() {
		return startSelected;
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 's') {
			s_keyClicked = true;
			sClicks ++ ;
		}else if(e.getKeyChar() == 'f') {
			f_keyClicked = true;
			fClicks ++ ;
		}
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getFinishX() {
		return finishX;
	}

	public int getFinishY() {
		return finishY;
	}

	public boolean[][] getObs() {
		return obs;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX() / Programme.cellSize;
		int y = e.getY() / Programme.cellSize;
		if(x < obs.length && x>=0 && y < obs.length && y >= 0) {
            Graphics g = pan.getGraphics();
            if ( !(x == startX && y == startY) && !(x == finishX && y == finishY)
					  &&  !path.contains(new Cellule(x,y,null)) ) {
                obs[x][y] = true;
                g.fillRect((x * Programme.cellSize)+1 , (y * Programme.cellSize) + 1, Programme.cellSize - 1,
						Programme.cellSize - 1);
            }else if(x == finishX && y == finishY){
                // A faire
            }
        }
    }

	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("x= "+e.getX() + " y="+e.getY());
	}

	public void resetAll(){
		this.f_keyClicked = false;
		this.s_keyClicked = false;
		this.fClicks = 0;
		this.sClicks = 0;
		this.finishX = -1;
		this.finishY = -1;
		this.startX = -1;
		this.startY = -1;
		this.finishSelected = false;
		this.startSelected = false;
		this.path = Fenetre.path;
	}

}
