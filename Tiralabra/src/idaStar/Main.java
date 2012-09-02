package idaStar;

import sovelluslogiikka.Pelitapahtuma;
import sovelluslogiikka.SiirtavaPelilauta;

/**
 *
 * @author neom
 */
public class Main {

    private static void tulostaTaulukko(int[] taulukko) {
        for (int i = 0; i < taulukko.length; i++) {
            if (taulukko[i] == -1) {
                System.out.print(0 + " ");
            } else {
                System.out.print(taulukko[i] + " ");
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) {



        int aikaKeskiarvo = 0;
        int siirtoKeskiarvo = 0;
        int maara = 10;

//        int[] lauta = {5, 1, -1, 7, 11, 3, 8, 2, 14, 10, 12, 13, 4, 15, 9, 6};

//        int[] test = {2, 1, 3, -1};

        for (int i = 0; i < maara; i++) {
        Haku haku = new Haku(new Pelitapahtuma(3, 3, 1000));
//        haku.setAlkutilanne(test);
            long aloitusAika = System.nanoTime();
        haku.iterativeDeepeningSearch(false, false);
            long lopetusAika = System.nanoTime();
            double aika = (lopetusAika - aloitusAika) / 1000000;
            System.out.println(aika + " ms, siirtoja " + haku.getSiirtojenMaara());
            aikaKeskiarvo += aika;
            siirtoKeskiarvo += haku.getSiirtojenMaara();
    }
        aikaKeskiarvo = aikaKeskiarvo / maara;
        siirtoKeskiarvo = siirtoKeskiarvo / maara;
        System.out.println("Keskiarvo: " + aikaKeskiarvo + "ms");
        System.out.println("Siirtokeskiarvo: " + siirtoKeskiarvo + " siirtoa");
        Haku haku = new Haku(new Pelitapahtuma(3, 3, 1000));
        haku.iterativeDeepeningSearch(true, false);
//
//        while (!haku.getReittiPino().isEmpty()) {
//            tulostaTaulukko(haku.getReittiPino().pop());
//        }

}
}