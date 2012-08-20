/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteetTest;

import org.junit.*;
import static org.junit.Assert.*;
import tietorakenteet.Pino;

/**
 *
 * @author neom
 */
public class PinoTest {
    
    private Pino pino;
    
    public PinoTest() {
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
    public void pinoonVoiLisataIntLukuja() {
//        pino = new Pino();
//        pino.push(3);
//        pino.push(-100);
//        assertEquals(pino.pop(), -100);
    }
    
    
    
    @Test 
    public void pinoonVoiLisataIntTaulukoita() {
        pino = new Pino(5);
        int[] a = {1, 2, 3};
        int[] b ={-1000, 0};
        pino.push(a);
        pino.push(b);

    }

//    @Test
//    public void pushLisaaOikeaanKohtaan() {
//        
//    }
    
    @Test
    public void topOnPushinJalkeenOikeassaKohdassa() {
//        pino = new Pino();
//        for (int i = 0; i < 4; i++) {
//            pino.push(i);
//        }
//        assertEquals(4, pino.getTop());
    }
    
//    @Test
//    public void taulukonKasvatusPushMetodinSisallaKasvattaaTaulukkoa() {
//        
//    }
            
}
