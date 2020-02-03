package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Grille extends JComponent{

	private static final long serialVersionUID = 1L;

	public Grille(int L , int l) {
		this.setPreferredSize(new Dimension(L,l));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int i = 0 ; i <= Programme.WIDTH - Programme.cellSize ; i += Programme.cellSize) {
			for(int j = 0; j <= Programme.HEIGHT - Programme.cellSize ; j+=Programme.cellSize ) {
				g.drawRect(i, j, Programme.cellSize, Programme.cellSize);
			}
		}
	}

}
