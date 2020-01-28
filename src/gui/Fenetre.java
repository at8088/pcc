package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;

import astar.RechercheChemin;

public class Fenetre extends JFrame{

	private static final long serialVersionUID = 1L;

	public Fenetre(int L , int l , boolean[][] obs) {
		super("Plus court chemin");
		this.setSize(L, l);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		JPanel pan = new JPanel();
		Grille gr = new Grille(L,l);
		pan.add(gr);
		EventCatcher lstn = new EventCatcher(pan , obs );
		pan.addMouseListener(lstn);
		this.addKeyListener(lstn);
		pan.addMouseMotionListener(lstn);
		this.setContentPane(pan);
		System.out.println("Waiting for start and finish nodes to be selected ...");
		while( !lstn.isFinishSelected() || !lstn.isStartSelected()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		new Thread(new RechercheChemin(lstn.getStartY(), lstn.getStartX(),
				lstn.getFinishY(), lstn.getFinishX(), lstn.getObs())).start();
	}
	


}
