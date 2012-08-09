
package idaStar;

import java.util.Stack;

/**
 * iteratiivinen syvyyshaku ilman heuristiikkaa. käyttää pinoa aputietorakenteena.
 * 
 */
public class IDDFS {
    
    // startia ja goalia voi käyttää r:n kautta?
    private Node startNode;
    private Node goalNode;
    private int pituus;
    private IDDFSRajapinta r;
    private Stack<Node> lapsiPino;
    
    /**
     *Konstruktori
     * @param r rajapinta, joka yhdistää pelin ja haun ja generoi solmut
     */
    public IDDFS (IDDFSRajapinta r) {
        this.r = r;
        this.startNode = r.getStartNode();
        this.goalNode = r.getGoalNode();
        // koska kaikissa tiloissa pelilaudan koko on sama, kaikkien nodejen 
        // taulukon pituus on sama.
        this.pituus = goalNode.getPituus();
    }
    
//    // tämä ei jää lopulliseen ohjelmaan! on testausta varten.
//    private void tulostaTaulukko(Node n) {
//        for (int i = 0; i < n.getPituus(); i++) {
//            System.out.print(n.getTilanne()[i]);    
//        }
//        System.out.println("");
//    }
    
    /**
     * nodejen taulukkoarvojen vertailu
     * @param eka
     * @param toka
     * @return true jos pelitilanne taulukossa on sama
     */
    private boolean vertaaNodeja(Node eka, Node toka) {
        for (int i = 0; i < eka.getTilanne().length; i++) {
            if (eka.getTilanne()[i] != toka.getTilanne()[i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * Hahmotelma rajatusta syvyyshausta, rekursiivinen
     * @param startNode
     * @param goalNode
     * @param depth
     * @return öö. katotaan mitäs tän pitikään palauttaa.
     */
    public Node depthLimitedSearch(Node node, Node goalNode, int depth) {
        if (depth >= 0 && node == goalNode) {
            return node;
        } else if (depth > 0) {
            //tulostaTaulukko(node);
            expand(node, r);
            //System.out.print("*");
           // tulostaTaulukko(lapsiPino.peek());
            depthLimitedSearch(lapsiPino.pop(), goalNode, depth-1);       
        }
        return null;
    }
    
    /**
     * Iteratiivinen syvyyshaku, joka kutsuu DLS:ää
     * @param node
     * @param goalNode
     * @return loppusolmu eli maali
     */
    public Node iterativeDeepeningSearch(Node node, Node goalNode) {
        int depth = 0;
        Node result = null;
        
        while (result == null) {
            result = depthLimitedSearch(node, goalNode, depth);
//            if (result == goalNode) {
//                return result;
//            }
            depth++;
        } 
        return result;
    }
    
    /**
     * expand-metodi on tekemisissä rajapinnan kanssa
     * @param current käsiteltävä solmu josta laajennetaan seuraaviin siirtoihin
     * @param r IDDFSRajapinta joka tuntee sekä pelin että haun
     */
    public void expand(Node current, IDDFSRajapinta r) {
        this.lapsiPino = r.luoNodelleLapsetPinoon(current);
 
        
    }
    
    /**
     * Metodi tarkistaa, onko käsiteltävä node maali, eli onko pelilauta
     * järjestyksessä ja peli loppunut
     *
     * @param current käsiteltävä solmu
     * @return true jos peli loppunut
     */
    public boolean isGoal(Node current) {
        
        for (int i = 0; i < pituus; i++) {
            if (current.getTilanne()[i] != goalNode.getTilanne()[i]) {
                return false;
            }     
        }
        return true;
              
    }
    
    /**
     * palauttaa maalisolmun
     * @return goalNode
     */
    public Node getGoalNode() {
        return goalNode;
    }

    /**
     * palauttaa lähtötilanteen solmun
     * @return startNode
     */
    public Node getStartNode() {
        return startNode;
    }
    
    
    
}
