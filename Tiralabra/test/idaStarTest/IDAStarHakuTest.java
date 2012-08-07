/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import idaStar.IDAStarHaku;
import idaStar.Rajapinta;
import org.junit.*;
import static org.junit.Assert.*;
import sovelluslogiikka.Pelitapahtuma;

/**
 *
 * @author neom
 */
public class IDAStarHakuTest {
    
    private Rajapinta r;
    private IDAStarHaku haku;

    public IDAStarHakuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        r = new Rajapinta(new Pelitapahtuma(4, 4, 10000));
        haku = new IDAStarHaku(r.getStartNode(), r.getGoalNode());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void isGoalTunnistaaMaalisolmun() {

        assertTrue(haku.isGoal(haku.getGoalNode()));
    }

    // tämä ei toimi, tarkista miksi!!!!
    @Test
    public void isGoalPalauttaaFalseJosSolmuOnVaara() {
        // siis väärä, ei vaara
        assertFalse(haku.isGoal(haku.getStartNode()));
    }
}
