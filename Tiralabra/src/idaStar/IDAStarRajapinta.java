package idaStar;

import sovelluslogiikka.Pelitapahtuma;

/**
 * Tästä tulee samantyyppinen kuin IDDFS-rajapinnasta, mutta nodeille tulee
 * heuristiikat. Työnjaot luokkien välillä, ja näiden kahden mahd. yhteinen
 * yliluokka tms. vielä harkinnassa
 *
 */
public class IDAStarRajapinta extends IDDFSRajapinta {

    /**
     *
     * @param peli
     */
    public IDAStarRajapinta(Pelitapahtuma peli) {
        super(peli);
        getGoalNode().setH(0);
    }

    
    /**
     * haetun napin x-koordinaatti
     * @param i
     * @param laudanLeveys
     * @return 
     */
    private int xKoord(int i, int laudanLeveys) {
        return i%laudanLeveys;
    }
    
    /**
     * haetun napin y-koordinaatti
     * @param i
     * @param laudanLeveys
     * @return 
     */
    private int yKoord(int i, int laudanLeveys) {
        return i/laudanLeveys;
    }



    /**
     * tästä tullee private
     * @param n
     * @param laudanLeveys 
     */
    public void laskeNodenArvoH(Node n, int laudanLeveys) {
        int[] tilanne = n.getTilanne();
        int summa = 0;

        for (int i = 0; i < tilanne.length; i++) {
            if (tilanne[i] == -1) {
                continue;
            }
            int vuorossa = tilanne[i];
            int vuorossaXKoord = xKoord(i, laudanLeveys);
            int vuorossaYKoord = yKoord(i, laudanLeveys);
            
            int goalXKoord = xKoord(haeGoalIndeksi(vuorossa), laudanLeveys);
            int goalYKoord = yKoord(haeGoalIndeksi(vuorossa), laudanLeveys); 
            
            summa += absValue(vuorossaXKoord - goalXKoord) + absValue(vuorossaYKoord - goalYKoord);  
        }
        
        n.setH(summa);
        

    }
    /**
     * 
     * @param haettava
     * @return 
     */
    private int haeGoalIndeksi(int haettava) {
        for (int i = 0; i < getGoalNode().getPituus(); i++) {
            if (getGoalNode().getTilanne()[i] == haettava) {
                return i;
            }
        }
        
        // tänne ei pitäisi ikinä joutua
        return -100;
    }
    
    

//    /**
//     *
//     * @param n
//     */
//    public void manhattanDistance(Node n) {
//        int[] state = n.getTilanne();
//        int laudanLeveys = getPeli().getPelilauta().getLeveys();
//    }

    /**
     * apumetodi itseisarvon laskemiseksi (korvaa Math.abs:n)
     * @param k luku jonka itseisarvo halutaan
     * @return Jos luku negatiivinen, se kerrotaan -1:llä, muuten luku palautetaan sellaisenaan
     */
    private int absValue(int k) {
        if (k < 0) {
            return k * (-1);
        } else {
            return k;
        }
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
}
