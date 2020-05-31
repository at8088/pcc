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
    public static boolean isReset = false;
    public ResetAction(JPanel pan , RechercheChemin pathFinder , EventCatcher lstn){
        this.pan = pan;
        this.lstn = lstn;
        this.pathFinder = pathFinder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //synchro maybe
        Programme.reset(pan,pathFinder,lstn);
        if(Fenetre.pathFindingThread != null){
            try {
                Fenetre.pathFindingThread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        isReset = true;
    }
}
