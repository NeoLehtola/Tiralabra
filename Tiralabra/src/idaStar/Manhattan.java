package idaStar;

/**
 * Ehkä lisään tähän luokkaan myös Linear Conflict -tilanteen
 *
 */
public class Manhattan {


  /**
   * Tämä laskee Manhattan Distancen, ja etsii sitä varten jokaisen luvun koordinaatit nykyisessä tilanteessa sekä maalitilanteessa
   * @param lopputilanne
   * @return summa eli haluttu h-arvo
   */
    public int laskeH(int[] tilanne, int laudanLeveys) {
        
        int summa = 0;

        for (int i = 0; i < tilanne.length; i++) {
            if (tilanne[i] == -1) {
                continue;
            }
            int vuorossa = tilanne[i];
            int vuorossaXKoord = i%laudanLeveys;
            int vuorossaYKoord = i/laudanLeveys;
            
            int maaliXKoord = (vuorossa-1)%laudanLeveys;
            int maaliYKoord = (vuorossa-1)/laudanLeveys; 
            
            summa += absValue(vuorossaXKoord - maaliXKoord) + absValue(vuorossaYKoord - maaliYKoord);  
        }
        return summa;
    }
    
    
    /**
     * apumetodi itseisarvon laskemiseksi (korvaa Math.abs:n)
     * @param k luku jonka itseisarvo halutaan
     * @return Jos luku negatiivinen, se kerrotaan -1:llä, muuten luku palautetaan sellaisenaan
     */
    private int absValue(int k) {
        if (k < 0) {
            return -k;
        } else {
            return k;
        }
    }

}
