package tietorakenteet;

import java.util.NoSuchElementException;

/**
 *
 *
 */
public class LinkitettyPino<Item> {

    // http : //algs4.cs.princeton.edu/13stacks/Stack.java.html
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
        eka.prev = edellinenEka;
        edellinenEka.next = eka;

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
     * @return pinon päällimmäinen, poistetaan
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pino on tyhjä!");
        }
        Item palautettava = eka.item;
        eka = eka.prev;
        eka.next = null;
        top--;
        
        return palautettava;

    }

    /**
     * 
     * @return pinon päällimmäinen, ei poisteta
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pino on tyhjä!");
        }
        return eka.item;
    }
    

    
}
