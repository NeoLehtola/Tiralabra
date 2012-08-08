package idaStar;

import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class Main {

    public static void main(String[] args) {
        
        IDDFSRajapinta r = new IDDFSRajapinta(new Pelitapahtuma(4, 4, 10000));
        IDAStarHaku haku = new IDAStarHaku(r.getStartNode(), r.getGoalNode());
        
        boolean joo = haku.isGoal(r.getStartNode());
    }
}
