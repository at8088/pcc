package gui;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import astar.Cellule;
import astar.RechercheChemin;

public class Fenetre extends JFrame {

	private static final long serialVersionUID = 1L;

	public Fenetre(int L, int l, boolean[][] obs) {
		super("Plus court chemin");
		this.setSize(L, l);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		JPanel pan = new JPanel();
		Grille gr = new Grille(L, l);
		pan.add(gr);
		LinkedList<Cellule> path = new LinkedList<>();
		EventCatcher lstn = new EventCatcher(pan, obs , path);
		pan.addMouseListener(lstn);
		this.addKeyListener(lstn);
		pan.addMouseMotionListener(lstn);
		this.setContentPane(pan);
		System.out.println("Waiting for start and finish nodes to be selected ...");
		while (!lstn.isFinishSelected() || !lstn.isStartSelected()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Thread pathFindingThread = new Thread(new RechercheChemin(lstn.getStartY(), lstn.getStartX(), lstn.getFinishY(),
				lstn.getFinishX(), lstn.getObs() , path));

		pathFindingThread.start();

		try {
			pathFindingThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		Graphics g = pan.getGraphics();
		g.setColor(Color.PINK);
		if(path.size() > 2) {
            for (Cellule cell : path.subList(1, path.size() - 2)) {
                g.fillRect((cell.getX() * 60) + 1, (cell.getY() * 60) + 6, 59, 59);
            }
        }
            g.dispose();
	}
	


}
