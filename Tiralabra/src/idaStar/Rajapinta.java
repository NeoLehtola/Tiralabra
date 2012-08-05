
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
    
    public Rajapinta(Pelitapahtuma peli) {
        this.peli = peli;
        this.nodeTaulukonPituus = laskeNodeTaulukonPituus();
    }
    
    private int laskeNodeTaulukonPituus() {
        int leveys = peli.getPelilauta().getLeveys();
        int korkeus = peli.getPelilauta().getKorkeus();
        return leveys * korkeus;
    }
    
    private int[] otaArvotTalteenPelilaudalta() {
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
    
    
    public Node luoNodeIlmanHeuristiikkaa() {
        return null;
    }
    
    public Node luoStartNode() {
        
        
        
        return null;
    }
    
    public Node luoGoalNode() {
        return null;
    }
    
    public int getNodeTaulukonPituus() {
        return nodeTaulukonPituus;
    }
    
}
