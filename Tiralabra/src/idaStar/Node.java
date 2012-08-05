
package idaStar;

/**
 *
 * Tämä luokka kapseloi yhden solmun toteutuksen; se sisältää tämänhetkisen pelitilanteen
 * sekä funktion f = g + h arvot
 */
public class Node {
    
    private int[] currentState;
    private int f;
    private int g;
    private int h;
    private int taulukonPituus;
    
    /**
     * Noden luominen ilman heuristiikkafunktiota
     * @param currentState 
     */
    public Node(int[] currentState) {
        this.currentState = currentState;
    }
    
    /**
     * Noden luominen heuristiikalla
     * @param currentState
     * @param g
     * @param h 
     */
    public Node(int[] currentState, int g, int h) {
        this.currentState = currentState;
        this.taulukonPituus = currentState.length;
        this.g = g;
        this.h = h;
        this.f = g + h;
    }
    
    /** currentState sisältää tiedon nykyisestä pelitilanteesta taulukkomuodossa
     * 
     * @return tilanne
     */
    public int[] getTilanne() {
        return currentState;
    }
    
    /**
     * f = g + h, eli tämänhetkinen "cost" + arvio
     * @param f 
     */
    public void setF(int f) {
        this.f = f;
    }
    
    /**  
     * 'g' = summa kaikista tämänastisista costeista
     * @param g
     */
    public void setG(int g) {
        this.g = g;
    }
    
    /**
     * heuristiikkafunktio, arvio siitä paljonko siirtoja vielä tarvitaan
     * @param h 
     */
    public void setH(int h) {
        this.h = h;
    }

    /**
     * 
     * @return 
     */
    public int getF() {
        return f;
    }

    /**
     * 
     * @return 
     */
    public int getG() {
        return g;
    }

    /**
     * 
     * @return 
     */
    public int getH() {
        return h;
    }

    /**
     * Noden kapseloiman taulukon pituus; tietoa tarvitaan haussa
     * @return taulukon pituus
     */
    public int getPituus() {
        return taulukonPituus;
    }
    
    
    
}
