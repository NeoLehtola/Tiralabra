package idaStar;

import java.util.Stack;
import sovelluslogiikka.Pelitapahtuma;
import tietorakenteet.Pino;

/**
 * IDDFS-haku. (Aika kamala megaluokka toistaiseksi.)
 *
 * @author pklehtol
 */
public class Haku {

    private Pelitapahtuma peli;
    private int taulukonPituus;
    private int[] tilanne;
    private final int[] ALKUTILANNE;
    private int funktionArvo;
    private int costLimit;
    private Manhattan m;
    private Stack<int[]> pathSoFar;

    /**
     *
     * @param peli
     */
    public Haku(Pelitapahtuma peli) {
        this.peli = peli;
        this.taulukonPituus = taulukonPituus();
        this.ALKUTILANNE = alkuArvotPelilaudalta();
        this.tilanne = kopioiTaulukko(ALKUTILANNE);
        this.pathSoFar = new Stack<int[]>();
        this.pathSoFar.push(ALKUTILANNE);
        this.costLimit = 0 + m.laskeH(ALKUTILANNE, peli.getPelilauta().getLeveys(), false);
        this.m = new Manhattan();


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
    public Stack<int[]> lapsetPinoon(int[] tilanne) {

        Stack<int[]> pino = new Stack<int[]>();
        int laudanLeveys = peli.getPelilauta().getLeveys();
        int tyhjanIndeksi = perakkaisHaku(tilanne);

        // indeksit: oikealle, vasemmalle, ylös, alas
        int[] siirtoIndeksit = {tyhjanIndeksi + 1, tyhjanIndeksi - 1, tyhjanIndeksi - laudanLeveys, tyhjanIndeksi + laudanLeveys};

        for (int i : siirtoIndeksit) {
            // ei voida siirtää sellaiseen suuntaan, joka ei ole pelilaudalla
            if (i < 0) {
                continue;
            }
            if (!laitonSiirto(i, laudanLeveys, tyhjanIndeksi)) {
                pino.push(teeUusiSiirtotilanne(i, tyhjanIndeksi));
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
     * @param taulukko jossa pelitilanteen vaihto tehdään
     * @return muutettu taulukko
     */
    private int[] teeUusiSiirtotilanne(int i, int tyhjanIndeksi) {
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
     * haku on tällä hetkellä täysin rikki ja palasina, sitä ei kannata katsoa koska sen ei ole
     * tarkoituskaan jäädä tuollaiseksi.
     * rajattu syvyyshaku, apumetodi iteratiiviselle syvyyshaulle
     *
     * @param startCost heuristiikkafunktion g-arvo käsittääkseni
     * @return
     */
    public int depthLimitedSearch(int startCost) {

        int[] node = pathSoFar.pop(); // vai pop?
        int minimumCost = startCost + m.laskeH(node, startCost, true);

        if (minimumCost > costLimit) {
            costLimit = minimumCost;
//        if (costLimit >= 0 && onMaali(tilanneNyt)) {
//            return tilanneNyt;
        }

        if (onMaali(node)) {
        }
        
        int nextCostLimit = Integer.MAX_VALUE;

        Stack<int[]> lapsiPino = lapsetPinoon(node);

        while (!lapsiPino.isEmpty()) {
            int newStartCost = startCost + 1;
            pathSoFar.push(lapsiPino.pop());

            int newCostLimit = depthLimitedSearch(startCost + 1);

        }
        return nextCostLimit;

    }

    /**
     * graafista sovellusta varten tarvitaan tieto siitä, mikä oli lyhin reitti
     * maalitilanteeseen
     */
    public void tallennaReitti() {
    }

    /**
     * Iteratiivinen syvyyshaku, joka kutsuu DLS:ää
     *
     * @return lopputilanne
     */
    public int[] iterativeDeepeningSearch() {

        //int syvyys = 0;


        while (true) {
            // pinossa on vain alkutilanne
//            tulos = depthLimitedSearch(0);
//            if (onMaali(tulos)) {
//                return tulos;
//            }
            //syvyys++;
        }
    }

    /**
     * palauttaa nykytilanteen
     *
     * @return this.tilanne
     */
    public int[] getTilanne() {
        return tilanne;
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
}
