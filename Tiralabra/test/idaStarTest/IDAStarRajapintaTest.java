/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import idaStar.IDAStarRajapinta;
import idaStar.Node;
import org.junit.*;
import static org.junit.Assert.*;
import sovelluslogiikka.Pelitapahtuma;

/**
 * Tätä luokkaa ei ole vielä toteutettu, testit kopioitu ajalta jolloin luokat oli järjestelty eri tavoin
 * ja siksi niitä pitää muuttaa
 * 
 */
public class IDAStarRajapintaTest {
    
    public IDAStarRajapintaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void arvoHTuleeLaskettuaOikein() {
        IDAStarRajapinta ir = new IDAStarRajapinta(new Pelitapahtuma(3, 3, 100));
        int[] test = {5, 7, -1, 4, 2, 3, 8, 6, 1};
        ir.setStartNode(new Node(test));
        ir.laskeNodenArvoH(ir.getStartNode(), 3);
        assertEquals(14, ir.getStartNode().getH());
    }
    
    
//    @Test
//    public void lapsiaJonossaKaksiKunOllaanNurkassa() {
//        int[] testi = {-1, 3, 4, 8, 2, 6, 7, 5, 1};
//        r.setStartNode(new Node(testi));
//        
//        assertEquals(2, r.luoNodelleLapsetJonoon(r.getStartNode()).size());
//        
//    }
    
//    @Test
//    public void lapsiaJonossaKolmeKunOllaanReunassa() {
//        
//    }
   
//    @Test
//    public void lapsiaJonossaNeljaKunOllaanKeskella() {
//        int[] testi = {4, 3, 2, 1, -1, 8, 7, 6, 5};
//        r.setStartNode(new Node(testi));
//        
//        assertEquals(4, r.luoNodelleLapsetJonoon(r.getStartNode()).size());
//    }
    
    
}
    
