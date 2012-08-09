package idaStar;

import java.util.Stack;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class Main {

    public static void main(String[] args) {
        
        IDDFSRajapinta r = new IDDFSRajapinta(new Pelitapahtuma(3, 3, 5000));
//        IDAStarHaku haku = new IDAStarHaku(r.getStartNode(), r.getGoalNode());
        IDDFS haku = new IDDFS(r);
        Node n = haku.depthLimitedSearch(r.getStartNode(), r.getGoalNode(), 1);

        
    }
}
