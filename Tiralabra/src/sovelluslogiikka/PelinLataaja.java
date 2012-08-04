package sovelluslogiikka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * lukee pelitilanteen tiedostosta ja muodostaa pelilaudan oikeilla nappuloiden
 * sijainneilla
 *
 */


public class PelinLataaja {

    private File file;


    /**
     * metodi lataa tiedostosta tallennetun pelin ja luo sen pohjalta uuden Pelitapahtuman
     * @param tiedostoNimi 
     */
    public PelinLataaja(String tiedostoNimi) {
        file = new File(tiedostoNimi);
    }

    /**
     * Metodi kerää tallennustiedostosta laudan korkeuden ja leveyden, tallennetun pelin
     * vuorojen määrän sekä nappuloiden tunnisteet apumetodin avulla. 
     * @return uusi pelitapahtuma, jossa pelilauta tallennetun mukainen
     */
    


    public Pelitapahtuma luoUusiPeliTallennetunPohjalta() {

        try {
        Scanner lukija = new Scanner(file);
        
        // jos tiedosto on tyhjä, palautetaan null
        if (!lukija.hasNextInt()) {
            return null;
        }
        
        int laudanKorkeus = lukija.nextInt();
        int laudanLeveys = lukija.nextInt();
        int vuorojenMaara = lukija.nextInt();
        lukija.close();
      
        int[] tunnisteet = keraaTunnisteet(laudanKorkeus, laudanLeveys);
        SiirtavaPelilauta uusiPelilauta = luoUusiPelilauta(laudanKorkeus, laudanLeveys, tunnisteet);
        
        return new Pelitapahtuma(uusiPelilauta, vuorojenMaara);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
    
    /**
     * apumetodi, joka lataa tiedostosta tallennetun pelin nappien tunnisteet
     * @param laudanKorkeus
     * @param laudanLeveys
     * @return tunnisteet int-taulukkona
     *  
     */
    private int[] keraaTunnisteet(int laudanKorkeus, int laudanLeveys) {
        try {
        Scanner lukija = new Scanner(file);
        lukija.nextLine();
        int[] tunnisteet = new int[laudanKorkeus*laudanLeveys];
        for (int i = 0; i < tunnisteet.length; i++) {
            tunnisteet[i] = lukija.nextInt();
        }
        return tunnisteet;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
    
    /**
     * apumetodi, joka luo ladattujen tunnisteiden pohjalta Nappula-olioista koostuvan
     * laudan. Tätä käytetään parametrina kun luodaan uusi Pelitapahtuma metodissa
     * luoUusiPeliTallennetunPohjalta().
     * @param laudanKorkeus
     * @param laudanLeveys
     * @param tunnisteet
     * @return 
     */
    
    private Nappula[][] luoLauta(int laudanKorkeus, int laudanLeveys, int[] tunnisteet) {
        Nappula[][] lauta = new Nappula[laudanKorkeus][laudanLeveys];
        int seuraavaTunniste = 0; 
        
        for (int i = 0; i < lauta.length; i++) {
            for (int j = 0; j < lauta[i].length; j++) {
                lauta[i][j] = new Nappula(tunnisteet[seuraavaTunniste]);
                seuraavaTunniste++;
            }
        }
        return lauta;
    }
    
    /**
     * apumetodi, joka luo uuden SiirtavaPelilauta-luokan ilmentymän.
     * @param laudanKorkeus
     * @param laudanLeveys
     * @param tunnisteet
     * @return 
     */
    private SiirtavaPelilauta luoUusiPelilauta(int laudanKorkeus, int laudanLeveys, int[] tunnisteet) {
        SiirtavaPelilauta uusiPelilauta = new SiirtavaPelilauta(luoLauta(laudanKorkeus, laudanLeveys, tunnisteet));
        uusiPelilauta.setKorkeus(laudanKorkeus);
        uusiPelilauta.setLeveys(laudanLeveys);
        return uusiPelilauta;
    }
    

    

    /**
     * tämä on vain testejä varten
     * @return 
     */

    public File getFile() {
        return file;
    }
    

    
    
}
