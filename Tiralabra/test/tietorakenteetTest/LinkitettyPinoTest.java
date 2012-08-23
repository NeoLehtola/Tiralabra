/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteetTest;

import org.junit.*;
import static org.junit.Assert.*;
import tietorakenteet.LinkitettyPino;

/**
 *
 * @author neom
 */
public class LinkitettyPinoTest {
    
    private LinkitettyPino<int[]> pino;
    
    public LinkitettyPinoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        pino = new LinkitettyPino<int[]>();
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
            
}
