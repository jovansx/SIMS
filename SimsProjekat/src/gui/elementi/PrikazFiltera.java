package gui.elementi;

import dao.ZanrDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrikazFiltera extends JDialog {
    private JPanel panelGlavni;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelZaFilter;
    private JPanel panelZaDugmad;
    private JRadioButton vremeIzvodjenjaRadioButton;
    private JRadioButton trajanjeRadioButton;
    private JRadioButton brojPristupaRadioButton;
    private JRadioButton rastuceRadioButton;
    private JRadioButton opadajuceRadioButton;
    private JPanel panelZaCombo;
    private JButton izaberiZanrButton;
    private JButton sviZanroviButton;
    private JComboBox comboBoxZanrova;

    public PrikazFiltera(GlavniProzor gp) {
        super();
        setTitle("Izaberi filter");
        setModal(true);

        inizijalizuj(gp);

        add(panelGlavni);

        podesiAkcije(gp);

        //setSize(dimension.width / 4, dimension.height / 6);
        pack();
        setResizable(false);
        setLocationRelativeTo(gp);
    }

    private void inizijalizuj(GlavniProzor gp) {
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(vremeIzvodjenjaRadioButton);
        buttonGroup.add(trajanjeRadioButton);
        buttonGroup.add(brojPristupaRadioButton);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(rastuceRadioButton);
        buttonGroup2.add(opadajuceRadioButton);

        comboBoxZanrova = new JComboBox<>(PrikazMuzickogDela.getNizZanrova(ZanrDAO.getZanrove()));
        comboBoxZanrova.setBackground(new Color(186, 186, 178));
        comboBoxZanrova.setEnabled(false);
        panelZaCombo.add(comboBoxZanrova);

        podesiAktivnoDugme(gp);
    }

    private void podesiAktivnoDugme(GlavniProzor gp) {

        String[] niz = gp.filter.split(" ");
        switch (niz[0]) {
            case "vremeIzvodjenja" -> vremeIzvodjenjaRadioButton.setSelected(true);
            case "brojPristupa" -> brojPristupaRadioButton.setSelected(true);
            case "trajanje" -> trajanjeRadioButton.setSelected(true);
        }

        switch (niz[1]) {
            case "desc" -> opadajuceRadioButton.setSelected(true);
            case "asc" -> rastuceRadioButton.setSelected(true);
        }

        if ("svi".equals(gp.filterZanra)) {
            comboBoxZanrova.setEnabled(false);
            izaberiZanrButton.setEnabled(true);
            sviZanroviButton.setEnabled(false);
        } else {
            comboBoxZanrova.setEnabled(true);
            comboBoxZanrova.setSelectedItem(gp.filterZanra);
            izaberiZanrButton.setEnabled(false);
            sviZanroviButton.setEnabled(true);
        }

    }

    private void podesiAkcije(GlavniProzor gp) {

        buttonOK.addActionListener(e -> onOK(gp));

        buttonCancel.addActionListener(e -> onCancel());

        izaberiZanrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxZanrova.setEnabled(true);
                izaberiZanrButton.setEnabled(false);
                sviZanroviButton.setEnabled(true);
            }
        });


        sviZanroviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comboBoxZanrova.setEnabled(false);
                izaberiZanrButton.setEnabled(true);
                sviZanroviButton.setEnabled(false);
                gp.filterZanra = "svi";
            }
        });


    }

    private void onOK(GlavniProzor gp) {

        String redosled;
        if (rastuceRadioButton.isSelected())
            redosled = "asc";
        else
            redosled = "desc";

        if (vremeIzvodjenjaRadioButton.isSelected())
            gp.filter = "vremeIzvodjenja " + redosled;
        else if (brojPristupaRadioButton.isSelected())
            gp.filter = "brojPristupa " + redosled;
        else if (trajanjeRadioButton.isSelected())
            gp.filter = "trajanje " + redosled;

        if (comboBoxZanrova.isEnabled())
            gp.filterZanra = comboBoxZanrova.getSelectedItem().toString();

        dispose();
    }

    private void onCancel() {
        dispose();
    }

}
