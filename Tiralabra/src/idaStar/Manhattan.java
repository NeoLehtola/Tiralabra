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
            
            summa += Math.abs(vuorossaXKoord - maaliXKoord) + Math.abs(vuorossaYKoord - maaliYKoord);  
        }
        return summa;
    }
    
}
