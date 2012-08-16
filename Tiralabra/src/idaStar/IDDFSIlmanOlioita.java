
package idaStar;

import java.util.Stack;
import sovelluslogiikka.Pelitapahtuma;

/**
 * tämä on niinku luonnos. en tiedä vielä mihin tässä päädytään.
 * @author pklehtol
 */
public class IDDFSIlmanOlioita { 
    
    /* kokeilen nyt alkuun sellaista, että yhdistän IDDFS- ja IDDFSRajapinta-luokat, jotta
     * saisin koodista tiiviimpää. 
    */ 
    
    private Pelitapahtuma peli;
    private int taulukonPituus;
    private int[] alkuTilanne;
    private final int[] LOPPUTILANNE;
    
    /**
     * 
     * @param peli 
     */
    public IDDFSIlmanOlioita(Pelitapahtuma peli) {
        this.peli = peli;
        this.taulukonPituus = taulukonPituus();
        this.alkuTilanne = alkuArvotPelilaudalta();
        this.LOPPUTILANNE = loppuTilanne();
        
    }
    
    /**
     * peli tuntee laudan matriisina, minä haluan sen taulukkona.
     * @return taulukon pituus
     */
    private int taulukonPituus() {
        return peli.getPelilauta().getKorkeus()*peli.getPelilauta().getLeveys();
    }
    
    /**
     * apumetodi konstruktorille alkutilannetaulukon luomista varten
     *
     * @return taulukko jossa pelitilanne numeroina alkusekoituksen jälkeen
     */
    private int[] alkuArvotPelilaudalta() {
        int[] arvot = new int[taulukonPituus];
        int taulIndeksi = 0;

        for (int i = 0; i < peli.getPelilauta().getKorkeus(); i++) {
            for (int j = 0; j < peli.getPelilauta().getLeveys(); j++) {
                arvot[taulIndeksi] = peli.getPelilauta().getNappula(i, j).getTunniste();
                taulIndeksi++;
            }
        }
        return arvot;
        
    }
    
    /**
     * apumetodi konstruktorille maalitilannetaulukon varten
     *
     * @return taulukko jossa numerot järjestyksessä ja viimeisenä tyhjä (-1);
     */
    private int[] loppuTilanne() {
        int[] arvot = new int[taulukonPituus];
        for (int i = 0; i < taulukonPituus - 1; i++) {
            arvot[i] = i + 1;
        }
        arvot[taulukonPituus - 1] = -1;

        return arvot;
    }
    
    
    
//    /**
//     * tehdään seuraavat pelitilanteet ja laitetaan ne nodeina pinoon
//     *
//     * @param current
//     * @return pino jossa noden lapset
//     */
//    public Stack<Node> luoNodelleLapsetPinoon(Node current) {
//        Stack<Node> pino = new Stack<Node>();
//        int[] tilanne = current.getTilanne();
//        int laudanLeveys = peli.getPelilauta().getLeveys();
//        int tyhjanIndeksi = perakkaisHaku(tilanne);
//        // indeksit: oikealle, vasemmalle, ylös, alas
//        int[] siirtoIndeksit = {tyhjanIndeksi + 1, tyhjanIndeksi - 1, tyhjanIndeksi - laudanLeveys, tyhjanIndeksi + laudanLeveys};
//
//        for (int i : siirtoIndeksit) {
//            // ei voida siirtää sellaiseen suuntaan, joka ei ole pelilaudalla
//            if (i < 0) {
//                continue;
//            }
//
//            if (!laitonSiirto(tilanne, i, laudanLeveys, tyhjanIndeksi)) {
//                pino.push(new Node(teeUusiSiirtotilanne(tilanne, i, tyhjanIndeksi)));
//            }
//        }
//
//        return pino;
//    }
    
    // turha?
    public Stack<int[]> lapsetPinoon(int[] tilanne) {
        return null;
    }
    
    
    
    
    
      
    /**
     * taulukkoarvojen vertailu sen tarkistamiseksi, ollaanko maalitilanteessa
     * Huom. nyt ei ole erillistä isGoal()-metodia koska tällä voi tehdä saman
     * @param eka
     * @param toka
     * @return true jos pelitilanne taulukossa on sama
     */
    private boolean vertaaTilanteita(int[] eka, int[] toka) {
//        if (eka == null || toka == null) {
//            return false;
//        }       
        for (int i = 0; i < eka.length; i++) {
            if (eka[i] != toka[i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * rajattu syvyyshaku, apumetodi iteratiiviselle syvyyshaulle
     * @param tilanneNyt
     * @param loppuTilanne
     * @param syvyys haun suurin syvyys, sitä pidemmälle ei jatketa
     * @return 
     */
    public int[] depthLimitedSearch(int[] tilanneNyt, int syvyys) {
        
        if (syvyys >= 0 && vertaaTilanteita(tilanneNyt, LOPPUTILANNE)) {
            return tilanneNyt;
        } else if (syvyys > 0) {
//            tulostaTaulukko(current);
            
            Stack<int[]> lapsiPino = lapsetPinoon(tilanneNyt);
            
            int[] tulos = null;
            while (!lapsiPino.isEmpty()) {
                tulos = depthLimitedSearch(lapsiPino.pop(), syvyys-1);
                if (vertaaTilanteita(tulos, LOPPUTILANNE)) {
                    break;
                }
            }
            return tulos;
        } else {
            return null;
        }
        
    }
    
    /**
     * Iteratiivinen syvyyshaku, joka kutsuu DLS:ää 
     * @param tilanneNyt
     * @param loppuTilanne
     * @return lopputilanne
     */
    public int[] iterativeDeepeningSearch(int[] tilanneNyt) {
        int syvyys = 0;
        int[] tulos;
        
        while (true) {
            tulos = depthLimitedSearch(tilanneNyt, syvyys);
            if (vertaaTilanteita(tulos, LOPPUTILANNE)) {
                return tulos;
            }
            syvyys++;
        } 
        
    }

    /**
     * 
     * @return 
     */
    public int[] getAlkuTilanne() {
        return alkuTilanne;
    }

    /**
     * 
     * @return 
     */
    public int[] getLoppuTilanne() {
        return LOPPUTILANNE;
    }

    /**
     * 
     * @return 
     */
    public Pelitapahtuma getPeli() {
        return peli;
    }

    /**
     * 
     * @return 
     */
    public int getTaulukonPituus() {
        return taulukonPituus;
    }
     
    
    
    
}
