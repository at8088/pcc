package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class EventCatcher implements MouseListener , KeyListener , MouseMotionListener{
	
	private JPanel pan = new JPanel();
	private boolean[][] obs;
	private boolean s_keyClicked = false;
	private int sClicks = 0;
	private boolean f_keyClicked = false;
	private boolean finishSelected = false , startSelected = false ;
	private int fClicks = 0;
	private int startX = -10 ,startY = -10,finishX = -10,finishY = -10;	
	
	public EventCatcher(JPanel p , boolean[][] ob ) {
		this.pan = p ;
		this.obs = ob;
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int x = e.getX() / 60 ;
		int y = e.getY() / 60;
		if( x < 9 && y < 9 ){
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
			g.fillRect((x * 60)+1, (y * 60) + 6, 59, 59);
			g.dispose();
		}
	}
	public boolean isFinishSelected() {
		return finishSelected;
	}

	public boolean isStartSelected() {
		return startSelected;
	}

	public int getsClicks() {
		return sClicks;
	}

	public int getfClicks() {
		return fClicks;
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
		int x = e.getX() / 60;
		int y = e.getY() / 60;
		Graphics g = pan.getGraphics();
		if(x!=startX && y!=startY){
			obs[x][y] = true;
			g.fillRect((x * 60)+1, (y * 60) + 6, 59, 59);
		}
    }

	@Override
	public void mouseMoved(MouseEvent e) {

	}



}
