
package tietorakenteet;

/**
 * teen tämän ensin luonnosmaisesti int-luvuille ja laajennan sitten
 * 
 */
public class Pino {
    
    private int[] taulukko;
    private int top;
    
    /**
     * 
     */
    public Pino() {
        this.taulukko = new int[10];
        this.top = 0;
    }
    
    /**
     * 
     * @param koko 
     */
    public Pino(int koko) {
        this.taulukko = new int[koko];
        this.top = 0;
    }
    
    /**
     * 
     * @param uusi 
     */
    public void push(int uusi) {
        taulukko[top] = uusi;
        if (top >= taulukko.length-1) {
            kasvataTaulukkoa();
        } 
        top++;
    }

    /**
     * 
     * @return 
     */
    public int getTop() {
        return top;
    }
    
    /**
     * 
     * @return 
     */
    public int pop() {
        int palautettava = taulukko[top];
        taulukko[top] = 0;
        top--;
        return palautettava;
        
    }
    
    /**
     * 
     */
    private void kasvataTaulukkoa() {
        int[] uusi = new int[taulukko.length*2];
        for (int i = 0; i < taulukko.length; i++) {
            uusi[i] = taulukko[i];
        }
        taulukko = uusi;
        
        
    }
    
    
    
    
    
}
