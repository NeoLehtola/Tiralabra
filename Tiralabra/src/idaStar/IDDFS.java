
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
            expand(node, r);
            depthLimitedSearch(lapsiPino.pop(), goalNode, depth-1);       
        }
        return null;
    }
    
    /**
     * Iteratiivinen syvyyshaku, joka kutsuu DLS:ää
     * @param root
     * @param goalNode
     * @return loppusolmun eli maalin
     */
    public Node iterativeDeepeningSearch(Node root, Node goalNode) {
        int depth = 0;
        // tämä näyttää nyt hieman riskialttiilta!!!
        while (true) {
            Node result = depthLimitedSearch(root, goalNode, depth);
            if (result == goalNode) {
                return result;
            }
            depth++;
        }      
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
