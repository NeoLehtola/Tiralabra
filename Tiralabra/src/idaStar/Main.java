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

        System.out.println(haku.iterativeDeepeningSearch());
        
//        haku.kaynnista();
        

        
        

        
    }
}
