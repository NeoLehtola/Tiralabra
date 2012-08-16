package idaStar;

/**
 * Ehkä lisään tähän luokkaan myös Linear Conflict -tilanteen
 *
 */
public class Manhattan {

    private int laudanLeveys;
    
    // ei mitää järkee että laudanleveys on oliomuuttuja. korjaan
    public Manhattan(int laudanLeveys) {

        this.laudanLeveys = laudanLeveys;
    }

    
    /**
     * haetun napin x-koordinaatti
     * @param i
     * @param laudanLeveys
     * @return 
     */
    private int xKoord(int i) {
        return i%laudanLeveys;
    }
    
    /**
     * haetun napin y-koordinaatti
     * @param i
     * @param laudanLeveys
     * @return 
     */
    private int yKoord(int i) {
        return i/laudanLeveys;
    }

  /**
   * Tämä laskee Manhattan Distancen, ja etsii sitä varten jokaisen luvun koordinaatit nykyisessä tilanteessa sekä maalitilanteessa
   * @param lopputilanne
   * @return summa eli haluttu h-arvo
   */
    public int laskeH(int[] tilanne, int[] lopputilanne) {
        
        int summa = 0;

        for (int i = 0; i < tilanne.length; i++) {
            if (tilanne[i] == -1) {
                continue;
            }
            int vuorossa = tilanne[i];
            int vuorossaXKoord = xKoord(i);
            int vuorossaYKoord = yKoord(i);
            
            int maaliXKoord = xKoord(maalinIndeksi(vuorossa, lopputilanne));
            int maaliYKoord = yKoord(maalinIndeksi(vuorossa, lopputilanne)); 
            
            summa += absValue(vuorossaXKoord - maaliXKoord) + absValue(vuorossaYKoord - maaliYKoord);  
        }
        return summa;
    }
    
    /**
     * haetaan se indeksi, jossa maalitaulukossa sijaitsee kysytty luku
     * @param haettava
     * @return 
     */
    private int maalinIndeksi(int haettava, int[] lopputilanne) {
        for (int i = 0; i < lopputilanne.length; i++) {
            if (lopputilanne[i] == haettava) {
                return i;
            }
        }      
        // tänne ei pitäisi ikinä joutua
        return -100;
    }
    

    /**
     * apumetodi itseisarvon laskemiseksi (korvaa Math.abs:n)
     * @param k luku jonka itseisarvo halutaan
     * @return Jos luku negatiivinen, se kerrotaan -1:llä, muuten luku palautetaan sellaisenaan
     */
    private int absValue(int k) {
        if (k < 0) {
            return k * (-1);
        } else {
            return k;
        }
    }

}
