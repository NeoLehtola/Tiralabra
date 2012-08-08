package idaStar;

import java.util.PriorityQueue;
import sovelluslogiikka.Pelitapahtuma;

/**
 * Tämä luokka yhdistää toisiinsa 15-pelin toteutuksen ja hakualgoritmin
 * luomalla algoritmille oikean malliset syötteet. Luokka käsittelee
 * toistaiseksi vain nodeja, joihin ei ole liitetty mitään heuristiikkaa.
 */
public class Rajapinta {

    private Pelitapahtuma peli;
    private int nodeTaulukonPituus;
    private Node startNode;
    private Node goalNode;

    /**
     * konstruktori
     *
     * @param peli pelitapahtuma-olio
     */
    public Rajapinta(Pelitapahtuma peli) {
        this.peli = peli;
        this.nodeTaulukonPituus = laskeNodeTaulukonPituus();
        this.startNode = new Node(otaAlkuArvotTalteenPelilaudalta());
        this.goalNode = new Node(luoGoalNodenArvot());
    }

    /**
     * apumetodi konstruktorille
     *
     * @return pelilaudan nappuloiden määrä, joka on taulukon pituus
     */
    private int laskeNodeTaulukonPituus() {
        int leveys = peli.getPelilauta().getLeveys();
        int korkeus = peli.getPelilauta().getKorkeus();
        return leveys * korkeus;
    }

    /**
     * apumetodi konstruktorille startNoden luomista varten
     *
     * @return taulukko jossa pelitilanne numeroina alkusekoituksen jälkeen
     */
    private int[] otaAlkuArvotTalteenPelilaudalta() {
        int[] arvot = new int[nodeTaulukonPituus];
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
     * apumetodi konstruktorille goalNoden luomista varten
     *
     * @return taulukko jossa numerot järjestyksessä ja viimeisenä tyhjä (-1);
     */
    private int[] luoGoalNodenArvot() {
        int[] arvot = new int[nodeTaulukonPituus];
        for (int i = 0; i < nodeTaulukonPituus - 1; i++) {
            arvot[i] = i + 1;
        }
        arvot[nodeTaulukonPituus - 1] = -1;

        return arvot;
    }

    /**
     * tekee priority queueen nykyistä nodea seuraavat siirrot Huom. priority
     * queuen käyttö johtuu siitä että myöhemmin nodeihin lisätään heuristiikat.
     *
     * @param current Node jolle halutaan lapset
     * @return palauttaa noden lapset priority queuena.
     */
    public PriorityQueue luoNodelleLapset(Node current) {
        PriorityQueue<Node> jono = new PriorityQueue<Node>();
        int[] tilanne = current.getTilanne();
        int laudanLeveys = peli.getPelilauta().getLeveys();
        int tyhjanIndeksi = perakkaisHaku(tilanne);


        jono.add(vaihdaOikeanpuolimmaiseen(tilanne, laudanLeveys, tyhjanIndeksi));
        jono.add(vaihdaVasemmanpuolimmaiseen(tilanne, laudanLeveys, tyhjanIndeksi));
        jono.add(vaihdaYlapuoliseen(tilanne, laudanLeveys, tyhjanIndeksi));
        jono.add(vaihdaAlapuoliseen(tilanne, laudanLeveys, tyhjanIndeksi));

        return jono;
    }

    /**
     *
     * @param tilanne
     * @param laudanLeveys
     * @param tyhjanIndeksi
     * @return
     */
    private Node vaihdaOikeanpuolimmaiseen(int[] tilanne, int laudanLeveys, int tyhjanIndeksi) {

        int i = tyhjanIndeksi + 1;

        // ollaanko oikeassa reunassa
        if (i == tilanne.length || i % laudanLeveys == 0) {
            return null;
        }

        Node node = new Node(teeUusiSiirtotilanne(tilanne, i, tyhjanIndeksi));
        return node;
    }

    /**
     *
     * @param tilanne
     * @param laudanLeveys
     * @param tyhjanIndeksi
     * @return
     */
    private Node vaihdaVasemmanpuolimmaiseen(int[] tilanne, int laudanLeveys, int tyhjanIndeksi) {

        int i = tyhjanIndeksi - 1;

        // ollaanko vasemmassa reunassa
        if (i < 0 || tyhjanIndeksi % laudanLeveys == 0) {
            return null;
        }

        Node node = new Node(teeUusiSiirtotilanne(tilanne, i, tyhjanIndeksi));
        return node;
    }

    /**
     *
     * @param tilanne
     * @param laudanLeveys
     * @param tyhjanIndeksi
     * @return
     */
    private Node vaihdaYlapuoliseen(int[] tilanne, int laudanLeveys, int tyhjanIndeksi) {

        int i = tyhjanIndeksi - laudanLeveys;

        // ollaanko yläreunassa
        if (i < 0) {
            return null;
        }

        Node node = new Node(teeUusiSiirtotilanne(tilanne, i, tyhjanIndeksi));
        return node;
    }

    /**
     *
     * @param tilanne
     * @param laudanLeveys
     * @param tyhjanIndeksi
     * @return
     */
    private Node vaihdaAlapuoliseen(int[] tilanne, int laudanLeveys, int tyhjanIndeksi) {

        int i = tyhjanIndeksi + laudanLeveys;

        if (i >= tilanne.length) {
            return null;
        }

        Node node = new Node(teeUusiSiirtotilanne(tilanne, i, tyhjanIndeksi));
        return node;
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
        // jos päästään tänne, jossain on jokin virhe
        return -100;
    }

    /**
     * kopion muodostaminen taulukosta
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
     * apumetodi nodejonon luovalle metodille
     *
     * @param taulukko jossa pelitilanteen vaihto tehdään
     * @return muutettu taulukko
     */
    private int[] teeUusiSiirtotilanne(int[] taulukko, int i, int tyhjanIndeksi) {
        int[] uusiTilanne = kopioiTaulukko(taulukko);
        int apu = uusiTilanne[i];
        uusiTilanne[i] = uusiTilanne[tyhjanIndeksi];
        uusiTilanne[tyhjanIndeksi] = apu;
        return uusiTilanne;
    }

    /**
     * palauttaa noden luomiseen käytetyn pelitilannetaulukon pituuden
     *
     * @return taulukon pituus
     */
    public int getNodeTaulukonPituus() {
        return nodeTaulukonPituus;
    }

    /**
     * palauttaa aloitussolmun
     *
     * @return startNode
     */
    public Node getStartNode() {
        return startNode;
    }

    /**
     * palauttaa maalisolmun, jossa napit ovat järjestyksessä
     *
     * @return goalNode
     */
    public Node getGoalNode() {
        return goalNode;
    }

    /**
     * aloitussolmun manuaalinen asetus, lähinnä testausta varten
     * @param startNode 
     */
    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }
}
