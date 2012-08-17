package idaStar;

/**
 * Ehkä lisään tähän luokkaan myös Linear Conflict -tilanteen
 *
 */
public class Manhattan {

    /**
     * Tämä laskee Manhattan Distancen, ja etsii sitä varten jokaisen luvun
     * koordinaatit nykyisessä tilanteessa sekä maalitilanteessa
     *
     * @param tilanne
     * @param laudanLeveys
     * @param laskeKonflikti jos true, otetaan Linear Conflict -tilanne
     * huomioon.
     *
     * @return summa eli haluttu h-arvo
     */
    public int laskeH(int[] tilanne, int laudanLeveys, boolean laskeKonflikti) {

        int summa = 0;

        for (int i = 0; i < tilanne.length; i++) {
            if (tilanne[i] == -1) {
                continue;
            }
            int vuorossa = tilanne[i];

            int vuorossaXKoord = i % laudanLeveys;
            int vuorossaYKoord = i / laudanLeveys;

            int maaliXKoord = (vuorossa - 1) % laudanLeveys;
            int maaliYKoord = (vuorossa - 1) / laudanLeveys;

            /* linear conflict
             * toistaiseksi ottaa huomioon vain vierekkäiset napit leveyssuunnassa,
             * seuraavaksi muutan niin ettei tarvitse olla vierekkäin
             */
            if (laskeKonflikti && i > 0) {

                //ensin tarkistetaan onko kaksi nappia "väärinpäin" ja sitten ovatko ne samalla rivillä
                if (vuorossaYKoord == maaliYKoord && tilanne[i] == tilanne[i - 1] - 1 && i % laudanLeveys != 0) {
                    summa += 2;
                }
            }

            // h-arvon laskeminen
            summa += Math.abs(vuorossaXKoord - maaliXKoord) + Math.abs(vuorossaYKoord - maaliYKoord);
        }
        return summa;
    }
}
