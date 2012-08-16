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
    
    private final int[] lopputilanne = {1, 2, 3, 4, 5, 6, 7, 8, -1};
    
    public ManhattanTest() {
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
        Manhattan m = new Manhattan(3);
        assertEquals(14, m.laskeH(test, lopputilanne));
    }
    
    

    
    
}
    
