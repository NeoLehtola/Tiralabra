
package tietorakenteet;

/**
 * teen tämän ensin luonnosmaisesti int-luvuille ja laajennan sitten
 * 
 */
public class Pino {
    
    private int[] taulukko;
    private int top;
    
    public Pino() {
        this.taulukko = new int[10];
        this.top = 0;
    }
    
    public void push(int uusi) {
        taulukko[top] = uusi;
        if (top >= taulukko.length-1) {
            kasvataTaulukkoa();
        } 
        top++;
    }

    public int getTop() {
        return top;
    }
    
    public int pop() {
        int palautettava = taulukko[top];
        taulukko[top] = 0;
        top--;
        return palautettava;
        
    }
    
    private void kasvataTaulukkoa() {
        // vanha pitää kopioida alkuun
        
    }
    
    
    
    
    
}
