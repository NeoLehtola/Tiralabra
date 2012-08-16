package idaStar;

import sovelluslogiikka.Pelitapahtuma;

/**
 * 
 *
 */
public class Manhattan {

    private int[] tilanne;
    private int laudanLeveys;
    
    public Manhattan(int[] tilanne, int laudanLeveys) {
        this.tilanne = tilanne;
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
     * manhattan
     * tästä tullee private

     */
    public int laskeH(int[] lopputilanne) {
        
        int summa = 0;

        for (int i = 0; i < tilanne.length; i++) {
            if (tilanne[i] == -1) {
                continue;
            }
            int vuorossa = tilanne[i];
            int vuorossaXKoord = xKoord(i);
            int vuorossaYKoord = yKoord(i);
            
            int goalXKoord = xKoord(haeGoalIndeksi(vuorossa, lopputilanne));
            int goalYKoord = yKoord(haeGoalIndeksi(vuorossa, lopputilanne)); 
            
            summa += absValue(vuorossaXKoord - goalXKoord) + absValue(vuorossaYKoord - goalYKoord);  
        }
        return summa;

        

    }
    /**
     * 
     * @param haettava
     * @return 
     */
    private int haeGoalIndeksi(int haettava, int[] lopputilanne) {
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
