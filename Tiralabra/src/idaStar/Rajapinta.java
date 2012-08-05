
package idaStar;

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
     * 
     * @param peli 
     */
    public Rajapinta(Pelitapahtuma peli) {
        this.peli = peli;
        this.nodeTaulukonPituus = laskeNodeTaulukonPituus();
        this.startNode = new Node(otaAlkuArvotTalteenPelilaudalta());
        this.goalNode = new Node(luoGoalNodenArvot());
    }
    
    /**
     * apumetodi
     * @return pelilaudan nappuloiden määrä, joka on taulukon pituus
     */
    private int laskeNodeTaulukonPituus() {
        int leveys = peli.getPelilauta().getLeveys();
        int korkeus = peli.getPelilauta().getKorkeus();
        return leveys * korkeus;
    }
    
    /**
     * apumetodi startNoden luomista varten
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
     * apumetodi goalNoden luomista varten
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
    
    
    public Node luoSeuraavaNode() {
        // pitänee ottaa edelliset muistiin??? missä ja miten?
        
        
        return null;
    }
    

    /**
     * palauttaa noden luomiseen käytetyn pelitilannetaulukon pituuden
     * @return taulukon pituus
     */
    public int getNodeTaulukonPituus() {
        return nodeTaulukonPituus;
    }
    
}
