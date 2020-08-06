package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.RecenzijaDAO;
import model.Izvodjac;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.Recenzija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private int brojElemenata;

    public PrikazElementa(Izvodjenje iz, GlavniProzor gp){
        super();
        setModal(true);
        setTitle("Prikaz informacija izvodjenja");

        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();

        podesiKomponente(iz, dimension);

        skrolPaneRecenzija.getVerticalScrollBar().setUnitIncrement(10);

        panelSkrola.setLayout(new BoxLayout(panelSkrola, BoxLayout.Y_AXIS));
        skrolPaneRecenzija.setPreferredSize(new Dimension(dimension.width / 4, dimension.height / 5));

        brojElemenata = 3;
        ucitajRecenzije(iz);

        add(panelGlavni);

        setSize(dimension.width / 4 + dimension.width / 17, dimension.height / 2);

        setResizable(false);
        setLocationRelativeTo(gp);
    }

    private void podesiKomponente(Izvodjenje iz, Dimension dim) {

        nazivDelaLabela.setText("Naziv dela : "+ ElementIzvodjenja.generateNazivIzvodjenja(iz));

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

        buttonPrikaziIzvodjaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxIzvodjaca.getSelectedIndex();
                if (index < 0){
                    JOptionPane.showMessageDialog(PrikazElementa.this, "Ne postoji izvodjac");
                } else {
                    PrikaziIzvodjaca pi = new PrikaziIzvodjaca(iz.getListaIzvodjaca().get(index), PrikazElementa.this);
                    pi.setVisible(true);
                }
            }
        });

        prikaziMuzickoDeloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxMuzickihDela.getSelectedIndex();
                if (index < 0){
                    JOptionPane.showMessageDialog(PrikazElementa.this, "Ne postoji muzicko delo");
                } else {
                    PrikaziMuzickoDelo pi = new PrikaziMuzickoDelo(iz.getListaMuzickihDela().get(index), PrikazElementa.this);
                    pi.setVisible(true);
                }
            }
        });

        String separator = System.getProperty("file.separator");

        labelaIkone.setSize(200, dim.height/20*3 - 10);
        ImageIcon retImageIcon = IzvodjenjeDAO.getSlikuIzvodjenja(iz, separator);
        Image im = retImageIcon.getImage();
        Image myImg = im.getScaledInstance(labelaIkone.getWidth(), labelaIkone.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon newImage = new ImageIcon(myImg);
        labelaIkone.setIcon(newImage);
        iz.setImage(newImage);

        comboBoxIzvodjaca = new JComboBox<String>(getNizIzvodjaca(iz.getListaIzvodjaca()));
        comboBoxIzvodjaca.setBackground(new Color(186,186,178));
        panelGlavni.add(comboBoxIzvodjaca);

        comboBoxMuzickihDela = new JComboBox<String>(getNizMuzickihDela(iz.getListaMuzickihDela()));
        comboBoxMuzickihDela.setBackground(new Color(186,186,178));
        panelGlavni.add(comboBoxMuzickihDela);
    }



    private void ucitajRecenzije(Izvodjenje iz) {
        resetRecenzije();

        List<Recenzija> recenzije = RecenzijaDAO.getRecenzijeIzvodjenja(iz.getId(), brojElemenata);

        for (Recenzija r : recenzije) {
            ElementRecenzija el = new ElementRecenzija(r);
            panelSkrola.add(el);
        }

        panelSkrola.validate();
        panelSkrola.repaint();

        skrolPaneRecenzija.validate();
        skrolPaneRecenzija.repaint();

    }

    private void resetRecenzije() {
        panelSkrola.removeAll();
        panelSkrola.validate();
        panelSkrola.repaint();
    }

    public static String[] getNizIzvodjaca(List<Izvodjac> listaIzvodjaca) {
        String[] itemsArray = new String[listaIzvodjaca.size()];
        int index = 0;
        for(Izvodjac izvodjac : listaIzvodjaca){
            itemsArray[index] = izvodjac.getNazivIzvodjaca();
            index++;

        }
        return itemsArray;
    }

    public static String[] getNizMuzickihDela(List<MuzickoDelo> listaMuzickihDela) {
        String[] itemsArray = new String[listaMuzickihDela.size()];
        int index = 0;
        for(MuzickoDelo muzickoDelo : listaMuzickihDela){
            itemsArray[index] = muzickoDelo.getNazivDela();
            index++;

        }
        return itemsArray;
    }
}
