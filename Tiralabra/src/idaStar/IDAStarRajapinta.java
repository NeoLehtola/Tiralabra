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

    private int[] goalNodenXarvot(int laudanLeveys) {
        return null;
    }
    
    private int[] goalNodenYarvot(int laudanLeveys) {
        return null;
    }

    /**
     *
     */
    private void startNodenArvoH() {
        int[] tilanne = getStartNode().getTilanne();

    }

    /**
     *
     * @param n
     */
    public void manhattanDistance(Node n) {
        int[] state = n.getTilanne();
        int laudanLeveys = getPeli().getPelilauta().getLeveys();



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
