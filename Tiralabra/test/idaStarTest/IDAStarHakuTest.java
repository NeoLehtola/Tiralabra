/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import idaStar.IDAStarHaku;
import idaStar.Node;
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
    public void goalNodenSisaltoOnOikea() {
        int[] maali = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, -1};
        
        assertArrayEquals(maali, haku.getGoalNode().getTilanne());
    }

    @Test
    public void isGoalTunnistaaMaalisolmun() {

        assertTrue(haku.isGoal(haku.getGoalNode()));
    }


    @Test
    public void isGoalPalauttaaFalseJosSolmuOnVaara() {
        int[] arvot = {1, 2, 4, 3, 5, 6, 7, 8, 10, 9, 11, 12, 13, 15, 15, -1};
        Node random = new Node(arvot);
        // siis väärä, ei vaara
        assertFalse(haku.isGoal(random));
    }
}
