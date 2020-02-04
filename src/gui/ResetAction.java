package gui;

import astar.RechercheChemin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tahiria on 2/4/20.
 */
public class ResetAction implements ActionListener {

    private JPanel pan;
    private RechercheChemin pathFinder;
    private EventCatcher lstn;

    public ResetAction(JPanel pan , RechercheChemin pathFinder , EventCatcher lstn){
        this.pan = pan;
        this.lstn = lstn;
        this.pathFinder = pathFinder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Programme.reset(pan,pathFinder,lstn);
        if(Fenetre.pathFindingThread != null){
            try {
                Fenetre.pathFindingThread.join();
                System.out.println(Fenetre.pathFindingThread);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
