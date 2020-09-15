package gui.dialogs;

import gui.panels.PanelRegistracije;
import gui.elementi.GlavniProzor;

import javax.swing.*;

public class DialogRegistracije extends JDialog {

    public DialogRegistracije(GlavniProzor gp) {

        setTitle("Registrovanje na sistem");
        setResizable(false);
        setModal(true);
        add(new PanelRegistracije(this));
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(gp);
    }

}
