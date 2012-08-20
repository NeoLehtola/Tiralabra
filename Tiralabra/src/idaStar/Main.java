package idaStar;

import java.util.Stack;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class Main {

    public static void main(String[] args) {
        

        
        Haku haku = new Haku(new Pelitapahtuma(3, 3, 100));

        int[] tulos = haku.iterativeDeepeningSearch();
        
        for (int i = 0; i < tulos.length; i++) {
            System.out.print(tulos[i]);
        }
//        haku.kaynnista();
        

        
        

        
    }
}
