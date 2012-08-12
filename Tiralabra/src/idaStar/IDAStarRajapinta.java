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
     *
     * @param laudanLeveys
     * @return
     */
    public int[] goalNodenXArvot(int laudanLeveys) {
        int pituus = getGoalNode().getPituus();
        int[] xArvot = new int[pituus];

        int i = 0;
        while (i < pituus) {
            xArvot[i] = i % laudanLeveys;
            i++;
        }
        return xArvot;
    }

    /**
     *
     * @param laudanLeveys
     * @return
     */
    public int[] goalNodenYArvot(int laudanLeveys) {
        int pituus = getGoalNode().getPituus();
        int[] yArvot = new int[pituus];

        int i = 0;
        int lisattava = 0;
        
        while (i < pituus) {          
            if (i != 0 && i%laudanLeveys == 0) {
                lisattava++;
            }
            yArvot[i] = lisattava;
            i++;
        }

        return yArvot;
    }

    /**
     *
     */
    private void startNodenArvoH() {
        int[] tilanne = getStartNode().getTilanne();
        
        for (int i = 0; i < tilanne.length; i++) {
            
        }
        

    }
    
    

    /**
     *
     * @param n
     */
    public void manhattanDistance(Node n) {
        int[] state = n.getTilanne();
        int laudanLeveys = getPeli().getPelilauta().getLeveys();



    }

    /**
     * apumetodi itseisarvon laskemiseksi (korvaa Math.abs:n)
     * @param k luku jonka itseisarvo halutaan
     * @return Jos luku negatiivinen, se kerrotaan -1:llä, muuten luku palautetaan sellaisenaan
     */
    private int absoluteValue(int k) {
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
