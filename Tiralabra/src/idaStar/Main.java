package idaStar;

import java.util.Stack;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class Main {

    public static void main(String[] args) {
        
        IDDFSRajapinta r = new IDDFSRajapinta(new Pelitapahtuma(2, 2, 5));
//        IDAStarHaku haku = new IDAStarHaku(r.getStartNode(), r.getGoalNode());
        IDDFS haku = new IDDFS(r);
        int[] testi = {1, 2, 3, 4, 5, 6, 7, 8, -1};
        Node n = haku.iterativeDeepeningSearch(r.getStartNode(), r.getGoalNode());
        

        
    }
}
