package idaStar;

import java.util.PriorityQueue;
import sovelluslogiikka.Pelitapahtuma;

/**
 * Luokka käsittelee toistaiseksi vain nodeja, joihin ei ole liitetty mitään
 * heuristiikkaa Rajapinta on työnimi, kehitän jotain parempaa
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

    // seuraava megametodi pitää ehdottomasti pilkkoa pienemmäksi. eihän tota lue erkkikään.
    /**
     * tekee priority queueen nykyistä nodea seuraavat siirrot
     *
     * @param current Node jolle halutaan lapset
     * @return palauttaa noden lapset priority queuena.
     */
    public PriorityQueue luoNodelleLapset(Node current) {
        PriorityQueue<Node> jono = new PriorityQueue<Node>();

        int pelilaudanLeveys = peli.getPelilauta().getLeveys();
        int pelilaudanKorkeus = peli.getPelilauta().getKorkeus();

        int[] uusiSiirto = kopioiTaulukko(current.getTilanne());
        int tyhjanIndeksi = perakkaisHaku(current.getTilanne());

        
        while (true) {


            break;
        }




        // tämä pitää nyt testata huolellisesti, toimiiko ensinkään
        // ja copy-pastet pois. mieti ehdot ja rakenne!
//        for (int i = 0; i < current.getPituus(); i++) {
//            if (i == tyhjanIndeksi - pelilaudanLeveys) {
//                jono.add(new Node(vaihdaKeskenaan(uusiSiirto, i, tyhjanIndeksi)));
//                
//            }
//
//            // jos tyhjanIndeksi%pelilaudanLeveys == 0, nappula on vasemmassa reunassa
//            // tarkista että indeksit menee oikein!!!!!
//            if (i == tyhjanIndeksi - 1 && tyhjanIndeksi%pelilaudanLeveys != 0) {
//                jono.add(new Node(vaihdaKeskenaan(uusiSiirto, i, tyhjanIndeksi)));
//            }
//            
//            if (i == tyhjanIndeksi+1  && tyhjanIndeksi+1%pelilaudanLeveys != 0) {
//                jono.add(new Node(vaihdaKeskenaan(uusiSiirto, i, tyhjanIndeksi)));
//            }
//            
//            if (i == tyhjanIndeksi + pelilaudanLeveys) {
//                jono.add(new Node(vaihdaKeskenaan(uusiSiirto, i, tyhjanIndeksi)));
//            }
//        }



        return jono;
    }
    
    /**
     * apumetodi tyhjän napin (-1) löytämiseksi
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
     * manuaalinen System.arraycopy
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
    // en nyt ole ihan varma että tapahtuuko muutos oikeaan taulukkoon... let's see.
    private int[] vaihdaKeskenaan(int[] taulukko, int i, int tyhjanIndeksi) {
        int apu = taulukko[i];
        taulukko[i] = taulukko[tyhjanIndeksi];
        taulukko[tyhjanIndeksi] = apu;
        return taulukko;
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
}
