package idaStar;

import java.util.PriorityQueue;
/**
 * IDA* -algoritmilla toteutettava haku verkosta, jossa solmut ovat
 * pelitilanteita aina seuraavan siirron jälkeen.
 *
 * Huom. Tämä luokka on pahasti vaiheessa! Pois kommentoidut metodit on siirretty IDDFS-luokkaan.
 * Näiden välinen työnjako ja yhteydet eivät ole vielä ihan selvillä.
 * 
 */
public class IDAStarHaku {

    private Node startNode;
    private Node goalNode;
    private int pituus;
    
    private int costLimit;
    


    /**
     *Konstruktori
     * @param startNode pelin sekoitettu alkutilanne
     * @param goalNode haluttu lopputilanne, eli järjestetty taulukko jossa lopussa tyhjää tarkoittava -1
     */
    public IDAStarHaku(Node startNode, Node goalNode) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        // koska kaikissa tiloissa pelilaudan koko on sama, kaikkien nodejen 
        // taulukon pituus on sama.
        this.pituus = goalNode.getPituus();
        // missäs h kannattaa asettaa?
        this.costLimit = startNode.getH();
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
