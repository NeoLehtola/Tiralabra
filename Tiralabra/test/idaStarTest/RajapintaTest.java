/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import idaStar.Node;
import org.junit.*;
import static org.junit.Assert.*;
import idaStar.Rajapinta;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class RajapintaTest {
    
    private Pelitapahtuma testiPeli;
    private Rajapinta r;

    public RajapintaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        testiPeli = new Pelitapahtuma(3, 3, 1000);
        r = new Rajapinta(testiPeli);
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void nodeTaulukonPituusOnOikea() {

       testiPeli = new Pelitapahtuma(5, 6, 10000);
       r = new Rajapinta(testiPeli);

       assertEquals(30, r.getNodeTaulukonPituus());
    }
    
    @Test 
    public void startNodenArvotSamatKuinUudenPelin() {
        
    }

    @Test
    public void goalNodenArvotOvatJarjestyksessa() {

        int[] maalitilanne = {1, 2, 3, 4, 5, 6, 7, 8, -1};
        
        assertArrayEquals(maalitilanne, r.getGoalNode().getTilanne());
    }
    
//    @Test
//    public void lapsiaJonossaKaksiKunOllaanNurkassa() {
//        int[] testi = {-1, 3, 4, 8, 2, 6, 7, 5, 1};
//        r.setStartNode(new Node(testi));
//        
//        assertEquals(2, r.luoNodelleLapset(r.getStartNode()).size());
//        
//    }
    
    @Test
    public void lapsiaJonossaKolmeKunOllaanReunassa() {
        
    }
   
    @Test
    public void lapsiaJonossaNeljaKunOllaanKeskella() {
        int[] testi = {4, 3, 2, 1, -1, 8, 7, 6, 5};
        r.setStartNode(new Node(testi));
        
        assertEquals(4, r.luoNodelleLapset(r.getStartNode()).size());
    }
    
    
    
    @Test
    public void lapsetOikeinKunNiitaKaksi() {
        
    }
    
    @Test
    public void lapsetOikeinKunNiitaKolme() {
        
    }
    
    @Test
    public void lapsetOikeinKunNiitaNelja() {
        
    }
}
