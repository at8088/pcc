package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class EventSouris implements MouseListener{
	
	private JPanel pan = new JPanel();
	private boolean[][] obs;
	
	public EventSouris(JPanel p , boolean[][] ob) {
		this.pan = p ;
		this.obs = ob;
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int x = (int) (e.getX() / 60) ;
		int y = (int)(e.getY() / 60);
		Graphics g = pan.getGraphics();
		if(obs[x][y]) {
			g.setColor(pan.getBackground());
			obs[x][y] = false; 
		}else {
			g.setColor(Color.BLACK);
			obs[x][y] = true;
		}
		g.fillRect((x * 60)+1, (y * 60) + 6, 59, 59);
		g.dispose();
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {}

}
