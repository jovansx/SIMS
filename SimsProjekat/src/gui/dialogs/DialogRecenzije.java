package gui.dialogs;

import gui.elementi.PrikazIzvodjenja;
import gui.elementi.PrikazMuzickogDela;
import gui.panels.PanelRecenzije;

import javax.swing.*;

public class DialogRecenzije extends JDialog {
    public PrikazIzvodjenja prikazIzvodjenja;
    public PrikazMuzickogDela prikazMuzickogDela;

    public DialogRecenzije(PrikazIzvodjenja pi, PrikazMuzickogDela pm) {
        prikazIzvodjenja = pi;
        prikazMuzickogDela = pm;
        setTitle("Recenziranje sadrzaja");
        setResizable(false);
        setModal(true);
        if(prikazIzvodjenja != null)
            add(new PanelRecenzije(this, pi.idIzvodjenja, 0, pi.glavniProzor));
        else
            add(new PanelRecenzije(this, 0, pm.idMuzickogDela, pm.glavniProzor));
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(pi);
    }
}
