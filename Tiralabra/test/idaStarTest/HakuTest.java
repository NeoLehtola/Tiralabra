/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import idaStar.Haku;
import java.util.Stack;
import org.junit.*;
import static org.junit.Assert.*;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * Hakumetodien toimivuutta on testattu mainissa.
 * @author pklehtol
 */
public class HakuTest {

//    int[] syote;
    private Pelitapahtuma peli;
    private Haku haku;

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
        peli = new Pelitapahtuma(3, 3, 1000);
        haku = new Haku(peli);
        
        
//
//        this.syote = new int[9];
//        int syoteIndeksi = 0;
//
//        for (int i = 0; i < peli.getPelilauta().getKorkeus(); i++) {
//            for (int j = 0; j < peli.getPelilauta().getLeveys(); j++) {
//                syote[syoteIndeksi] = peli.getPelilauta().getNappula(i, j).getTunniste();
//                syoteIndeksi++;
//            }
//        }
    }

    @After
    public void tearDown() {
    }
    


    @Test
    public void lapsiaPinossaKaksiKunOllaanNurkassa() {
        int[] testi = {-1, 3, 4, 8, 2, 6, 7, 5, 1};
        Stack<int[]> pino = haku.lapsetPinoon(testi);
        assertEquals(2, pino.size());
    }
    
    @Test
    public void lapsiaJonossaKolmeKunOllaanReunassa() {
        int[] testi = {3, 2, 1, -1, 4, 6, 5, 8, 7};
        Stack<int[]> pino = haku.lapsetPinoon(testi);
        assertEquals(3, pino.size());
    }
   
    @Test
    public void lapsiaPinossaNeljaKunOllaanKeskella() {
        int[] testi = {4, 3, 2, 1, -1, 8, 7, 6, 5};
        Stack<int[]> pino = haku.lapsetPinoon(testi);
        assertEquals(4, pino.size());
    }
    
    @Test
    public void hakuLoytaaLopputuloksenPienellaSekoitusmaarallaIlmanHeuristiikkaa() {
        peli = new Pelitapahtuma(3, 3, 100);
        haku = new Haku(peli);
        int[] lopputilanne = {1, 2, 3, 4, 5, 6, 7, 8, -1};
        int[] tulos = haku.iterativeDeepeningSearch();
        assertArrayEquals(lopputilanne, tulos);
        
    }
}
