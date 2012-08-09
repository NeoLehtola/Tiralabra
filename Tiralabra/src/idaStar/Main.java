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
        
        int[] keskiTilanne = {4, 3, 2, 1, -1, 8, 7, 6, 5};
        
        Stack<Node> pino = r.luoNodelleLapsetPinoon(new Node(keskiTilanne));
        
    }
}
