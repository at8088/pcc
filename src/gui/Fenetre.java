package gui;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.*;

import astar.Cellule;
import astar.RechercheChemin;

public class Fenetre extends JFrame {

	private static int extraMarge = 20;
	private static int leftSideWidth = 150 + extraMarge;
	private static final long serialVersionUID = 1L;
    private RechercheChemin pathFinder = null;
    public static Thread pathFindingThread = null;
    public static LinkedList<Cellule> path = new LinkedList<>();
    private EventCatcher lstn = null;
    private JPanel pan = new JPanel();


	public Fenetre(int L, int l, boolean[][] obs) {
		super("Plus court chemin");
		this.setSize(L + leftSideWidth, l + Programme.cellSize + extraMarge  );
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		Grille gr = new Grille(L + leftSideWidth, l);
		JButton button = new JButton("Reset");
		lstn = new EventCatcher(pan, obs , path);
        this.addKeyListener(lstn);
        pan.addMouseListener(lstn);
        pan.addMouseMotionListener(lstn);
        this.setContentPane(pan);
		this.setLayout(new BorderLayout());
		button.setBounds(910,100,1050-910,50);
		button.setVisible(true);
        button.setFocusable(false);
        button.addActionListener(new ResetAction(pan,pathFinder,lstn));
		pan.add(button);
        pan.add(gr);
        this.startPathFinding();
        this.realTimePaint();
		try {
			pathFindingThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        this.paintPath();
	}

	public void startPathFinding(){
        System.out.println("Waiting for start and finish nodes to be selected ...");
        while (!lstn.isFinishSelected() || !lstn.isStartSelected()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        pathFinder = new RechercheChemin(lstn.getStartY(), lstn.getStartX(), lstn.getFinishY(),
                lstn.getFinishX(), lstn.getObs() , path);
        pathFindingThread = new Thread(pathFinder);
        pathFindingThread.start();
    }

    public void realTimePaint(){
        Graphics g = pan.getGraphics();
        while (!pathFinder.isFinished()){
            synchronized (pathFinder){
                try {
                    pathFinder.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g.setColor(Color.cyan);
                for (Cellule cell : pathFinder.getOpenList()){
                    if( !(cell.getY() == lstn.getStartY() && cell.getX() == lstn.getStartX())
                            && !(cell.getY() == lstn.getFinishY()&& cell.getX() == lstn.getFinishX())){
                        g.fillRect((cell.getX() * Programme.cellSize) + 1, (cell.getY() * Programme.cellSize) + 1,
                                Programme.cellSize - 1, Programme.cellSize - 1);
                    }
                }
                g.setColor(Color.magenta);
                for (Cellule cell : pathFinder.getClosedList()){
                    if( !(cell.getY() == lstn.getStartY() && cell.getX() == lstn.getStartX())
                            && !(cell.getY() == lstn.getFinishY()&& cell.getX() == lstn.getFinishX())){
                        g.fillRect((cell.getX() * Programme.cellSize) + 1, (cell.getY() * Programme.cellSize) + 1,
                                Programme.cellSize - 1, Programme.cellSize - 1);
                    }
                }
            }

        }
        g.dispose();
    }

    private void paintPath(){
        Graphics g = pan.getGraphics();
        g.setColor(Color.PINK);
        if(path.size() > 2) {
            for (Cellule cell : path.subList(1, path.size() - 2)) {
                g.fillRect((cell.getX() * Programme.cellSize) + 1, (cell.getY() * Programme.cellSize) + 1,
                        Programme.cellSize - 1, Programme.cellSize - 1);
            }
        }
        g.dispose();
    }

}
