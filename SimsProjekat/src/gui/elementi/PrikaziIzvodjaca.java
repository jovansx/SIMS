package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.MuzickoDeloDAO;
import model.Izvodjac;
import model.Izvodjenje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PrikaziIzvodjaca extends JDialog {
    private JPanel panelGlavni;
    private JLabel labelaNaziva;
    private JLabel labelaOpisa;
    private JLabel labelaTipa;
    private JLabel labelaPripada;
    private JButton buttonPrikaziIzvodjaca;
    private JButton buttonPrikaziIzvodjenje;
    private JComboBox comboBoxClanova;
    private JComboBox comboBoxIzvodjenja;
    private Dimension dimension;

    public PrikaziIzvodjaca(Izvodjac izvodjac, GlavniProzor gp) {
        super();
        setModal(true);
        setTitle("Prikaz informacija izvodjaca");

        popuniIzvodjaca(izvodjac);

        inizijalizuj(izvodjac);

        podesiAkcije(izvodjac, gp);

        add(panelGlavni);

        setSize(dimension.width / 4, dimension.height / 4);

        setResizable(false);
        setLocationRelativeTo(gp);
    }

    private void inizijalizuj(Izvodjac izvodjac) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();

        labelaNaziva.setText("Naziv izvodjaca : " + izvodjac.getNazivIzvodjaca());
        labelaOpisa.setText("Opis : " + izvodjac.getOpis());
        labelaTipa.setText("Tip izvodjaca : " + izvodjac.getTipIzvodjaca());

        if (izvodjac.getPripadaGrupi() == null) {
            labelaPripada.setText("Pripada izvodjacu : Ne pripada");
        } else {
            labelaPripada.setText("Pripada izvodjacu : " + izvodjac.getPripadaGrupi().toString());
        }

        comboBoxClanova = new JComboBox<String>(PrikazIzvodjenja.getNizIzvodjaca(izvodjac.getImaClanove()));
        comboBoxClanova.setBackground(new Color(186, 186, 178));
        panelGlavni.add(comboBoxClanova);

        comboBoxIzvodjenja = new JComboBox<String>(getNizIzvodjenja(izvodjac.getListaIzvodjenja()));
        comboBoxIzvodjenja.setBackground(new Color(186, 186, 178));
        panelGlavni.add(comboBoxIzvodjenja);
    }

    private void popuniIzvodjaca(Izvodjac izvodjac) {
        izvodjac.setListaIzvodjenja(IzvodjenjeDAO.popuniListeIzvodjaca(izvodjac));

        for (Izvodjenje izvodjenje : izvodjac.getListaIzvodjenja()) {
            izvodjenje.setListaMuzickihDela(MuzickoDeloDAO.getMuzickaDelaIzvodjenja(izvodjenje.getId()));
        }
    }

    private void podesiAkcije(Izvodjac izvodjac, GlavniProzor gp) {

        buttonPrikaziIzvodjaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxClanova.getSelectedIndex();
                if (index < 0) {
                    JOptionPane.showMessageDialog(PrikaziIzvodjaca.this, "Ne postoji izvodjac");
                } else {
                    PrikaziIzvodjaca pi = new PrikaziIzvodjaca(izvodjac.getImaClanove().get(index), gp);
                    PrikaziIzvodjaca.this.dispose();
                    pi.setVisible(true);
                }
            }
        });

        buttonPrikaziIzvodjenje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboBoxIzvodjenja.getSelectedIndex();
                if (index < 0) {
                    JOptionPane.showMessageDialog(PrikaziIzvodjaca.this, "Ne postoji izvodjenje");
                } else {
                    PrikazIzvodjenja pi = new PrikazIzvodjenja(izvodjac.getListaIzvodjenja().get(index), gp);
                    PrikaziIzvodjaca.this.dispose();
                    pi.setVisible(true);
                }
            }
        });



    }

    public static String[] getNizIzvodjenja(List<Izvodjenje> listaIzvodjenja) {
        String[] itemsArray = new String[listaIzvodjenja.size()];
        int index = 0;
        for (Izvodjenje izvodjenje : listaIzvodjenja) {
            itemsArray[index] = ElementIzvodjenja.generateNazivIzvodjenja(izvodjenje);
            index++;
        }
        return itemsArray;
    }

}
