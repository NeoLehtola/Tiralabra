
package tietorakenteet;

/**
 * 
 * 
 */
public class LinkitettyPino<Item> {
    
    private Node eka;
    private int top;
    
    /**
     * 
     */
    
    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }
    
    public LinkitettyPino() {
        this.eka = null;
        this.top = 0;
    }
    
    public boolean isEmpty() {
        return eka == null;
    }
    
    public int size() {
        return this.top;
    }
    
 
    /**
     * 
     * @param uusi 
     */
    public void push(Item uusi) {
        // MUISTA että pinossa eka on aina se päällimmäinen!
        Node edellinenEka = eka;
        eka = new Node();
        eka.item = uusi;
        eka.next = edellinenEka;
        
        
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
    public Item pop() {
//        Item palautettava = taulukko[top-1];
//        taulukko[top-1] = null;
//        top--;
//        return palautettava;
        
    }
    

        
        
    }
    
    
    
    
    
}
