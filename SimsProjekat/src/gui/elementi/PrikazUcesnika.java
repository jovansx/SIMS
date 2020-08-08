package gui.elementi;

import dao.MuzickoDeloDAO;
import model.Ucesnik;

import javax.swing.*;
import java.awt.*;

public class PrikazUcesnika extends JDialog {


    private JLabel labelaNaziva;
    private JLabel labelaOpisa;
    private JPanel panelGlavni;
    private JLabel labelaTipUcesnika;
    private JButton prikaziMuzickoDeloButton;
    private Dimension dimension;
    private JComboBox comboBoxMuzickihDela;

    public PrikazUcesnika(Ucesnik ucesnik, GlavniProzor gp) {
        super();
        setModal(true);
        setTitle("Prikaz informacija izvodjaca");

        popuniUcesnika(ucesnik);

        inizijalizuj(ucesnik);

        podesiAkcije(ucesnik, gp);

        add(panelGlavni);

        setSize(dimension.width / 4, dimension.height / 4);

        setResizable(false);
        setLocationRelativeTo(gp);
    }

    private void popuniUcesnika(Ucesnik ucesnik) {
        ucesnik.setListaMuzickihDela(MuzickoDeloDAO.getMuzickaDelaUcesnika(ucesnik));
    }

    private void podesiAkcije(Ucesnik ucesnik, GlavniProzor gp) {

        prikaziMuzickoDeloButton.addActionListener(e -> {
            int index = comboBoxMuzickihDela.getSelectedIndex();
            if (index < 0) {
                JOptionPane.showMessageDialog(PrikazUcesnika.this, "Ne postoji ucesnik");
            } else {
                PrikazMuzickogDela md = new PrikazMuzickogDela(ucesnik.getListaMuzickihDela().get(index), gp);
                PrikazUcesnika.this.dispose();
                md.setVisible(true);
            }
        });

    }

    private void inizijalizuj(Ucesnik ucesnik) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();

        labelaNaziva.setText("Naziv ucesnika : " + ucesnik.getNaziv());
        labelaTipUcesnika.setText("Tip ucesnika : " + ucesnik.getTip());

        if (ucesnik.getOpis() == null) {
            labelaOpisa.setText("Opis : Nema opisa");
        } else {
            labelaOpisa.setText("Opis : " + ucesnik.getOpis());
        }

        comboBoxMuzickihDela = new JComboBox<>(PrikazIzvodjenja.getNizMuzickihDela(ucesnik.getListaMuzickihDela()));
        comboBoxMuzickihDela.setBackground(new Color(186, 186, 178));
        panelGlavni.add(comboBoxMuzickihDela);
    }

}
