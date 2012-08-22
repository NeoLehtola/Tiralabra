
package tietorakenteet;

/**
 * Tähän pinoon voi tallettaa vain int-taulukoita. 
 * @author pklehtol
 */
public class TaulukkoPino {
    
    private int[][] taulukko;
    private int top;
    
    /**
     * 
     */
    public TaulukkoPino() {
        this.taulukko = new int[10][];
        this.top = 0;
    }
    
    /**
     * @param koko
     */
    public TaulukkoPino(int koko) {
        this.taulukko = new int[koko][];
        this.top = 0;
        
    }
    
    /**
     * 
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * 
     * @return 
     */
    public int size() {
        return top;
    }
    
    /**
     * 
     * @param uusi 
     */
    public void push(int[] uusi) {
        taulukko[top] = uusi;
        if (top >= taulukko.length-1) {
            kasvataTaulukkoa();
        } 
        top++;
    }


    
    /**
     * 
     * @return palautettava
     */
    public int[] pop() {
        int[] palautettava = taulukko[top-1];
        taulukko[top-1] = null;
        top--;
        return palautettava;
        
    }
    
    /**
     * 
     * @return 
     */
    public int[] peek() {
        return taulukko[top-1];
    }
    
    /**
     * 
     */
    private void kasvataTaulukkoa() {
        int[][] uusi = new int[taulukko.length*2][];
        for (int i = 0; i < taulukko.length; i++) {
            uusi[i] = taulukko[i];
        }
        taulukko = uusi;
        
        
    }
    
}
