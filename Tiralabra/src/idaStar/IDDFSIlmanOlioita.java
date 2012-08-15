
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
    private int[] loppuTilanne;
    
    /**
     * 
     * @param peli 
     */
    public IDDFSIlmanOlioita(Pelitapahtuma peli) {
        this.peli = peli;
        this.taulukonPituus = taulukonPituus();
        this.alkuTilanne = alkuArvotPelilaudalta();
        this.loppuTilanne = loppuTilanne();
        
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
     
    
    
}
