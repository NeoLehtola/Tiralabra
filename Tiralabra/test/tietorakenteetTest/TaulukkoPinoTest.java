/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteetTest;

import org.junit.*;
import static org.junit.Assert.*;
import tietorakenteet.TaulukkoPino;

/**
 *
 * @author pklehtol
 */
public class TaulukkoPinoTest {
    
    private TaulukkoPino pino;
    
    public TaulukkoPinoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        this.pino = new TaulukkoPino();
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void isEmptyPalauttaaOikeinKunPinoTyhja() {
        assertTrue(pino.isEmpty());
    }
    
    @Test
    public void tyhjaanPinoonLisataanEnsimmainenSolmu() {
        int[] test = {1, 2, 3, -1};
        pino.push(test);
        assertFalse(pino.isEmpty());
    }
    
    @Test
    public void palauttaaEkanSolmunSisallonPoistamatta() {
        int[] test = {1, 2, 3, -1};
        pino.push(test);
        assertArrayEquals(pino.peek(), test);   
    }
    
    @Test
    public void palauttaaJaPoistaaEkanSolmunSisallon() {
        int[] test = {1, 2, 3, -1};
        pino.push(test);
        assertArrayEquals(pino.pop(), test);
    }
    
    @Test
    public void pinoonLisataanUseitaSolmuja() {
        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};
        int[] c = {7, 8, 9};
        pino.push(a);
        pino.push(b);
        pino.push(c);
        assertEquals(3, pino.size());
        pino.pop();
        assertArrayEquals(pino.peek(), b);
    }
    
    @Test
    public void taulukkoKasvaaTarvittaessa() {
        int[] a = {1, 2, 3};
        for (int i = 0; i < 15; i++) {
            pino.push(a);
        }
        assertEquals(15, pino.size());
    }
}
