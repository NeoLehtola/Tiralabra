/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import org.junit.*;
import static org.junit.Assert.*;
import idaStar.Rajapinta;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class RajapintaTest {

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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void nodeTaulukonPituusOnOikea() {

        Pelitapahtuma testiPeli = new Pelitapahtuma(5, 6, 10000);
        Rajapinta r = new Rajapinta(testiPeli);
        assertEquals(30, r.getNodeTaulukonPituus());
    }
    
    @Test 
    public void startNodenArvotSamatKuinUudenPelin() {
        
    }

    @Test
    public void goalNodenArvotOvatJarjestyksessa() {

        Pelitapahtuma testiPeli = new Pelitapahtuma(3, 3, 1000);
        Rajapinta r = new Rajapinta(testiPeli);
        int[] maalitilanne = {1, 2, 3, 4, 5, 6, 7, 8, -1};
        
        assertArrayEquals(maalitilanne, r.getGoalNode().getTilanne());
    }
    
    @Test
    public void lapsiaJonossaKaksiKunOllaanNurkassa() {
        
    }
    
    @Test
    public void lapsiaJonossaKolmeKunOllaanReunassa() {
        
    }
    
    @Test
    public void lapsiaJonossaNeljaKunOllaanKeskella() {
        
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
