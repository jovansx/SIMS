package gui.dialogs;

import gui.panels.PanelPrijave;
import gui.elementi.GlavniProzor;

import javax.swing.*;

public class DialogPrijave extends JDialog {

    public DialogPrijave(GlavniProzor gp) {

        setTitle("Prijava na sistem");
        setResizable(false);
        setModal(true);
        add(new PanelPrijave(this, gp));
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(gp);
    }


}
