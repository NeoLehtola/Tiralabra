package idaStar;

import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class Main {

    public static void main(String[] args) {




//        for (int i = 0; i < 50; i++) {
            Haku haku = new Haku(new Pelitapahtuma(3, 3, 100));
//            long aloitusAika = System.nanoTime();
            haku.iterativeDeepeningSearch(true, false);
//            long lopetusAika = System.nanoTime();
//            double aika = (lopetusAika - aloitusAika) / 1000000;
//            System.out.println(aika + " ms");
//        }




//        if (haku.isRatkaisuLoytynyt()) {
//            System.out.println("Löytyi!");
//            
//        }








    }
}
