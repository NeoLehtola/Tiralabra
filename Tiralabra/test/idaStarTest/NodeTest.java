package idaStarTest;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;
import sovelluslogiikka.Pelitapahtuma;
import sovelluslogiikka.SiirtavaPelilauta;
import idaStar.Node;

/*
 *  Heuristiikkoja ei ole vielä testattu, koska en ole vielä käyttänyt niitä missään.
 * 
 */
public class NodeTest {
    
    int[] syote;
    
    public NodeTest() {
            
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        Pelitapahtuma peli = new Pelitapahtuma(3, 3, 1000);
        
        this.syote = new int[9];
        int syoteIndeksi = 0;
        
        for (int i = 0; i < peli.getPelilauta().getKorkeus(); i++) {
            for (int j = 0; j < peli.getPelilauta().getLeveys(); j++) {
                syote[syoteIndeksi] = peli.getPelilauta().getNappula(i, j).getTunniste();
                syoteIndeksi++;
            }      
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void konstruktoriLuoUudenNodenJollaPelitilanneTaulukkona() {
 
        Node node = new Node(syote, 0, 0);
        
        for (int i = 0; i < node.getPituus(); i++) {
            String nro = node.getTilanne()[i] + "";
            assertTrue(nro.matches("[1-8]") || nro.equals("-1"));
  
        }
    }
    
    @Test
    public void pelitilanneTaulukkoOnOikeanMittainen() {
        
         Node node = new Node(syote, 0, 0);  
         assertTrue(node.getPituus() == 9);
             
    }
}
