
package idaStar;

import java.util.Stack;

/**
 * iteratiivinen syvyyshaku ilman heuristiikkaa. käyttää pinoa aputietorakenteena.
 * 
 */
public class IDDFS {
    
    
    private Node startNode;
    private Node goalNode;
    private int pituus;
    private IDDFSRajapinta r;

    
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
    
    // tämä ei jää lopulliseen ohjelmaan! on testausta varten.
    private void tulostaTaulukko(Node n) {
        for (int i = 0; i < n.getPituus(); i++) {
            System.out.print(n.getTilanne()[i]);    
        }
        System.out.println("");
    }
    
    /**
     * nodejen taulukkoarvojen vertailu
     * @param eka
     * @param toka
     * @return true jos pelitilanne taulukossa on sama
     */
    private boolean vertaaNodeja(Node eka, Node toka) {
        if (eka == null || toka == null) {
            return false;
        }
        
        for (int i = 0; i < eka.getTilanne().length; i++) {
            if (eka.getTilanne()[i] != toka.getTilanne()[i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * rajattu syvyyshaku, apumetodi iteratiiviselle syvyyshaulle
     * @param current
     * @param goalNode
     * @param depth haun suurin syvyys, sitä pidemmälle ei jatketa
     * @return node tai null
     */
    public Node depthLimitedSearch(Node current, Node goalNode, int depth) {
        
        if (depth >= 0 && vertaaNodeja(current, goalNode)) {
            return current;
        } else if (depth > 0) {
            tulostaTaulukko(current);
            
            Stack<Node> lapsiPino = expand(current, r);
            
            Node result = null;
            while (!lapsiPino.isEmpty()) {
                result = depthLimitedSearch(lapsiPino.pop(), goalNode, depth-1);
                if (vertaaNodeja(result, goalNode)) {
                    break;
                }
            }
            return result;
        } else {
            return null;
        }
        
        

    }
    
    /**
     * Iteratiivinen syvyyshaku, joka kutsuu DLS:ää 
     * @param current tämänhetkinen käsiteltävä node
     * @param goalNode
     * @return loppusolmu eli maali
     */
    public Node iterativeDeepeningSearch(Node current, Node goalNode) {
        int depth = 0;
        Node result;
        
        while (true) {
            result = depthLimitedSearch(current, goalNode, depth);
            if (vertaaNodeja(result, goalNode)) {
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
    public Stack<Node> expand(Node current, IDDFSRajapinta r) {
         return r.luoNodelleLapsetPinoon(current);
 
        
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
