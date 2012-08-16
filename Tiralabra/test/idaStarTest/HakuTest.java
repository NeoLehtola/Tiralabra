/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import org.junit.*;
import static org.junit.Assert.*;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * Huom. testejä ei toistaiseksi ole täällä, kahdesta syystä:
 * 1. Olen testannut varsinaisen haun toimivuutta mainissa
 * 2. Olen myllännyt koko ohjelman luokkajakoineen juuri uusiksi, ja muutoksia tulee
 * vielä lisää. Tästä johtuen aiemmat testit ovat muuttuneet käymättömiksi, ja en ole vielä
 * ennättänyt tehdä uusia. Tulevat kyllä asap.
 * @author pklehtol
 */
public class HakuTest {

    int[] syote;

    public HakuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        Pelitapahtuma peli = new Pelitapahtuma(3, 3, 1000);

        this.syote = new int[9];
        int syoteIndeksi = 0;

        for (int i = 0; i < peli.getPelilauta().getKorkeus(); i++) {
            for (int j = 0; j < peli.getPelilauta().getLeveys(); j++) {
                syote[syoteIndeksi] = peli.getPelilauta().getNappula(i, j).getTunniste();
                syoteIndeksi++;
            }
        }
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void jotain() {
        
    }

    //    @Test
//    public void lapsiaJonossaKaksiKunOllaanNurkassa() {
//        int[] testi = {-1, 3, 4, 8, 2, 6, 7, 5, 1};   
//    }
    
//    @Test
//    public void lapsiaJonossaKolmeKunOllaanReunassa() {
//        
//    }
   
//    @Test
//    public void lapsiaJonossaNeljaKunOllaanKeskella() {
//        int[] testi = {4, 3, 2, 1, -1, 8, 7, 6, 5};

//    }
}
