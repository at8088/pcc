package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;

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
		pan.addMouseListener(new EventSouris(pan , obs));
		this.setContentPane(pan);
		
		
	}
	

	

}
