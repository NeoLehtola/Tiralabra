package tietorakenteet;

import java.util.NoSuchElementException;

/**
 * Tähän pinoon voi tallettaa vain int-taulukoita.
 *
 */
public class TaulukkoPino {

    private int[][] taulukko;
    private int top;

    /**
     * parametriton konstruktori luo aina kymmenen pituisen pinon
     */
    public TaulukkoPino() {
        this.taulukko = new int[10][];
        this.top = 0;
    }

    /**
     * Konstruktori jolle voi antaa parametrina haluamansa pinon koon
     *
     * @param koko
     */
    public TaulukkoPino(int koko) {
        this.taulukko = new int[koko][];
        this.top = 0;

    }

    /**
     * tarkistaa onko pino tyhjä eli top osoittaa nollaa
     *
     * @return true jos tyhjä
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * palauttaa pinon koon
     *
     * @return top
     */
    public int size() {
        return top;
    }

    /**
     * uuden elementin lisäys pinoon. tarkistaa myös onko pino täysi. Jos on
     * niin sitä kasvatetaan
     *
     * @param uusi lisättävä elementti
     */
    public void push(int[] uusi) {
        taulukko[top] = uusi;
        if (top >= taulukko.length - 1) {
            kasvataTaulukkoa();
        }
        top++;
    }

    /**
     * palauttaa ja poistaa päällimmäisen elementin
     *
     * @return palautettava
     */
    public int[] pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pino on tyhjä");
        }

        int[] palautettava = taulukko[top - 1];
        taulukko[top - 1] = null;
        top--;
        return palautettava;

    }

    /**
     * palauttaa päällimmäisen elementin mutta ei poista sitä
     * @return päällimmäinen elementti
     */
    public int[] peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pino on tyhjä");
        }

        return taulukko[top - 1];
    }

    /**
     * jos pino tulee täyteen, pinon koko tuplataan
     */
    private void kasvataTaulukkoa() {
        int[][] uusi = new int[taulukko.length * 2][];
        for (int i = 0; i < taulukko.length; i++) {
            uusi[i] = taulukko[i];
        }
        taulukko = uusi;


    }
}
