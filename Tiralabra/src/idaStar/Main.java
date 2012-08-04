package idaStar;

import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class Main {

    public static void main(String[] args) {
        Pelitapahtuma peli = new Pelitapahtuma(3, 3, 1000);

        int[] syote = new int[9];
        int syoteIndeksi = 0;

        for (int i = 0; i < peli.getPelilauta().getKorkeus(); i++) {
            for (int j = 0; j < peli.getPelilauta().getLeveys(); j++) {
                syote[syoteIndeksi] = peli.getPelilauta().getNappula(i, j).getTunniste();
                syoteIndeksi++;
            }
        }

        Node node = new Node(syote, 0, 0);

        for (int i = 0; i < node.getPituus(); i++) {
            System.out.print(node.getTilanne()[i] + " ");
        }

    }
}
