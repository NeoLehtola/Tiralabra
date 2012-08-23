package idaStar;

import java.util.Stack;
import sovelluslogiikka.Pelitapahtuma;
import tietorakenteet.LinkitettyPino;

/**
 *
 * @author neom
 */
public class Main {

    public static void main(String[] args) {
        

        
        Haku haku = new Haku(new Pelitapahtuma(3, 3, 100));


        haku.iterativeDeepeningSearch(true, false);
        

        

        
        

        
    }
}
