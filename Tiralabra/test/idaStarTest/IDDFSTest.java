/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package idaStarTest;

import idaStar.IDDFS;
import idaStar.IDDFSRajapinta;
import idaStar.Node;
import static org.junit.Assert.*;
import org.junit.*;
import sovelluslogiikka.Pelitapahtuma;

/*
 *  Hakumetodeille ei toistaiseksi ole Junit-testejä, vaan olen testannut niitä mainissa. 
 *  
 */
public class IDDFSTest {

    private IDDFSRajapinta r;
    private IDDFS haku;

    public IDDFSTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        r = new IDDFSRajapinta(new Pelitapahtuma(4, 4, 10000));
        haku = new IDDFS(r);
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
        int[] arvot = {1, 2, 4, 3, 5, 6, 7, 8, 10, 9, 11, 12, 13, 15, 14, -1};
        Node random = new Node(arvot);
        // siis väärä, ei vaara
        assertFalse(haku.isGoal(random));
    }
}
