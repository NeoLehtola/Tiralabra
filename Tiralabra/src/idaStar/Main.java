package idaStar;

import java.util.Stack;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class Main {

    public static void main(String[] args) {
        
        /* testattu DLS- ja IterativeDeepening-metodeita erilaisilla syötteillä, ja
         * todettu, että toistaiseksi haku jää jossain kohti ikuiseen silmukkaan.
         * JUnit-testejä ei ole hakumetodeille toistaiseksi.
         * 
         */
         
        
//        IDDFSRajapinta r = new IDDFSRajapinta(new Pelitapahtuma(3, 3, 100));
//        IDAStarHaku haku = new IDAStarHaku(r.getStartNode(), r.getGoalNode());

//        IDDFS haku = new IDDFS(r);
//        
//        Node n = haku.iterativeDeepeningSearch(r.getStartNode(), r.getGoalNode());
//        if (haku.isGoal(n)) {
//            System.out.println("Löytyi! ^_^");
//        }
        
        IDAStarRajapinta ir = new IDAStarRajapinta(new Pelitapahtuma(3, 4, 100));
        int[] t = ir.goalNodenYKoordinaatit(3);
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i]);
        }
        
        

        
    }
}
