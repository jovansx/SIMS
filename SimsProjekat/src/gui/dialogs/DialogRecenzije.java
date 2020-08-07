package gui.dialogs;

import gui.elementi.PrikazIzvodjenja;
import gui.panels.PanelRecenzije;

import javax.swing.*;

public class DialogRecenzije extends JDialog {
    public PrikazIzvodjenja prikazIzvodjenja;

    public DialogRecenzije(PrikazIzvodjenja pi) {
        prikazIzvodjenja = pi;
        setTitle("Recenziranje sadrzaja");
        setResizable(false);
        setModal(true);
        add(new PanelRecenzije(this, pi.idIzvodjenja, pi.glavniProzor));
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(pi);
    }
}
