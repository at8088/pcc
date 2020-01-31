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
		
		for(int j = 0 ; j < this.getWidth() - 60 ; j += 60) {
			for(int i = 0; i < this.getHeight() - 60 ; i+=60 ) {
				g.drawRect(i, j, 60, 60);
			}
		}
	}

}
