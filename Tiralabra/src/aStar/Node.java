
package aStar;

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
    
    // tarviiko noden tietää parent ja successor?
    public Node(int[] currentState, int g, int h) {
        this.currentState = currentState;
        this.taulukonPituus = currentState.length;
        this.g = g;
        this.h = h;
        this.f = g + h;
    }
    
    // currentState sisältää tiedon nykyisestä pelitilanteesta taulukkomuodossa
    public int[] getTilanne() {
        return currentState;
    }
    
    // f = g + h
    public void setF(int f) {
        this.f = f;
    }
    
    //  'g' is the sum of all the costs it took to get here
    public void setG(int g) {
        this.g = g;
    }
    
    //  'h' is our heuristic function, the estimate of what it will take to get to the goal.
    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public int getPituus() {
        return taulukonPituus;
    }
    
    
    
}
