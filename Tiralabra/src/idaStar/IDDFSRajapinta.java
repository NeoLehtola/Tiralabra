package idaStar;

import java.util.PriorityQueue;
import java.util.Stack;
import sovelluslogiikka.Pelitapahtuma;

/**
 * Tämä luokka yhdistää toisiinsa 15-pelin toteutuksen ja IDDFS-hakualgoritmin
 * luomalla algoritmille oikean malliset syötteet. Tämä luokka ei siis luo nodeja joilla on heuristiikka.
 */
public class IDDFSRajapinta {

    private Pelitapahtuma peli;
    private int nodeTaulukonPituus;
    private Node startNode;
    private Node goalNode;

    /**
     * konstruktori
     *
     * @param peli pelitapahtuma-olio
     */
    public IDDFSRajapinta(Pelitapahtuma peli) {
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

//    /**
//     * tekee priority queueen nykyistä nodea seuraavat siirrot Huom. tätä pitää muokata,
//     * priority queue tulee käyttöön vasta sitten heuristiikan kanssa, joten tämä luo nyt vääriä nodeja.
//     * voi olla että tämä siirtyy eri luokkaan!!!
//     * 
//     * @param current Node jolle halutaan lapset
//     * @return palauttaa noden lapset priority queuena.
//     */
//    public PriorityQueue luoNodelleLapsetJonoon(Node current) {
//        PriorityQueue<Node> jono = new PriorityQueue<Node>();
//        int[] tilanne = current.getTilanne();
//        int laudanLeveys = peli.getPelilauta().getLeveys();
//        int tyhjanIndeksi = perakkaisHaku(tilanne);
//
//
//        return jono;
//    }
    
    /**
     * tehdään seuraavat pelitilanteet ja laitetaan ne nodeina pinoon
     * @param current
     * @return pino jossa noden lapset
     */
    public Stack<Node> luoNodelleLapsetPinoon(Node current) {
        Stack<Node> pino = new Stack<Node>();
        int[] tilanne = current.getTilanne();
        int laudanLeveys = peli.getPelilauta().getLeveys();
        int tyhjanIndeksi = perakkaisHaku(tilanne);

        pino.push(seuraavaSiirto(tilanne, tyhjanIndeksi+1, laudanLeveys, tyhjanIndeksi));
        
        return pino;
    }
    
    /**
     * ei toimi vielä oikein. korjaamme.
     * @param tilanne
     * @param i
     * @param laudanLeveys
     * @param tyhjanIndeksi
     * @return 
     */
    private Node seuraavaSiirto(int[] tilanne, int i, int laudanLeveys, int tyhjanIndeksi) {
        // ollaanko oikeassa reunassa
        if (i == tilanne.length || i % laudanLeveys == 0) {
            return seuraavaSiirto(tilanne, tyhjanIndeksi-1, laudanLeveys, tyhjanIndeksi);
        }
        
        // ollaanko vasemmassa reunassa
        if (i < 0 || tyhjanIndeksi % laudanLeveys == 0) {
            return seuraavaSiirto(tilanne, tyhjanIndeksi-laudanLeveys, laudanLeveys, tyhjanIndeksi);
        }
        
        // ollaanko yläreunassa
        if (i < 0) {
            return seuraavaSiirto(tilanne, tyhjanIndeksi+laudanLeveys, laudanLeveys, tyhjanIndeksi);
        }
        
        // ollaanko alareunassa
        if (i >= tilanne.length) {
            return seuraavaSiirto(tilanne, tyhjanIndeksi+1, laudanLeveys, tyhjanIndeksi);
        }
        
        return new Node(teeUusiSiirtotilanne(tilanne, i, tyhjanIndeksi));
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
