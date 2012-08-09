/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import idaStar.Node;
import org.junit.*;
import static org.junit.Assert.*;
import idaStar.IDDFSRajapinta;
import java.util.Stack;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class IDDFSRajapintaTest {
    
    private Pelitapahtuma testiPeli;
    private IDDFSRajapinta r;

    public IDDFSRajapintaTest() {
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
        r = new IDDFSRajapinta(testiPeli);
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void nodeTaulukonPituusOnOikea() {

       testiPeli = new Pelitapahtuma(5, 6, 10000);
       r = new IDDFSRajapinta(testiPeli);

       assertEquals(30, r.getNodeTaulukonPituus());
    }
    
//    @Test 
//    public void startNodenArvotSamatKuinUudenPelin() {
//        
//    }

    @Test
    public void goalNodenArvotOvatJarjestyksessa() {

        int[] maalitilanne = {1, 2, 3, 4, 5, 6, 7, 8, -1};
        
        assertArrayEquals(maalitilanne, r.getGoalNode().getTilanne());
    }
    

    
    
    @Test
    public void lapsiaPinossaKaksiKunOllaanNurkassa() {
        // tyhjä vasemmassa ylänurkassa
        int[] testi = {-1, 3, 4, 8, 2, 6, 7, 5, 1};
        r.setStartNode(new Node(testi));
        
        assertEquals(2, r.luoNodelleLapsetPinoon(r.getStartNode()).size());
        
    }
    
    @Test
    public void lapsiaPinossaKolmeKunOllaanReunassa() {
        // tyhjä oikeassa reunassa keskellä
        int[] testi = {5, 4, 3, 2, 1, -1, 8, 7, 6};
        r.setStartNode(new Node(testi));
        assertEquals(3, r.luoNodelleLapsetPinoon(r.getStartNode()).size());
    }
    
    @Test
    public void lapsiaPinossaNeljaKunOllaanKeskella() {
        // tyhjä pelilaudan keskellä
        int[] testi = {4, 3, 2, 1, -1, 8, 7, 6, 5};
        r.setStartNode(new Node(testi));
        
        assertEquals(4, r.luoNodelleLapsetPinoon(r.getStartNode()).size());
    }
    
//    
    @Test
    public void lapsetOikeinKunNiitaKaksi() {
        // tyhjä oikeassa yläkulmassa
        int[] testi = {4, 3, -1, 8, 2, 6, 7, 5, 1};
        r.setStartNode(new Node(testi));
        Stack<Node> pino = r.luoNodelleLapsetPinoon(r.getStartNode());
        // ensin luodaan vasen, sitten alaspäin
        int[] tarkistaVasen = {4, -1, 3, 8, 2, 6, 7, 5, 1};
        int[] tarkistaAla = {4, 3, 6, 8, 2, -1, 7, 5, 1};
        
        assertArrayEquals(tarkistaAla, pino.pop().getTilanne());
        assertArrayEquals(tarkistaVasen, pino.pop().getTilanne());
        
        
        
    }
    
    @Test
    public void lapsetOikeinKunNiitaKolme() {
        // tyhjä alareunassa keskellä
        int[] testi = {5, 4, 3, 2, 1, 7, 8, -1, 6};
        r.setStartNode(new Node(testi));
        Stack<Node> pino = r.luoNodelleLapsetPinoon(r.getStartNode());
        // ensin luodaan oikea, sitten vasen, sitten ylä
        int[] tarkistaOikea = {5, 4, 3, 2, 1, 7, 8, 6, -1};
        int[] tarkistaVasen = {5, 4, 3, 2, 1, 7, -1, 8, 6};
        int[] tarkistaYla = {5, 4, 3, 2, -1, 7, 8, 1, 6};
        
        assertArrayEquals(tarkistaYla, pino.pop().getTilanne());
        assertArrayEquals(tarkistaVasen, pino.pop().getTilanne());
        assertArrayEquals(tarkistaOikea, pino.pop().getTilanne());
        
        
    }
    
    @Test
    public void lapsetOikeinKunNiitaNelja() {
        int[] testi = {4, 3, 2, 1, -1, 8, 7, 6, 5};
        r.setStartNode(new Node(testi));
        Stack<Node> pino = r.luoNodelleLapsetPinoon(r.getStartNode());
        
        // luodaan järjestyksessä oikea, vasen, ylä, ala
        int[] tarkistaOikea = {4, 3, 2, 1, 8, -1, 7, 6, 5};
        int[] tarkistaVasen = {4, 3, 2, -1, 1, 8, 7, 6, 5};
        int[] tarkistaYla = {4, -1, 2, 1, 3, 8, 7, 6, 5};
        int[] tarkistaAla = {4, 3, 2, 1, 6, 8, 7, -1, 5};
        
        assertArrayEquals(tarkistaAla, pino.pop().getTilanne());
        assertArrayEquals(tarkistaYla, pino.pop().getTilanne());
        assertArrayEquals(tarkistaVasen, pino.pop().getTilanne());
        assertArrayEquals(tarkistaOikea, pino.pop().getTilanne());
        
        
    }
}
