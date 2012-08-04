package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import sovelluslogiikka.PelinLataaja;
import sovelluslogiikka.Pelitapahtuma;
//import sovelluslogiikka.Pelitapahtuma;

public class GUI implements Runnable {

    private JFrame frame;
    private Pelitapahtuma peli;
    private final int KOONALARAJA = 3;
    private final int KOONYLARAJA = 6;

    @Override
    public void run() {
        frame = new JFrame("15 puzzle");

        // nappien koon määritys????
        frame.setPreferredSize(new Dimension(KOONYLARAJA * 75, KOONYLARAJA * 75));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * toiminnot kokoava metodi
     * @param container 
     */
    private void luoKomponentit(Container container) {

        container.setLayout(new BorderLayout());
        kysyUusiVaiTallennettuJaLuoPeli(container);

        GUIPelilauta pelilauta = new GUIPelilauta(peli);
        JButton tallenna = new JButton("Tallenna");
        tallenna.addActionListener(new napinKuuntelija());

        container.add(tallenna, BorderLayout.NORTH);
        container.add(pelilauta, BorderLayout.CENTER);
        container.add(pelilauta.getVuorot(), BorderLayout.SOUTH);
    }

    /**
     * tehdään valinta uuden ja tallennetun pelin välillä
     * @param container 
     */
    private void kysyUusiVaiTallennettuJaLuoPeli(Container container) {
        String[] vaihtoehdot = {"Uusi peli", "Avaa tallennettu peli"};
        int valinta = JOptionPane.showOptionDialog(container, "Valitse pelityyppi", "Uusi peli", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, vaihtoehdot, null);

        if (valinta == 0) {
            int korkeus = kysySivunPituus(container, "korkeus");
            int leveys = kysySivunPituus(container, "leveys");

            peli = new Pelitapahtuma(korkeus, leveys, korkeus * leveys * 1000);


        } else if (valinta == 1) {
            PelinLataaja l = new PelinLataaja("src/sovelluslogiikka/Tallennus.txt");
            peli = l.luoUusiPeliTallennetunPohjalta();
        }
    }
    
    
    /**
     * apumetodi testaa, ettei tehdä liian suurta tai pientä lautaa
     * @param sivu
     * @return 
     */
    private boolean kelvollinenSivunPituus(int sivu) {
        return sivu >= KOONALARAJA && sivu <= KOONYLARAJA;
    }

    
    /**
     * metodi kysyy korkeuden ja leveyden pituudet
     * @param container
     * @param kumpi
     * @return 
     */
    private int kysySivunPituus(Container container, String kumpi) throws NumberFormatException {
        int sivunPituus;
        do {
            String valinta = JOptionPane.showInputDialog(container, "Valitse " + kumpi + " väliltä " + KOONALARAJA + "-" + KOONYLARAJA);
            if (valinta == null) {
                System.exit(0);
            }
                     
            sivunPituus = Integer.parseInt(valinta);
            
            if (!kelvollinenSivunPituus(sivunPituus)) {
                JOptionPane.showMessageDialog(container, sivunPituus + " ei kelpaa ;_;");               
            }
        } while (!kelvollinenSivunPituus(sivunPituus));

        return sivunPituus;
    }
    
    

    public JFrame getFrame() {
        return frame;
    }

    
    /**
     * apuluokka
     */
    private class napinKuuntelija implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                peli.tallennaPeli("src/sovelluslogiikka/Tallennus.txt");
                pelinLopetus(JOptionPane.showConfirmDialog(frame, "Tallennus onnistui. ^^ \n Haluatko lopettaa?"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Tallennus ei onnistunut ;_;");
            }
        }

        private void pelinLopetus(int vastaus) {
            if (vastaus == 0) {
                System.exit(0);
            }
        }
    }
}
