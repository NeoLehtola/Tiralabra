package idaStar;

import sovelluslogiikka.Pelitapahtuma;

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



//        int aikaKeskiarvo = 0;
//        int siirtoKeskiarvo = 0;
//        
//        for (int i = 0; i < 10; i++) {
//            Haku haku = new Haku(new Pelitapahtuma(4, 4, 1000));
//            long aloitusAika = System.nanoTime();
//            haku.iterativeDeepeningSearch(true, false);
//            long lopetusAika = System.nanoTime();
//            double aika = (lopetusAika - aloitusAika) / 1000000;
//            System.out.println(aika + " ms, siirtoja " + haku.getSiirtojenMaara());
//            aikaKeskiarvo += aika;
//            siirtoKeskiarvo += haku.getSiirtojenMaara();
//        }
//        
//        aikaKeskiarvo = aikaKeskiarvo /10;
//        siirtoKeskiarvo = siirtoKeskiarvo / 10;
//        System.out.println("Keskiarvo: " + aikaKeskiarvo + "ms");
//        System.out.println("Siirtokeskiarvo: " + siirtoKeskiarvo + " siirtoa");





//        if (haku.isRatkaisuLoytynyt()) {
//            System.out.println("Löytyi!");
//            
//        }


        Haku haku = new Haku(new Pelitapahtuma(3, 3, 1000));
        haku.iterativeDeepeningSearch(true, false);

        while (!haku.getReittiPino().isEmpty()) {
            tulostaTaulukko(haku.getReittiPino().pop());
        }



    }
}
