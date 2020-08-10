package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.MuzickoDeloDAO;
import model.Izvodjac;
import model.Izvodjenje;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrikazIzvodjaca extends JDialog {
    private JPanel panelGlavni;
    private JLabel labelaNaziva;
    private JLabel labelaOpisa;
    private JLabel labelaTipa;
    private JLabel labelaPripada;
    private JButton buttonPrikaziIzvodjaca;
    private JButton buttonPrikaziIzvodjenje;
    private JComboBox comboBoxClanova;
    private JComboBox comboBoxIzvodjenja;

    public PrikazIzvodjaca(Izvodjac izvodjac, GlavniProzor gp) {
        super();
        setModal(true);
        setTitle("Prikaz informacija izvodjaca");

        popuniIzvodjaca(izvodjac);

        inizijalizuj(izvodjac);
        panelGlavni.setBackground(new Color(188, 204, 111));

        podesiAkcije(izvodjac, gp);
        dodajKomponente();
        add(panelGlavni);

        pack();
        setResizable(false);
        setLocationRelativeTo(gp);
    }

    private void inizijalizuj(Izvodjac izvodjac) {

        buttonPrikaziIzvodjaca = new JButton("Prikazi clana");
        buttonPrikaziIzvodjaca.setBackground(new Color(105,135,139));
        buttonPrikaziIzvodjenje = new JButton("Prikazi izvodjenje");
        buttonPrikaziIzvodjenje.setBackground(new Color(105,135,139));

        panelGlavni = new JPanel();

        labelaNaziva = new JLabel("<html><p style=\"font-size:13px\">" + izvodjac.getNazivIzvodjaca() + "</p></html>");
        labelaOpisa = new JLabel("Opis : Nema opisa");
        labelaPripada = new JLabel("Pripada izvodjacu : Ne pripada");
        labelaTipa = new JLabel("Tip izvodjaca : " + izvodjac.getTipIzvodjaca());

        if (izvodjac.getOpis() != null)
            labelaOpisa.setText("Opis : " + izvodjac.getOpis());

        if (izvodjac.getPripadaGrupi() != null)
            labelaPripada.setText("Pripada izvodjacu : " + izvodjac.getPripadaGrupi().toString());

        comboBoxClanova = new JComboBox<>(PrikazIzvodjenja.getNizIzvodjaca(izvodjac.getImaClanove()));
        comboBoxClanova.setBackground(new Color(186, 186, 178));
        if(izvodjac.getImaClanove().size() == 0) {
            comboBoxClanova.setEnabled(false);
            buttonPrikaziIzvodjaca.setEnabled(false);
        }

        comboBoxIzvodjenja = new JComboBox<>(getNizIzvodjenja(izvodjac.getListaIzvodjenja()));
        comboBoxIzvodjenja.setBackground(new Color(186, 186, 178));
    }

    private void dodajKomponente() {

        GridBagLayout bg = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        panelGlavni.setLayout(bg);

        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        con.insets = new Insets(20, 20, 10, 5);
        con.anchor = GridBagConstraints.CENTER;
        con.fill = GridBagConstraints.BOTH;
        panelGlavni.add(labelaNaziva, con);

        con.gridy = 1;
        con.insets = new Insets(5, 20, 10, 20);
        con.fill = GridBagConstraints.NONE;
        panelGlavni.add(labelaOpisa, con);

        con.gridy = 2;
        panelGlavni.add(labelaPripada, con);

        con.gridy = 3;
        panelGlavni.add(labelaTipa, con);

        con.gridy = 4;
        con.gridwidth = 1;
        con.anchor = GridBagConstraints.LINE_END;
        panelGlavni.add(comboBoxClanova, con);

        con.gridy = 5;
        con.insets = new Insets(5, 20, 15, 20);
        panelGlavni.add(comboBoxIzvodjenja, con);

        con.gridy = 4;
        con.gridx = 1;
        con.anchor = GridBagConstraints.LINE_START;
        con.insets = new Insets(5, 20, 10, 20);
        panelGlavni.add(buttonPrikaziIzvodjaca, con);

        con.gridy = 5;
        con.insets = new Insets(5, 20, 15, 20);
        con.gridwidth = 1;
        panelGlavni.add(buttonPrikaziIzvodjenje, con);
    }

    private void popuniIzvodjaca(Izvodjac izvodjac) {
        izvodjac.setListaIzvodjenja(IzvodjenjeDAO.popuniListeIzvodjaca(izvodjac));

        if(izvodjac.getListaIzvodjenja().size() == 0) {
            comboBoxIzvodjenja.setEnabled(false);
            buttonPrikaziIzvodjenje.setEnabled(false);
        }

        for (Izvodjenje izvodjenje : izvodjac.getListaIzvodjenja()) {
            izvodjenje.setListaMuzickihDela(MuzickoDeloDAO.getMuzickaDelaIzvodjenja(izvodjenje.getId()));
        }
    }

    private void podesiAkcije(Izvodjac izvodjac, GlavniProzor gp) {

        buttonPrikaziIzvodjaca.addActionListener(e -> {
            int index = comboBoxClanova.getSelectedIndex();

            PrikazIzvodjaca pi = new PrikazIzvodjaca(izvodjac.getImaClanove().get(index), gp);
            PrikazIzvodjaca.this.dispose();
            pi.setVisible(true);
        });

        buttonPrikaziIzvodjenje.addActionListener(e -> {
            int index = comboBoxIzvodjenja.getSelectedIndex();

            PrikazIzvodjenja pi = new PrikazIzvodjenja(izvodjac.getListaIzvodjenja().get(index), gp);
            PrikazIzvodjaca.this.dispose();
            pi.setVisible(true);
        });
    }

    public static String[] getNizIzvodjenja(List<Izvodjenje> listaIzvodjenja) {
        String[] itemsArray = new String[listaIzvodjenja.size()];
        int index = 0;
        for (Izvodjenje izvodjenje : listaIzvodjenja) {
            itemsArray[index] = ElementIzvodjenja.generisiNazivIzvodjaca(izvodjenje);
            index++;
        }
        return itemsArray;
    }
}