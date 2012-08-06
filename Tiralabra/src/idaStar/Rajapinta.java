
package idaStar;

import java.util.PriorityQueue;
import sovelluslogiikka.Pelitapahtuma;

/**
 * Luokka käsittelee toistaiseksi vain nodeja, joihin ei ole liitetty mitään heuristiikkaa
 * Rajapinta on työnimi, kehitän jotain parempaa
 */
public class Rajapinta {
    
    private Pelitapahtuma peli;
    private int nodeTaulukonPituus;
    private Node startNode;
    private Node goalNode;
    
    /**
     * konstruktori
     * @param peli luodaan uusi Pelitapahtuma-olio
     */
    public Rajapinta(Pelitapahtuma peli) {
        this.peli = peli;
        this.nodeTaulukonPituus = laskeNodeTaulukonPituus();
        this.startNode = new Node(otaAlkuArvotTalteenPelilaudalta(), tilanneMatriisina());
        this.goalNode = new Node(luoGoalNodenArvot(), maalitilanneMatriisina());
    }
    
    /**
     * apumetodi konstruktorille
     * @return pelilaudan nappuloiden määrä, joka on taulukon pituus
     */
    private int laskeNodeTaulukonPituus() {
        int leveys = peli.getPelilauta().getLeveys();
        int korkeus = peli.getPelilauta().getKorkeus();
        return leveys * korkeus;
    }
    
    /**
     * apumetodi konstruktorille startNoden luomista varten
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
     * @return taulukko jossa numerot järjestyksessä ja viimeisenä tyhjä (-1);
     */
    private int[] luoGoalNodenArvot() {
        int[] arvot = new int[nodeTaulukonPituus];
        for (int i = 0; i < nodeTaulukonPituus - 1; i++) {
            arvot[i] = i+1;
        }
        arvot[nodeTaulukonPituus - 1] = -1;
        
        return arvot;
    }
    
    
    // pitääkös tää metodi nyt olla erikseen alkutilanteelle ja muille 
    /**
     * Node tietää pelitilanteen myös matriisimuodossa, siirtojen tekemisen helpottamiseksi
     * @return matriisi
     */
    private int[][] tilanneMatriisina() {
        int leveys = peli.getPelilauta().getLeveys();
        int korkeus = peli.getPelilauta().getKorkeus();
        int[][] matriisi = new int[korkeus][leveys];
        
        for (int i = 0; i < korkeus; i++) {
            for (int j = 0; j < leveys; j++) {
                matriisi[i][j] = peli.getPelilauta().getNappula(i, j).getTunniste();
            }
        }
        
        return matriisi;
    }
    
    /**
     * en ole varma tarviiko tätä erikseen
     * @return 
     */
    private int[][] maalitilanneMatriisina() {
        return null;
    }
    
    /**
     * tekee priority queueen nykyistä nodea seuraavat siirrot
     * @param current Node jolle halutaan lapset
     * @return palauttaa noden lapset priority queuena.
     */
    public PriorityQueue luoNodelleLapset(Node current) {
        int[][] nykyinen = current.getTilanneMatriisina();
        
        for (int i = 0; i < nykyinen.length; i++) {
            for (int j = 0; j < nykyinen[i].length; j++) {
                
            }
        }
        
        return null;
    }
    
    
    

    /**
     * palauttaa noden luomiseen käytetyn pelitilannetaulukon pituuden
     * @return taulukon pituus
     */
    public int getNodeTaulukonPituus() {
        return nodeTaulukonPituus;
    }
    
    /**
     * palauttaa aloitussolmun
     * @return startNode 
     */
    public Node getStartNode() {
        return startNode;
    }
    
    /**
     * palauttaa maalisolmun, jossa napit ovat järjestyksessä
     * @return goalNode
     */
    public Node getGoalNode() {
        return goalNode;
    }
    
}
