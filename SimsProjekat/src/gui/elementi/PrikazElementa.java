package gui.elementi;

import dao.RecenzijaDAO;
import kontroler.GlavniProzorKON;
import model.Izvodjac;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.Recenzija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.List;

public class PrikazElementa extends JDialog {
    private JPanel panelGlavni;
    private JComboBox comboBoxIzvodjaca;
    private JLabel brojPristupaLabela;
    private JLabel trajanjeLabela;
    private JLabel tipIzvodjenjaLabela;
    private JLabel mestoIzvodjenjaLabela;
    private JLabel labelaIkone;
    private JScrollPane skrolPaneRecenzija;
    private JPanel panelSkrola;
    private JComboBox comboBoxMuzickihDela;
    private JLabel nazivDelaLabela;
    private JButton buttonPrikaziIzvodjaca;
    private JButton prikaziMuzickoDeloButton;
    private JLabel vremeIzvodjenjaLabela;
    private List<ElementRecenzija> elementi;
    private int brojElemenata;

    public PrikazElementa(Izvodjenje iz, Element el){
        super();
        setModal(true);
        setTitle("Prikaz nformacija izvodjenja");

        createUIComponents();
        podesiKomponente(iz);

        elementi = new ArrayList<ElementRecenzija>();
        skrolPaneRecenzija.getVerticalScrollBar().setUnitIncrement(10);

        panelSkrola.setLayout(new BoxLayout(panelSkrola, BoxLayout.Y_AXIS));

        brojElemenata = 3;
        ucitajRecenzije(iz);
        //podesiAkcije();
        add(panelGlavni);

        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();
        setSize(dimension.width / 4 + dimension.width / 17, dimension.height / 2);

        setResizable(false);
        setLocationRelativeTo(el);
    }

    private void podesiKomponente(Izvodjenje iz) {
        StringBuilder name = new StringBuilder();
        for (MuzickoDelo mz : iz.getListaMuzickihDela()) {
            name.append(mz.getNazivDela()).append(",");
        }
        name = new StringBuilder(name.substring(0, name.length() - 1));

        nazivDelaLabela.setText("Naziv dela : "+name.toString());

        vremeIzvodjenjaLabela.setText("Vreme izvodjenja : "+iz.getVremeIzvodjenja().toString());

        brojPristupaLabela.setText("Broj prisupa : "+iz.getBrPristupa());

        trajanjeLabela.setText("Trajanje : "+iz.getTrajanje());

        tipIzvodjenjaLabela.setText("Tip izvodjenja : "+iz.getTipIzvodjenja());

        mestoIzvodjenjaLabela.setText("Mesto izvodjenja : "+iz.getMestoIzvodjenja());

        skrolPaneRecenzija.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JViewport vp = skrolPaneRecenzija.getViewport();
                if (vp.getView().getHeight() <= vp.getHeight() + vp.getViewPosition().y) {
                    brojElemenata+=1;
                    ucitajRecenzije(iz);
                }
            }
        });
    }



    private void ucitajRecenzije(Izvodjenje iz) {
        resetRecenzije();

        List<Recenzija> recenzije = RecenzijaDAO.getRecenzijeIzvodjenja(iz.getId(), brojElemenata);

        for (Recenzija r : recenzije) {
            ElementRecenzija el = new ElementRecenzija(r);
            elementi.add(el);
            panelSkrola.add(el);
        }

        panelSkrola.validate();
        panelSkrola.repaint();

        skrolPaneRecenzija.validate();
        skrolPaneRecenzija.repaint();

    }

    private void resetRecenzije() {
        elementi.clear();
        panelSkrola.removeAll();
        panelSkrola.validate();
        panelSkrola.repaint();
    }

    private void createUIComponents(){
        comboBoxIzvodjaca = new JComboBox(new String[]{"Bird", "Cat", "Dog", "Rabbit", "Pig"});
    }
}
