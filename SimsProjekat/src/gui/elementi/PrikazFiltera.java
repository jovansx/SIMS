package gui.elementi;

import javax.swing.*;
import java.awt.*;

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
    private Dimension dimension;

    public PrikazFiltera(GlavniProzor gp) {
        super();
        setTitle("Izaberi filter");
        setModal(true);

        inizijalizuj(gp);

        add(panelGlavni);

        podesiAkcije(gp);

        setSize(dimension.width / 4, dimension.height / 6);
        setResizable(false);
        setLocationRelativeTo(gp);
    }

    private void inizijalizuj(GlavniProzor gp) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(vremeIzvodjenjaRadioButton);
        buttonGroup.add(trajanjeRadioButton);
        buttonGroup.add(brojPristupaRadioButton);

        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(rastuceRadioButton);
        buttonGroup2.add(opadajuceRadioButton);

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
    }

    private void podesiAkcije(GlavniProzor gp) {

        buttonOK.addActionListener(e -> onOK(gp));

        buttonCancel.addActionListener(e -> onCancel());

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

        dispose();
    }

    private void onCancel() {
        dispose();
    }

}
