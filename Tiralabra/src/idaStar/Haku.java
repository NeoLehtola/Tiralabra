package idaStar;

import sovelluslogiikka.Pelitapahtuma;
import tietorakenteet.LinkitettyPino;

/**
 * IDDFS-haku. (Aika kamala megaluokka toistaiseksi.)
 *
 * @author pklehtol
 */
public class Haku {

    private Pelitapahtuma peli;
    private int taulukonPituus;
    private final int[] ALKUTILANNE;
    private Manhattan m;
    private boolean ratkaisuLoytynyt;

    /**
     *
     * @param peli
     */
    public Haku(Pelitapahtuma peli) {
        this.peli = peli;
        this.taulukonPituus = taulukonPituus();
        this.ALKUTILANNE = alkuArvotPelilaudalta();

        this.m = new Manhattan();
        this.ratkaisuLoytynyt = false;


    }

    /**
     * peli tuntee laudan matriisina, minä haluan sen taulukkona.
     *
     * @return taulukon pituus
     */
    private int taulukonPituus() {
        return peli.getPelilauta().getKorkeus() * peli.getPelilauta().getLeveys();
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
     * luodaan nykytilannetta seuraavat siirrot ja laitetaan ne pinoon hakua
     * varten
     *
     * @param tilanne
     * @return pino jossa seuraavat siirrot
     */
    public LinkitettyPino<int[]> lapsetPinoon(int[] tilanne) {

//        Stack<int[]> pino = new Stack<int[]>();
        LinkitettyPino<int[]> pino = new LinkitettyPino<int[]>();
        int laudanLeveys = peli.getPelilauta().getLeveys();
        int tyhjanIndeksi = perakkaisHaku(tilanne);

        // indeksit: oikealle, vasemmalle, ylös, alas
//        int[] siirtoIndeksit = {tyhjanIndeksi + 1, tyhjanIndeksi - 1, tyhjanIndeksi - laudanLeveys, tyhjanIndeksi + laudanLeveys};

        int[] siirtoIndeksit = {tyhjanIndeksi + 1, tyhjanIndeksi - 1, tyhjanIndeksi - laudanLeveys, tyhjanIndeksi + laudanLeveys};



        for (int i : siirtoIndeksit) {
            // ei voida siirtää sellaiseen suuntaan, joka ei ole pelilaudalla
            if (i < 0) {
                continue;
            }
            if (!laitonSiirto(i, laudanLeveys, tyhjanIndeksi)) {
                pino.push(teeUusiSiirtotilanne(tilanne, i, tyhjanIndeksi));
            }
        }
        return pino;
    }

    /**
     * metodi testaa, voiko kysyttyä siirtoa tehdä.
     *
     * @param vaihdettava vaihdettavan pelinappulan indeksi taulukossa
     * @param laudanLeveys
     * @param tyhjanIndeksi tyhjän napin indeksi
     * @return true jos siirto on laiton eli ei voida suorittaa, ja false jos
     * voidaan siirtää
     */
    private boolean laitonSiirto(int vaihdettava, int laudanLeveys, int tyhjanIndeksi) {

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
     * apumetodi joka muodostaa seuraavan siirron
     *
     * @param tilanne
     * @param i
     * @param tyhjanIndeksi
     * @return muutettu taulukko
     */
    private int[] teeUusiSiirtotilanne(int[] tilanne, int i, int tyhjanIndeksi) {
        int[] seuraavaSiirto = kopioiTaulukko(tilanne);
        int apu = seuraavaSiirto[i];
        seuraavaSiirto[i] = seuraavaSiirto[tyhjanIndeksi];
        seuraavaSiirto[tyhjanIndeksi] = apu;
        return seuraavaSiirto;
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
     *
     * @param tilanne
     * @return
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
*
     * @param tilanneNyt
     * @param syvyys haun suurin syvyys, sitä pidemmälle ei jatketa
     * @return
     */
    // syvyys = g?
    // f = syvyys + laskeH
    public boolean depthLimitedSearch(int[] tilanne, int syvyys) {

        if (onMaali(tilanne)) {
            return true;
        }

        if (syvyys == 0) {
            return false;
        }

        LinkitettyPino<int[]> lapsiPino = lapsetPinoon(tilanne);
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
     * Iteratiivinen syvyyshaku, joka kutsuu DLS:ää
     *
     * @return lopputilanne
     */
    public void iterativeDeepeningSearch(boolean heuristiikkaOn) {
        int syvyys = 0;

        if (!heuristiikkaOn) {
            while (!ratkaisuLoytynyt) {
                ratkaisuLoytynyt = depthLimitedSearch(ALKUTILANNE, syvyys);
                syvyys++;
            }
        } else {
            int h = m.laskeH(ALKUTILANNE, peli.getPelilauta().getLeveys(), false);
            while (!ratkaisuLoytynyt) {
                ratkaisuLoytynyt = idaStarSearch(ALKUTILANNE, syvyys, h);
                
            }
        }
    }

    /**
     *
     * @param tilanne
     * @param syvyys
     * @param raja
     * @return
     */
    public boolean idaStarSearch(int[] tilanne, int syvyys, int raja) {
        
        if (onMaali(tilanne)) {
            return true;
        }

        int f = syvyys+m.laskeH(tilanne, peli.getPelilauta().getLeveys(), false);
        if (f > raja) {
            return false;
        }

        LinkitettyPino<int[]> lapsiPino = lapsetPinoon(tilanne);
        //Stack<Integer> reittiPino = new Stack<Integer>();

        boolean onko = false;
        while (!lapsiPino.isEmpty()) {

            onko = idaStarSearch(lapsiPino.pop(), syvyys+1, f);
            if (onko) {
                break;
            }   
            
        }

        return onko;

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
     * palauttaa pelitaulukon pituuden
     *
     * @return this.taulukonPituus
     */
    public int getTaulukonPituus() {
        return taulukonPituus;
    }

    public int[] getAlkutilanne() {
        return ALKUTILANNE;
    }

    public boolean isRatkaisuLoytynyt() {
        return ratkaisuLoytynyt;
    }
}
