package tietorakenteet;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Pino joka on toteutettu linkkien avulla ja on yleiskäyttöinen
 *
 */
public class LinkitettyPino<Item> {

    // http : //algs4.cs.princeton.edu/13stacks/Stack.java.html
    private Node eka;
    private int top;

    /**
     * luokka Linkitetyn pinon sisäiseen käyttöön:
     * sisältö ja linkit edeltävään ja seuraavaan
     */
    private class Node {

        private Item item;
        private Node prev;
        private Node next;
    }

    /**
     * luodaan tyhjä pino
     */
    public LinkitettyPino() {
        this.eka = null;
        this.top = 0;
    }

    /**
     * tarkistetaan onko pinossa jotain
     * @return true jos top osoittaa nollaa eli pino tyhjä
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * palauttaa pinon koon
     * @return top
     */
    public int size() {
        return this.top;
    }

    /**
     * lisätään pinoon uusi elementti ja laitetaan linkit kuntoon
     * @param uusi
     */
    public void push(Item uusi) {
        // MUISTA että pinossa eka on aina se päällimmäinen!
        Node edellinenEka = eka;
        eka = new Node();
        eka.item = uusi;
        eka.prev = edellinenEka;
        if (edellinenEka != null) {
            edellinenEka.next = eka;
        }
        top++;
    }


    /**
     * pop-operaatio palauttaa ja poistaa pinon päällimmäisen elementin
     * @return pinon päällimmäinen, poistetaan
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pino on tyhjä!");
        }
        Item palautettava = eka.item;
        // jos ao. ehtoa ei ole niin tulee NullPointerException
        if (eka.prev != null) {
            eka = eka.prev;
        }
        eka.next = null;
        top--;
        
        return palautettava;

    }

    /**
     * peek-operaatio palauttaa päällimmäisen, mutta ei poista sitä
     * @return pinon päällimmäinen, ei poisteta
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pino on tyhjä!");
        }
        return eka.item;
    }
    

//    /**
//     * 
//     * @return 
//     */
//    public Iterator<Item> iterator() {
//        return null;
//    }
    
}
