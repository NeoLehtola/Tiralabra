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
        
//        IDAStarRajapinta ir = new IDAStarRajapinta(new Pelitapahtuma(3, 3, 100));
//        int[] test = {5, 7, -1, 4, 2, 3, 8, 6, 1};
//        ir.setStartNode(new Node(test));
//        ir.laskeNodenArvoH(ir.getStartNode(), 3);
//        System.out.println(ir.getStartNode().getH());
        
        Haku haku = new Haku(new Pelitapahtuma(3, 3, 100));
        
        for (int i = 0; i < haku.getTaulukonPituus(); i++) {
            System.out.print(haku.getTilanne()[i]);          
        }
        System.out.println("");
        
        int[] tulos = haku.iterativeDeepeningSearch();
        
        for (int i = 0; i < tulos.length; i++) {
            System.out.print(tulos[i]);
        }
//        haku.kaynnista();
        

        
        

        
    }
}
