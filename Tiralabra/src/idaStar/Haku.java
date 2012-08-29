package idaStar;

import sovelluslogiikka.Pelitapahtuma;
import tietorakenteet.LinkitettyPino;
import tietorakenteet.TaulukkoPino;

/**
 * IDDFS-haku ilman heuristiikkaa sekä IDA*-haku jossa on heuristiikka
 *
 * @author pklehtol
 */
public class Haku {

    private Pelitapahtuma peli;
    private int taulukonPituus;
    private final int[] ALKUTILANNE;
    private int laudanLeveys;
    private int edellinenTyhjanIndeksi; 
    private Manhattan m;
    private boolean ratkaisuLoytynyt;
    private int siirtoja;

    /**
     * konstruktori
     *
     * @param peli uusi Pelitapahtuma-olio, joka sekoittaa pelilaudan
     */
    public Haku(Pelitapahtuma peli) {
        this.peli = peli;
        this.taulukonPituus = taulukonPituus();
        this.ALKUTILANNE = alkuArvotPelilaudalta();
        this.laudanLeveys = peli.getPelilauta().getLeveys();
        this.edellinenTyhjanIndeksi = perakkaisHaku(ALKUTILANNE);
        this.m = new Manhattan();
        this.ratkaisuLoytynyt = false;
        this.siirtoja = 0;


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

//    /**
//     * apumetodi joka muodostaa seuraavan siirron
//     *
//     * @param tilanne
//     * @param i
//     * @param tyhjanIndeksi
//     * @return muutettu taulukko
//     */
//    private int[] teeUusiSiirtotilanne(int[] tilanne, int i, int tyhjanIndeksi) {
//        int[] seuraavaSiirto = kopioiTaulukko(tilanne);
//        int apu = seuraavaSiirto[i];
//        seuraavaSiirto[i] = seuraavaSiirto[tyhjanIndeksi];
//        seuraavaSiirto[tyhjanIndeksi] = apu;
//        return seuraavaSiirto;
//    }

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
        //LinkitettyPino<Integer> reittiPino = new LinkitettyPino<Integer>();

        boolean onko = false;
        while (!lapsiPino.isEmpty()) {

            onko = depthLimitedSearch(lapsiPino.pop(), syvyys - 1);
            if (onko) {
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
                ratkaisuLoytynyt = depthLimitedSearch(ALKUTILANNE, syvyys);
                syvyys++;
            }
        } else {
            int raja = m.laskeH(ALKUTILANNE, laudanLeveys, linearOn);
            while (!ratkaisuLoytynyt) {
                ratkaisuLoytynyt = idaStarSearch(ALKUTILANNE, syvyys, raja, linearOn);
                raja++;
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
        
        int h = m.laskeH(tilanne, laudanLeveys, false);
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
                  
            
            onko = idaStarSearch(lapsiPino.pop(), g+1, raja, linearOn);
            if (onko) {
                siirtoja++;
                break;
            }
        }

        return onko;

    }

    /**
     * @param tilanne
     * @return reittiPino
     */
    private LinkitettyPino<Character> tallennaReitti(int[] tilanne) {
        LinkitettyPino<Character> reittiPino = new LinkitettyPino<Character>();
        int tyhjanIndeksi = perakkaisHaku(tilanne);


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
        return ALKUTILANNE;
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
}
