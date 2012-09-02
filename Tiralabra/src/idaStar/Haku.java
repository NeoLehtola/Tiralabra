package idaStar;

import sovelluslogiikka.Pelitapahtuma;
import tietorakenteet.TaulukkoPino;

/**
 * IDDFS-haku ilman heuristiikkaa sekä IDA*-haku jossa on heuristiikka
 *
 * @author pklehtol
 */
public class Haku {

    private Pelitapahtuma peli;
    private int taulukonPituus;
    private int[] alkutilanne;
    private int laudanLeveys;
    private int edellinenTyhjanIndeksi; 
    private boolean ratkaisuLoytynyt;
    private int siirtoja;
    private TaulukkoPino reittiPino;

    /**
     * konstruktori
     *
     * @param peli uusi Pelitapahtuma-olio, joka sekoittaa pelilaudan
     */
    public Haku(Pelitapahtuma peli) {
        this.peli = peli;
        this.taulukonPituus = taulukonPituus();
        this.alkutilanne = alkuArvotPelilaudalta();
        this.laudanLeveys = peli.getPelilauta().getLeveys();
        this.edellinenTyhjanIndeksi = perakkaisHaku(alkutilanne);
        this.ratkaisuLoytynyt = false;
        this.siirtoja = 0;
        this.reittiPino = new TaulukkoPino();

    }

    /**
     * peli tuntee laudan matriisina, mutta tässä ohjelmassa sitä käsitellään
     * taulukkona. Tätä apumetodia voi käyttää for-silmukoissa yms.
     *
     * @return taulukon pituus
     */
    private int taulukonPituus() {
        return peli.getPelilauta().getKorkeus() * peli.getPelilauta().getLeveys();
    }

    /**
     * apumetodi konstruktorille alkutilannetaulukon luomista varten
     *
     * @return arvot taulukko jossa pelitilanne numeroina alkusekoituksen
     * jälkeen
     */
    private int[] alkuArvotPelilaudalta() {
        int[] arvot = new int[taulukonPituus];
        int taulIndeksi = 0;

        for (int i = 0; i < peli.getPelilauta().getKorkeus(); i++) {
            for (int j = 0; j < peli.getPelilauta().getLeveys(); j++) {
                arvot[taulIndeksi] = peli.getPelilauta().getArvo(i, j);
                taulIndeksi++;
            }
        }
        return arvot;

    }

    /**
     * luodaan nykytilannetta seuraavat siirrot ja laitetaan ne pinoon hakua
     * varten
     *
     * @param tilanne
     * @return pino jossa seuraavat siirrot
     */
    public TaulukkoPino lapsetPinoon(int[] tilanne) {
        
        TaulukkoPino pino = new TaulukkoPino();
        int tyhjanIndeksi = perakkaisHaku(tilanne);

        // indeksit: oikealle, vasemmalle, ylös, alas
        int[] siirtoIndeksit = {tyhjanIndeksi + 1, tyhjanIndeksi - 1, tyhjanIndeksi - laudanLeveys, tyhjanIndeksi + laudanLeveys};

        for (int i : siirtoIndeksit) {
            // ei voida siirtää sellaiseen suuntaan, joka ei ole pelilaudalla
            if (i < 0) {
                continue;
            }
            if (!laitonSiirto(i, tyhjanIndeksi) && i != edellinenTyhjanIndeksi) {
                int[] seuraavaSiirto = kopioiTaulukko(tilanne);
                int apu = seuraavaSiirto[i];
                seuraavaSiirto[i] = seuraavaSiirto[tyhjanIndeksi];
                seuraavaSiirto[tyhjanIndeksi] = apu;
                pino.push(seuraavaSiirto);
            }
        }
        edellinenTyhjanIndeksi = tyhjanIndeksi;
        
        return pino;
    }
    

    /**
     * metodi testaa, voiko kysyttyä siirtoa tehdä.
     *
     * @param vaihdettava vaihdettavan pelinappulan indeksi taulukossa
     *
     * @param tyhjanIndeksi tyhjän napin indeksi
     * @return true jos siirto on laiton eli ei voida suorittaa, ja false jos
     * voidaan siirtää
     */
    private boolean laitonSiirto(int vaihdettava, int tyhjanIndeksi) {

        // tarkistettu jo aiemmin, ettei nollaindeksillä voi tehdä vääriä siirtoja
        if (tyhjanIndeksi == 0) {
            return false;
        } else {
            // vaihdettava nappi on tyhjän vasemmalla puolella
            if (vaihdettava == tyhjanIndeksi - 1) {
                return tyhjanIndeksi % laudanLeveys == 0;
                // vaihdettava nappi on tyhjän oikealla puolella
            } else if (vaihdettava == tyhjanIndeksi + 1) {
                return vaihdettava % laudanLeveys == 0;
            } else {
                return vaihdettava >= taulukonPituus;
            }
        }
    }


    /**
     * kopion muodostaminen taulukosta (manuaalinen System.arraycopy)
     *
     * @param alkup
     * @return uusi taulukko, joka on kopio
     */
    private int[] kopioiTaulukko(int[] alkup) {
        int[] uusi = new int[alkup.length];

        for (int i = 0; i < uusi.length; i++) {
            uusi[i] = alkup[i];
        }
        return uusi;
    }

    /**
     * apumetodi tyhjän napin (-1) löytämiseksi
     *
     * @param taulukko
     * @return tyhjän kohdan indeksi
     */
    private int perakkaisHaku(int[] taulukko) {
        for (int i = 0; i < taulukko.length; i++) {
            if (taulukko[i] == -1) {
                return i;
            }
        }
//        // jos päästään tänne, jossain on jokin virhe
        return -100;
    }

    /**
     * apumetodi joka tarkistaa, onko nykyinen pelitilanne pelin maalitilanne
     *
     * @param tilanne
     * @return true jos pelilauta on järjestyksessä
     */
    private boolean onMaali(int[] tilanne) {
        if (tilanne == null) {
            return false;
        }
        // tarkistetaan vain toiseksi viimeiseen indeksiin, viimeinen on -1
        for (int i = 0; i < tilanne.length - 1; i++) {
            if (tilanne[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * rajattu syvyyshaku, apumetodi iteratiiviselle syvyyshaulle
     *
     * @param tilanne pelin tilanne
     * @param syvyys haun suurin syvyys, sitä pidemmälle ei jatketa
     * @return true jos ollaan maalitilanteessa, muuten false
     */
    public boolean depthLimitedSearch(int[] tilanne, int syvyys) {
                
        if (onMaali(tilanne)) {
            return true;
        }

        if (syvyys == 0) {
            return false;
        }

        TaulukkoPino lapsiPino = lapsetPinoon(tilanne);

        boolean onko = false;
        while (!lapsiPino.isEmpty()) {

            int[] lapsi = lapsiPino.pop();
            onko = depthLimitedSearch(lapsi, syvyys - 1);
            if (onko) {
                reittiPino.push(lapsi);
                siirtoja++;
                break;
            }
        }

        return onko;
    }

    /**
     * iteratiivinen syvyyshaku
     *
     * @param manhattanOn true jos manhattan distance otetaan käyttöön
     * @param linearOn true jos linear conflict otetaan käyttöön
     */
    public void iterativeDeepeningSearch(boolean manhattanOn, boolean linearOn) {
        int syvyys = 0;

        // linear ei voi olla päällä ilman manhattania
        if (!manhattanOn) {
            while (!ratkaisuLoytynyt) {
                ratkaisuLoytynyt = depthLimitedSearch(alkutilanne, syvyys);
                syvyys++;
            }
        } else {
            int raja = laskeH(alkutilanne, linearOn);
            while (!ratkaisuLoytynyt) {
                ratkaisuLoytynyt = idaStarSearch(alkutilanne, syvyys, raja, linearOn);
                raja++;
//                System.out.println(raja);
            }
        }
    }

    /**
     * syvyyshaku heuristiikalla
     *
     * @param tilanne pelin tilanne
     * @param g haun syvyys (heuristiikkafunktion g-arvo)
     * @param raja arvioitu etäisyys maaliin
     * @param linearOn true, jos linearconflict käytössä
     * @return true jos ollaan maalitilanteessa, muuten false
     */
    public boolean idaStarSearch(int[] tilanne, int g, int raja, boolean linearOn) {
        
        int h = laskeH(tilanne, linearOn);
        if (h == 0) {
            return true;
        }
        
        int f = g + h;
        if (f > raja) {
            return false;
        }
                   
        TaulukkoPino lapsiPino = lapsetPinoon(tilanne);
        
        boolean onko = false;
        while (!lapsiPino.isEmpty()) {
                  
            int[] lapsi = lapsiPino.pop();
            onko = idaStarSearch(lapsi, g+1, raja, linearOn);
            if (onko) {
                reittiPino.push(lapsi);            
                siirtoja++;
           
                break;
            }
        }

        return onko;

    }
    
    /**
     * Tämä laskee Manhattan Distancen, ja etsii sitä varten jokaisen luvun
     * koordinaatit nykyisessä tilanteessa sekä maalitilanteessa
     *
     * @param tilanne pelitilanne
     * @param laudanLeveys
     * @param laskeKonflikti jos true, otetaan Linear Conflict -tilanne
     * huomioon.
     *
     * @return summa eli haluttu h-arvo
     */
    public int laskeH(int[] tilanne, boolean laskeKonflikti) {

        int summa = 0;

        for (int i = 0; i < tilanne.length; i++) {
            if (tilanne[i] == -1) {
                continue;
            }
            int vuorossa = tilanne[i];

            int vuorossaXKoord = i % laudanLeveys;
            int vuorossaYKoord = i / laudanLeveys;

            int maaliXKoord = (vuorossa - 1) % laudanLeveys;
            int maaliYKoord = (vuorossa - 1) / laudanLeveys;

            /* linear conflict
             * ottaa huomioon vain vierekkäiset napit leveyssuunnassa,
             * 
             */
            if (laskeKonflikti && i > 0) {
              
                //ensin tarkistetaan onko kaksi nappia "väärinpäin" ja sitten ovatko ne samalla rivillä
                if (vuorossaYKoord == maaliYKoord && tilanne[i] == tilanne[i - 1] - 1 && i % laudanLeveys != 0) {
                    summa += 2;
                }
           
            }

            // h-arvon laskeminen
            summa += Math.abs(vuorossaXKoord - maaliXKoord) + Math.abs(vuorossaYKoord - maaliYKoord);
        }
        return summa;
    }

    /**
     * 
     * @return reittiPino
     */
    public TaulukkoPino getReittiPino() {
        return reittiPino;
    }
   

    /**
     * palauttaa pelitapahtuman jossa haku tehdään
     *
     * @return this.peli
     */
    public Pelitapahtuma getPeli() {
        return peli;
    }

    /**
     * palauttaa pelin aloitustilanteen
     *
     * @return ALKUTILANNE
     */
    public int[] getAlkutilanne() {
        return alkutilanne;
    }

    /**
     * palauttaa tiedon siitä ollaanko maalissa
     *
     * @return ratkaisuLoytynyt
     */
    public boolean isRatkaisuLoytynyt() {
        return ratkaisuLoytynyt;
    }
    
    /**
     * palauttaa tiedon siitä, monellako siirrolla ratkaisu löytyi
     * @return siirtojen määrä
     */
    public int getSiirtojenMaara() {
        return siirtoja;
    }
    
    
    public void setAlkutilanne(int[] tilanne) {
        this.alkutilanne = tilanne;
    }
}
