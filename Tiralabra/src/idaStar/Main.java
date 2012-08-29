package idaStar;

import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class Main {

    public static void main(String[] args) {



        int aikaKeskiarvo = 0;
        int siirtoKeskiarvo = 0;
        
        for (int i = 0; i < 10; i++) {
            Haku haku = new Haku(new Pelitapahtuma(4, 4, 1000));
            long aloitusAika = System.nanoTime();
            haku.iterativeDeepeningSearch(true, false);
            long lopetusAika = System.nanoTime();
            double aika = (lopetusAika - aloitusAika) / 1000000;
            System.out.println(aika + " ms, siirtoja " + haku.getSiirtojenMaara());
            aikaKeskiarvo += aika;
            siirtoKeskiarvo += haku.getSiirtojenMaara();
        }
        
        aikaKeskiarvo = aikaKeskiarvo /10;
        siirtoKeskiarvo = siirtoKeskiarvo / 10;
        System.out.println("Keskiarvo: " + aikaKeskiarvo + "ms");
        System.out.println("Siirtokeskiarvo: " + siirtoKeskiarvo + " siirtoa");
        




//        if (haku.isRatkaisuLoytynyt()) {
//            System.out.println("Löytyi!");
//            
//        }








    }
}
