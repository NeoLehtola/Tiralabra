package sovelluslogiikka;

/**
 * Tämä luokka hoitaa pelitilanteen tallentamisen
 */
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


// yhdistänkö tämän ja lataajan? harkinnassa
public class PelinTallentaja {

    private Pelitapahtuma peli;
    private File file;

    public PelinTallentaja(Pelitapahtuma peli, String tiedostoNimi) {
        this.peli = peli;
        file = new File(tiedostoNimi);

    }

    /**
     * tallentaa pelitilanteen tiedostoon seuraavasti:
     * 1. rivi: laudan korkeus, laudan leveys, vuorojen määrä
     * 2. riviltä eteenpäin pelilaudan nappuloiden tunnisteet
     * tallennetun pelitilanteen mukaisessa järjestyksessä
     */
    public void tallennaPeli() throws Exception {


        PrintWriter pw = new PrintWriter(file);
        pw.print(peli.getPelilauta().getKorkeus() + " ");
        pw.print(peli.getPelilauta().getLeveys() + " ");
        pw.println(peli.getVuorojenMaara());
        for (int i = 0; i < peli.getPelilauta().getKorkeus(); i++) {
            for (int j = 0; j < peli.getPelilauta().getLeveys(); j++) {
                pw.print(peli.getPelilauta().getNappula(i, j).getTunniste() + " ");
            }
            pw.println();
        }
        // 
        pw.close();
    }
        

    /**
     * testejä varten
     * @return 
     */
    public File getFile() {
        return file;
    }
}
