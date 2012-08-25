package sovelluslogiikka;

public class Pelilauta {
    
    /*
     * Pelilauta on yliluokka, ja se luo laudan ja antaa alkuarvot,
     * mutta ei tiedä sekoituksista ja siirroista mitään. Aliluokka hoitaa nämä.
     */

    private int[][] lauta;
    private int leveys;
    private int korkeus;

    /**
     * tätä konstruktoria käytetään, jos aloitetaan kokonaan uusi peli
     * @param korkeus
     * @param leveys 
     */
    public Pelilauta(int korkeus, int leveys) {
        this.korkeus = korkeus;
        this.leveys = leveys;
        lauta = new int[korkeus][leveys];
        asetaNumerotJarjestykseenJaJataViimeinenTyhjaksi();
    }
    
    /**
     * tämä konstruktori on tallennetun pelin lataamista varten
     * @param lauta 
     */
    
    public Pelilauta(int[][] lauta) {
        this.lauta = lauta;
    }



    /**
     * konstruktorin apumetodi
     */
    private void asetaNumerotJarjestykseenJaJataViimeinenTyhjaksi() {
        int nro = 1;
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                lauta[i][j] = nro;
                nro++;
                if (i == korkeus - 1 && j == leveys - 1) {
                    lauta[i][j] = -1;
                }
            }
        }
    }

    /**
     * 
     * @param korkeus
     * @param leveys
     * @return 
     */
    public int getArvo(int korkeus, int leveys) {
        return lauta[korkeus][leveys];
    }
    
    /**
     * 
     * @param korkeus
     * @param leveys
     * @param nro 
     */
    public void setArvo(int korkeus, int leveys, int nro) {
        lauta[korkeus][leveys] = nro;
    }

    /**
     * 
     * @return 
     */
    public int getLeveys() {
        return leveys;
    }

    /**
     * 
     * @return 
     */
    public int getKorkeus() {
        return korkeus;
    }

    /**
     * 
     * @return 
     */
    public int[][] getLauta() {
        return lauta;
    }

    /**
     * 
     * @param korkeus 
     */
    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
    }

    /**
     * 
     * @param leveys 
     */
    public void setLeveys(int leveys) {
        this.leveys = leveys;
    }
    
    

}
