package gui.elementi;

import dao.IzvodjacDAO;
import dao.IzvodjenjeDAO;
import dao.RecenzijaDAO;
import model.Izvodjac;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.Recenzija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

public class PrikazIzvodjenja extends JDialog {

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
    private Dimension dimension;
    private int brojElemenata;

    public PrikazIzvodjenja(Izvodjenje iz, GlavniProzor gp) {
        super();
        setModal(true);
        setTitle("Prikaz informacija izvodjenja");

        popuniIzvodjenje(iz);

        inizijalizuj(iz);
        dodajNaGlavniPanel();
        podesiAkcije(iz, gp);

        ucitajRecenzije(iz, gp);

        add(panelGlavni);
        pack();
        //setSize(dimension.width / 4 + dimension.width / 17, dimension.height / 2);

        setResizable(false);
        setLocationRelativeTo(gp);
    }

    private void popuniIzvodjenje(Izvodjenje iz) {
        iz.setListaIzvodjaca(IzvodjacDAO.popuniListeIzvodjenja(iz));
    }

    private void inizijalizuj(Izvodjenje iz) {

        buttonPrikaziIzvodjaca = new JButton("Prikazi izvodjaca");
        prikaziMuzickoDeloButton = new JButton("Prikazi muzicko delo");
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        String separator = System.getProperty("file.separator");
        panelGlavni = new JPanel();
        nazivDelaLabela = new JLabel("<html><p style=\"font-size:13px\">Naziv dela : " + ElementIzvodjenja.generateNazivIzvodjenja(iz) + "</p></html>");

        vremeIzvodjenjaLabela = new JLabel("Vreme izvodjenja : " + iz.getVremeIzvodjenja().toString());

        brojPristupaLabela = new JLabel("Broj prisupa : " + iz.getBrPristupa());

        trajanjeLabela = new JLabel("Trajanje : " + iz.getTrajanje());

        tipIzvodjenjaLabela = new JLabel("Tip izvodjenja : " + iz.getTipIzvodjenja());

        mestoIzvodjenjaLabela = new JLabel("Mesto izvodjenja : " + iz.getMestoIzvodjenja());

        labelaIkone = new JLabel();
        labelaIkone.setSize(200, dimension.height / 20 * 3 - 10);
        ImageIcon retImageIcon = IzvodjenjeDAO.getSlikuIzvodjenja(iz, separator);
        Image im = retImageIcon.getImage();
        Image myImg = im.getScaledInstance(labelaIkone.getWidth(), labelaIkone.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon newImage = new ImageIcon(myImg);
        labelaIkone.setIcon(newImage);
        iz.setImage(newImage);

        comboBoxIzvodjaca = new JComboBox<String>(getNizIzvodjaca(iz.getListaIzvodjaca()));
        comboBoxIzvodjaca.setBackground(new Color(186, 186, 178));
        //panelGlavni.add(comboBoxIzvodjaca);
        panelGlavni.setBackground(new Color(105,135,139));
        comboBoxMuzickihDela = new JComboBox<String>(getNizMuzickihDela(iz.getListaMuzickihDela()));
        comboBoxMuzickihDela.setBackground(new Color(186, 186, 178));
        //panelGlavni.add(comboBoxMuzickihDela);
        panelSkrola = new JPanel();
        panelSkrola.setBackground(new Color(153,179,185));
        panelSkrola.setLayout(new BoxLayout(panelSkrola, BoxLayout.Y_AXIS));

        skrolPaneRecenzija = new JScrollPane(panelSkrola);
        skrolPaneRecenzija.getVerticalScrollBar().setUnitIncrement(10);
        skrolPaneRecenzija.setPreferredSize(new Dimension(dimension.width / 4 + dimension.width / 18, 2 * dimension.height / 12));
        skrolPaneRecenzija.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        brojElemenata = 3;

    }

    private void dodajNaGlavniPanel() {
        GridBagLayout bg = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        panelGlavni.setLayout(bg);

        con.fill = GridBagConstraints.NONE;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        con.insets = new Insets(5, 20, 15, 20);
        con.anchor = GridBagConstraints.CENTER;
        panelGlavni.add(labelaIkone, con);

        con.insets = new Insets(5, 20, 5, 20);
        con.gridy = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        panelGlavni.add(nazivDelaLabela, con);

        con.gridy = 2;
        con.fill = GridBagConstraints.NONE;
        panelGlavni.add(brojPristupaLabela, con);

        con.gridy = 3;
        panelGlavni.add(mestoIzvodjenjaLabela, con);

        con.gridy = 4;
        panelGlavni.add(tipIzvodjenjaLabela, con);

        con.gridy = 5;
        panelGlavni.add(trajanjeLabela, con);

        con.gridy = 6;
        panelGlavni.add(vremeIzvodjenjaLabela, con);

        con.gridy = 7;
        con.gridwidth = 1;
        con.anchor = GridBagConstraints.LINE_END;
        con.insets = new Insets(5, 20, 5, 2);
        panelGlavni.add(comboBoxIzvodjaca, con);

        con.gridx = 1;
        con.anchor = GridBagConstraints.LINE_START;
        con.insets = new Insets(5, 3, 5, 20);

        panelGlavni.add(buttonPrikaziIzvodjaca, con);

        con.gridy = 8;
        con.gridx = 0;
        con.anchor = GridBagConstraints.LINE_END;
        con.insets = new Insets(5, 20, 5, 2);

        panelGlavni.add(comboBoxMuzickihDela, con);

        con.gridx = 1;
        con.anchor = GridBagConstraints.LINE_START;
        con.insets = new Insets(5, 3, 5, 20);
        panelGlavni.add(prikaziMuzickoDeloButton, con);

        con.gridy = 9;
        con.gridx = 0;
        con.fill = GridBagConstraints.BOTH;
        con.insets = new Insets(5, 25, 5, 25);
        con.anchor = GridBagConstraints.CENTER;
        con.gridwidth = 2;
        panelGlavni.add(skrolPaneRecenzija, con);
    }

    private void podesiAkcije(Izvodjenje iz, GlavniProzor gp) {

        skrolPaneRecenzija.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JViewport vp = skrolPaneRecenzija.getViewport();
                if (vp.getView().getHeight() <= vp.getHeight() + vp.getViewPosition().y) {
                    brojElemenata += 1;
                    ucitajRecenzije(iz, gp);
                }
            }
        });

        buttonPrikaziIzvodjaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxIzvodjaca.getSelectedIndex();
                if (index < 0) {
                    JOptionPane.showMessageDialog(PrikazIzvodjenja.this, "Ne postoji izvodjac");
                } else {
                    PrikaziIzvodjaca pi = new PrikaziIzvodjaca(iz.getListaIzvodjaca().get(index), gp);
                    PrikazIzvodjenja.this.dispose();
                    pi.setVisible(true);
                }
            }
        });
        prikaziMuzickoDeloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxMuzickihDela.getSelectedIndex();
                if (index < 0) {
                    JOptionPane.showMessageDialog(PrikazIzvodjenja.this, "Ne postoji muzicko delo");
                } else {
                    PrikaziMuzickoDelo pi = new PrikaziMuzickoDelo(iz.getListaMuzickihDela().get(index), gp);
                    PrikazIzvodjenja.this.dispose();
                    pi.setVisible(true);

                }
            }
        });


    }


    private void ucitajRecenzije(Izvodjenje iz, GlavniProzor gp) {
        resetRecenzije(gp);

        List<Recenzija> recenzije = RecenzijaDAO.getRecenzijeIzvodjenja(iz.getId(), brojElemenata);
        for (Recenzija r : recenzije) {
            ElementRecenzija el = new ElementRecenzija(r);
            panelSkrola.add(el);
        }

        gp.refreshComponent(skrolPaneRecenzija);
    }

    private void resetRecenzije(GlavniProzor gp) {
        panelSkrola.removeAll();
        gp.refreshComponent(panelSkrola);
    }

    public static String[] getNizIzvodjaca(List<Izvodjac> listaIzvodjaca) {
        String[] itemsArray = new String[listaIzvodjaca.size()];
        int index = 0;
        for (Izvodjac izvodjac : listaIzvodjaca) {
            itemsArray[index] = izvodjac.getNazivIzvodjaca();
            index++;

        }
        return itemsArray;
    }

    public static String[] getNizMuzickihDela(List<MuzickoDelo> listaMuzickihDela) {
        String[] itemsArray = new String[listaMuzickihDela.size()];
        int index = 0;
        for (MuzickoDelo muzickoDelo : listaMuzickihDela) {
            itemsArray[index] = muzickoDelo.getNazivDela();
            index++;
        }
        return itemsArray;
    }
}