/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import idaStar.Manhattan;
import org.junit.*;
import static org.junit.Assert.*;
import sovelluslogiikka.Pelitapahtuma;

/**
 * Tätä luokkaa ei ole vielä toteutettu, testit kopioitu ajalta jolloin luokat oli järjestelty eri tavoin
 * ja siksi niitä pitää muuttaa
 * 
 */
public class ManhattanTest {
    
    private Manhattan m;
    
    public ManhattanTest() {
        this.m = new Manhattan();
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
    
    // tiedän että tuo arvo on 14 koska laskin sen käsin
    @Test
    public void arvoHTuleeLaskettuaOikein() {     
        int[] test = {5, 7, -1, 4, 2, 3, 8, 6, 1};
        assertEquals(14, m.laskeH(test, 3, false));
    }
    
    @Test
    // nimeä tämä paremmin
    public void arvoOikeinJosLinearConflictHuomioidaan() {
        int[] test = {1, 3, 2, 4, 5, 6, 7, 8, -1};
        assertEquals(4, m.laskeH(test, 3, true));
    }
    
    @Test
    public void konfliktiHuomioidaanVainJosSeOnLopullisellaRivillä() {
        int[] test = {1, 2, 3, 8, 7, -1, 4, 5, 6};
        assertEquals(7, m.laskeH(test, 3, true));
    }
    
    @Test
    public void arvoOikeinJosUseitaLinearConflictTilanteita() {
        
    }
    
    @Test
    public void hooOnNollaJosParametrinaMaalitilanne() {
        int[] test = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -1};
        assertEquals(0, m.laskeH(test, 4, false));
    }
    
    @Test
    public void arvoOikeinJosPelilautaEiOleNelio() {
        // Oletetaan että lauta on 3x4-matriisi
        // teen tämän kunhan jaksan laskea jollekin laudalle h-arvon
    }
    
    

    
    
}
    
