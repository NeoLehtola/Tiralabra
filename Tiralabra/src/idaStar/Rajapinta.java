
package idaStar;

import sovelluslogiikka.Pelitapahtuma;

/**
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
     * apumetodi
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
     * apumetodi
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
    
    
    public Node luoNodeIlmanHeuristiikkaa() {
        return null;
    }
    

    /**
     * 
     * @return 
     */
    public int getNodeTaulukonPituus() {
        return nodeTaulukonPituus;
    }
    
}
