
package tietorakenteet;

/**
 * 
 * 
 */
public class Pino {
    
    private Object[] taulukko;
    private int top;
    
    /**
     * 
     */
    public Pino() {
        this.taulukko = new Object[10];
        this.top = 0;
    }
    
    /**
     * 
     * @param koko 
     */
    public Pino(int koko) {
        this.taulukko = new Object[koko];
        this.top = 0;
    }
    
    /**
     * 
     * @param uusi 
     */
    public void push(Object uusi) {
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
    public Object pop() {
        Object palautettava = taulukko[top-1];
        taulukko[top] = null;
        top--;
        return palautettava;
        
    }
    
    /**
     * 
     */
    private void kasvataTaulukkoa() {
        Object[] uusi = new Object[taulukko.length*2];
        for (int i = 0; i < taulukko.length; i++) {
            uusi[i] = taulukko[i];
        }
        taulukko = uusi;
        
        
    }
    
    
    
    
    
}
