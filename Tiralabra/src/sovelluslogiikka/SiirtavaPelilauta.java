package sovelluslogiikka;

import java.util.Random;

/**
 * Tämä luokka perii Pelilauta-luokan, lisäte siihen sekoittamisen ja
 * nappuloiden siirtämisen. Nimensä mukaisesti tämä luokka siis varsinaisesti
 * käyttää pelilautaa.
 */
public class SiirtavaPelilauta extends Pelilauta {

    private int sekoitusMaara;

    /**
     * tämä konstruktori on kokonaan uuden pelin luomista varten
     * @param korkeus
     * @param leveys
     * @param sekoitusMaara 
     */
    public SiirtavaPelilauta(int korkeus, int leveys, int sekoitusMaara) {
        super(korkeus, leveys);
        this.sekoitusMaara = sekoitusMaara;
        sekoitaNumerot();
    }
    
    /**
     * tämä konstruktori on tallennetun pelin lataamista varten
     * @param lauta 
     */
    public SiirtavaPelilauta(int[][] lauta) {
        super(lauta);
        this.sekoitusMaara = 0;
    }
    

    /**
     * Lauta sekoitetaan nimenomaan tekemällä sallittuja siirtoja, sillä pelilaudan
     * on oltava ratkaistavissa.
     */
    private void sekoitaNumerot() {
        Random r = new Random();
        for (int i = 0; i < sekoitusMaara; i++) {
            int siirronKorkeus = r.nextInt(super.getKorkeus());
            int siirronLeveys = r.nextInt(super.getLeveys());
            this.teeSiirto(siirronKorkeus, siirronLeveys);

        }

    }


    // muokkaan siirtometodit lopullisesti jahka ehdin
    
    /**
     * jos siirretään vasemmalle, suunta oltava -1, jos oikealle, suunta 1
     * @param napinKorkeus
     * @param napinLeveys
     * @param suunta
     * @return 
     */
    protected boolean siirraVaakasuunnassa(int napinKorkeus, int napinLeveys, int suunta) {
       if ((suunta == 1 && napinLeveys + 1 >= getLeveys()) || (suunta == -1 &&  napinLeveys - 1 < 0) ) {
           return false;
       } 
       
       if  (getLauta()[napinKorkeus][napinLeveys + suunta] == -1) {
            getLauta()[napinKorkeus][napinLeveys + suunta] = getLauta()[napinKorkeus][napinLeveys];
            getLauta()[napinKorkeus][napinLeveys] = -1;
            return true;
       }
    
        return false;
    }
    
    /**
     * jos siirretään ylös, suunta -1, jos alas, suunta 1
     * @param napinKorkeus
     * @param napinLeveys
     * @param suunta
     * @return 
     */
    protected boolean siirraPystysuunnassa(int napinKorkeus, int napinLeveys, int suunta) {
        if ((suunta == 1 && napinKorkeus + 1 >= getKorkeus()) || (suunta == -1 && napinKorkeus - 1 < 0 )) {
            return false;
        }
        
        if (getLauta()[napinKorkeus + suunta][napinLeveys] == -1) {
            getLauta()[napinKorkeus + suunta][napinLeveys] = getLauta()[napinKorkeus][napinLeveys];
            getLauta()[napinKorkeus][napinLeveys] = -1;
            return true;
        }
        
        return false;
    }



    /**
     * tarkistaa siirrettävän napin koordinaatit
     * @param napinKorkeus
     * @param napinLeveys
     * @return true jos nappi laudalla, false jos nappi on laudan ulkopuolella
     */
    private boolean siirrettavaksiPyydettyNumeroOnLaudalla(int napinKorkeus, int napinLeveys) {
        return napinKorkeus <= getKorkeus() && napinLeveys <= getLeveys() && napinKorkeus >= 0 && napinLeveys >= 0;
            

    }

    /**
     * kokoava julkinen metodi, joka tarkistaa mihin suuntaan pystytään
     * siirtymään, ja tekee siirron
     *
     * @param napinKorkeus
     * @param napinLeveys
     * @return true jos siirto johonkin suuntaan onnistuu
     */
    public boolean teeSiirto(int napinKorkeus, int napinLeveys) {
        if (!siirrettavaksiPyydettyNumeroOnLaudalla(napinKorkeus, napinLeveys)) {
            return false;
        }

        return voikoNappiaSiirtaaJohonkinSuuntaan(napinKorkeus, napinLeveys);


    }

    /**
     * tarkistaa, voiko annettua nappia siirtää mihinkään suuntaan
     * @param napinKorkeus
     * @param napinLeveys
     * @return true jos jokin siirto onnistuu
     */
    private boolean voikoNappiaSiirtaaJohonkinSuuntaan(int napinKorkeus, int napinLeveys) {
        return siirraVaakasuunnassa(napinKorkeus, napinLeveys, 1) || siirraVaakasuunnassa(napinKorkeus, napinLeveys, -1)|| (siirraPystysuunnassa(napinKorkeus, napinLeveys, -1)) || (siirraPystysuunnassa(napinKorkeus, napinLeveys, 1));

    }

    /**
     * tarkistaa ovatko nappulat järjestyksessä ja peli päättynyt
     *
     * @return true jos peli päättynyt
     */
    public boolean lautaValmis() {
        int nro = 1;
        for (int i = 0; i < getKorkeus(); i++) {
            for (int j = 0; j < getLeveys(); j++) {
                if (i == getKorkeus() - 1 && j == getLeveys() - 1) {
                    return true;
                }

                if (getArvo(i, j) != nro) {
                    return false;
                }
                nro++;
            }
        }

        return true;
    }

    public int getSekoitusMaara() {
        return sekoitusMaara;
    }

    /**
     * tällä voi valita montako kertaa laudan nappuloita siirretään
     * sekoitusvaiheessa hyödyllisempi testivaiheessa kuin varsinaisessa pelissä
     *
     * @param sekoitusMaara
     */
    public void setSekoitusMaara(int sekoitusMaara) {
        this.sekoitusMaara = sekoitusMaara;
    }
}
