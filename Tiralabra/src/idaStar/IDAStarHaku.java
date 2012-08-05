package idaStar;

import java.util.Stack;

/**
 * IDA* -algoritmilla toteutettava haku verkosta, jossa solmut ovat
 * pelitilanteita aina seuraavan siirron jälkeen.
 *
 *
 */
public class IDAStarHaku {

    private Node startNode;
    private Node goalNode;
    private int pituus;
    private Stack<Node> pino;

    /**
     *
     * @param startNode
     * @param goalNode
     */
    public IDAStarHaku(Node startNode, Node goalNode) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        // koska kaikissa tiloissa pelilaudan koko on sama, kaikkien nodejen 
        // taulukon pituus on sama.
        this.pituus = goalNode.getPituus();
    }

    /**
     * KESKEN!
     *
     * @param startNode
     * @param goalNode
     * @param depth
     */
    public Node depthLimitedSearch(Node node, Node goalNode, int depth) {
        if (depth >= 0 && node == goalNode) {
            return node;
        } else if (depth > 0) {
            
        }
        return null;
    }

    /**
     * 
     * @param root
     * @param goalNode
     * @return 
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
    
    // tämä ei tule olemaan void, vaan ehkä Stack?
    // miten expand-operaatio käytännössä toteutetaan?
    // rajapinta kytketään tähän luokkaan, ja annetaan expandille syötteeksi generoidut nodet?
    public void expand(Node current) {
    }

    /**
     * Metodi tarkistaa, onko käsiteltävä node maali, eli onko pelilauta
     * järjestyksessä ja peli loppunut
     *
     * @param current
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
    //    /**
//     * arvio siitä, kuinka monta siirtoa vielä tarvitaan. Kaava: ....
//     * @param node 
//     */
//    public void heuristiikka(Node node) {
//        int arvo = 0; 
//        
//        for (int i = 0; i < pituus; i++) {
//            
//        }
//    }
}
